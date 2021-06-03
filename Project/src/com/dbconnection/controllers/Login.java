package com.dbconnection.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.UsuarioModel;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getSession().setAttribute("IdUsuarioActivo", null);
		request.getSession().setAttribute("EstadoUsuario", null);
		
		response.sendRedirect("Inicia_sesion.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		List<UsuarioModel> listaUsuarios = buscarUsuario(request, response);
		
		if (listaUsuarios.isEmpty() == false) {
			
			request.getSession().setAttribute("IdUsuarioActivo", listaUsuarios.get(0).getId());
			request.getSession().setAttribute("ActivoUsuario", listaUsuarios.get(0).getActivo());
			
			response.sendRedirect("Inicio_.jsp");
			//response.sendRedirect("IndexPreguntas?numeroPagina=0");
		}
		else {
			request.setAttribute("usuarioIncorrecto", "true");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Inicia_sesion.jsp");
        	dispatcher.forward(request, response);
		}
	}
	
	private List<UsuarioModel> buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<UsuarioModel> listaUsuarios = null;
		
		UsuarioModel usuarioElegido = null;
		
		String usuario = request.getParameter("Nombre_Usuario");
		String contra = request.getParameter("Contra_Usuario");
		
		usuarioElegido = new UsuarioModel();
		
		usuarioElegido.setNomUsuario(usuario);
		usuarioElegido.setContra(contra);
		
		try {
			listaUsuarios = UsuarioDAO.getUsuarios("LOGIN", usuarioElegido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}

}
