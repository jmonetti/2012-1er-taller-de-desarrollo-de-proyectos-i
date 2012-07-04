package com.seguridad.settings;

import android.app.ListActivity;
import android.os.Bundle;

import com.seguridad.R;

public class UserInformationActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_information);
	}
}
