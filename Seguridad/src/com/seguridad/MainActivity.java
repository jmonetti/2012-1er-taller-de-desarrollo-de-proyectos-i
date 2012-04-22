package com.seguridad;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {
	private List<Integer> resources;	
	private LocationManager locationManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.resources = new LinkedList<Integer>();
        this.resources.add(R.drawable.red_button);
        this.resources.add(R.drawable.red_button_on);
        
        this.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }
    
    public void sendEmergencyCall(View view)
    {
    	Toast.makeText(this, R.string.sending_emergency_call, Toast.LENGTH_SHORT).show();
    	ImageButton btnEmergency = (ImageButton)view;
    	btnEmergency.setClickable(false);
    	btnEmergency.startAnimation(new ImageButtonAnimation(btnEmergency,  this.resources, 750));
    	this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);    	
    }

	public void onLocationChanged(Location location) {
		String msg = "Lat:" + location.getLatitude();
		msg += " - Long:" + location.getLongitude();
		msg += " - Acc:" + location.getAccuracy();
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
}
