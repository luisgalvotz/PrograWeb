/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbconnection.dao;

import com.dbconnection.models.UserModel;
import com.dbconnection.utils.DbConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magoc
 */
public class UserDAO {

    // Query de creacion de Tabla
    /**
     * CREATE TABLE `user` ( `idUser` INT NOT NULL AUTO_INCREMENT, `nameUser`
     * VARCHAR(45) NULL, `password` VARCHAR(45) NULL, `urlImage` VARCHAR(255)
     * NULL, PRIMARY KEY (`idUser`));
     *
     */
    /**
     * Inserta un usuario en la base de datos
     *
     * @param user
     * @return
     */

     //TODO: Es ideal incluir el "throws Exception" ?
    public static int insertUser(UserModel user) throws Exception {
    	Connection con = null;
    	CallableStatement statement = null;

        int rowsAffectted = 0;
        try {
            con = DbConnection.getConnection();
            // En el proyecto solo podran hacer uso de Store Procedures
            // No llamadas directas como esta
            // Esta linea prepara la llamada a la base de datos para insertar
            // Cada ? significa un valor a ser remplazado
            statement = con.prepareCall("INSERT INTO user (nameUser, password, urlImage) VALUES (?,?,?)");
            // Remplazamos el primer parametro por el nombre
            statement.setString(1, user.getUserName());
            // El segundo por la contrase�a
            statement.setString(2, user.getPassword());
            // El ultimo por la url de la imagen
            statement.setString(3, user.getUrlImage());
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

    
    public static List<UserModel> getUsers() throws Exception  {
        List<UserModel> users = new ArrayList<>();
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
                users.add(new UserModel(name, password, urlImage));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            statement.close();
            con.close();
        }

        return users;
    }
}
