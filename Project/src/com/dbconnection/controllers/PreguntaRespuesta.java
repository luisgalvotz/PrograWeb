package com.dbconnection.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dbconnection.dao.*;
import com.dbconnection.models.*;
import com.dbconnection.utils.userType;

/**
 * Servlet implementation class PreguntaRespuesta
 */
@WebServlet("/PreguntaRespuesta")
public class PreguntaRespuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreguntaRespuesta() {
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
			boolean existePregunta = getPregunta(request, response);
			
			if (existePregunta == true)
				dispatcher = request.getRequestDispatcher("DetallePregunta.jsp");
			else {
				request.setAttribute("error", "Parece que la pregunta que esta buscando ha sido eliminada");
				dispatcher = request.getRequestDispatcher("Error.html");
			}
				
		} catch(NullPointerException  e) {
			request.setAttribute("error", "Parece que no se encontro la pregunta que estabas buscando");
			dispatcher = request.getRequestDispatcher("Error.html");
			
		} catch(NumberFormatException  e) {
			request.setAttribute("error", "¿Trataste de buscar una pregunta con un caracter?");
			dispatcher = request.getRequestDispatcher("Error.html");
		} catch(Exception  e) { //Error inesperado
			request.setAttribute("error", "Error inesperado");
			dispatcher = request.getRequestDispatcher("Error.html");
		}
		finally {
			dispatcher.forward(request, response);
		}
	}

	private boolean getPregunta(HttpServletRequest request, HttpServletResponse response) {
		boolean existePregunta = false;
		
		PreguntaModel preguntaElegida = null;
		
		List<PreguntaModel> listaPreguntaAux = null;
		PreguntaModel preguntaAux = null;
		
		int IdPregunta = 0;
		
		if (request.getParameter("IdPregunta") != null) {
			IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));
		}
		
		preguntaAux = new PreguntaModel(IdPregunta);
		
		try {
			listaPreguntaAux = PreguntaDAO.getPregunta("VER", preguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (listaPreguntaAux.size() > 0) {
			preguntaElegida = listaPreguntaAux.get(0);
			
			if (preguntaElegida.getActivo() == 1)
				existePregunta = true;
			else
				return false;

		}
		
		request.setAttribute("preguntaElegida", preguntaElegida);
		
		getTipoUsuario(preguntaElegida, request, response);
		//getRespuestaCorrecta(preguntaElegida.getRespuesta_Correcta(), request, response);
		//getRespuestas(preguntaElegida.getIdPregunta(), request, response);
		
		return existePregunta;

	}
	
	private void getTipoUsuario(PreguntaModel preguntaElegida, HttpServletRequest request, HttpServletResponse response) {
		userType usuarioActivo = null;
		
		int IdUsuarioPregunta = preguntaElegida.getIdUsuario();
		byte EstadoUsuario = -1;
		
		int IdUsuarioActivo = -1;
		if (request.getSession().getAttribute("IdUsuarioActivo") != null) {
			IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
			EstadoUsuario = (byte)request.getSession().getAttribute("EstadoUsuario");
		}
		
		if (IdUsuarioActivo == -1 || EstadoUsuario == 0) {
			usuarioActivo = userType.Invited;
		}
		else {
			if (IdUsuarioActivo == IdUsuarioPregunta) {
				usuarioActivo = userType.questionOwner;
			}
			else {
				usuarioActivo = userType.normalUser;
			}
		}
		
		request.setAttribute("usuarioActivo", usuarioActivo);
		request.setAttribute("IdUsuarioActivo", IdUsuarioActivo);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
