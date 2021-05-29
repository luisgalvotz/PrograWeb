package com.dbconnection.models;


public class UsuarioModel {

    private String nombre;
    private String apellidos;
    private String fechaNac;
    private String correo;
    private String nomUsuario;
    private String contra;
    private String urlImage;

    public UsuarioModel() {
    }

    public UsuarioModel(String nombre, String apellidos, String fechaNac, String correo, String nomUsuario, String contra, String urlImage) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.nomUsuario = nomUsuario;
        this.contra = contra;
        this.urlImage = urlImage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getNomUsuario() {
    	return nomUsuario;
    }
    
    public void setNomUsuario(String nomUsuario) {
    	this.nomUsuario = nomUsuario;
    }
    
    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}
