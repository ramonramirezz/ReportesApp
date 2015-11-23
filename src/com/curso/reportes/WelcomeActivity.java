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
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends Activity implements OnClickListener {
	

	TextView nombreUsuario, _tvNuevoReporte, _tvHistory;
	String user, contra;

	ImageButton _nuevoReporte ,hystory;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Bienvenido");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3789E1")));
		
		_tvNuevoReporte = (TextView) findViewById(R.id.tvNuevoReport);
		_tvHistory = (TextView) findViewById(R.id.tvHistory);
		nombreUsuario =(TextView) findViewById(R.id.tvNombreUsuario);
		
		_nuevoReporte = (ImageButton) findViewById(R.id.btnNuevoReporte);
		hystory = (ImageButton) findViewById(R.id.btnHistory);
		
		
		_tvNuevoReporte.setOnClickListener(this);
		_tvHistory.setOnClickListener(this);
		_nuevoReporte.setOnClickListener(this);
		hystory.setOnClickListener(this);
		
		user = getIntent().getStringExtra("user");
		contra = getIntent().getStringExtra("contra");
		
		
		nombreUsuario.setText(user);
		
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

			case R.id.btnNuevoReporte:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
			case R.id.btnHistory:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
			case R.id.tvNuevoReport:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;
			case R.id.tvHistory:
				startActivity(new Intent (WelcomeActivity.this, NewReport.class));
				break;


		}
		}
	}

