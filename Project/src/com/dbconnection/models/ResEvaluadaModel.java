package com.dbconnection.models;

public class ResEvaluadaModel{
	
	private int idResEv;
	private byte util;
	private byte noUtil;
	private int idUsuario;
	private int idRespuesta;
	
	public ResEvaluadaModel() {
		
	}

	public ResEvaluadaModel(int idResEv) {
		this.idResEv = idResEv;
	}

	public ResEvaluadaModel(int idUsuario, int idRespuesta) {
		this.idUsuario = idUsuario;
		this.idRespuesta = idRespuesta;
	}

	public int getIdResEv() {
		return idResEv;
	}

	public void setIdResEv(int idResEv) {
		this.idResEv = idResEv;
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	
	
	
}