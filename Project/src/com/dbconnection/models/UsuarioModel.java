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
    
    private int cantPreguntasUsuario;
    private int cantRespuestasUsuario;
    private int cantPreguntasFavoritasUsuario;
    private int cantPreguntasUtilesUsuario;
    private int cantPreguntasNoUtilesUsuario;

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
    
    public String getEdad() {
    	String Edad = "";
		
		int yearNac = fechaNac.getYear();
		int monthNac = fechaNac.getMonth();
		int dayNac = fechaNac.getDay();
		
		int yearActual = new java.util.Date().getYear();
		int monthActual = new java.util.Date().getMonth();
		int dayActual = new java.util.Date().getDay();
		
		boolean futureBirthday = false;
		int yearDifference = yearActual - yearNac;
		
		if(		(monthNac >  monthActual)		|| 		(monthNac ==  monthActual	&&		dayNac > dayActual)		) {
			futureBirthday = true;
		}
		
		if(futureBirthday == false) {
			Edad = String.valueOf( yearDifference );
		}
		else {
			Edad = String.valueOf( yearDifference - 1 );
		}
		return Edad;
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
    
    public InputStream isImagen() {
    	if (imagen != null) {
			PushbackInputStream pushbackInputStream = new PushbackInputStream(imagen);
		    int bytes = 0;
		    try {
				bytes = pushbackInputStream.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if (bytes == -1) {
		    	return null;
		    }
		    else {
		    	return imagen;
		    }
			
		}
		else {
		    return null;
		}
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

	public int getCantPreguntasUsuario() {
		return cantPreguntasUsuario;
	}

	public void setCantPreguntasUsuario(int cantPreguntasUsuario) {
		this.cantPreguntasUsuario = cantPreguntasUsuario;
	}

	public int getCantRespuestasUsuario() {
		return cantRespuestasUsuario;
	}

	public void setCantRespuestasUsuario(int cantRespuestasUsuario) {
		this.cantRespuestasUsuario = cantRespuestasUsuario;
	}

	public int getCantPreguntasFavoritasUsuario() {
		return cantPreguntasFavoritasUsuario;
	}

	public void setCantPreguntasFavoritasUsuario(int cantPreguntasFavoritasUsuario) {
		this.cantPreguntasFavoritasUsuario = cantPreguntasFavoritasUsuario;
	}

	public int getCantPreguntasUtilesUsuario() {
		return cantPreguntasUtilesUsuario;
	}

	public void setCantPreguntasUtilesUsuario(int cantPreguntasUtilesUsuario) {
		this.cantPreguntasUtilesUsuario = cantPreguntasUtilesUsuario;
	}

	public int getCantPreguntasNoUtilesUsuario() {
		return cantPreguntasNoUtilesUsuario;
	}

	public void setCantPreguntasNoUtilesUsuario(int cantPreguntasNoUtilesUsuario) {
		this.cantPreguntasNoUtilesUsuario = cantPreguntasNoUtilesUsuario;
	}

}
