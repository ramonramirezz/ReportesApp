package com.curso.reportes;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.Spinner;


public class NewReport extends Activity implements OnClickListener{
	
	Spinner _tipoServicio;
	LinearLayout linearInfra, linearLim, linearCom;
	String[] _infra = {"Iluminaci�n", "Electricidad","Aire Acondicionado","Falta de Mesabancos/sillas",
			"Falta de mesa y/o escritorio", "Ventanas","Contacto el�ctrico","Llave de aulas"};
	String [] _limpieza ={"Aseo de aula(s)", "Aseo de cub�culos","Aseo de pasillos","Aseo de laboratorios",
			"Aseo de ba�os"};
	String [] _equipo ={"Configuraci�n de red","Instalaci�n de red","Falta de internet","Formateo de equipo de c�mputo",
			"Instalaci�n de software","Instalaci�n de equipo (impresora, computadora)", "Mantenimiento de equipo preventivo",
			"Mantenimiento de equipo correctivo","Mantenimiento de impresoras preventivo", "Mantenimiento de impresoras correctivo",
			"Mantenimiento de ca��n correctivo","Reparaci�n de cables(VGA, corriente)"};
	CheckBox check1;
	Button crear;
	//bool
	boolean _isInfra=false, _isLimpieza=false, _isequipo=false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_report);	
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Enviar Reporte");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		
		linearInfra = (LinearLayout) findViewById(R.id.llcheckboxes);
		linearLim = (LinearLayout) findViewById(R.id.llcheckboxes2);
		linearCom = (LinearLayout) findViewById(R.id.llcheckboxes3);
		crear = (Button) findViewById(R.id.btnCrear);
		crear.setOnClickListener(this);
		_tipoServicio = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("Infraestructura");
        list.add("Limpieza");
        list.add("Equipo de c�mputo");
        
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
			cbIfra.setId(i+6);
            linearInfra.addView(cbIfra);
		}
       
        //checbox limpieza
        for (int i = 0; i < _limpieza.length; i++) {
        	CheckBox cbLim = new CheckBox(this);
			cbLim.setText(_limpieza[i]);
			cbLim.setId(i);
            linearLim.addView(cbLim);
		}
        
        //checkbox computo
        for (int i = 0; i < _equipo.length; i++) {
        	CheckBox cbCom = new CheckBox(this);
			cbCom.setText(_equipo[i]);
			cbCom.setId(i);
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
                		
                	}else if (_tipoServicio.getItemAtPosition(i).toString()=="Equipo de c�mputo"){
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
			startActivity(chooser);
			
		}
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		
		}
	}
}
