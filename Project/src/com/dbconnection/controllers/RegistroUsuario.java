package com.dbconnection.controllers;

import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.UsuarioModel;
import com.dbconnection.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "RegistroUsuario", urlPatterns = { "/RegistroUsuario" })
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class RegistroUsuario extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int usernameEncontrado = verificarNombreUsuario(request,response);
		if( usernameEncontrado == 1 ) {
			request.setAttribute("usernameEncontrado", "true");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Registro.jsp");
        	dispatcher.forward(request, response);
		}
		else if( usernameEncontrado == 0 ){
			insertarUsuario(request, response);
	        response.sendRedirect("Inicia_sesion.jsp");
		}
	}
	
	private int verificarNombreUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int usernameEncontrado = 0;
		String UsernameUsuario = request.getParameter("Nombre_Usu");
		try {
			usernameEncontrado = UsuarioDAO.getUsuario(UsernameUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usernameEncontrado;
	}
	
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UsuarioModel us = null;
		
		String nombre = request.getParameter("Nombre_");
		String apellidos = request.getParameter("Apellidos_Usu");

		SimpleDateFormat formatoFechaNac = new SimpleDateFormat("yyyy-MM-dd");
		Date FechaNacUsuario = null;
		try {
			FechaNacUsuario = formatoFechaNac.parse(request.getParameter("FechaNac_Usu"));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		String correo = request.getParameter("Correo_Usu");
		String nUsuario = request.getParameter("Nombre_Usu");
		String contra = request.getParameter("Contra_Usu");
		// String contraC = request.getParameter("ContraC_Usu");

		InputStream ImagenPerfilUsuario = null;
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("Imag_Usu");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			ImagenPerfilUsuario = filePart.getInputStream();
		}
		
		us = new UsuarioModel(nombre, apellidos, FechaNacUsuario, correo, nUsuario, contra, ImagenPerfilUsuario);

		try {
			UsuarioDAO.iudUser("AGREGAR", us);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
