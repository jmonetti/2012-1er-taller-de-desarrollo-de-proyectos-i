package com.seguridad.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.seguridad.R;

public class Settings {
	private static Context context;
	public static final Settings instance = new Settings();
	public static final long LOCATION_NETWORK_UPDATE_MIN_INTERVAL = 30000; // 30 secs
	public static final long LOCATION_GPS_UPDATE_MIN_INTERVAL = 60000; // 60 secs
	
	private String emergencyServerUrl;
	private String emergencyNumber;
	
	public static void init(Context context) {
		Settings.context = context;
		Settings.instance.emergencyServerUrl = context.getString(R.string.emergency_server_url);
		Settings.instance.emergencyNumber = context.getString(R.string.emergency_number);
	}
	
	public String getEmergencyServerUrl() {
		return this.emergencyServerUrl;
	}
	
	public String getEmergencyNumber() {
		return this.emergencyNumber;
	}
	
	public String getUserInformation() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String id = context.getString(R.string.additional_information);
		String info = preferences.getString(id, "");
		return info;
	}
}
