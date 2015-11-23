package com.curso.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.array;
import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.format.DateFormat;
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
	String id_user;
	
	LinearLayout linearInfra, linearLim, linearCom;
	String[] _infra = {"Iluminación", "Electricidad","Aire Acondicionado","Falta de Mesabancos/sillas",
			"Falta de mesa y/o escritorio", "Ventanas","Contacto eléctrico","Llave de aulas"};
	String [] _limpieza ={"Aseo de aula(s)", "Aseo de cubículos","Aseo de pasillos","Aseo de laboratorios",
			"Aseo de baños"};
	String [] _equipo ={"Configuración de red","Instalación de red","Falta de internet","Formateo de equipo de cómputo",
			"Instalación de software","Instalación de equipo (impresora, computadora)", "Mantenimiento y/o reparación de equipo",
			"Mantenimiento y/o reparación de impresoras",
			"Mantenimiento de cañón correctivo","Reparación de cables(VGA, corriente)"};
	EditText _ubicacion, _descripcion;
	Button crear;
	//
	ArrayList<String> incidencias = new ArrayList<String>();
	
	SparseArray<CheckBox> array = new SparseArray<CheckBox>();
	
	//bool
	boolean _isInfra=false, _isLimpieza=false, _isequipo=false;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_report);	
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Enviar Reporte");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		
		id_user = getIntent().getStringExtra("id_user");
		
		
		
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

                	} else if (_tipoServicio.getItemAtPosition(i).toString()=="Limpieza"){
                		linearLim.setVisibility(View.VISIBLE);
                		linearCom.setVisibility(View.GONE);
                		linearInfra.setVisibility(View.GONE);
                		
                	}else if (_tipoServicio.getItemAtPosition(i).toString()=="Equipo de cómputo"){
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
		Intent inte = new Intent(NewReport.this, WelcomeActivity.class);
		
		String ubicacion, descrip;
		Intent i = null, chooser = null;
		if(view.getId()==R.id.btnCrear){
			ubicacion = _ubicacion.getText().toString();
			descrip = _descripcion.getText().toString();
			if (ubicacion.equals("")) {
				Toast toast = Toast.makeText(getApplicationContext(), "Escriba una ubicación.", Toast.LENGTH_SHORT);
				toast.show();
			}else{
				for (int j = 0; j <array.size(); j++) {	
					if (array.get(j).isChecked()) {
						incidencias.add(array.get(j).getText().toString());
					}
				}
				
				try {
					Conexion con = new Conexion(this);					
					String fecha = this.date();
					con.abrir();
				String status = con.setHistory(id_user, ubicacion, fecha);
				con.cerrar();
				
					if (status.equals("bien")) {
						i = new Intent(Intent.ACTION_SEND);
						i.setData(Uri.parse("mailto:"));
						String[] to = {"reporte.isi@gmail.com"};
						i.putExtra(i.EXTRA_EMAIL, to);
						i.putExtra(i.EXTRA_SUBJECT, "Reporte para el aula " + ubicacion);
						i.putExtra(i.EXTRA_TEXT, incidencias + " " + fecha +" " + descrip);
						i.setType("message/rfc822");
						chooser = i.createChooser(i, "Send Email");
						startActivity(chooser);	
						
						
//						Toast toast = Toast.makeText(getApplicationContext(), "Nuevo reporte enviado", Toast.LENGTH_SHORT);
//						toast.show();
					
						for (int j = 0; j <array.size(); j++) {	
							array.get(j).setChecked(false);
						}
						_ubicacion.setText("");
						_descripcion.setText("");
						incidencias.clear();
						
					}else{
						Toast toast = Toast.makeText(getApplicationContext(), "Error en enviar reporte", Toast.LENGTH_SHORT);
						toast.show();				
		                
					}					
				} catch (Exception e) {
					Toast toast = Toast.makeText(getApplicationContext(), "Error ex", Toast.LENGTH_SHORT);
					toast.show();
				}
			}

//			if (status) {
//				Toast toast = Toast.makeText(getApplicationContext(), "SE inserto tu historia", Toast.LENGTH_SHORT);
//				toast.show();
//				i.putExtra("id_user", id_user);
//				this.startActivity(inte);
//			}else{
//				Toast toast = Toast.makeText(getApplicationContext(), "pasa algo", Toast.LENGTH_SHORT);
//				toast.show();
//			}
		
		}	
	}
	
	public String date(){
		String date = "";
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		date = dateFormat.format(fecha);
		return date;
	}


	
}
