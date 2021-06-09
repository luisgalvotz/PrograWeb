package com.dbconnection.models;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RespuestaModel{
	
	private int id;
	private String contenido;
	private InputStream imagen;
	private Date fechaCreacion;
	private byte activo;
	private byte editada;
	
	private int idUsuario;
	private int idPregunta;
	
	private int numRespuestaPag;
	
	private String nomUsuarioRespuesta;
	private InputStream imagenUsuarioRespuesta;
	
	private int votosUtil;
	private int votosNoUtil;
	
	
	public RespuestaModel(int id, String contenido, InputStream imagen, Date fechaCreacion, byte activo, byte editada,
			int idUsuario, int idPregunta) {
		this.id = id;
		this.contenido = contenido;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.editada = editada;
		this.idUsuario = idUsuario;
		this.idPregunta = idPregunta;
	}

	public RespuestaModel(int id, String contenido, InputStream imagen, int idUsuario, int idPregunta) {
		this.id = id;
		this.contenido = contenido;
		this.imagen = imagen;
		this.idUsuario = idUsuario;
		this.idPregunta = idPregunta;
	}

	public RespuestaModel(int id) {
		this.id = id;
	}

	public RespuestaModel(String contenido, InputStream imagen, Date fechaCreacion, byte activo, byte editada,
			int idUsuario, int idPregunta) {
		this.contenido = contenido;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.editada = editada;
		this.idUsuario = idUsuario;
		this.idPregunta = idPregunta;
	}

	public RespuestaModel(String contenido, InputStream imagen, int idUsuario, int idPregunta) {
		this.contenido = contenido;
		this.imagen = imagen;
		this.idUsuario = idUsuario;
		this.idPregunta = idPregunta;
	}

	public RespuestaModel() {
		
	}

	public RespuestaModel(int numRespuestaPag, String num) {
		this.numRespuestaPag = numRespuestaPag;
	}

	public RespuestaModel(int idPregunta, int numRespuestaPag) {
		this.idPregunta = idPregunta;
		this.numRespuestaPag = numRespuestaPag;
	}

	public RespuestaModel(int id, String contenido, InputStream imagen, Date fechaCreacion, byte activo, byte editada,
			int idUsuario, int idPregunta, String nomUsuarioRespuesta, InputStream imagenUsuarioRespuesta,
			int votosUtil, int votosNoUtil) {
		this.id = id;
		this.contenido = contenido;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.editada = editada;
		this.idUsuario = idUsuario;
		this.idPregunta = idPregunta;
		this.nomUsuarioRespuesta = nomUsuarioRespuesta;
		this.imagenUsuarioRespuesta = imagenUsuarioRespuesta;
		this.votosUtil = votosUtil;
		this.votosNoUtil = votosNoUtil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
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
	
	public String getFechaCreacionString() {
		return fechaCreacion.toGMTString();
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

	public byte getEditada() {
		return editada;
	}

	public void setEditada(byte editada) {
		this.editada = editada;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public int getNumRespuestaPag() {
		return numRespuestaPag;
	}

	public void setNumRespuestaPag(int numRespuestaPag) {
		this.numRespuestaPag = numRespuestaPag;
	}

	public String getNomUsuarioRespuesta() {
		return nomUsuarioRespuesta;
	}

	public void setNomUsuarioRespuesta(String nomUsuarioRespuesta) {
		this.nomUsuarioRespuesta = nomUsuarioRespuesta;
	}

	public InputStream getImagenUsuarioRespuesta() throws IOException {
		PushbackInputStream pushbackInputStream = new PushbackInputStream(imagenUsuarioRespuesta);
	    int bytes;
	    bytes = pushbackInputStream.read();
	    if (bytes == -1) {
	      return null;
	    }
	    else {
	    	return imagenUsuarioRespuesta;
	    }
	}

	public void setImagenUsuarioRespuesta(InputStream imagenUsuarioRespuesta) {
		this.imagenUsuarioRespuesta = imagenUsuarioRespuesta;
	}

	public int getVotosUtil() {
		return votosUtil;
	}

	public void setVotosUtil(int votosUtil) {
		this.votosUtil = votosUtil;
	}

	public int getVotosNoUtil() {
		return votosNoUtil;
	}

	public void setVotosNoUtil(int votosNoUtil) {
		this.votosNoUtil = votosNoUtil;
	}
	
}