package com.curso.reportes;

import com.example.loginbd.Conexion;
import com.example.loginbd.Conexion.BDHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexion {
	public static final String ID_USUARIO = "_id";
	public static final String ID_RESGISTRO = "_id";
	public static final String USUARIO = "usuario";
	public static final String NOMBRE = "nombre";
	public static final String CONTRA = "contrasenia";
	
	public static final String FECHA = "fecha";
	public static final String ID_USUARIOR = "usuario";
	
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
	
	
	
}
