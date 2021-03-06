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
	<!-- BARRA DE NAVEGACI??????N -->

	<nav class="navbar navbar-expand-md  navbar-light">
		<ul class="navbar-nav mr-auto">
			<img class="logopag" src="Imagenes/que_.png" alt="Logo">
		</ul>

		<!-- Boton que aparece cuando colapsas la navbar en tama??????o md es la "palanca" (toggle) que expande los elementos en el div con id:navbarmenu -->
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
						Categor??as </a>
					<div class="dropdown-menu" aria-labelledby="Categoriasnavbar">
						<c:forEach var="iCategoria" items="${listaCategorias}">
							<a class="dropdown-item" href="BuscarPreguntas?Busqueda=Preguntas&numeroPagina=1&categories=${iCategoria.getId()}"> 
								${iCategoria.getNombre()}
							</a>
						</c:forEach>
					</div></li>

				<!-- Busqueda dropdown -->
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" id="Busquedanavbar"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">B??squeda</a>
					<!-- dropdown del link B??????squeda -->
					<div class="dropdown-menu" aria-labelledby="Busquedanavbar"
						style="width: 370px;">
						<form id="form_busqueda" action="BuscarPreguntas">
							<div class="container">
								<div class="row">
									<input name="Busqueda" type="hidden" value="Preguntas">
									<input name="numeroPagina" type="hidden" value="1">
									<div class="col-10">
										<input style="width: 295px;" type="text" name="BusquedaN" id="BuscarNormal">
									</div>
									<div class="col-2">
										<button class="BuscarLupa">
											<img style="width: 20px; height: 20px;" src="Imagenes/Lupa.png" alt="LupaBuscar">
										</button>
									</div>
								</div>
							</div>
						</form>
						<header>
							<div class="text-center TituloBusquedaA">
								<input type="checkbox" name="ConfirmaBusquedaA"
									id="ConfirmaBusquedaA"> <label for="ConfirmaBusquedaA">B??squeda
									Avanzada</label>
							</div>
						</header>

						<!-- Filtro categoria -->
						<form style="margin-left: 10px;" action="">
							<label for="categoriasbusca">Categor??a:</label> <select
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

						<!-- Filtro personas que marcaron ??????til -->
						<label style="margin-left: 10px;" for="NutilBusca"> N??mero
							de personas que les pareci?? ??til </label> <input type="checkbox"
							disabled="disabled" name="NutilBusca" id="NutilBusca">

						<!-- Filtro personas que marcaron favorita -->
						<label style="margin-left: 10px;" for="NfavoritaBusca">
							N??mero de personas que marcaron favoritas </label> <input type="checkbox"
							disabled="disabled" name="NfavoritaBusca" id="NfavoritaBusca">


					</div></li>


			</ul>
		</div>

		<ul class="navbar-nav ml-auto">
			<c:if test="${empty usuarioElegido}">
				<li class="nav-item"><a href="Inicia_sesion.jsp"
					class="nav-link"> <img style="height: 40px; width: 40px;"
						src="Imagenes/Perfil.png" alt=""> Iniciar sesi??n
				</a></li>
			</c:if>

			<c:if test="${not empty usuarioElegido}">
				<li class="nav-item"><a href="PerfilUsuario?IdUsuario=${usuarioElegido.getId()}" class="nav-link">
						<img style="height: 40px; width: 40px;"
						src="GeneralServlet?Imagen=Usuario&Id=${usuarioElegido.getId()}">
						<c:out value="${usuarioElegido.getNomUsuario()}"></c:out>
				</a></li>
			</c:if>
		</ul>
	</nav>
	<!-- TERMINA BARRA DE NAVEGACI??????N -->


	<!-- CUERPO DE LA P??????GINA -->

	<div class="container main">
		<div class="row">
			<div class="col-sm-9 col-lg-9">
				<!-- Pregunta 1 -->
				<section>
					<div class="container">
						<p style="border-bottom: solid; margin: 0;">
							<a href="PerfilUsuario?IdUsuario=${preguntaElegida.getIdUsuario()}" class="imagen_nombre_usuario">
								<img class="imagen_usu_inicio"
									src="GeneralServlet?Imagen=Usuario&Id=${preguntaElegida.getIdUsuario()}"
									alt="">${preguntaElegida.getNomUsuarioPregunta()}
							</a>
							<c:if test="${preguntaElegida.getEditada() == 1}">
								<p>Editada</p>
							</c:if>
						</p>
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
					<form id="form_borrar_preg" action="PreguntaRespuesta"
						method="post">
						<input name="tipo" type="hidden" value="BorrarPregunta"> <input
							name="IdPregunta" type="hidden"
							value="${preguntaElegida.getId()}">
						<button class="boton_borrar" id="eliminar_pregunta" type="button">
							<img src="Imagenes/eliminar.png" class="imagen_borrar">
						</button>
					</form>
					<form id="form_editar_preg${preguntaElegida.getId()}"
						action="SubirPregunta" method="post" enctype="multipart/form-data">
						<input name="abrirPregunta" type="hidden" value="true"> <input
							name="IdPregunta" type="hidden"
							value="${preguntaElegida.getId()}">
					</form>
					<button form="form_editar_preg${preguntaElegida.getId()}"
						class="boton_editar" id="editar_pregunta">
						<img class="imagen_editar" src="Imagenes/editar.png">
					</button>
				</div>
			</c:if>
		</div>
		<div class="row" style="margin-left: 10px;">
			<div class="col-1">
				<p>
					<c:if test="${usuarioActivo == userType.normalUser}">
						<form id="form_util_pregunta${preguntaElegida.getId()}"
							method="post" action="PreguntaRespuesta">
							<input name="tipo" type="hidden" value="Pregunta"> <input
								name="IdPregunta" type="hidden"
								value="${preguntaElegida.getId()}"> <input name="Vote"
								type="hidden" value="Util">
						</form>
					</c:if>
					<button class="util_noutil_fav_btn"
						form="form_util_pregunta${preguntaElegida.getId()}"
						id="like_pregunta" name="like_pregunta">
						<img class="util_noutil_fav" src="Imagenes/Like.png">
					</button>
					${preguntaElegida.getVotosUtil()}
				</p>
			</div>
			<div class="col-1">
				<p>
					<c:if test="${usuarioActivo == userType.normalUser}">
						<form id="form_noutil_pregunta${preguntaElegida.getId()}"
							method="post" action="PreguntaRespuesta">
							<input name="tipo" type="hidden" value="Pregunta"> <input
								name="IdPregunta" type="hidden"
								value="${preguntaElegida.getId()}"> <input name="Vote"
								type="hidden" value="NoUtil">
						</form>
					</c:if>
					<button class="util_noutil_fav_btn"
						form="form_noutil_pregunta${preguntaElegida.getId()}"
						id="dislike_pregunta" name="dislike_pregunta">
						<img class="util_noutil_fav" src="Imagenes/Dislike.png">
					</button>
					<c:if test="${usuarioActivo == userType.questionOwner}">
						${preguntaElegida.getVotosNoUtil()}
					</c:if>
				</p>
			</div>
			<div class="col-1">
				<p>
					<c:if test="${usuarioActivo == userType.normalUser}">
						<form id="form_fav_pregunta${preguntaElegida.getId()}"
							method="post" action="PreguntaRespuesta">
							<input name="tipo" type="hidden" value="Pregunta"> <input
								name="IdPregunta" type="hidden"
								value="${preguntaElegida.getId()}"> <input name="Vote"
								type="hidden" value="Fav">
						</form>
					</c:if>
					<button class="util_noutil_fav_btn"
						form="form_fav_pregunta${preguntaElegida.getId()}"
						id="fav_pregunta" name="fav_pregunta">
						<img class="util_noutil_fav" src="Imagenes/Favorita.png">
					</button>
					${preguntaElegida.getVotosFavorito()}
				</p>
			</div>
		</div>
	</div>

	<!-- BOTON RESPONDER -->
	<div class="container">
		<div class="row">
			<c:if test="${usuarioActivo == userType.normalUser}">
				<form id="form_responder_pregunta${preguntaElegida.getId()}"
					method="post" action="SubirRespuesta" enctype="multipart/form-data">
					<button class="botones" style="margin-top: 15px; width: 200px;">
						Responder <img style="width: 60px; height: 40px;"
							src="Imagenes/respuesta.png" alt="responder_img">
					</button>
					<input name="IdPregunta" type="hidden"
						value="${preguntaElegida.getId()}"> <input
						name="abrirRespuesta" type="hidden" value="true">
				</form>
			</c:if>
		</div>
	</div>


	<%-- RESPUESTA CORRECTA--%>
	<c:if test="${not empty respuestaCorrecta}">
		<c:if
			test="${IdUsuarioActivo == respuestaCorrecta.getIdUsuario()  && usuarioActivo != userType.Invited}">
			<c:set var="usuarioActivo" value="${userType.answerOwner}"></c:set>
		</c:if>
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
											<a href="PerfilUsuario?IdUsuario=${respuestaCorrecta.getIdUsuario()}" class="imagen_nombre_usuario">
												<img class="imagen_usu_inicio"
													src="GeneralServlet?Imagen=Usuario&Id=${respuestaCorrecta.getIdUsuario()}"
													alt="">${respuestaCorrecta.getNomUsuarioRespuesta()}
											</a>
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
							<c:if test="${usuarioActivo == userType.answerOwner}">
								<div class="col-1">
									<form id="form_borrar_rc" action="PreguntaRespuesta"
										method="post">
										<input name="IdPregunta" type="hidden"
											value="${preguntaElegida.getId()}"> <input
											name="tipo" type="hidden" value="BorrarRespuesta"> <input
											name="IdRespuesta" type="hidden"
											value="${respuestaCorrecta.getId()}">
										<button class="boton_borrar" id="eliminar_respuesta_correcta"
											type="button">
											<img src="Imagenes/eliminar.png" class="imagen_borrar">
										</button>
									</form>
									<form id="form_editar_rc${respuestaCorrecta.getId()}"
										action="SubirRespuesta" method="post"
										enctype="multipart/form-data">
										<input name="abrirRespuesta" type="hidden" value="true">
										<input name="IdPregunta" type="hidden"
											value="${preguntaElegida.getId()}"> <input
											name="IdRespuesta" type="hidden"
											value="${respuestaCorrecta.getId()}">
									</form>
									<button form="form_editar_rc${respuestaCorrecta.getId()}"
										class="boton_editar" id="editar_respuesta">
										<img class="imagen_editar" src="Imagenes/editar.png">
									</button>
								</div>
							</c:if>
						</div>
						<div class="row" style="margin-left: 10px;">
							<div class="col-1">
								<p>
									<c:if
										test="${usuarioActivo == userType.normalUser || usuarioActivo == userType.questionOwner}">
										<form id="form_util_rc${respuestaCorrecta.getId()}"
											method="post" action="PreguntaRespuesta">
											<input name="tipo" type="hidden" value="Respuesta"> <input
												name="IdRespuesta" type="hidden"
												value="${respuestaCorrecta.getId()}"> <input
												name="IdPregunta" type="hidden"
												value="${preguntaElegida.getId()}"> <input
												name="Vote" type="hidden" value="Util">
										</form>
									</c:if>
									<button class="util_noutil_fav_btn"
										form="form_util_rc${respuestaCorrecta.getId()}"
										id="like_respuesta" name="like_respuesta">
										<img class="util_noutil_fav" src="Imagenes/Like.png">
									</button>
									${respuestaCorrecta.getVotosUtil()}
								</p>
							</div>

							<div class="col-1">
								<p>
									<c:if
										test="${usuarioActivo == userType.normalUser || usuarioActivo == userType.questionOwner}">
										<form id="form_noutil_rc${respuestaCorrecta.getId()}"
											method="post" action="PreguntaRespuesta">
											<input name="tipo" type="hidden" value="Respuesta"> <input
												name="IdRespuesta" type="hidden"
												value="${respuestaCorrecta.getId()}"> <input
												name="IdPregunta" type="hidden"
												value="${preguntaElegida.getId()}"> <input
												name="Vote" type="hidden" value="NoUtil">
										</form>
									</c:if>
									<button class="util_noutil_fav_btn"
										form="form_noutil_rc${respuestaCorrecta.getId()}"
										id="dislike_respuesta" name="dislike_respuesta">
										<img class="util_noutil_fav" src="Imagenes/Dislike.png">
									</button>
									<c:if test="${usuarioActivo == userType.answerOwner}">
										${respuestaCorrecta.getVotosNoUtil()}
									</c:if>
								</p>
							</div>

							<div class="col-1">
								<c:if test="${usuarioActivo == userType.questionOwner}">
									<form id="form_res_cor${respuestaCorrecta.getId()}"
										method="post" action="PreguntaRespuesta">
										<input name="IdPregunta" type="hidden"
											value="${preguntaElegida.getId()}"> <input
											name="tipo" type="hidden" value="RespuestaCorrecta">
										<input name="IdRespuesta" type="hidden"
											value="${respuestaCorrecta.getId()}">
										<button class="util_noutil_fav_btn_cor" id="correcta_respuesta"
											name="correcta_respuesta">
											<img class="util_noutil_fav" src="Imagenes/correcta.png">
										</button>
									</form>
								</c:if>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<c:if test="${usuarioActivo == userType.answerOwner}">
			<c:set var="usuarioActivo" value="${userType.normalUser}"></c:set>
		</c:if>
	</c:if>

	<%-- RESPUESTA NORMAL --%>
	<c:forEach var="iRespuesta" items="${lista10Respuestas}">
		<c:if test="${iRespuesta.getId() != preguntaElegida.getIdRespuesta()}">
			<c:if test="${iRespuesta.getActivo() == 1}">
				<c:if test="${IdUsuarioActivo == iRespuesta.getIdUsuario() && usuarioActivo != userType.Invited}">
					<c:set var="usuarioActivo" value="${userType.answerOwner}"></c:set>
				</c:if>
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
													<a href="PerfilUsuario?IdUsuario=${iRespuesta.getIdUsuario()}" class="imagen_nombre_usuario">
														<img class="imagen_usu_inicio"
															src="GeneralServlet?Imagen=Usuario&Id=${iRespuesta.getIdUsuario()}"
															alt="">${iRespuesta.getNomUsuarioRespuesta()}
													</a>
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
									<c:if test="${usuarioActivo == userType.answerOwner}">
										<div class="col-1">
											<form id="form_borrar_res" action="PreguntaRespuesta"
												method="post">
												<input name="IdPregunta" type="hidden"
													value="${preguntaElegida.getId()}"> <input
													name="tipo" type="hidden" value="BorrarRespuesta">
												<input name="IdRespuesta" type="hidden"
													value="${iRespuesta.getId()}">
												<button class="boton_borrar" id="eliminar_respuesta"
													type="button">
													<img src="Imagenes/eliminar.png" class="imagen_borrar">
												</button>
											</form>
											<form id="form_editar_res${iRespuesta.getId()}"
												action="SubirRespuesta" method="post"
												enctype="multipart/form-data">
												<input name="abrirRespuesta" type="hidden" value="true">
												<input name="IdPregunta" type="hidden"
													value="${preguntaElegida.getId()}"> <input
													name="IdRespuesta" type="hidden"
													value="${iRespuesta.getId()}">
											</form>
											<button form="form_editar_res${iRespuesta.getId()}"
												class="boton_editar" id="editar_respuesta">
												<img class="imagen_editar" src="Imagenes/editar.png">
											</button>
										</div>
									</c:if>
								</div>

								<div class="row" style="margin-left: 10px;">
									<div class="col-1">
										<p>
											<c:if
												test="${usuarioActivo == userType.normalUser || usuarioActivo == userType.questionOwner}">
												<form id="form_util_res${iRespuesta.getId()}" method="post"
													action="PreguntaRespuesta">
													<input name="tipo" type="hidden" value="Respuesta">
													<input name="IdRespuesta" type="hidden"
														value="${iRespuesta.getId()}"> <input
														name="IdPregunta" type="hidden"
														value="${preguntaElegida.getId()}"> <input
														name="Vote" type="hidden" value="Util">
												</form>
											</c:if>
											<button class="util_noutil_fav_btn" id="like_respuesta"
												name="like_respuesta">
												<img class="util_noutil_fav" src="Imagenes/Like.png">
											</button>
											${iRespuesta.getVotosUtil()}
										</p>
									</div>

									<div class="col-1">
										<p>
											<c:if
												test="${usuarioActivo == userType.normalUser || usuarioActivo == userType.questionOwner}">
												<form id="form_noutil_res${iRespuesta.getId()}"
													method="post" action="PreguntaRespuesta">
													<input name="tipo" type="hidden" value="Respuesta">
													<input name="IdRespuesta" type="hidden"
														value="${iRespuesta.getId()}"> <input
														name="IdPregunta" type="hidden"
														value="${preguntaElegida.getId()}"> <input
														name="Vote" type="hidden" value="NoUtil">
												</form>
											</c:if>
											<button class="util_noutil_fav_btn" id="dislike_respuesta"
												name="dislike_respuesta">
												<img class="util_noutil_fav" src="Imagenes/Dislike.png">
											</button>
											<c:if test="${usuarioActivo == userType.answerOwner}">
										${iRespuesta.getVotosNoUtil()}
									</c:if>
										</p>
									</div>

									<div class="col-1">
										<c:if test="${usuarioActivo == userType.questionOwner}">
											<form id="form_res_cor${iRespuesta.getId()}" method="post"
												action="PreguntaRespuesta">
												<input name="IdPregunta" type="hidden"
													value="${preguntaElegida.getId()}"> <input
													name="tipo" type="hidden" value="RespuestaCorrecta">
												<input name="IdRespuesta" type="hidden"
													value="${iRespuesta.getId()}">
												<button class="util_noutil_fav_btn_cor" id="correcta_respuesta"
													name="correcta_respuesta">
													<img class="util_noutil_fav" src="Imagenes/correcta.png">
												</button>
											</form>
										</c:if>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<c:if test="${usuarioActivo == userType.answerOwner}">
					<c:set var="usuarioActivo" value="${userType.normalUser}"></c:set>
				</c:if>
			</c:if>
			<c:if test="${iRespuesta.getActivo() == 0}">
				<div class="container">
					<div class="row">
						<div class="col-1"></div>
                			<div class="col-11">
                				<div class="container main_respuesta_normal">
									<p class="text">La respuesta ha sido eliminada</p>
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

<!-- FOOTER DE LA P??????GINA -->
<footer class=" text-lg-start" style="background-color: #f28825;">
	<!-- Grid container -->
	<div class="container p-4">
		<!--Grid row-->
		<div class="row">
			<!--Grid column-->
			<div class="col-lg-6 col-sm-6">
				<h5 class=" text-center" style="font-weight: bolder;">
					<img src="Imagenes/que_.png"
						style="width: 100px; height: 50px; border-radius: 15px;" alt="">
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
			<p class="col-12 text-center">?? 2021 Copyright</p>
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


<!-- TERMINA FOOTER DE LA P??????GINA -->
</html>