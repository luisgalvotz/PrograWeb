package com.dbconnection.models;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Date;

public class UsuarioModel {

	private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNac;
    private String correo;
    private String nomUsuario;
    private String contra;
    private InputStream imagen;
    private Date fechaCreacion;
    private byte activo;

    public UsuarioModel() {
    }

    public UsuarioModel(int id, String nombre, String apellidos, Date fechaNac, String correo, String nomUsuario, String contra, InputStream imagen, Date fechaCreacion, byte activo) {
        this.id = id;
    	this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.nomUsuario = nomUsuario;
        this.contra = contra;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }
    
    public UsuarioModel(String nombre, String apellidos, Date fechaNac, String correo, String nomUsuario, String contra, InputStream imagen) {
    	this.nombre = nombre;
    	this.apellidos = apellidos;
    	this.fechaNac = fechaNac;
    	this.correo = correo;
    	this.nomUsuario = nomUsuario;
    	this.contra = contra;
    	this.imagen = imagen;
    }
    
    public UsuarioModel(int id) {
    	this.id = id;
    }

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
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
    
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
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

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }
    
    public Date getFechaCreacion() {
    	return fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
    	this.fechaCreacion = fechaCreacion;
    }
    
    public byte getActivo() {
    	return activo;
    }
    
    public void setActivo(byte activo) {
    	this.activo = activo;
    }

}
