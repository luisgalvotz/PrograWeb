package com.dbconnection.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.dao.*;
import com.dbconnection.models.*;
import com.dbconnection.utils.FileUtils;

/**
 * Servlet implementation class PerfilUsuario
 */
@WebServlet("/PerfilUsuario")
public class PerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfilUsuario() {
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
			
			getUsuario(request, response);
			
			String pagina = request.getParameter("pagina");
			
			if (pagina != null) {
				request.setAttribute("pagina", pagina);

				switch (pagina) {
				case "preguntas":
					getPreguntasUsuario(request, response);
					break;

				case "respuestas":
					getRespuestasUsuario(request, response);
					break;

				case "preguntasFavoritas":
					getPreguntasFavoritasUsuario(request, response);

					break;

				case "preguntasUtiles":
					getPreguntasUtilesParaUsuario(request, response);

					break;

				case "preguntasNoUtiles":
					getPreguntasNoUtilesParaUsuario(request, response);

					break;

				default:
//				404 Not Found

				}
			}
			
			dispatcher = request.getRequestDispatcher("Perfil.jsp");
			
		} catch(IndexOutOfBoundsException e) {
			request.setAttribute("error", "¡Parece que el perfil de este usuario no existe!");
			dispatcher = request.getRequestDispatcher("error.jsp");
		} catch(NumberFormatException e) {
			request.setAttribute("error", "Los perfiles no se buscan con caracteres, ten cuidado");
			dispatcher = request.getRequestDispatcher("error.jsp");
		} catch(Exception e) {
			request.setAttribute("error", "Error inesperado");
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		finally {
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void getUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UsuarioModel> usuarioElegido = null;
		UsuarioModel usuarioAux = null;
		
		int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
		usuarioAux = new UsuarioModel(IdUsuario);
		
		int IdUsuarioActivo = -1;
		if (request.getSession().getAttribute("IdUsuarioActivo") != null)
			IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
		
		try {
			usuarioElegido = UsuarioDAO.getUsuarios("USP", usuarioAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("usuarioElegido", usuarioElegido.get(0));
		request.setAttribute("IdUsuarioActivo", IdUsuarioActivo);
		
	}
	
	private void getPreguntasUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
		
		int numeroPregunta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			numeroPregunta = 10 * (numeroPagina - 1);
		}
		
		PreguntaModel parametroPreguntas = new PreguntaModel();
		parametroPreguntas.setIdUsuario(IdUsuario);
		parametroPreguntas.setNumPreguntaPag(numeroPregunta);
		
		List<PreguntaModel> preguntasUsuarioElegido = null;
		
		try {
			preguntasUsuarioElegido = PreguntaDAO.getPregunta("UP", parametroPreguntas);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		request.setAttribute("preguntasUsuarioElegido", preguntasUsuarioElegido); 
		
	}
	
	private void getRespuestasUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
		
		int numeroRespuesta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			numeroRespuesta = 10 * (numeroPagina - 1);
		}
		
		RespuestaModel parametroRespuestas = new RespuestaModel();
		parametroRespuestas.setIdUsuario(IdUsuario);
		parametroRespuestas.setNumRespuestaPag(numeroRespuesta);
		
		List<RespuestaModel> respuestasUsuarioElegido = null;
		
		try {
			respuestasUsuarioElegido = RespuestaDAO.getRespuesta("UR", parametroRespuestas);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		request.setAttribute("respuestasUsuarioElegido", respuestasUsuarioElegido); 
		
	}
	
	private void getPreguntasFavoritasUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
		
		int numeroPregunta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			numeroPregunta = 10 * (numeroPagina - 1);
		}
		
		PreguntaModel parametroPreguntasFavoritas = new PreguntaModel();
		parametroPreguntasFavoritas.setIdUsuario(IdUsuario);
		parametroPreguntasFavoritas.setNumPreguntaPag(numeroPregunta);
		
		List<PreguntaModel> preguntasFavoritasUsuarioElegido = null;
		
		try {
			preguntasFavoritasUsuarioElegido = PreguntaDAO.getPregunta("UPF", parametroPreguntasFavoritas);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		request.setAttribute("preguntasUsuarioElegidoFavoritas", preguntasFavoritasUsuarioElegido); 
		
	}
	
	private void getPreguntasUtilesParaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
		
		int numeroPregunta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			numeroPregunta = 10 * (numeroPagina - 1);
		}
		
		PreguntaModel parametroPreguntasUtiles = new PreguntaModel();
		parametroPreguntasUtiles.setIdUsuario(IdUsuario);
		parametroPreguntasUtiles.setNumPreguntaPag(numeroPregunta);
		
		List<PreguntaModel> preguntasUtilesUsuarioElegido = null;
		
		try {
			preguntasUtilesUsuarioElegido = PreguntaDAO.getPregunta("UPU", parametroPreguntasUtiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("preguntasUtilesUsuarioElegido", preguntasUtilesUsuarioElegido); 
		
	}
	
	private void getPreguntasNoUtilesParaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
		
		int numeroPregunta = 0;
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			
			numeroPregunta = 10 * (numeroPagina - 1);
		}
		
		PreguntaModel parametroPreguntasNoUtiles = new PreguntaModel();
		parametroPreguntasNoUtiles.setIdUsuario(IdUsuario);
		parametroPreguntasNoUtiles.setNumPreguntaPag(numeroPregunta);
		
		List<PreguntaModel> preguntasNoUtilesUsuarioElegido = null;
		
		try {
			preguntasNoUtilesUsuarioElegido = PreguntaDAO.getPregunta("UPNU", parametroPreguntasNoUtiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("preguntasNoUtilesUsuarioElegido", preguntasNoUtilesUsuarioElegido); 
	}
	
	private void getInfoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	

}
