<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.dbconnection.models.*"%>
<%@page import="com.dbconnection.utils.userType"%>
<%@page import="java.util.*"%>
<%@page import="com.dbconnection.controllers.GeneralServlet"%>

<%
UsuarioModel usuarioElegido = GeneralServlet.getUsuario(request, response);
pageContext.setAttribute("usuarioElegido", usuarioElegido);

List<CategoriaModel> listaCategorias = GeneralServlet.getCategorias();
pageContext.setAttribute("listaCategorias", listaCategorias);

userType usuarioActivo = (userType) request.getAttribute("usuarioActivo");
pageContext.setAttribute("usuarioActivo", usuarioActivo);

int IdUsuarioActivo = (int) request.getAttribute("IdUsuarioActivo");
pageContext.setAttribute("IdUsuarioActivo", IdUsuarioActivo);

PreguntaModel preguntaElegida = (PreguntaModel) request.getAttribute("preguntaElegida");
pageContext.setAttribute("preguntaElegida", preguntaElegida);

RespuestaModel respuestaCorrecta = (RespuestaModel) request.getAttribute("respuestaCorrecta");
pageContext.setAttribute("respuestaCorrecta", respuestaCorrecta);

List<RespuestaModel> lista10Respuestas = (List<RespuestaModel>) request.getAttribute("lista10Respuestas");
pageContext.setAttribute("lista10Respuestas", lista10Respuestas);

int numeroPagina = 1;

if (request.getAttribute("numeroPagina") != null) {
	numeroPagina = (int) request.getAttribute("numeroPagina");
	pageContext.setAttribute("numeroPagina", numeroPagina);
}
%>

<!doctype html>
<html lang="en">
<head>
<title>Queuestions</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/Detallepreg.css">
<link rel="stylesheet" href="css/jquery.sweet-modal.min.css">
<link rel="stylesheet" href="css/validacion.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/ImagenRegistro.js"></script>
<script src="js/SelecImg.js"></script>
<script src="js/validaciones.js"></script>

</head>
<body>

	<%-- ojsdfksdljj --%>
	<!-- BARRA DE NAVEGACIï¿½N -->

	<nav class="navbar navbar-expand-md  navbar-light">
		<ul class="navbar-nav mr-auto">
			<img class="logopag" src="Imagenes/que.png" alt="Logo">
		</ul>

		<!-- Boton que aparece cuando colapsas la navbar en tamaï¿½o md es la "palanca" (toggle) que expande los elementos en el div con id:navbarmenu -->
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarmenu" aria-controls="navbarmenu"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!--navbarmenu: div que es colapsable y dentro tiene un navegador que se ajusto al centro con mx-auto  -->
		<div class="collapse navbar-collapse" id="navbarmenu">
			<ul class="navbar-nav mx-auto ">

				<!-- Boton de inicio -->
				<li class="nav-item"><a href="IndexPreguntas?numeroPagina=1"
					class="nav-link">Inicio</a></li>

				<!-- Dropdown de categorias -->
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" id="Categoriasnavbar"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Categorías </a>
					<div class="dropdown-menu" aria-labelledby="Categoriasnavbar">
						<c:forEach var="iCategoria" items="${listaCategorias}">
							<a class="dropdown-item" href="#"> ${iCategoria.getNombre()}
							</a>
						</c:forEach>
					</div></li>

				<!-- Busqueda dropdown -->
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" id="Busquedanavbar"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Búsqueda</a>
					<!-- dropdown del link Bï¿½squeda -->
					<div class="dropdown-menu" aria-labelledby="Busquedanavbar"
						style="width: 370px;">
						<div class="container">
							<div class="row">
								<div class="col-10">
									<input style="width: 295px;" type="text" name="BusquedaN"
										id="BuscarNormal">
								</div>
								<div class="col-2">
									<a href="#">
										<button class="BuscarLupa">
											<img style="width: 20px; height: 20px;"
												src="Imagenes/Lupa.png" alt="LupaBuscar">
										</button>

									</a>
								</div>
							</div>
						</div>
						<header>
							<div class="text-center TituloBusquedaA">
								<input type="checkbox" name="ConfirmaBusquedaA"
									id="ConfirmaBusquedaA"> <label for="ConfirmaBusquedaA">Búsqueda
									Avanzada</label>
							</div>
						</header>

						<!-- Filtro categoria -->
						<form style="margin-left: 10px;" action="">
							<label for="categoriasbusca">Categoría:</label> <select
								disabled="disabled" id="categoriasbusca" class="combo dropdown">
								<option value="Ninguna">Ninguna</option>
								<option value="Comida">Comida</option>
								<option value="Deportes">Deportes</option>
								<option value="Juegos">Juegos</option>
								<option value="Amor">Amor</option>
								<option value="Trabajo">Trabajo</option>
							</select>
						</form>

						<datalist class="categoriasbusca" id="html_elements">
							<option value="Comida"></option>
							<option value="Deportes"></option>
							<option value="Juegos"></option>
							<option value="Amor"></option>
							<option value="Trabajo"></option>
						</datalist>

						<!-- Filtro rango de fechas -->
						<label style="margin-left: 10px;" for="">Rango de fecha</label> <br>
						<input disabled="disabled"
							style="margin-left: 10px; width: 150px;" type="date"
							name="FechaIncio" id="FechaInicio"> <label>--</label> <input
							disabled="disabled" style="width: 150px;" type="date"
							name="FechaFin" id="FechaFin">

						<!-- Filtro personas que marcaron ï¿½til -->
						<label style="margin-left: 10px;" for="NutilBusca"> Número
							de personas que les pareció útil </label> <input type="checkbox"
							disabled="disabled" name="NutilBusca" id="NutilBusca">

						<!-- Filtro personas que marcaron favorita -->
						<label style="margin-left: 10px;" for="NfavoritaBusca">
							Número de personas que marcaron favoritas </label> <input type="checkbox"
							disabled="disabled" name="NfavoritaBusca" id="NfavoritaBusca">


					</div></li>


			</ul>
		</div>

		<ul class="navbar-nav ml-auto">
			<c:if test="${empty usuarioElegido}">
				<li class="nav-item"><a href="Inicia_sesion.jsp"
					class="nav-link"> <img style="height: 40px; width: 40px;"
						src="Imagenes/Perfil.png" alt=""> Iniciar sesión
				</a></li>
			</c:if>

			<c:if test="${not empty usuarioElegido}">
				<li class="nav-item"><a href="Perfil.jsp" class="nav-link">
						<img style="height: 40px; width: 40px;"
						src="GeneralServlet?Imagen=Usuario&Id=${usuarioElegido.getId()}">
						<c:out value="${usuarioElegido.getNomUsuario()}"></c:out>
				</a></li>
			</c:if>
		</ul>
	</nav>
	<!-- TERMINA BARRA DE NAVEGACIï¿½N -->


	<!-- CUERPO DE LA Pï¿½GINA -->

	<div class="container main">
		<div class="row">
			<div class="col-sm-9 col-lg-9">
				<!-- Pregunta 1 -->
				<section>
					<div class="container">
						<p style="border-bottom: solid; margin: 0;">
							<img class="imagen_usu_inicio"
								src="GeneralServlet?Imagen=Usuario&Id=${preguntaElegida.getIdUsuario()}"
								alt="">${preguntaElegida.getNomUsuarioPregunta()}
							<c:if test="${preguntaElegida.getEditada() == 1}">
								<p>Editada</p>
						</p>
						</c:if>

						<p class="fecha_hora_pregunta">
							${preguntaElegida.getFechaCreacionToString()}</p>

						<p class="pregunta"
							style="margin-bottom: 0; margin-top: 0px; border-bottom: solid;">
							${preguntaElegida.getTitulo()}</p>
						<p id="categoriapreg">
							${preguntaElegida.getCategoriaPregunta()}</p>
						<c:if test="${preguntaElegida.getDescripcion() != ''}">
							<p class="descripcion">${preguntaElegida.getDescripcion()}</p>
						</c:if>
					</div>
				</section>
			</div>
			<c:if test="${preguntaElegida.isImagen() != null}">
				<div class="col-sm-2 col-lg-2 text-center">
					<img class="imagenpregunta"
						src="GeneralServlet?Imagen=Pregunta&Id=${preguntaElegida.getId()}"
						alt="">
				</div>
			</c:if>
			<c:if test="${usuarioActivo == userType.questionOwner}">
				<div class="col-sm-1 col-lg-1">
					<form id="form_borrar_preg" action="PreguntaRespuesta" method="post">
						<input name="tipo" type="hidden" value="BorrarPregunta">
						<input name="IdPregunta" type="hidden" value="${preguntaElegida.getId()}">
						<button class="boton_borrar" id="eliminar_pregunta" type="button">
							<img src="Imagenes/eliminar.png" class="imagen_borrar">
						</button>
					</form>
					<form id="form_editar_preg${preguntaElegida.getId()}" action="SubirPregunta" method="post" enctype="multipart/form-data">
						<input name="abrirPregunta" type="hidden" value="true">
						<input name="IdPregunta" type="hidden" value="${preguntaElegida.getId()}">
					</form>
						<button form="form_editar_preg${preguntaElegida.getId()}" class="boton_editar" id="editar_pregunta" type="button">
							<img class="imagen_editar" src="Imagenes/editar.png">
						</button>
					</div>
			</c:if>
		</div>
		<div class="row" style="margin-left: 10px;">
			<div class="col-1">
				<p>
					<button class="util_noutil_fav_btn" id="like_pregunta"
						name="like_pregunta">
						<img class="util_noutil_fav" src="Imagenes/Like.png">
					</button>
					0
				</p>
			</div>
			<div class="col-1">
				<p>
					<button class="util_noutil_fav_btn" id="dislike_pregunta"
						name="dislike_pregunta">
						<img class="util_noutil_fav" src="Imagenes/Dislike.png">
					</button>
					0
				</p>
			</div>
			<div class="col-1">
				<p>
					<button class="util_noutil_fav_btn" id="fav_pregunta"
						name="fav_pregunta">
						<img class="util_noutil_fav" src="Imagenes/Favorita.png">
					</button>
					0
				</p>
			</div>
		</div>
	</div>

	<%-- RESPUESTA FORMULARIO --%>
	<div class="container">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-11">
				<section class="formulario_respuesta">
					<form id="form_hacer_respuesta" action="" method="post">

						<c:if test="${empty preguntaElegida}">
							<div class="container">
								<div class="row">
									<div class="col-12">
										<textarea class="RespuestaS" rows="3"
											placeholder="Escribe aquí tu respuesta"
											name="respuesta_texto" id="respuesta_texto"></textarea>
									</div>

									<div class="col-lg-2 col-sm-3">
										<div id="img-container-respuesta"> 
											<input class="Seleccionimagen" type='file'
												name="Imagen_respuesta" id="Imagen_respuesta"
												onchange="readURL(this);" /> <img id="Imagenseleccionada"
												src="#" alt="" />
												<button class="botones" style="margin-bottom: 8px;">
													Eliminar Imagen
												</button>
										</div>
									</div>
								</div>
								<input class="botones" type="submit" form="form_hacer_respuesta"
									value="Publicar respuesta"> <input
									name="respuestaNueva" type="hidden" value="true">
							</div>
						</c:if>

						<c:if test="${not empty preguntaElegida}">

						</c:if>

					</form>
				</section>
			</div>
		</div>
	</div>

	<%-- RESPUESTA CORRECTA--%>
	<c:if test="${not empty respuestaCorrecta}">
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-11">
					<div class="container main_correcta ">
						<div class="row">
							<div class="col-9">
								<section>
									<div class="container">
										<p style="border-bottom: solid; margin: 0;">
											<img class="imagen_usu_inicio"
												src="GeneralServlet?Imagen=Usuario&Id=${respuestaCorrecta.getIdUsuario()}"
												alt="">${respuestaCorrecta.getNomUsuarioRespuesta()}
											<c:if test="${respuestaCorrecta.getEditada() == 1}">
												<p style="font-size: 14px;">Editada</p>
											</c:if>
										</p>

										<p class="fecha_hora_respuesta">
											${respuestaCorrecta.getFechaCreacionString()}</p>

										<p class="respuesta_correcta"
											style="margin-bottom: 0; margin-top: 0px; border-bottom: solid;">
											${respuestaCorrecta.getContenido()}</p>

									</div>

								</section>
							</div>

							<c:if test="${respuestaCorrecta.isImagen() != null}">
								<div class="col-2">
									<img class="imagenrespuesta"
										src="GeneralServlet?Imagen=Respuesta&Id=${respuestaCorrecta.getId()}"
										alt="">
								</div>
							</c:if>

							<div class="col-1">
								<button class="boton_borrar" id="eliminar_respuesta_correcta"
									type="button">
									<img src="Imagenes/eliminar.png" class="imagen_borrar">
								</button>

								<button class="boton_editar" id="editar_respuesta" type="button">
									<img class="imagen_editar" src="Imagenes/editar.png">
								</button>
							</div>

						</div>

						<div class="row" style="margin-left: 10px;">
							<div class="col-1">
								<p>
									<button class="util_noutil_fav_btn" id="like_respuesta"
										name="like_respuesta">
										<img class="util_noutil_fav" src="Imagenes/Like.png">
									</button>
									0
								</p>
							</div>

							<div class="col-1">
								<p>
									<button class="util_noutil_fav_btn" id="dislike_respuesta"
										name="dislike_respuesta">
										<img class="util_noutil_fav" src="Imagenes/Dislike.png">
									</button>
									0
								</p>
							</div>

							<div class="col-1">
								<button class="util_noutil_fav_btn" id="correcta_respuesta"
									name="correcta_respuesta">
									<img class="util_noutil_fav" src="Imagenes/correcta.png">
								</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- RESPUESTA NORMAL --%>
	<c:forEach var="iRespuesta" items="${lista10Respuestas}">
	<c:if test="${iRespuesta.getId() != preguntaElegida.getIdRespuesta()}">
	<c:if test="${iRespuesta.getActivo() == 1}">
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-11">
					<div class="container main_respuesta_normal">
						<div class="row">
							<div class="col-9">
								<section>
									<div class="container">
										<p style="border-bottom: solid; margin: 0;">
											<img class="imagen_usu_inicio"
												src="GeneralServlet?Imagen=Usuario&Id=${iRespuesta.getIdUsuario()}"
												alt="">${iRespuesta.getNomUsuarioRespuesta()}
											<c:if test="${iRespuesta.getEditada() == 1}">
												<p style="font-size: 14px;">Editada</p>
											</c:if>
										</p>

										<p class="fecha_hora_respuesta">
											${iRespuesta.getFechaCreacionString()}</p>

										<p class="respuesta_normal"
											style="margin-bottom: 0; margin-top: 0px; border-bottom: solid;">
											${iRespuesta.getContenido()}</p>

									</div>

								</section>
							</div>

							<c:if test="${iRespuesta.isImagen() != null}">
								<div class="col-2">
									<img class="imagenrespuesta"
										src="GeneralServlet?Imagen=Respuesta&Id=${iRespuesta.getId()}"
										alt="">
								</div>
							</c:if>

							<div class="col-1">
								<button class="boton_borrar" id="eliminar_respuesta"
									type="button">
									<img src="Imagenes/eliminar.png" class="imagen_borrar">
								</button>

								<button class="boton_editar" id="editar_respuesta" type="button">
									<img class="imagen_editar" src="Imagenes/editar.png">
								</button>
							</div>

						</div>

						<div class="row" style="margin-left: 10px;">
							<div class="col-1">
								<p>
									<button class="util_noutil_fav_btn" id="like_respuesta"
										name="like_respuesta">
										<img class="util_noutil_fav" src="Imagenes/Like.png">
									</button>
									0
								</p>
							</div>

							<div class="col-1">
								<p>
									<button class="util_noutil_fav_btn" id="dislike_respuesta"
										name="dislike_respuesta">
										<img class="util_noutil_fav" src="Imagenes/Dislike.png">
									</button>
									0
								</p>
							</div>

							<div class="col-1">
								<button class="util_noutil_fav_btn" id="correcta_respuesta"
									name="correcta_respuesta">
									<img class="util_noutil_fav" src="Imagenes/correcta.png">
								</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</c:if>
	</c:if>
	</c:forEach>

	<!-- CONTAINER PARA LA PAGINACION -->
	<div class="container paginacion_inicio">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<!-- PAGINACION -->
				<div class="container-fluid">
					<br> <br>
					<nav>
						<ul class="pagination ">

							<c:if test="${numeroPagina - 1 == 0}">
								<li class="page-item"><a class="page-link" id="atras"
									href="PreguntaRespuesta?IdPregunta=${preguntaElegida.getId()}&numeroPagina=1">
										<img class="paginacionimg" src="Imagenes/pagina_anterior.png"
										alt="">
								</a></li>
							</c:if>

							<c:if test="${numeroPagina - 1 != 0}">
								<li class="page-item"><a class="page-link" id="atras"
									href="PreguntaRespuesta?IdPregunta=${preguntaElegida.getId()}&numeroPagina=${numeroPagina - 1}">
										<img class="paginacionimg" src="Imagenes/pagina_anterior.png"
										alt="">
								</a></li>
							</c:if>

							<li class="page-item active"><a class="page-link"
								id="numeropagina" href="">${numeroPagina}</a></li>

							<li class="page-item"><a class="page-link" id="adelante"
								href="PreguntaRespuesta?IdPregunta=${preguntaElegida.getId()}&numeroPagina=${numeroPagina + 1}">
									<img class="paginacionimg" src="Imagenes/pagina_siguiente.png"
									alt="">
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="col-4"></div>
		</div>
	</div>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<%-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script> --%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="js/inicio_.js"></script>
</body>

<!-- FOOTER DE LA Pï¿½GINA -->
<footer class=" text-lg-start" style="background-color: #f28825;">
	<!-- Grid container -->
	<div class="container p-4">
		<!--Grid row-->
		<div class="row">
			<!--Grid column-->
			<div class="col-lg-6 col-sm-6">
				<h5 class=" text-center" style="font-weight: bolder;">
					<img src="Imagenes/que.png"
						style="width: 70px; height: 50px; border-radius: 15px;" alt="">
					Queuestions
				</h5>

				<p class="text-center">Foro para conversar sobre temas variados.
				</p>


			</div>
			<!--Grid column-->


			<!--Grid column-->
			<div class="col-lg-6 col-sm-6 text-center">
				<h5 class=" mb-0">Estamos en:</h5>

				<ul class="list-unstyled">
					<li><a target="_blank"
						href="https://www.facebook.com/Queuestions-101688152055852"
						class="text-dark">Facebook</a></li>
				</ul>


			</div>
			<!--Grid column-->
		</div>
		<!--Grid row-->
		<div class="row">
			<p class="col-12 text-center">© 2021 Copyright</p>
		</div>
		<div class="row">
			<p class="col-2"></p>
			<p class="col-4" id="Luis">
				Luis Alejandro Galvan Ortiz <label for="Luis">1813703</label>
			</p>
			<p class="col-4" id="Miguel">
				Miguel Angel Villanueva Infante <label for="Miguel">1841237</label>
			</p>

		</div>
	</div>
	<!-- Grid container -->


</footer>


<!-- TERMINA FOOTER DE LA Pï¿½GINA -->
</html>