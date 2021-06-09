package com.dbconnection.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.models.PregEvaluadaModel;
import com.dbconnection.utils.DbConnection;

public class PregEvaluadaDAO{
	
	public static int iudPregEv(String opc, PregEvaluadaModel preguntaCalificada) throws Exception {
		Connection con = null;
    	CallableStatement statement = null;

        int rowsAffectted = 0;
        try {
        	con = DbConnection.getConnection();
            
            statement = con.prepareCall("CALL SP_PregEv(?, ?, ?);");
            
            statement.setString(1, opc);
            statement.setInt(2, preguntaCalificada.getIdUsuario());
            statement.setInt(3, preguntaCalificada.getIdPregunta());
            
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
	
}