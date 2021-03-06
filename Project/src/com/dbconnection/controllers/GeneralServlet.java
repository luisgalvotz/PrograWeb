package com.dbconnection.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.dao.CategoriaDAO;
import com.dbconnection.models.CategoriaModel;
import com.dbconnection.dao.UsuarioDAO;
import com.dbconnection.models.UsuarioModel;
import com.dbconnection.dao.PreguntaDAO;
import com.dbconnection.models.PreguntaModel;
import com.dbconnection.dao.RespuestaDAO;
import com.dbconnection.models.RespuestaModel;

/**
 * Servlet implementation class GeneralServlet
 */
@WebServlet("/GeneralServlet")
public class GeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneralServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Imagen = request.getParameter("Imagen");
		
		switch(Imagen) {
		case "Usuario":
			getUsuarioImagen(request, response);
			break;
			
		case "Pregunta":
			getPreguntaImagen(request, response);
			break;
			
		case "Respuesta":
			getRespuestaImagen(request, response);
			
			break;
			
		default:			
//			404 Not Found
		
		}
	}
	
	private void getUsuarioImagen(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String imageName = "usuario.png"; // Returns "foo.png".
		List<UsuarioModel> usuarioElegido = null;

		int IdUsuario = Integer.parseInt(request.getParameter("Id"));
		UsuarioModel usuarioAux = new UsuarioModel(IdUsuario);
		
		try {
			usuarioElegido = UsuarioDAO.getUsuarios("VER", usuarioAux);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (usuarioElegido.size() != 0) {
			if (usuarioElegido.get(0).getImagen() != null) {
				byte[] content = usuarioElegido.get(0).getImagen().readAllBytes();

				response.setContentType(getServletContext().getMimeType(imageName));
				response.setContentLength(content.length);
				response.getOutputStream().write(content);
			} 

		}
	}
	
	private void getPreguntaImagen(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String imageName = "pregunta.png"; // Returns "foo.png".
		List<PreguntaModel> preguntaElegida = null;
		
		int IdPregunta = Integer.parseInt(request.getParameter("Id"));
		PreguntaModel preguntaAux = new PreguntaModel(IdPregunta);
		
		try {
			preguntaElegida = PreguntaDAO.getPregunta("SEL", preguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (preguntaElegida.size() != 0) {
			if (preguntaElegida.get(0).getImagen() != null) {

				byte[] content = preguntaElegida.get(0).getImagen().readAllBytes();

				response.setContentType(getServletContext().getMimeType(imageName)); // response.setContentType("image/jpeg");
				response.setContentLength(content.length);
				response.getOutputStream().write(content);
			} 

		}
	}
	
	private void getRespuestaImagen(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String imageName = "respuesta.png"; // Returns "foo.png".
		
		List<RespuestaModel> respuestaElegida = null;
		
		int IdRespuesta = Integer.parseInt(request.getParameter("Id"));
		RespuestaModel respuestaAux = new RespuestaModel(IdRespuesta);
		
		try {
			respuestaElegida = RespuestaDAO.getRespuesta("SEL", respuestaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (respuestaElegida.size() != 0) {
			if (respuestaElegida.get(0).getImagen() != null) {

				byte[] content = respuestaElegida.get(0).getImagen().readAllBytes();

				response.setContentType(getServletContext().getMimeType(imageName));
				response.setContentLength(content.length);
				response.getOutputStream().write(content);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static UsuarioModel getUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		}
		
		if (usuarioElegido != null)
			return usuarioElegido.get(0);
		else
			return null;
	}
	
	public static List<CategoriaModel>getCategorias() {
		List<CategoriaModel> listaCategorias = null;

		try {
			listaCategorias = CategoriaDAO.getListCategoria();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCategorias;
	}

}
