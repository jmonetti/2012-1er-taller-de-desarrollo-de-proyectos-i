package com.seguridad.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.seguridad.R;

public class UserInformationActivity extends Activity {
	private EditText txtInformation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_information);

		this.txtInformation = (EditText) this.findViewById(R.id.txtInformation);
	}

	public void saveInformation() {
		SharedPreferences preferences = this
				.getPreferences(Activity.MODE_PRIVATE);
		this.txtInformation.setText(preferences.getString(
				SettingsActivity.USER_INFORMATION, ""));
		// preferences.(SettingsActivity.USER_INFORMATION,
		// this.txtInformation.getText());
	}
}
