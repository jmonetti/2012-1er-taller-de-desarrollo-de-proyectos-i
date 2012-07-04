package com.seguridad.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.seguridad.domain.EmergencyNotification;

/*
 * Gets the location updates and sends them 
 * to the destination server
 */
public class LocationNotificator implements LocationListener {
	public static final String OK = "OK";

	private EmergencyActivator activator;
	private LocationManager locationManager;

	private EmergencyNotification currentNotification;

	public LocationNotificator(EmergencyActivator context) {
		this.activator = context;
		this.locationManager = this.activator.getLocationManager();
	}

	/**
	 * Create a new emergency and start listening for location updates.
	 */
	public void start(String appId) {
		this.currentNotification = new EmergencyNotification(appId);

		this.locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, this);

		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	/**
	 * stop listening for location updates
	 */
	public void stop() {
		// Unregister location listener
		this.locationManager.removeUpdates(this);
	}

	public void onLocationChanged(Location location) {
		if (this.currentNotification.updateLocation(location)) {
			boolean sent = false;
			String data = this.currentNotification.getJson().toString();

			if (this.activator.isOnline())
				sent = sendHTTP(data);

			if (!sent) {
				Toast.makeText((Context) this.activator, "Not connected",
						Toast.LENGTH_LONG).show();
				sent = sendSMS(data);
			}

			if (sent)
				this.activator.sentEmergencyCall();
		}
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * Sends an emergency signal to the server
	 * 
	 * @param location
	 * @return true if the signal was sent successfully, false otherwise.
	 */
	public static boolean sendHTTP(String data) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				Settings.instance.getEmergencyServerUrl());

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("data", data));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = "";
			if (entity != null)
				result = EntityUtils.toString(entity);

			return result.equals(OK);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Sends an emergency signal to the server
	 * 
	 * @param location
	 * @return true if the signal was sent successfully, false otherwise.
	 */
	public static boolean sendSMS(String data) {
		SmsManager smsManager = SmsManager.getDefault();

		ArrayList<String> parts = smsManager.divideMessage(data);

		smsManager
		.sendMultipartTextMessage(
				Settings.instance.getEmergencyNumber(), null, parts,
				null, null);

		return true;
	}
}
