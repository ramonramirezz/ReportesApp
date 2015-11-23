package com.curso.reportes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class NewReport extends Activity{
	
	Spinner _tipoServicio;
	LinearLayout linear;
	String[] _infra = {"Iluminación", "Electricidad","Aire Acondicionado","Falta de Mesabancos/sillas",
			"Falta de mesa y/o escritorio", "Ventanas","Contacto eléctrico","Llave de aulas"};
	String [] _limpieza ={"Aseo de aula(s)", "Aseo de cubículos","Aseo de pasillos","Aseo de laboratorios",
			"Aseo de baños"};
	String [] _equipo ={"Configuración de red","Instalación de red","Falta de internet","Formateo de equipo de cómputo",
			"Instalación de software","Instalación de equipo (impresora, computadora)", "Mantenimiento de equipo preventivo",
			"Mantenimiento de equipo correctivo","Mantenimiento de impresoras preventivo", "Mantenimiento de impresoras correctivo",
			"Mantenimiento de cañón correctivo","Reparación de cables(VGA, corriente)"};
	
	
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
       // final CheckBox cb = new CheckBox(this);
        /*
        _tipoServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { 
                if	(_tipoServicio.getSelectedItem().toString()=="Infraestructura"){
                	 linear = (LinearLayout) findViewById(R.id.llcheckboxes);
                     for(int j = 0; j < _infra.length; j++) {
                    	 
                         cb.setText(_infra[i]);
                         cb.setId(i+6);
                         linear.addView(cb);
                     }
                }
            } 

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            } 
        }); */
        

       
       
        
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
