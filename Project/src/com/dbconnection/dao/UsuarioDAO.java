package com.dbconnection.dao;

import com.dbconnection.models.UsuarioModel;
import com.dbconnection.utils.DbConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
       
	//TODO: Es ideal incluir el "throws Exception" ?
    public static int insertUser(UsuarioModel us) throws Exception {
    	Connection con = null;
    	CallableStatement statement = null;

        int rowsAffectted = 0;
        try {
            con = DbConnection.getConnection();
            // Esta linea prepara la llamada a la base de datos para insertar
            // Cada ? significa un valor a ser remplazado
            //statement = con.prepareCall("INSERT INTO user (nameUser, password, urlImage) VALUES (?,?,?)");
            statement = con.prepareCall("CALL SP_Usuario('AGREGAR', NULL, ?, ?, ?, ?, ?, ?, ?);");

            statement.setString(1, us.getNomUsuario());
            statement.setString(2, us.getContra());
            statement.setString(3, us.getNombre());
            statement.setString(4, us.getApellidos());
            statement.setDate(5, java.sql.Date.valueOf(us.getFechaNac()));
            statement.setString(6, us.getCorreo());
            statement.setString(7, us.getUrlImage());
            
            // Ejecuta el Statement y retorna cuantos registros
            // fueron actualizados
            
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

    /*
    public static List<UsuarioModel> getUsers() throws Exception  {
        List<UsuarioModel> users = new ArrayList<>();
        Connection con = null;
        CallableStatement statement = null;

        try {
            con = DbConnection.getConnection();
            statement = con.prepareCall("SELECT * FROM user");
            ResultSet resultSet = statement.executeQuery();
            // Si el resultSet tiene resultados lo recorremos
            while (resultSet.next()) {
                // Obtenemos el valor del result set en base al nombre de la
                // columna
                String name = resultSet.getString("nameUser");
                String password = resultSet.getString("password");
                String urlImage = resultSet.getString("urlImage");
                // Agregamos el usuario a la lista
                users.add(new UsuarioModel(name, password, urlImage));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            statement.close();
            con.close();
        }

        return users;
    }
    */
}