package com.seguridad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void sendEmergencyCall(View view)
    {
    	Toast.makeText(this, R.string.sending_emergency_call, Toast.LENGTH_SHORT).show();
    	ImageButton btnEmergency = (ImageButton)view;
    	btnEmergency.setClickable(false);
    }
}