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

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/*
 * Gets the location updates and sends them 
 * to the destination server
 */
public class LocationNotificator implements LocationListener {

	private String phoneNumber;
	private Context context;
	private Location currentLocation;

	public LocationNotificator(Context context) {
		this.context = context;
		TelephonyManager telephonyManager = (TelephonyManager) this.context
				.getSystemService(Context.TELEPHONY_SERVICE);
		phoneNumber = telephonyManager.getDeviceId();
	}

	public void onLocationChanged(Location location) {
		this.currentLocation = location;

		this.sendEmergencyData(this.currentLocation);

		String msg = "Lat:" + location.getLatitude();
		msg += " - Long:" + location.getLongitude();
		msg += " - Acc:" + location.getAccuracy();
		Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
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

	private void sendEmergencyData(Location location) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				this.context.getString(R.string.emergency_server_url));

		JSONObject json = new JSONObject();
		try {
			json.put("number", this.phoneNumber);
			json.put("lat", Double.toString(location.getLatitude()));
			json.put("long", Double.toString(location.getLongitude()));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("data", json.toString()));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			httpClient.execute(httpPost);

			// Execute HTTP Post Request
			// HttpResponse response = httpClient.execute(httpPost);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifies if the emergency server is active
	 * @return 
	 */
	public boolean checkServerAlive() {
		try {
			URL url = new URL(
					this.context.getString(R.string.emergency_server_url));

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
