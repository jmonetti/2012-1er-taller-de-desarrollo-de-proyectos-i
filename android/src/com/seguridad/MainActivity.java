package com.seguridad;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements EmergencyActivator {
	private LocationManager locationManager;
	private TelephonyManager telephonyManager;

	private List<Integer> resources;
	private LocationNotificator locationNotificator;

	private ImageButton btnEmergency;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.btnEmergency = (ImageButton) this.findViewById(R.id.btnEmergency);

		this.resources = new LinkedList<Integer>();
		this.resources.add(R.drawable.red_button);
		this.resources.add(R.drawable.red_button_on);

		this.telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);

		this.locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		this.locationNotificator = new LocationNotificator(this,
				this.telephonyManager.getDeviceId());
	}

	public void sendEmergencyCall(View view) {
		Toast.makeText(this, R.string.sending_emergency_call,
				Toast.LENGTH_SHORT).show();

		this.btnEmergency.setClickable(false);
		this.btnEmergency.startAnimation(new ImageButtonAnimation(btnEmergency,
				this.resources, 500));

		this.locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0,
				this.locationNotificator);
	}

	/**
	 * Removes the location listener, notifies the user and stops the button
	 * animation.
	 */
	public void sentEmergencyCall() {
		// Confirm signal sent
		String msg = this.getString(R.string.emergency_signal_sent);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

		// Unregister location listener
		this.locationManager.removeUpdates(this.locationNotificator);

		this.btnEmergency.clearAnimation();
		this.btnEmergency.setClickable(true);
	}

	public String getEmergencyServerUrl() {
		return this.getString(R.string.emergency_server_url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
}
