package com.dbconnection.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.UsuarioModel;

/**
 * Servlet implementation class EditarPerfilUsuario
 */
@WebServlet("/EditarPerfilUsuario")
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class EditarPerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfilUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getUsuario(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Perfil.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UsuarioModel> usuarioElegido = null;
		UsuarioModel usuarioAux = null;
		
		int IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
		usuarioAux = new UsuarioModel(IdUsuarioActivo);
		
		try {
			usuarioElegido = UsuarioDAO.getUsuarios("VER", usuarioAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("usuarioElegido", usuarioElegido.get(0)); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actualizarUsuario(request, response);
		
		int IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
		response.sendRedirect("PerfilUsuario?IdUsuario=" + IdUsuarioActivo);
		
	}
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UsuarioModel usuarioNuevo = null;
		
		int IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
		String NombreUsuario = request.getParameter("Nombres");
		String ApellidosUsuario = request.getParameter("Apellidos");

		SimpleDateFormat formatoFechaNac = new SimpleDateFormat("yyyy-MM-dd");
		Date FechaNacimientoUsuario = null;
		try {
			FechaNacimientoUsuario = formatoFechaNac.parse(request.getParameter("FechaNac"));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		String CorreoUsuario = request.getParameter("Correo");

		InputStream ImagenPerfilUsuario = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("ImagenUs");
		if (filePart != null) {
			
			if (filePart.getSize() != 0)
				ImagenPerfilUsuario = filePart.getInputStream();
		}

		String UsernameUsuario = request.getParameter("NombreUs");
		String PasswordUsuario = request.getParameter("Contraseña");
		
		usuarioNuevo = new UsuarioModel(IdUsuarioActivo, NombreUsuario, ApellidosUsuario,
				FechaNacimientoUsuario, CorreoUsuario, UsernameUsuario, PasswordUsuario, ImagenPerfilUsuario);
		
		try {
			UsuarioDAO.iudUser("EDITAR", usuarioNuevo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
