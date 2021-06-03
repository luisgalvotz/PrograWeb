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
		String nombre = request.getParameter("Nombre_");
		String apellidos = request.getParameter("Apellidos_Usuario");

		SimpleDateFormat formatoFechaNac = new SimpleDateFormat("yyyy-MM-dd");
		Date FechaNacUsuario = null;
		try {
			FechaNacUsuario = formatoFechaNac.parse(request.getParameter("FechaNac_Usuario"));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		String correo = request.getParameter("Correo_Usuario");
		String nUsuario = request.getParameter("Nombre_Usuario");
		String contra = request.getParameter("Contra_Usuario");
		// String contraC = request.getParameter("ContraC_Usuario");

		InputStream ImagenPerfilUsuario = null;
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("Imag_Usuario");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			ImagenPerfilUsuario = filePart.getInputStream();
		}
		
		UsuarioModel us = new UsuarioModel(nombre, apellidos, FechaNacUsuario, correo, nUsuario, contra, ImagenPerfilUsuario);

		try {
			UsuarioDAO.insertUser("AGREGAR", us);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("Perfil.jsp");
	}

}
