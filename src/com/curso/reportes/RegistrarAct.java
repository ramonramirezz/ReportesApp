package com.curso.reportes;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarAct extends Activity implements OnClickListener{

	EditText _nombres, _usuario, _contra1, _contra2;
	Button aceptar, cancelar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrar);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		_nombres = (EditText) findViewById(R.id.edNombre);
		_usuario = (EditText) findViewById(R.id.edUsuario);
		_contra1 = (EditText) findViewById(R.id.edContra);
		_contra2 = (EditText) findViewById(R.id.edContra1);
		
		aceptar = (Button) findViewById(R.id.btnAceptar);
		aceptar.setOnClickListener(this);
		
		cancelar = (Button) findViewById(R.id.btnCancelar);
		cancelar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				
		switch	(v.getId()){
		case R.id.btnAceptar:			
			boolean status;
			try {
				String nom, usuario, contra1, contra2;			
				nom = _nombres.getText().toString();
				usuario = _usuario.getText().toString();
				contra1 = _contra1.getText().toString();
				contra2 = _contra2.getText().toString();
				if(nom.equals("") || usuario.equals("") || contra1.equals("") || contra2.equals("")){
					
					Context context = getApplicationContext();
					Toast toast = Toast.makeText(context, "Todos los campos tienes que estar llenos", Toast.LENGTH_SHORT);
					toast.show();
					
				}
				if (contra1.equals(contra2)) {
					Conexion con = new Conexion(RegistrarAct.this);
					con.abrir();
					status = con.addUser(nom, usuario, contra1);
					con.cerrar();
					
					if (status) {
						
							Dialog d = new Dialog(this);
							d.setTitle("Se creo su usuario exitosamente");
							TextView tv = new TextView(this);
							d.setContentView(tv);
							d.show();
							startActivity(new Intent (RegistrarAct.this, MainActivity.class));
						
					}else {
						Toast toast = Toast.makeText(getApplicationContext(), "No se creo usuario", Toast.LENGTH_SHORT);
						toast.show();
					}
				}else{
					Dialog d = new Dialog(this);
					d.setTitle("Contraseñas");
					TextView tv = new TextView(this);
					tv.setText("Las contraseñas no coinciden");
					d.setContentView(tv);
					d.show();	
					_contra1.setText("");
					_contra2.setText("");
					
				}
	
			} catch (Exception e) {
				// TODO: handle exception
				status = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funciona :(");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.btnCancelar:
			_nombres.setText("");
			_usuario.setText("");
			_contra1.setText("");
			_contra2.setText("");
			
			startActivity(new Intent (RegistrarAct.this, MainActivity.class));
			break;
	}
  }
}
