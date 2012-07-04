package com.seguridad.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class SettingsActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
}
