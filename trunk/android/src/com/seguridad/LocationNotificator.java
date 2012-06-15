package com.seguridad;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/*
 * Gets the location updates and sends them 
 * to the destination server
 */
public class LocationNotificator implements LocationListener {

	private String phoneNumber;
	private EmergencyActivator context;
	private Location currentLocation;

	public LocationNotificator(EmergencyActivator context, String phoneNumber) {
		this.context = context;

		this.phoneNumber = phoneNumber;
	}

	public void onLocationChanged(Location location) {
		System.out.println(location.getProvider());
		if (this.updateLocation(location)) {
			this.currentLocation = location;

			if (this.sendEmergencyData(this.currentLocation)) {
				this.context.sentEmergencyCall();
			}
		}
	}

	private boolean updateLocation(Location location) {
		if (
				this.currentLocation == null || 
				!location.equals(this.currentLocation) || 
				this.currentLocation.getAccuracy() < location.getAccuracy()
			)
				return true;

		return false;
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sends an emergency signal to the server
	 * 
	 * @param location
	 * @return true if the signal was sent successfully, false otherwise.
	 */
	private boolean sendEmergencyData(Location location) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(this.context.getEmergencyServerUrl());

		JSONObject json = new JSONObject();
		try {
			json.put("number", this.phoneNumber);
			json.put("lat", Double.toString(location.getLatitude()));
			json.put("lng", Double.toString(location.getLongitude()));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("data", json.toString()));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			// HttpResponse response = httpClient.execute(httpPost);

			// TODO: verificar el response code
			httpClient.execute(httpPost);

			return true;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Verifies if the emergency server is active
	 * 
	 * @return
	 */
	public boolean checkServerAlive() {
		try {
			URL url = new URL(this.context.getEmergencyServerUrl());

			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			urlc.setRequestProperty("User-Agent", "Android Application");
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(1000 * 30);
			urlc.connect();
			if (urlc.getResponseCode() == 200) {
				return true;
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
