package com.curso.reportes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarAct extends Activity implements OnClickListener{

	EditText _nombres, _usuario, _contra1, _contra2;
	Button aceptar;
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
		String nom, usuario, contra1, contra2;
		
		nom = _nombres.getText().toString();
		usuario = _usuario.getText().toString();
		contra1 = _contra1.getText().toString();
		contra2 = _contra2.getText().toString();
				
		switch	(v.getId()){
		case R.id.btnAceptar:
			
			startActivity(new Intent (RegistrarAct.this, MainActivity.class));
			break;
		}
		
	}
}
