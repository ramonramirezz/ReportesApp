package com.curso.reportes;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
   Button _iniciar, _registrar;
   EditText _usuario, _contra;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		_iniciar = (Button)findViewById(R.id.btnIniciar);
		_registrar = (Button)findViewById(R.id.btnRegrist);
		_usuario = (EditText)findViewById(R.id.edUsuario);
		_contra = (EditText)findViewById(R.id.edContra);
		
		_iniciar.setOnClickListener(this);
		_registrar.setOnClickListener(this);
		
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
		switch(v.getId()){
		
		case R.id.btnIniciar:
			String usuatrio = _usuario.getText().toString();
			String contrasenia = _contra.getText().toString();
			_usuario.setText("");
			_contra.setText("");
			
			try {
				Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
				Conexion con = new Conexion(MainActivity.this);
				con.abrir();
				boolean status = con.login(usuatrio, contrasenia);
				
				if (status){
					i.putExtra("user", usuatrio);
					i.putExtra("contra", contrasenia);
					startActivity(i);
					
				}else{
					Toast toast = Toast.makeText(getApplicationContext(), "Usuario/Contraseña incorrecto", Toast.LENGTH_SHORT);
					toast.show();
				}
				
				con.cerrar();
			} catch (Exception e) {
				// TODO: handle exception
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funciona :(");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
<<<<<<< HEAD
			
=======
>>>>>>> origin/master
			break;
			
		case R.id.btnRegrist:

			 startActivity(new Intent(MainActivity.this, RegistrarAct.class));			
			break;
		
		}
	}

}
