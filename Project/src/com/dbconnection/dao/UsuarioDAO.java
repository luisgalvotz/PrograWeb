package com.dbconnection.dao;

import com.dbconnection.models.UsuarioModel;
import com.dbconnection.utils.DbConnection;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

	// TODO: Es ideal incluir el "throws Exception" ?
	public static int iudUser(String opc, UsuarioModel us) throws Exception {
		Connection con = null;
		CallableStatement statement = null;

		int rowsAffectted = 0;
		try {
			con = DbConnection.getConnection();
			// Esta linea prepara la llamada a la base de datos para insertar
			// Cada ? significa un valor a ser remplazado
			// statement = con.prepareCall("INSERT INTO user (nameUser, password, urlImage)
			// VALUES (?,?,?)");
			statement = con.prepareCall("CALL SP_Usuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			statement.setString(1, opc);
			statement.setInt(2, us.getId());
			statement.setString(3, us.getNomUsuario());
			statement.setString(4, us.getContra());
			statement.setString(5, us.getNombre());
			statement.setString(6, us.getApellidos());

			if (us.getFechaNac() != null) {
				java.util.Date utilDateFechaNac = us.getFechaNac();
				java.sql.Date fechaConvertidaFechaNac = new java.sql.Date(utilDateFechaNac.getTime());

				statement.setDate(7, fechaConvertidaFechaNac);
			} else {
				statement.setDate(7, null);
			}

			statement.setString(8, us.getCorreo());
			statement.setBlob(9, us.getImagen());

			if (us.getFechaCreacion() != null) {
				java.util.Date utilDateFechaCrea = us.getFechaCreacion();
				java.sql.Date fechaConvertidaFechaCrea = new java.sql.Date(utilDateFechaCrea.getTime());

				statement.setDate(10, fechaConvertidaFechaCrea);
			} else {
				statement.setDate(10, null);
			}
			
			statement.setByte(11, us.getActivo());

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

	public static List<UsuarioModel> getUsuarios(String opc, UsuarioModel us) throws Exception {
		List<UsuarioModel> listaUsuarios = new ArrayList<>();
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getConnection();
			statement = con.prepareCall("CALL SP_Usuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			statement.setString(1, opc);
			statement.setInt(2, us.getId());
			statement.setString(3, us.getNomUsuario());
			statement.setString(4, us.getContra());
			statement.setString(5, us.getNombre());
			statement.setString(6, us.getApellidos());
			
			if (us.getFechaNac() != null) {
				java.util.Date utilDateFechaNac = us.getFechaNac();
				java.sql.Date fechaConvertidaFechaNac = new java.sql.Date(utilDateFechaNac.getTime());

				statement.setDate(7, fechaConvertidaFechaNac);
			} else {
				statement.setDate(7, null);
			}

			statement.setString(8, us.getCorreo());
			statement.setBlob(9, us.getImagen());

			if (us.getFechaCreacion() != null) {
				java.util.Date utilDateFechaCrea = us.getFechaCreacion();
				java.sql.Date fechaConvertidaFechaCrea = new java.sql.Date(utilDateFechaCrea.getTime());

				statement.setDate(10, fechaConvertidaFechaCrea);
			} else {
				statement.setDate(10, null);
			}
			
			statement.setByte(11, us.getActivo());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int Id_Usuario = resultSet.getInt("Id_Usuario");
				String Nombre_Usuario = resultSet.getString("Nombre_Usuario");
				String Contrasena = resultSet.getString("Contrasena");
				String Nombres = resultSet.getString("Nombres");
				String Apellidos = resultSet.getString("Apellidos");
				java.util.Date Fecha_Nac = resultSet.getDate("Fecha_Nac");
				String Correo = resultSet.getString("Correo");
				InputStream Img_Perfil = resultSet.getBinaryStream("Img_Perfil");
				java.util.Date Fecha_Creacion = resultSet.getDate("Fecha_Creacion");
				byte Activo = resultSet.getByte("Activo");
				
				int cantPreguntasUsuario = 0;
                int cantRespuestasUsuario = 0;
                int cantPreguntasFavoritasUsuario = 0;
                int cantPreguntasUtilesUsuario = 0;
                int cantPreguntasNoUtilesUsuario = 0;
                
                if (DbConnection.hasColumn(resultSet, "CantPreguntasUsuario"))
                	cantPreguntasUsuario = resultSet.getInt("CantPreguntasUsuario");
                
                if (DbConnection.hasColumn(resultSet, "CantRespuestasUsuario"))
                	cantRespuestasUsuario = resultSet.getInt("CantRespuestasUsuario");
                
                if (DbConnection.hasColumn(resultSet, "CantPreguntasFavoritasUsuario"))
                	cantPreguntasFavoritasUsuario = resultSet.getInt("CantPreguntasFavoritasUsuario");
                
                if (DbConnection.hasColumn(resultSet, "CantPreguntasUtilesUsuario"))
                	cantPreguntasUtilesUsuario = resultSet.getInt("CantPreguntasUtilesUsuario");
                
                if (DbConnection.hasColumn(resultSet, "CantPreguntasNoUtilesUsuario"))
                	cantPreguntasNoUtilesUsuario = resultSet.getInt("CantPreguntasNoUtilesUsuario");

				listaUsuarios.add(new UsuarioModel(Id_Usuario, Nombres, Apellidos, Fecha_Nac, Correo, Nombre_Usuario,
						Contrasena, Img_Perfil, Fecha_Creacion, Activo, cantPreguntasUsuario, cantRespuestasUsuario,
						cantPreguntasFavoritasUsuario, cantPreguntasUtilesUsuario, cantPreguntasNoUtilesUsuario));
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			statement.close();
			con.close();
		}

		return listaUsuarios;
	}

	public static int getUsuario(String UsernameUsuario) throws Exception  {
		int usernameEncontrado = 0;
        Connection con = null;
        CallableStatement statement = null;
        
        try {
            con = DbConnection.getConnection();
            statement = con.prepareCall("CALL SP_Usuario('NOMUS', NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
            statement.setString(1, UsernameUsuario);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next())
           	 usernameEncontrado = resultSet.getInt("UsernameEncontrado");
            
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            statement.close();
            con.close();
        }

        return usernameEncontrado;
	}
}