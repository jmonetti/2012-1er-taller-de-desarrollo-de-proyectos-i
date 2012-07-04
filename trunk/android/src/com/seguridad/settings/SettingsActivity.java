package com.seguridad.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SettingsActivity extends ListActivity {
	public static final String USER_INFORMATION = "UserInformation";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.loadOptions();
	}
	
	private void loadOptions() {
		List<Map<String, Object>> opciones = new ArrayList<Map<String,Object>>();		
		
		Map<String, Object> opcion = new HashMap<String, Object>();		
		opcion.put("title", "Información Adicional");
		opcion.put("intent", new Intent(this, UserInformationActivity.class));
		opciones.add(opcion);
		
		opcion = new HashMap<String, Object>();
		opcion.put("title", "Contactos");
		opcion.put("intent", null);
		opciones.add(opcion);
		
		setListAdapter(new SimpleAdapter(this, opciones,
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> data = (Map<String, Object>) l.getItemAtPosition(position);

		Intent intent = (Intent) data.get("intent");
		this.startActivity(intent);
	}
}
