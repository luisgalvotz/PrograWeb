package com.dbconnection.dao;

import com.dbconnection.models.RespuestaModel;
import com.dbconnection.utils.DbConnection;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RespuestaDAO{
	
	public static int iudRespuesta(String opc, RespuestaModel respuesta) throws Exception {
		Connection con = null;
    	CallableStatement statement = null;

        int rowsAffectted = 0;
        try {
        	con = DbConnection.getConnection();
            
            statement = con.prepareCall("CALL SP_Respuesta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            statement.setString(1, opc);
            statement.setInt(2, respuesta.getId());
            statement.setString(3, respuesta.getContenido());
            statement.setBlob(4, respuesta.getImagen());
            
            if (respuesta.getFechaCreacion() != null) {
            	java.util.Date utilDateFechaCrea = respuesta.getFechaCreacion();
        		java.sql.Date fechaConvertidaFechaCrea = new java.sql.Date(utilDateFechaCrea.getTime());    		
                statement.setDate(5, fechaConvertidaFechaCrea);
			} else {
				statement.setDate(5, null);
			}
            
            statement.setByte(6, respuesta.getActivo());
            statement.setByte(7, respuesta.getEditada());
            statement.setInt(8, respuesta.getIdUsuario());
            statement.setInt(9, respuesta.getIdPregunta());
            statement.setInt(10, respuesta.getNumRespuestaPag());
            
            rowsAffectted = statement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            rowsAffectted = 0;
        } finally {
        	statement.close();
            con.close();
        }
        return rowsAffectted;
	}
	
	public static List<RespuestaModel> getRespuesta(String opc, RespuestaModel respuesta) throws Exception  {
		List<RespuestaModel> listaRespuestas = new ArrayList<>();
        Connection con = null;
        CallableStatement statement = null;
        
        try {
        	con = DbConnection.getConnection();
            
            statement = con.prepareCall("CALL SP_Respuesta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            statement.setString(1, opc);
            statement.setInt(2, respuesta.getId());
            statement.setString(3, respuesta.getContenido());
            statement.setBlob(4, respuesta.getImagen());
            
            if (respuesta.getFechaCreacion() != null) {
            	java.util.Date utilDateFechaCrea = respuesta.getFechaCreacion();
        		java.sql.Date fechaConvertidaFechaCrea = new java.sql.Date(utilDateFechaCrea.getTime());    		
                statement.setDate(5, fechaConvertidaFechaCrea);
			} else {
				statement.setDate(5, null);
			}
            
            statement.setByte(6, respuesta.getActivo());
            statement.setByte(7, respuesta.getEditada());
            statement.setInt(8, respuesta.getIdUsuario());
            statement.setInt(9, respuesta.getIdPregunta());
            statement.setInt(10, respuesta.getNumRespuestaPag());
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	int IdRespuesta = resultSet.getInt("Id_Respuesta");
                String TextoRespuesta = resultSet.getString("Contenido");
                InputStream ImagenRespuesta = resultSet.getBinaryStream("Imagen");
                
                Timestamp timeStampAux = resultSet.getTimestamp("Fecha_Creacion");
				Date FechaCreacionRespuesta = null;
				if (timeStampAux != null)
                	FechaCreacionRespuesta = new Date(timeStampAux.getTime());
                
                byte EstadoRespuesta = resultSet.getByte("Activo");
                byte EditadoRespuesta = resultSet.getByte("Editada");
                int Usuario_Responde = resultSet.getInt("Id_Usuario");
                int Pregunta_Respondida = resultSet.getInt("Id_Pregunta");
               
              
                String usernameUsuarioRespuesta = "";
                InputStream imagenPerfilUsuarioRespuesta = null;
                int cantidadVotosUtiles = -1;
                int cantidadVotosNoUtiles = -1;
                
                if (DbConnection.hasColumn(resultSet, "Nombre_Usuario"))
                	usernameUsuarioRespuesta = resultSet.getString("Nombre_Usuario");
                
                if (DbConnection.hasColumn(resultSet, "Img_Perfil"))
                	imagenPerfilUsuarioRespuesta = resultSet.getBinaryStream("Img_Perfil");
                
                if (DbConnection.hasColumn(resultSet, "CantVotosUtiles"))
                	cantidadVotosUtiles = resultSet.getInt("CantVotosUtiles");
                
                if (DbConnection.hasColumn(resultSet, "CantVotosNoUtiles"))
                	cantidadVotosNoUtiles = resultSet.getInt("CantVotosNoUtiles");
                
                listaRespuestas.add(new RespuestaModel(IdRespuesta, TextoRespuesta, ImagenRespuesta, FechaCreacionRespuesta, 
						EstadoRespuesta, EditadoRespuesta, Usuario_Responde, Pregunta_Respondida,
						usernameUsuarioRespuesta, imagenPerfilUsuarioRespuesta,
						cantidadVotosUtiles, cantidadVotosNoUtiles));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            statement.close();
            con.close();
        }

        return listaRespuestas;
	}
	
	
}