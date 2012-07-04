package com.seguridad;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.seguridad.settings.SettingsActivity;

public class MainActivity extends Activity implements EmergencyActivator {
	private LocationManager locationManager;
	private Vibrator vibrator;

	private List<Integer> resources;
	private LocationNotificator locationNotificator;

	private ImageButton btnEmergency;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.btnEmergency = (ImageButton) this.findViewById(R.id.btnEmergency);
		this.btnEmergency.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				sendEmergencyCall();
				return true;
			}
		});

		this.resources = new LinkedList<Integer>();
		this.resources.add(R.drawable.red_button);
		this.resources.add(R.drawable.red_button_on);

		this.locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		this.vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		this.locationNotificator = new LocationNotificator(this,
				Installation.id(this));
	}

	public void sendEmergencyCall() {
		Toast.makeText(this, R.string.sending_emergency_call,
				Toast.LENGTH_SHORT).show();

		this.btnEmergency.setClickable(false);
		this.btnEmergency.startAnimation(new ImageButtonAnimation(btnEmergency,
				this.resources, 500));

		this.locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0,
				this.locationNotificator);

		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 0, 0, this.locationNotificator);

		this.vibrator.vibrate(250);
	}

	/**
	 * Removes the location listener, notifies the user and stops the button
	 * animation.
	 */
	public void sentEmergencyCall() {
		this.vibrator.vibrate(new long[] { 0, 250, 250, 250 }, -1);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			openSettings();
			return true;
		case R.id.help:
			showHelp();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showHelp() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.help_message);
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.create().show();
	}

	/**
	 * Inicia la actividad de configuración.
	 */
	private void openSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
}
