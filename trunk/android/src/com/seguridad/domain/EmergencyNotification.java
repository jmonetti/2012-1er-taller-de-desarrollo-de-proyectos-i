package com.seguridad.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.seguridad.core.Settings;

import android.location.Location;

public class EmergencyNotification {
	private static String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";
	private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	private String id;
	private String appId;
	private String information;
	private Location location;

	public EmergencyNotification(String appId) {
		this.id = UUID.randomUUID().toString();
		this.appId = appId;
		this.information = Settings.instance.getUserInformation();
	}

	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("emergency-id", this.id);
			json.put("app-id", this.appId);
			json.put("lat", Double.toString(this.location.getLatitude()));
			json.put("lng", Double.toString(this.location.getLongitude()));
			json.put("location-provider", this.location.getProvider());
			json.put("date", dateFormat.format(new Date()));
			json.put("info", this.information);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * Evaluate if the location should be updated with the new one.
	 * 
	 * @param newLocation
	 * @return
	 */
	public boolean updateLocation(Location newLocation) {
		boolean update = false;
		
		if (this.location == null
				|| !newLocation.equals(this.location)
				|| this.location.getAccuracy() < newLocation
						.getAccuracy())
			update = true;

		if (update)
			this.location = newLocation;

		return update;
	}
}
