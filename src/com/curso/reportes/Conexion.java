package com.curso.reportes;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexion {
	public static final String ID_USUARIO = "_id";
	public static final String ID_RESGISTRO = "id";
	public static final String USUARIO = "usuario";
	public static final String NOMBRE = "nombre";
	public static final String CONTRA = "contrasenia";
	
	public static final String FECHA = "fecha";
	public static final String ID_USUARIOR = "usuario";
	public static final String DESCRIP = "Descripcion";
	
	private static final String N_BD = "reportes";
	private static final String N_USUARIOS = "usuarios";
	private static final String N_HISTORIAL = "historial";
	
	private static final int VERSION_BD = 1; 
	
	private BDHelper nHelper;
	private final Context nContexto;
	private SQLiteDatabase nBD;	
	
	
	private static class BDHelper extends SQLiteOpenHelper{
		
		public BDHelper(Context context){
			super(context, N_BD, null, VERSION_BD);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL("CREATE TABLE "+ N_USUARIOS + "(" + 
					ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					USUARIO + " TEXT NOT NULL," + 
					NOMBRE + " TEXT NOT NULL," +
					CONTRA + " TEXT NOT NULL);"
		);
			db.execSQL("CREATE TABLE "+ N_HISTORIAL + "(" + 
					ID_RESGISTRO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					ID_USUARIO + " TEXT NOT NULL," + 
					DESCRIP + " TEXT NOT NULL," +
					FECHA + " TEXT NOT NULL);"
					);
			
			}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ N_USUARIOS);
			db.execSQL("DROP TABLE IF EXISTS "+ N_HISTORIAL);
			onCreate(db);
			}
	}	
	
	public Conexion (Context c){
		nContexto = c;
	}
	
	public Conexion abrir() {
		// TODO Auto-generated method stub
		nHelper = new BDHelper(nContexto);
		nBD = nHelper.getWritableDatabase();
		return this;	
	}	
	
	public void cerrar() {
		// TODO Auto-generated method stub
		nHelper.close();	
	}

	public boolean addUser(String nom, String usuario2, String contra) {
		// TODO Auto-generated method stub
		try {
			ContentValues cv = new ContentValues();
			cv.put(USUARIO, usuario2);
			cv.put(NOMBRE, nom);
			cv.put(CONTRA, contra);
			nBD.insert(N_USUARIOS, null, cv);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		
				
	}

	public boolean login(String usuatrio, String contrasenia) {
		// TODO Auto-generated method stub
		Cursor c = nBD.rawQuery("SELECT * FROM " + N_USUARIOS + " WHERE usuario=? AND contrasenia=?", new String[]{usuatrio,contrasenia});
		if(c.getCount() > 0){
			return true; 
		}
		
		return false;
	}
	
	public ArrayList<String> getHistory (String usrario, String contra){
		ArrayList<String> registro=new ArrayList<String>();
		Cursor c = nBD.rawQuery("SELECT * FROM " + N_USUARIOS + " WHERE usuario=? AND contrasenia=?", new String[]{usrario,contra});
		 
		//Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	 String reporte = c.getString(0) + " " + c.getString(1) + " " +c.getString(2);
		         registro.add(reporte);
		        
		     } while(c.moveToNext());
		}
		return registro;
	}
	
	
	public String getName (String usrario, String contra){
		String nombre = null;
		Cursor c = nBD.rawQuery("SELECT nombre FROM " + N_USUARIOS + " WHERE usuario=? AND contrasenia=?", new String[]{usrario,contra});
		 
		//Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
			int i = 0;
		     do {
		         nombre =c.getString(0);
		         
		     } while(c.moveToNext());
		}
		return nombre;
	}
	
	
}
