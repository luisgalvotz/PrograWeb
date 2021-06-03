package com.dbconnection.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.models.CategoriaModel;
import com.dbconnection.utils.DbConnection;

public class CategoriaDAO{
	
	
	public static List<CategoriaModel> getListCategoria() throws Exception  {
        List<CategoriaModel> listaCategorias = new ArrayList<>();
        Connection con = null;
        CallableStatement statement = null;

        try {
            con = DbConnection.getConnection();
            statement = con.prepareCall("CALL SP_Categoria('LIST', NULL, NULL);");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	int IdCategoria = resultSet.getInt("Id_Categoria");
                String NombreCategoria = resultSet.getString("Nombre_Cat");
                
                
                listaCategorias.add(new CategoriaModel(IdCategoria, NombreCategoria));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            statement.close();
            con.close();
        }

        return listaCategorias;
    }
	
}