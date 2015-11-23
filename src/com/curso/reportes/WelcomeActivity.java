package com.curso.reportes;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class WelcomeActivity extends Activity implements OnClickListener {
	
<<<<<<< HEAD
<<<<<<< HEAD
	TextView nombreUsuario, _tvNuevoReporte;
	
=======
	TextView nombreUsuario;
=======
	TextView nombreUsuario, NuevoReporte;
>>>>>>> origin/master
	String user, contra;
>>>>>>> origin/master
	ImageButton _nuevoReporte;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Bienvenido");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		
		NuevoReporte = (TextView) findViewById(R.id.tvNuevoReport);
		nombreUsuario =(TextView) findViewById(R.id.tvNombreUsuario);
		
		_nuevoReporte = (ImageButton) findViewById(R.id.btnCrearReporte);
		
		NuevoReporte.setOnClickListener(this);
		_nuevoReporte.setOnClickListener(this);
		
<<<<<<< HEAD
		_tvNuevoReporte = (TextView) findViewById(R.id.tvNuevoReport);
		_tvNuevoReporte.setOnClickListener(this);
=======
		user = getIntent().getStringExtra("user");
		contra = getIntent().getStringExtra("contra");
		
		
		nombreUsuario.setText(user);
		
>>>>>>> origin/master
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
<<<<<<< HEAD
			case R.id.btnCrearReporte:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
			case R.id.tvHistorial:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
=======
		case R.id.btnCrearReporte:
			startActivity(new Intent (WelcomeActivity.this, NewReport.class));
			break;
			
		case R.id.tvNuevoReport:
			startActivity(new Intent (WelcomeActivity.this, NewReport.class));
			break;
>>>>>>> origin/master
		}
		}
	}

