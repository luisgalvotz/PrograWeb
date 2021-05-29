/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbconnection.utils;

import java.sql.*;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import javax.sql.DataSource; //TODO: CU�L ES MEJOR? EL DE JAVAX O EL DE TOMCAT

/**
 * Esta clase nos ayuda a crear la conexion a la base de datos
 *
 */
public class DbConnection {

    // Creamos el objeto conexion
    private static final BasicDataSource dataSource = new BasicDataSource();

    // Inicializamos la conexion
    static {
        // Tipo de Driver, este es el de mysql
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // La URL a la conexion, especificando que es de mysql
        // la ruta que es localhost puerto 3306
        // el nombre de la base de datos que es INSERTAR_NOMBRE
        // lo demas son parametros para que no tengan problemas con zonas horarias
        dataSource.setUrl("jdbc:mysql://localhost:3306/bd_queuestions?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        // El nombre de usuario de su conexion
        dataSource.setUsername("admin");
        // La contrase�a del usuario de su conexion
        dataSource.setPassword("root");
        // dataSource.setMaxIdle(0);
        // dataSource.setMaxActive(100);
    }

    /**
     * *
     * Metodo para obtener la conexion
     *
     * @return Conexion a Base de datos
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Metodo para cerrar la conexion
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        dataSource.close();
    }
}
