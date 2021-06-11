package com.dbconnection.models;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreguntaModel {
	
	private int id;
	private String titulo;
	private String descripcion;
	private InputStream imagen;
	private Date fechaCreacion;
	private byte activo;
	private byte editada;
	
	private int idUsuario;
	private int idCategoria;
	private int idRespuesta;
	
	private int numPreguntaPag;
	
	private int votosUtil;
	private int votosNoUtil;
	private int votosFavorito;
	
	private String nomUsuarioPregunta;
	private InputStream imagenUsuarioPregunta;
	private byte activoUsuarioPregunta;
	
	private String categoriaPregunta;
	
	public PreguntaModel(int id, String titulo, String descripcion, InputStream imagen, Date fechaCreacion, byte activo,
			byte editada, int idUsuario, int idCategoria, int idRespuesta) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.editada = editada;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.idRespuesta = idRespuesta;
	}

	public PreguntaModel(int id) {
		this.id = id;
	}
	
	public PreguntaModel(String titulo, String descripcion, InputStream imagen, Date fechaCreacion, byte activo,
			byte editada, int idUsuario, int idCategoria, int idRespuesta) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.editada = editada;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.idRespuesta = idRespuesta;
	}
	
	public PreguntaModel() {
		
	}

	public PreguntaModel(int numPreguntaPag, String num) {
		this.numPreguntaPag = numPreguntaPag;
	}
	
	public PreguntaModel(int id, int idRespuesta) {
		this.id = id;
		this.idRespuesta = idRespuesta;
	}

	public PreguntaModel(String titulo, String descripcion, InputStream imagen, int idUsuario, int idCategoria) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
	}

	public PreguntaModel(int id, String titulo, String descripcion, InputStream imagen, int idUsuario,
			int idCategoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
	}

	public PreguntaModel(int id, String titulo, String descripcion, InputStream imagen, Date fechaCreacion, byte activo,
			byte editada, int idUsuario, int idCategoria, int idRespuesta, int votosUtil, int votosNoUtil,
			int votosFavorito, String nomUsuarioPregunta, InputStream imagenUsuarioPregunta, byte activoUsuarioPregunta,
			String categoriaPregunta) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.editada = editada;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.idRespuesta = idRespuesta;
		this.votosUtil = votosUtil;
		this.votosNoUtil = votosNoUtil;
		this.votosFavorito = votosFavorito;
		this.nomUsuarioPregunta = nomUsuarioPregunta;
		this.imagenUsuarioPregunta = imagenUsuarioPregunta;
		this.activoUsuarioPregunta = activoUsuarioPregunta;
		this.categoriaPregunta = categoriaPregunta;
	}
	
	public PreguntaModel(int numPreguntaPag, String titulo, int idCategoria) {
		this.numPreguntaPag = numPreguntaPag;
		this.titulo = titulo;
		this.idCategoria = idCategoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public String getFechaCreacionToString() {
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

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public int getNumPreguntaPag() {
		return numPreguntaPag;
	}

	public void setNumPreguntaPag(int numPreguntaPag) {
		this.numPreguntaPag = numPreguntaPag;
	}

	public int getVotosUtil() {
		return votosUtil;
	}
	
	public String getVotosUtilString() {
		if (votosUtil != -1) {
			return String.valueOf(votosUtil);
		}
		else {
			return "";
		}
	}

	public void setVotosUtil(int votosUtil) {
		this.votosUtil = votosUtil;
	}

	public int getVotosNoUtil() {
		return votosNoUtil;
	}
	
	public String getVotosNoUtilString() {
		if (votosNoUtil != -1) {
			return String.valueOf(votosNoUtil);
		}
		else {
			return "";
		}
	}

	public void setVotosNoUtil(int votosNoUtil) {
		this.votosNoUtil = votosNoUtil;
	}

	public int getVotosFavorito() {
		return votosFavorito;
	}
	
	public String getVotosFavoritoString() {
		if (votosFavorito != -1) {
			return String.valueOf(votosFavorito);
		}
		else {
			return "";
		}
	}

	public void setVotosFavorito(int votosFavorito) {
		this.votosFavorito = votosFavorito;
	}

	public String getNomUsuarioPregunta() {
		return nomUsuarioPregunta;
	}

	public void setNomUsuarioPregunta(String nomUsuarioPregunta) {
		this.nomUsuarioPregunta = nomUsuarioPregunta;
	}

	public InputStream getImagenUsuarioPregunta() throws IOException {
		PushbackInputStream pushbackInputStream = new PushbackInputStream(imagenUsuarioPregunta);
	    int bytes;
	    bytes = pushbackInputStream.read();
	    if (bytes == -1) {
	      return null;
	    }
	    else {
	    	return imagenUsuarioPregunta;
	    }
	}

	public void setImagenUsuarioPregunta(InputStream imagenUsuarioPregunta) {
		this.imagenUsuarioPregunta = imagenUsuarioPregunta;
	}

	public byte getActivoUsuarioPregunta() {
		return activoUsuarioPregunta;
	}

	public void setActivoUsuarioPregunta(byte activoUsuarioPregunta) {
		this.activoUsuarioPregunta = activoUsuarioPregunta;
	}

	public String getCategoriaPregunta() {
		return categoriaPregunta;
	}

	public void setCategoriaPregunta(String categoriaPregunta) {
		this.categoriaPregunta = categoriaPregunta;
	}

}