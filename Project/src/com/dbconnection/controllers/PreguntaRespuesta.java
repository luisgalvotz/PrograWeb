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
		getRespuestaCorrecta(preguntaElegida.getIdRespuesta(), request, response);
		getRespuestas(preguntaElegida.getId(), request, response);
		
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
	
	private void getRespuestaCorrecta(int respuesta_Correcta, HttpServletRequest request, HttpServletResponse response) {
		List<RespuestaModel> respuestaCorrecta = null;
		
		RespuestaModel respuestaAux = null;
		
		respuestaAux = new RespuestaModel(respuesta_Correcta);
		
		try {
			respuestaCorrecta = RespuestaDAO.getRespuesta("VER", respuestaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (respuestaCorrecta != null) {
        	if (respuestaCorrecta.get(0).getId() != 0)
        		request.setAttribute("respuestaCorrecta", respuestaCorrecta.get(0));
        }
		
	}

	private void getRespuestas(int IdPregunta, HttpServletRequest request, HttpServletResponse response) {
		List<RespuestaModel> lista10Respuestas = null;
		
		RespuestaModel respuestaAux = null;
		int numeroRespuesta = 0;
		
		if (request.getParameter("numeroPagina") != null) {
			int numeroPagina = Integer.valueOf(request.getParameter("numeroPagina"));
			if (numeroPagina <= 0)
				numeroPagina = 1;
				
			request.setAttribute("numeroPagina", numeroPagina);
			numeroRespuesta = 10 * (numeroPagina - 1);
		}
		
		respuestaAux = new RespuestaModel(IdPregunta, numeroRespuesta);
		
		try {
			lista10Respuestas = RespuestaDAO.getRespuesta("VER10", respuestaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        request.setAttribute("lista10Respuestas", lista10Respuestas);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String tipo = "";
		
		if (request.getSession().getAttribute("IdUsuarioActivo") != null) {			
			tipo = request.getParameter("tipo");
			
			switch(tipo) {
			case "Pregunta":
			{
				String Voto = request.getParameter("Vote");

				switch (Voto) {
				case "Util":
					ingresarEvPregunta(request, response, 1);
					break;

				case "NoUtil":
					ingresarEvPregunta(request, response, 2);
					break;

				case "Fav":
					ingresarEvPregunta(request, response, 3);
					break;

				default:
//				404 Not Found
				}
				break;
			}	
			case "Respuesta":
			{	
				String Voto = request.getParameter("Vote");

				switch (Voto) {
				case "Util":
					ingresarEvRespuesta(request, response, 1);
					break;

				case "NoUtil":
					ingresarEvRespuesta(request, response, 2);
					break;
					
				default:
//				404 Not Found

				}
				
				break;
			}
			case "RespuestaCorrecta": 
				ingresarRespuestaCorrecta(request, response);
				break;
			case "BorrarPregunta": 
				borrarPregunta(request, response);
				break;
			case "BorrarRespuesta": 
				borrarRespuesta(request, response);
				break;
			default:
			}
			
		}
		
		if (tipo.equals("BorrarPregunta") == false) {
			int IdPregunta = 0;
			if (request.getParameter("IdPregunta") != null) {
				IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));
			}
			response.sendRedirect("PreguntaRespuesta?IdPregunta=" + IdPregunta + "&numeroPagina=1");
		}
		else {
			response.sendRedirect("IndexPreguntas?numeroPagina=1");			
		}
		
	}

	private void ingresarRespuestaCorrecta(HttpServletRequest request, HttpServletResponse response) {
		List<PreguntaModel> PreguntaElegida = null;
		PreguntaModel PreguntaParametros = null;
		
		int IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));
		int IdRespuesta = Integer.parseInt(request.getParameter("IdRespuesta"));
		
		PreguntaParametros = new PreguntaModel(IdPregunta, IdRespuesta);
		
		try {
			PreguntaElegida = PreguntaDAO.getPregunta("SEL", PreguntaParametros);
			
			if (PreguntaElegida.size() == 1) {
				if (PreguntaElegida.get(0).getIdRespuesta() != IdRespuesta) {
					PreguntaDAO.iudPregunta("RC", PreguntaParametros);
				}
				else {
					PreguntaDAO.iudPregunta("NRC", PreguntaParametros);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void ingresarEvPregunta(HttpServletRequest request, HttpServletResponse response, int EvPreguntaCalificada) {
		PregEvaluadaModel evNuevoVoto = null;
		
		int IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
		int IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));
		
		if (true) {
			evNuevoVoto = new PregEvaluadaModel(IdUsuarioActivo, IdPregunta);
			
			try {
				if (EvPreguntaCalificada == 1) {
					PregEvaluadaDAO.iudPregEv("UTIL", evNuevoVoto);
				}
				else if (EvPreguntaCalificada == 2) {
					PregEvaluadaDAO.iudPregEv("NOUTIL", evNuevoVoto);
				}
				else if (EvPreguntaCalificada == 3) {
					PregEvaluadaDAO.iudPregEv("FAV", evNuevoVoto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void ingresarEvRespuesta(HttpServletRequest request, HttpServletResponse response, int EvRespuestaCalificada) {
		ResEvaluadaModel evNuevoVoto = null;
		
		int IdUsuarioActivo = (int)request.getSession().getAttribute("IdUsuarioActivo");
		int IdRespuesta = Integer.parseInt(request.getParameter("IdRespuesta"));
		
		if (true) {
			evNuevoVoto = new ResEvaluadaModel(IdUsuarioActivo, IdRespuesta);
			
			try {
				if (EvRespuestaCalificada == 1) {
					ResEvaluadaDAO.iudResEv("UTIL", evNuevoVoto);
				}
				else if (EvRespuestaCalificada == 2) {
					ResEvaluadaDAO.iudResEv("NOUTIL", evNuevoVoto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private int borrarPregunta(HttpServletRequest request, HttpServletResponse response) {
		PreguntaModel PreguntaParametros = null;
		
		int IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));
		
		PreguntaParametros = new PreguntaModel(IdPregunta);
		
		try {
			PreguntaDAO.iudPregunta("BORRAR", PreguntaParametros);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return IdPregunta;
		
	}
	
	private void borrarRespuesta(HttpServletRequest request, HttpServletResponse response) {
		RespuestaModel RespuestaParametros = null;
		
		int IdRespuesta = Integer.parseInt(request.getParameter("IdRespuesta"));
		
		RespuestaParametros = new RespuestaModel(IdRespuesta);
		
		try {
			RespuestaDAO.iudRespuesta("BORRAR", RespuestaParametros);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
