package com.dbconnection.controllers;

import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.UsuarioModel;
import com.dbconnection.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class RegistroUsuario
 */
@WebServlet(name = "RegistroUsuario", urlPatterns = {"/RegistroUsuario"})
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class RegistroUsuario extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("Nombre_");
		String apellidos = request.getParameter("Apellidos_Usuario");
		String fechaNac = request.getParameter("FechaNac_Usuario");
		String correo = request.getParameter("Correo_Usuario");
		String nUsuario = request.getParameter("Nombre_Usuario");
		String contra = request.getParameter("Contra_Usuario");
		String contraC = request.getParameter("ContraC_Usuario");
		
		String path = request.getServletContext().getRealPath("");
		
		File fileSaveDir = new File(path + FileUtils.RUTE_USER_IMAGE); // Obtenemos la Direccion donde deseamos guardarlo
        // Sino existe el directorio la creamos
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        Part file = request.getPart("Imag_Usuario"); // Obtenemos la imagen, debe coincidir con el name del input
        String contentType = file.getContentType();
        
        String nameImage = file.getName() + System.currentTimeMillis() + FileUtils.GetExtension(contentType); // Remplazamos el nombre que tiene para que no existan duplicados
        String fullPath = path + FileUtils.RUTE_USER_IMAGE + "/" + nameImage;
        
        file.write(fullPath); // Copiamos la imagen en la ruta especificada
        UsuarioModel us = new UsuarioModel(nombre, apellidos, fechaNac, correo, nUsuario, contra, FileUtils.RUTE_USER_IMAGE + "/" + nameImage);
        
        //System.out.println("Contra: " + contra + " / ContraC: " + contraC);
        
        try {
           	UsuarioDAO.insertUser(us);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        response.sendRedirect("Perfil.jsp");
	}

	
	
}
