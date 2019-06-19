package com.equipo.starwash.Entidades;

public class Usuario {

    private int id;
    private String correo;
    private  String contraseña;
    private String nombreCliente;

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    //Constructor que acciona la clase
    public Usuario(String nombreCliente, String correo, String contraseña) {
        this.nombreCliente = nombreCliente;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }


}
