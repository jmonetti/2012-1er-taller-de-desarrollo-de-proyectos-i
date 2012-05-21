package com.seguridad;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private List<Integer> resources;
	private LocationManager locationManager;
	private LocationNotificator locationNotificator;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.resources = new LinkedList<Integer>();
		this.resources.add(R.drawable.red_button);
		this.resources.add(R.drawable.red_button_on);

		this.locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		this.locationNotificator = new LocationNotificator(this);
	}

	public void sendEmergencyCall(View view) {
		Toast.makeText(this, R.string.sending_emergency_call,
				Toast.LENGTH_SHORT).show();
		
		ImageButton btnEmergency = (ImageButton) view;
		btnEmergency.setClickable(false);
		btnEmergency.startAnimation(new ImageButtonAnimation(btnEmergency,
				this.resources, 500));

		this.locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0,
				this.locationNotificator);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
}
