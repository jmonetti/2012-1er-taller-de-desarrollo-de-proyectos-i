package com.seguridad.core;

import android.location.LocationManager;

public interface EmergencyActivator {
	void sentEmergencyCall();
	/**
	 * Indicates if there's Internet connectivity.
	 * @return
	 */
	boolean isOnline();
	LocationManager getLocationManager();
}
