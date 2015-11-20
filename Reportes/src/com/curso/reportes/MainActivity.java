package com.curso.reportes;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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

			break;
			
		case R.id.btnRegrist:
			
			
			 startActivity(new Intent(MainActivity.this, RegistrarAct.class));			
			break;
		
		}
	}

}
