package com.dbconnection.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.dao.CategoriaDAO;
import com.dbconnection.dao.PreguntaDAO;
import com.dbconnection.models.CategoriaModel;
import com.dbconnection.models.PreguntaModel;


/**
 * Servlet implementation class BuscarPreguntas
 */
@WebServlet("/BuscarPreguntas")
public class BuscarPreguntas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPreguntas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		
		try {
			if (request.getParameter("Busqueda") != null) {
				request.setAttribute("Busqueda", "Busqueda");
				buscarListaPreguntasBuscadas(request, response);	
			}
			
			int IdUsuarioActivo = -1;
			if (request.getSession().getAttribute("IdUsuarioActivo") != null)
				IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
			
			byte EstadoUsuario = 0;
			if (request.getSession().getAttribute("EstadoUsuario") != null)
				EstadoUsuario = (byte)request.getSession().getAttribute("EstadoUsuario");

			request.setAttribute("IdUsuarioActivo", IdUsuarioActivo);
			request.setAttribute("EstadoUsuario", EstadoUsuario);
			
			dispatcher = request.getRequestDispatcher("Busqueda.jsp");
			
		} catch(NumberFormatException  e) {
			request.setAttribute("error", "¿Trataste de buscar una pregunta con un caracter?");
			dispatcher = request.getRequestDispatcher("Error.html");
		} catch(Exception e) {
			request.setAttribute("error", e.toString());
			dispatcher = request.getRequestDispatcher("Error.html");
		}
		finally {
			dispatcher.forward(request, response);
		}
	}
	
	private void buscarListaPreguntasBuscadas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PreguntaModel> listaPreguntasBuscadas = null;
		
		PreguntaModel parametrosPregunta = null;
		
		int numeroPregunta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			numeroPregunta = 10 * (numeroPagina - 1);
		}
		
		String TituloPregunta = null;
		if (request.getParameter("BusquedaN") != null && request.getParameter("BusquedaN") != ""){
			TituloPregunta = request.getParameter("BusquedaN");
		}
		
		int IdCategoriaPregunta = -1;
		if (request.getParameter("categories") != null){
			IdCategoriaPregunta = Integer.valueOf(request.getParameter("categories"));
		}
		
		parametrosPregunta = new PreguntaModel(numeroPregunta, TituloPregunta, IdCategoriaPregunta);
		
		request.setAttribute("parametrosPregunta", parametrosPregunta);
		
		try {
			if (IdCategoriaPregunta != -1){
				listaPreguntasBuscadas = PreguntaDAO.getPregunta("BUSCARC", parametrosPregunta);
			}
			else {
				listaPreguntasBuscadas = PreguntaDAO.getPregunta("BUSCAR", parametrosPregunta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        request.setAttribute("lista10Preguntas", listaPreguntasBuscadas);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
