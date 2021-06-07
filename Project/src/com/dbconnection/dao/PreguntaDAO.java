package com.dbconnection.dao;

import java.io.InputStream;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dbconnection.models.PreguntaModel;
import com.dbconnection.utils.DbConnection;

public class PreguntaDAO {
	
	public static int iudPregunta(String opc, PreguntaModel pregunta) throws Exception {
		Connection con = null;
    	CallableStatement statement = null;

        int rowsAffectted = 0;
        try {
        	con = DbConnection.getConnection();
        	statement = con.prepareCall("CALL SP_Pregunta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        	
        	statement.setString(1, opc);
            statement.setInt(2, pregunta.getId());
            statement.setString(3, pregunta.getTitulo());
            statement.setString(4, pregunta.getDescripcion());
            statement.setBlob(5, pregunta.getImagen());
            
            if (pregunta.getFechaCreacion() != null) {
            	java.util.Date utilDateFechaCrea = pregunta.getFechaCreacion();
            	java.sql.Date fechaConvertidaFechaCrea = new java.sql.Date(utilDateFechaCrea.getTime());    		
                statement.setDate(6, fechaConvertidaFechaCrea);
            }
            else {
            	statement.setDate(6, null);
            }
            
            statement.setByte(7, pregunta.getActivo());
            statement.setByte(8, pregunta.getEditada());
            statement.setInt(9, pregunta.getIdUsuario());
            statement.setInt(10, pregunta.getIdCategoria());
            statement.setInt(11, pregunta.getIdRespuesta());
            statement.setInt(12, pregunta.getNumPreguntaPag());
            statement.setInt(13, pregunta.getVotosUtil());
            statement.setInt(14, pregunta.getVotosNoUtil());
            statement.setInt(15, pregunta.getVotosFavorito());
            
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
	
	public static List<PreguntaModel> getPregunta(String opc, PreguntaModel pregunta) throws Exception {
		List<PreguntaModel> listaPreguntas = new ArrayList<>();
        Connection con = null;
        CallableStatement statement = null;
        
        try {
        	con = DbConnection.getConnection();
            statement = con.prepareCall("CALL SP_Pregunta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            statement.setString(1, opc);
            statement.setInt(2, pregunta.getId());
            statement.setString(3, pregunta.getTitulo());
            statement.setString(4, pregunta.getDescripcion());
            statement.setBlob(5, pregunta.getImagen());
            
            if (pregunta.getFechaCreacion() != null) {
            	java.util.Date utilDateFechaCrea = pregunta.getFechaCreacion();
            	java.sql.Date fechaConvertidaFechaCrea = new java.sql.Date(utilDateFechaCrea.getTime());    		
                statement.setDate(6, fechaConvertidaFechaCrea);
            }
            else {
            	statement.setDate(6, null);
            }
            
            statement.setByte(7, pregunta.getActivo());
            statement.setByte(8, pregunta.getEditada());
            statement.setInt(9, pregunta.getIdUsuario());
            statement.setInt(10, pregunta.getIdCategoria());
            statement.setInt(11, pregunta.getIdRespuesta());
            statement.setInt(12, pregunta.getNumPreguntaPag());
            statement.setInt(13, pregunta.getVotosUtil());
            statement.setInt(14, pregunta.getVotosNoUtil());
            statement.setInt(15, pregunta.getVotosFavorito());
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	
            	int IdPregunta = resultSet.getInt("Id_Pregunta");
                String TituloPregunta = resultSet.getString("Titulo");
                String DescripcionPregunta = resultSet.getString("Descripcion");
                InputStream ImagenPregunta = resultSet.getBinaryStream("Imagen");
                
                Timestamp timeStampAux = resultSet.getTimestamp("Fecha_Creacion");
				Date FechaCreacionPregunta = null;
					if (timeStampAux != null)
                FechaCreacionPregunta = new Date(timeStampAux.getTime());
                
                byte EstadoPregunta = resultSet.getByte("Activo");
                byte EditadoPregunta = resultSet.getByte("Editada");
                int Usuario_Pregunta = resultSet.getInt("Id_Usuario");
                int Respuesta_Correcta = resultSet.getInt("Id_Respuesta");
                int Categoria_Pregunta = resultSet.getInt("Id_Categoria");
                
                
                String usernameUsuarioPregunta = "";
                InputStream imagenPerfilUsuarioPregunta = null;
                String tituloCategoriaPregunta = "";
                int cantidadVotosUtiles = -1;
                int cantidadVotosNoUtiles = -1;
                int cantidadVotosFavoritos = -1;
                byte EstadoUsuarioPregunta = -1;
                
                if (DbConnection.hasColumn(resultSet, "Nombre_Usuario"))
                	usernameUsuarioPregunta = resultSet.getString("Nombre_Usuario");
                
                if (DbConnection.hasColumn(resultSet, "Img_Perfil"))
                	imagenPerfilUsuarioPregunta = resultSet.getBinaryStream("Img_Perfil");
                
                if (DbConnection.hasColumn(resultSet, "Activo_Usuario"))
                	EstadoUsuarioPregunta = resultSet.getByte("Activo_Usuario");
                
                if (DbConnection.hasColumn(resultSet, "Nombre_Cat"))
                	tituloCategoriaPregunta = resultSet.getString("Nombre_Cat");
                
                if (DbConnection.hasColumn(resultSet, "CantVotosUtiles"))
                	cantidadVotosUtiles = resultSet.getInt("CantVotosUtiles");
                
                if (DbConnection.hasColumn(resultSet, "CantVotosNoUtiles"))
                	cantidadVotosNoUtiles = resultSet.getInt("CantVotosNoUtiles");
                
                if (DbConnection.hasColumn(resultSet, "CantVotosFavoritas"))
                	cantidadVotosFavoritos = resultSet.getInt("CantVotosFavoritas");
                
                // Agregamos el usuario a la lista
                
                listaPreguntas.add(new PreguntaModel(IdPregunta, TituloPregunta, DescripcionPregunta, ImagenPregunta,
                							FechaCreacionPregunta, EstadoPregunta, EditadoPregunta, Usuario_Pregunta, Categoria_Pregunta, Respuesta_Correcta,
                							cantidadVotosUtiles, cantidadVotosNoUtiles, cantidadVotosFavoritos, usernameUsuarioPregunta, imagenPerfilUsuarioPregunta, 
                							EstadoUsuarioPregunta, tituloCategoriaPregunta));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            statement.close();
            con.close();
        }

        return listaPreguntas;
	}
	
}