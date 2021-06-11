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

import com.dbconnection.dao.CategoriaDAO;
import com.dbconnection.dao.PreguntaDAO;
import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.CategoriaModel;
import com.dbconnection.models.PreguntaModel;
import com.dbconnection.models.UsuarioModel;

/**
 * Servlet implementation class SubirPregunta
 */
@WebServlet("/SubirPregunta")
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class SubirPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubirPregunta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getUsuarioActivo(request, response);
		getPregunta(request, response);
		getCategorias(request, response);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Pregunta.jsp");
		dispatcher.forward(request, response);
	}

	private void getUsuarioActivo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	private void getCategorias(HttpServletRequest request, HttpServletResponse response) {
		List<CategoriaModel> listaCategorias = null;

		try {
			listaCategorias = CategoriaDAO.getListCategoria();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("listaCategorias", listaCategorias);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("abrirPregunta") != null) {

			if (Boolean.valueOf(request.getParameter("abrirPregunta")) == true)
				doGet(request, response);
		} else {
			int IdPregunta = insertarPregunta(request, response);

			response.sendRedirect("IndexPreguntas?numeroPagina=1");
		}
	}

	private int insertarPregunta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		int IdPregunta = 0;

		PreguntaModel preguntaNueva = null;

		String TituloPregunta = request.getParameter("Titulo_pregunta");
		String DescripcionPregunta = request.getParameter("Descripcion_pregunta");

		InputStream ImagenPregunta = null; // input stream of the upload file

		List<PreguntaModel> preguntaIngresada = null;

		// obtains the upload file part in this multipart request

		Part filePart = request.getPart("Imagen_PR");

		if (filePart.getSize() != 0) {

			if (filePart.getSize() != 0)
				ImagenPregunta = filePart.getInputStream();
		}

		
		
		int IdUsuarioPregunta = (int) request.getSession().getAttribute("IdUsuarioActivo");

		int IdCategoriaPregunta = Integer.valueOf(request.getParameter("SelCategoria"));

		if (Boolean.valueOf(request.getParameter("preguntaNueva")) == true) {

			preguntaNueva = new PreguntaModel(TituloPregunta, DescripcionPregunta, ImagenPregunta, IdUsuarioPregunta,
					IdCategoriaPregunta);

			try {
				PreguntaDAO.iudPregunta("AGREGAR", preguntaNueva);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else {
			IdPregunta = Integer.parseInt(request.getParameter("IdPregunta"));

			int eliminarImagen = Integer.valueOf(request.getParameter("eliminarImagen"));
			
			preguntaNueva = new PreguntaModel(IdPregunta, TituloPregunta, DescripcionPregunta, ImagenPregunta, IdUsuarioPregunta,
					IdCategoriaPregunta);
			
			try {
				if (eliminarImagen == 0) {
					PreguntaDAO.iudPregunta("EDITAR", preguntaNueva);
				} else if (eliminarImagen == 1) {
					PreguntaDAO.iudPregunta("EDITARN", preguntaNueva);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return IdPregunta;
	}

}
