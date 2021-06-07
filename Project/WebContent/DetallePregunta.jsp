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

userType usuarioActivo = (userType)request.getAttribute("usuarioActivo");
pageContext.setAttribute("usuarioActivo", usuarioActivo);

int IdUsuarioActivo = (int)request.getAttribute("IdUsuarioActivo");
pageContext.setAttribute("IdUsuarioActivo", IdUsuarioActivo);

PreguntaModel preguntaElegida = (PreguntaModel)request.getAttribute("preguntaElegida");
pageContext.setAttribute("preguntaElegida", preguntaElegida);

int numeroPagina = 1;

if (request.getAttribute("numeroPagina") != null){
	numeroPagina = (int)request.getAttribute("numeroPagina");
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
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/ImagenRegistro.js"></script>
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
				<li class="nav-item"><a href="IndexPreguntas" class="nav-link">Inicio</a></li>

				<!-- Dropdown de categorias -->
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" id="Categoriasnavbar"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Categorías </a>
					<div class="dropdown-menu" aria-labelledby="Categoriasnavbar">
						<c:forEach var="iCategoria" items="${listaCategorias}">
							<a class="dropdown-item" href="#"> ${iCategoria.getNombre()} </a>
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
						<label style="margin-left: 10px;" for="NutilBusca">
							Número de personas que les pareció útil </label> <input
							type="checkbox" disabled="disabled" name="NutilBusca"
							id="NutilBusca">

						<!-- Filtro personas que marcaron favorita -->
						<label style="margin-left: 10px;" for="NfavoritaBusca">
							Número de personas que marcaron favoritas </label> <input
							type="checkbox" disabled="disabled" name="NfavoritaBusca"
							id="NfavoritaBusca">


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
						<p style="border-bottom: solid; margin: 0;"><img class="imagen_usu_inicio" 
							src="GeneralServlet?Imagen=Usuario&Id=${preguntaElegida.getIdUsuario()}" 
							alt="">${preguntaElegida.getNomUsuarioPregunta()}</p>

                              <p class="fecha_hora_pregunta"> ${preguntaElegida.getFechaCreacionToString()} </p>

						<p class="pregunta"
							style="margin-bottom: 0; margin-top: 0px; border-bottom: solid;"> ${preguntaElegida.getTitulo()} </p>
							<p id="categoriapreg"> ${preguntaElegida.getCategoriaPregunta()} </p>
						<c:if test="${preguntaElegida.getDescripcion() != ''}">
							<p class="descripcion"> ${preguntaElegida.getDescripcion()} </p>
						</c:if>
					</div>
				</section>
			</div>
			<c:if test="${preguntaElegida.isImagen() != null}">
				<div class="col-sm-2 col-lg-2 text-center">
					<img class="imagenpregunta" src="GeneralServlet?Imagen=Pregunta&Id=${preguntaElegida.getId()}" alt="">
				</div>
			</c:if>
			<c:if test="${usuarioActivo == userType.questionOwner}">
               <div class="col-sm-1 col-lg-1">
                    <button  class="boton_borrar" id="eliminar_pregunta" type="button"><img src="Imagenes/eliminar.png" class="imagen_borrar"> </button>

                    <button class="boton_editar" type="button"><img class="imagen_editar" src="Imagenes/editar.png"> </button>
               </div>
            </c:if>
		</div>
          <div class="row" style="margin-left: 10px;">
               <div class="col-1"> <p><button class="util_noutil_fav_btn"><img class="util_noutil_fav" src="Imagenes/Like.png"> </button> 0</p> </div>
               <div class="col-1"> <p><button class="util_noutil_fav_btn"><img class="util_noutil_fav" src="Imagenes/Dislike.png"> </button> 0</p></div>
               <div class="col-1"> <p><button class="util_noutil_fav_btn"><img class="util_noutil_fav" src="Imagenes/Favorita.png"> </button> 0</p></div>
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
				<h5 class=" text-center">
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
	</div>
	<!-- Grid container -->


</footer>


<!-- TERMINA FOOTER DE LA Pï¿½GINA -->
</html>