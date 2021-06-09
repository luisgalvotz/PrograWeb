package com.dbconnection.models;

public class PregEvaluadaModel{
	
	private int idPregEv;
	private byte util;
	private byte noUtil;
	private byte favorito;
	private int idUsuario;
	private int idPregunta;
	
	public PregEvaluadaModel() {
		
	}

	public PregEvaluadaModel(int idPregEv) {
		this.idPregEv = idPregEv;
	}

	public PregEvaluadaModel(int idUsuario, int idPregunta) {
		this.idUsuario = idUsuario;
		this.idPregunta = idPregunta;
	}

	public int getIdPregEv() {
		return idPregEv;
	}

	public void setIdPregEv(int idPregEv) {
		this.idPregEv = idPregEv;
	}

	public byte getUtil() {
		return util;
	}

	public void setUtil(byte util) {
		this.util = util;
	}

	public byte getNoUtil() {
		return noUtil;
	}

	public void setNoUtil(byte noUtil) {
		this.noUtil = noUtil;
	}

	public byte getFavorito() {
		return favorito;
	}

	public void setFavorito(byte favorito) {
		this.favorito = favorito;
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
	
	
	
}