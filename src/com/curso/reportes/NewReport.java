package com.curso.reportes;

import java.util.ArrayList;
import java.util.List;

import android.R.array;
import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.SparseArray;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class NewReport extends Activity{
	
	Spinner _tipoServicio;
	LinearLayout linearInfra, linearLim, linearCom;
	String[] _infra = {"Iluminación", "Electricidad","Aire Acondicionado","Falta de Mesabancos/sillas",
			"Falta de mesa y/o escritorio", "Ventanas","Contacto eléctrico","Llave de aulas"};
	String [] _limpieza ={"Aseo de aula(s)", "Aseo de cubículos","Aseo de pasillos","Aseo de laboratorios",
			"Aseo de baños"};
	String [] _equipo ={"Configuración de red","Instalación de red","Falta de internet","Formateo de equipo de cómputo",
			"Instalación de software","Instalación de equipo (impresora, computadora)", "Mantenimiento de equipo preventivo",
			"Mantenimiento de equipo correctivo","Mantenimiento de impresoras preventivo", "Mantenimiento de impresoras correctivo",
			"Mantenimiento de cañón correctivo","Reparación de cables(VGA, corriente)"};
	EditText _ubicacion, _descripcion;
	Button crear;
	//
	ArrayList<String> incidencias = new ArrayList<String>();
	
	SparseArray<CheckBox> array = new SparseArray<CheckBox>();
	//bool
	boolean _isInfra=false, _isLimpieza=false, _isequipo=false;
	
	TextView request;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_report);	
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Enviar Reporte");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		request = (TextView) findViewById(R.id.textView1);
		
		linearInfra = (LinearLayout) findViewById(R.id.llcheckboxes);
		linearLim = (LinearLayout) findViewById(R.id.llcheckboxes2);
		linearCom = (LinearLayout) findViewById(R.id.llcheckboxes3);
		
		_ubicacion = (EditText) findViewById(R.id.etUbicacion);
		_descripcion = (EditText) findViewById(R.id.etDescrip);
		
		//crear = (Button) findViewById(R.id.btnCrear);
		//crear.setOnClickListener(this);
		
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
               
      //creo todos los checkbox       
        //checkbox infraestructura
        for (int i = 0; i < _infra.length; i++) {
        	CheckBox cbIfra = new CheckBox(this);
			cbIfra.setText(_infra[i]);
			cbIfra.setId(i);
			cbIfra.setTextSize(12);			
			array.put(i, cbIfra);
            linearInfra.addView(cbIfra);
		}
       
        //checbox limpieza
        for (int i = 0; i < _limpieza.length; i++) {
        	CheckBox cbLim = new CheckBox(this);
			cbLim.setText(_limpieza[i]);
			cbLim.setId(i);
			cbLim.setTextSize(12);
			array.put(i+_infra.length, cbLim);
            linearLim.addView(cbLim);
		}
        
        //checkbox computo
        for (int i = 0; i < _equipo.length; i++) {
        	CheckBox cbCom = new CheckBox(this);
			cbCom.setText(_equipo[i]);
			cbCom.setId(i);
			cbCom.setTextSize(12);
			array.put(i+_limpieza.length, cbCom);
            linearCom.addView(cbCom);
		}
        
        _tipoServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { 
                	if	(_tipoServicio.getItemAtPosition(i).toString()=="Infraestructura"){
                		linearInfra.setVisibility(View.VISIBLE);
                		linearLim.setVisibility(View.GONE);
                		linearCom.setVisibility(View.GONE);
                   		_isInfra=true;
                   		_isLimpieza=false;
                   		_isequipo=false;
                	} else if (_tipoServicio.getItemAtPosition(i).toString()=="Limpieza"){
                		_isLimpieza=true;
                		_isInfra=false;
                   		_isequipo=false;
                		linearLim.setVisibility(View.VISIBLE);
                		linearCom.setVisibility(View.GONE);
                		linearInfra.setVisibility(View.GONE);
                		
                	}else if (_tipoServicio.getItemAtPosition(i).toString()=="Equipo de cómputo"){
                		_isequipo=true;
                		_isInfra=false;
                		_isLimpieza=false;
                		linearCom.setVisibility(View.VISIBLE);
                		linearLim.setVisibility(View.GONE);
                		linearInfra.setVisibility(View.GONE);
                		
                	}
                }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            } 
        });

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void send(View view){
		String ubicacion, descrip;
		Intent i = null, chooser = null;
		if(view.getId()==R.id.btnCrear){
			ubicacion = _ubicacion.getText().toString();
			descrip = _descripcion.getText().toString();
			if (ubicacion!="") {
				for (int j = 0; j <array.size(); j++) {	
					if (array.get(j).isChecked()) {
						//Toast toast = Toast.makeText(getApplicationContext(), array.get(i).getText().toString(), Toast.LENGTH_SHORT);
						//toast.show();
						incidencias.add(array.get(j).getText().toString());
					}
				}
				request.setText(incidencias.toString());
			}else{
				Toast toast = Toast.makeText(getApplicationContext(), "Escriba una ubicación.", Toast.LENGTH_SHORT);
				toast.show();
			}
//			i = new Intent(Intent.ACTION_SEND);
//			i.setData(Uri.parse("mailto:"));
//			String[] to = {"reporte.isi@gmail.com"};
//			i.putExtra(i.EXTRA_EMAIL, to);
//			i.putExtra(i.EXTRA_SUBJECT, "Hi is only a Text");
//			i.putExtra(i.EXTRA_TEXT, "Please that send!!");
//			i.setType("message/rfc822");
//			chooser = i.createChooser(i, "Send Email");
//			startActivity(chooser);			
		}	
	}


	
}
