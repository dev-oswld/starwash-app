package com.equipo.starwash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.equipo.starwash.Utilidades.Utilidades;
import com.equipo.starwash.Entidades.Usuario;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context) {
        super(context, Utilidades.DB_NOMBRE, null, Utilidades.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Solo para la primera vez
        db.execSQL(Utilidades.CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Solo para actualizacion y hacer operaciones con el
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA);
        onCreate(db);
    }

    public Usuario ConsultaUsuario(String nombreCliente, String correo, String contraseña) {

        SQLiteDatabase db = this.getReadableDatabase();
        Usuario user = null;

        //Objeto que sirve de iterador para hacer los QUERY a la BD
        Cursor cursor = db.query(Utilidades.TABLA,
                new String[]{Utilidades.ID,
                        Utilidades.NOMBRE_USUARIO,
                        Utilidades.CORREO,
                        Utilidades.CONTRASENA},

                Utilidades.NOMBRE_USUARIO + "=? and " + Utilidades.CORREO + "=? and " + Utilidades.CONTRASENA + "=?",
                new String[]{nombreCliente, correo, contraseña}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new Usuario(cursor.getString(1), cursor.getString(2),cursor.getString(3));
        }
        return user;
    }

    public void AltaDeUsuario(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.NOMBRE_USUARIO, user.getNombreCliente());
        values.put(Utilidades.CORREO, user.getCorreo());
        values.put(Utilidades.CONTRASENA, user.getContraseña());

        //Inserccion de los campos
        db.insert(Utilidades.TABLA, null, values);
        db.close();
    }
}
