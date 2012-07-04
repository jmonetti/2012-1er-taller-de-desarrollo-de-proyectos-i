package com.seguridad.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.seguridad.R;

public class UserInformationActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_information);
	}
}
