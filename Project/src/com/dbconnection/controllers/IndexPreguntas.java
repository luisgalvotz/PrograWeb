package com.dbconnection.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.dao.PreguntaDAO;
import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.PreguntaModel;

/**
 * Servlet implementation class IndexPreguntas
 */
@WebServlet("/IndexPreguntas")
public class IndexPreguntas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexPreguntas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		buscarListaPreguntasRecientes(request, response);
		
		int IdUsuarioActivo = -1;
		if (request.getSession().getAttribute("IdUsuarioActivo") != null)
			IdUsuarioActivo = (Integer)request.getSession().getAttribute("IdUsuarioActivo");
		
		request.setAttribute("IdUsuarioActivo", IdUsuarioActivo);
		
		byte EstadoUsuario = 0;
		if (request.getSession().getAttribute("EstadoUsuario") != null)
			EstadoUsuario = (byte)request.getSession().getAttribute("EstadoUsuario");
		
		request.setAttribute("EstadoUsuario", EstadoUsuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio_.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	private void buscarListaPreguntasRecientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PreguntaModel> listaPreguntasRecientes = null;
		
		PreguntaModel preguntaAux = null;
		int numeroPregunta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			
			numeroPregunta = 10 * (numeroPagina - 1);
		}
        // Agregamos el usuario a la lista
		preguntaAux = new PreguntaModel(numeroPregunta, "num");
		
		try {
			listaPreguntasRecientes = PreguntaDAO.getPregunta("INICIO", preguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("lista10Preguntas", listaPreguntasRecientes);
	}

}
