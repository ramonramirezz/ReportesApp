package com.curso.reportes;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class WelcomeActivity extends Activity implements OnClickListener {
	
	TextView nombreUsuario, _tvNuevoReporte;
	
	ImageButton _nuevoReporte;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
	
		_nuevoReporte = (ImageButton) findViewById(R.id.btnCrearReporte);
		_nuevoReporte.setOnClickListener(this);
		
		_tvNuevoReporte = (TextView) findViewById(R.id.tvNuevoReport);
		_tvNuevoReporte.setOnClickListener(this);
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
			case R.id.btnCrearReporte:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
			case R.id.tvHistorial:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
		}
		}
	}

