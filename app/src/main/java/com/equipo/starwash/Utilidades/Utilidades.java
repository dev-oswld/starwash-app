package com.equipo.starwash.Utilidades;

public class Utilidades {

    //Constantes campos de la tabla usuario x3
    public static final String DB_NOMBRE = "usariosLavado.db";
    public static final int DB_VERSION = 1;

    public static final String TABLA = "clientes";
    public static final String ID = "id";
    public static final String NOMBRE_USUARIO = "nombreCliente";
    public static final String CORREO = "correo";
    public static final String CONTRASENA = "contraseña";

    public static final String CREAR_TABLA =
            "CREATE TABLE  " + TABLA + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOMBRE_USUARIO + " TEXT NOT NULL," +
                    CORREO + " TEXT NOT NULL," +
                    CONTRASENA + " TEXT NOT NULL)";
}