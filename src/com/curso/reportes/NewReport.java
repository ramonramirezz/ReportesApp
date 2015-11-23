package com.curso.reportes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class NewReport extends Activity{
	
	Spinner _tipoServicio;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_report);	
		_tipoServicio = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("Infraestructura");
        list.add("Limpieza");
        list.add("Equipo de cómputo");
  
         
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                     (this, android.R.layout.simple_spinner_item,list);
                      
        dataAdapter.setDropDownViewResource
                     (android.R.layout.simple_spinner_dropdown_item);
                      
        _tipoServicio.setAdapter(dataAdapter);
       
     
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void send(View view){
		Intent i = null, chooser = null;
		if(view.getId()==R.id.btnCrear){
			i = new Intent(Intent.ACTION_SEND);
			i.setData(Uri.parse("mailto:"));
			String[] to = {"reporte.isi@gmail.com"};
			i.putExtra(i.EXTRA_EMAIL, to);
			i.putExtra(i.EXTRA_SUBJECT, "Hi is only a Text");
			i.putExtra(i.EXTRA_TEXT, "Please that send!!");
			i.setType("message/rfc822");
			chooser = i.createChooser(i, "Send Email");
			startActivity(i);
			
		}
		
	}
}
