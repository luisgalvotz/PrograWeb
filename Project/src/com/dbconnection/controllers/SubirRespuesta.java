package com.dbconnection.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dbconnection.dao.CategoriaDAO;
import com.dbconnection.dao.PreguntaDAO;
import com.dbconnection.dao.RespuestaDAO;
import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.CategoriaModel;
import com.dbconnection.models.PreguntaModel;
import com.dbconnection.models.RespuestaModel;
import com.dbconnection.models.UsuarioModel;

/**
 * Servlet implementation class SubirRespuesta
 */
@WebServlet("/SubirRespuesta")
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class SubirRespuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubirRespuesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getUsuarioActivo(request, response);
		getPregunta(request, response);
		getRespuesta(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Respuesta.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("abrirRespuesta") != null) {
			if (Boolean.valueOf(request.getParameter("abrirRespuesta")) == true)
				doGet(request, response);
		}
		else {
			int IdPreguntaRespondida = insertarRespuesta(request, response);
			
			response.sendRedirect("PreguntaRespuesta?IdPregunta=" + IdPreguntaRespondida + "&numeroPagina=1");
		}
	}
	
	private int insertarRespuesta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int IdRespuesta = 0;
		
		RespuestaModel respuestaNueva = null;
		
		String TextoRespuesta = request.getParameter("respuesta_texto");
		InputStream ImagenRespuesta = null;
		Part filePart = request.getPart("Imagen_PR");
		
		if (filePart.getSize() != 0) {

			if (filePart.getSize() != 0)
				ImagenRespuesta = filePart.getInputStream();
		}
		
		int IdUsuarioRespuesta = (int) request.getSession().getAttribute("IdUsuarioActivo");
		
		int IdPreguntaRespondida = Integer.parseInt(request.getParameter("IdPregunta"));
		
		if (Boolean.valueOf(request.getParameter("respuestaNueva")) == true) {
			
			respuestaNueva = new RespuestaModel(TextoRespuesta, ImagenRespuesta, IdUsuarioRespuesta, IdPreguntaRespondida);
			try {
				RespuestaDAO.iudRespuesta("AGREGAR", respuestaNueva);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			
			IdRespuesta = Integer.parseInt(request.getParameter("IdRespuesta"));
			int eliminarImagen = Integer.valueOf(request.getParameter("eliminarImagen"));
			
			respuestaNueva = new RespuestaModel(IdRespuesta, TextoRespuesta, ImagenRespuesta, IdUsuarioRespuesta, IdPreguntaRespondida);
			
			try {
				if (eliminarImagen == 0) {
					RespuestaDAO.iudRespuesta("EDITAR", respuestaNueva);
				} else if (eliminarImagen == 1) {
					RespuestaDAO.iudRespuesta("EDITARN", respuestaNueva);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return IdPreguntaRespondida;
	}

	private void getUsuarioActivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UsuarioModel> usuarioElegido = null;
		UsuarioModel usuarioAux = null;
		
		if (request.getSession().getAttribute("IdUsuarioActivo") != null) {
			
			int IdUsuarioActivo = (int) request.getSession().getAttribute("IdUsuarioActivo");
			usuarioAux = new UsuarioModel(IdUsuarioActivo);
			
			try {
				usuarioElegido = UsuarioDAO.getUsuarios("VER", usuarioAux);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("usuarioElegido", usuarioElegido.get(0));
		}
	}
	
	private void getPregunta(HttpServletRequest request, HttpServletResponse response) {
		int IdPregunta = 0;
		
		if (request.getParameter("IdPregunta") != null) {
			
			IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));
			
			PreguntaModel preguntaElegida = null;
			
			List<PreguntaModel> listaPreguntaAux = null;
			PreguntaModel preguntaAux = null;
			
			preguntaAux = new PreguntaModel(IdPregunta);
			
			try {
				listaPreguntaAux = PreguntaDAO.getPregunta("VER", preguntaAux);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (listaPreguntaAux.size() > 0) {
				preguntaElegida = listaPreguntaAux.get(0);
			}

			request.setAttribute("preguntaElegida", preguntaElegida);
			
		}
	}
	
	private void getRespuesta(HttpServletRequest request, HttpServletResponse response) {
		int IdRespuesta = 0;
		
		if (request.getParameter("IdRespuesta") != null) {
			
			IdRespuesta = Integer.parseInt(request.getParameter("IdRespuesta"));
			
			RespuestaModel respuestaElegida = null;
			
			List<RespuestaModel> listaRespuestaAux = null;
			RespuestaModel respuestaAux = null;
			
			respuestaAux = new RespuestaModel(IdRespuesta);
			
			try {
				listaRespuestaAux = RespuestaDAO.getRespuesta("VER", respuestaAux);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (listaRespuestaAux.size() > 0) {
				respuestaElegida = listaRespuestaAux.get(0);
			}

			request.setAttribute("respuestaElegida", respuestaElegida);
			
		}
		
	}
	
}
