<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.dbconnection.models.*"%>
<%@page import="java.util.*"%>
<%@page import="com.dbconnection.controllers.GeneralServlet"%>

<%
UsuarioModel usuarioElegido = GeneralServlet.getUsuario(request, response); //el usuario que hizo login
pageContext.setAttribute("usuarioElegido", usuarioElegido);

List<CategoriaModel> listaCategorias = GeneralServlet.getCategorias();
pageContext.setAttribute("listaCategorias", listaCategorias);
%>

<!doctype html>
<html lang="en">
<head>
<title>Perfil de Usuario</title>
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

<link rel="stylesheet" href="css/perfil.css">
<link rel="stylesheet" href="css/validacion.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/validaciones.js"></script>

</head>
<body>

	<!-- BARRA DE NAVEGACIÓN -->

	<nav class="navbar navbar-expand-md  navbar-light">
		<ul class="navbar-nav mr-auto">
			<img class="logopag" src="Imagenes/que.png" alt="Logo">
		</ul>

		<!-- Boton que aparece cuando colapsas la navbar en tamaño md es la "palanca" (toggle) que expande los elementos en el div con id:navbarmenu -->
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarmenu" aria-controls="navbarmenu"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!--navbarmenu: div que es colapsable y dentro tiene un navegador que se ajusto al centro con mx-auto  -->
		<div class="collapse navbar-collapse" id="navbarmenu">
			<ul class="navbar-nav mx-auto ">

				<!-- Boton de inicio -->
				<li class="nav-item"><a href="IndexPreguntas?numeroPagina=1" class="nav-link">Inicio</a></li>

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
					<!-- dropdown del link Búsqueda -->
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
									id="ConfirmaBusquedaA"> <label for="ConfirmaBusquedaA">Busqueda
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

						<!-- Filtro personas que marcaron útil -->
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
	<!-- TERMINA BARRA DE NAVEGACIÓN -->


	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-lg-2 col-md-3 col-sm-3 imagendeperfil">
				<img src="GeneralServlet?Imagen=Usuario&Id=${usuarioElegido.getId()}" style="width: 80px; height: 80px;" alt="">
			</div>
			<div class="col-lg-5 col-md-5 col-sm-5 info_perfil">
				<p style="margin-top: 8px;" class="mr-auto"> <c:out value="${usuarioElegido.getNomUsuario()}"></c:out> </p>
			</div>
			<div class="col-lg-5 col-md-4 col-sm-4 info_perfil">
				<div style="text-align: right; padding-bottom: 8px;">
					<button style="margin-top: 8px;" class="botones_perfil">
						<img style="width: 20px; height: 20px;" src="Imagenes/Ver.png"
							alt="OjoVer">
					</button>
					<br>
					<button style="margin-top: 8px;" class="botones_perfil">
						<a href="Login"><img style="width: 20px; height: 20px;"
							src="Imagenes/Salir.png" alt="PuertaSalir"></a>
					</button>
				</div>
			</div>
		</div>

		<div class="row " style="margin-top: 10px;">
			<div style="text-align: center;" class="col-lg-2 col-sm-2 opc_perfil">
				<a href="#" class="opc" id="preg_barra">Preguntas</a>
                    <label for="preg_barra">0</label>
			</div>

			<div style="text-align: center;" class="col-lg-3 col-sm-3 opc_perfil">
				<a href="#" class="opc" id="resp_barra">Respuestas</a>
                    <label for="resp_barra">0</label>
			</div>

			<div style="text-align: center;" class="col-lg-2 col-sm-2 opc_perfil">
				<a href="#" class="opc" id="util_barra">Útiles</a>
                    <label for="util_barra">0</label>
			</div>

               <div style="text-align: center;" class="col-lg-3 col-sm-3 opc_perfil">
                    <a href="#" class="opc" id="noutil_barra">No útiles</a>
                    <label for="noutil_barra">0</label>
               </div>

			<div style="text-align: center;" class="col-lg-2 col-sm-2 opc_perfil">
				<a href="#" class="opc" id="fav_barra">Favoritos</a>
                    <label for="fav_barra">0</label>
			</div>

		</div>
	</div>



	<section class="formulario_perfil">
		<form id="form_editar_perfil" action="" method="POST"> 
		<div class="container">
			<div class="row">
				<div class="col-lg-1 col-md-0"></div>
				<div class="col-lg-6 col-md-5 col-sm-5">
					<!-- nombres -->
					<div class="row">
						<div class="col-lg-3">
							<p>Nombre(s)</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="text" name="Nombres" id="Nombres"
								placeholder="Nombre(s)" value="${usuarioElegido.getNombre()}">
						</div>
					</div>
					<!-- apellidos -->
					<div class="row">
						<div class="col-lg-3 ">
							<p>Apellidos</p>
						</div>
						<div class="col-lg-3 ">
							<input class="campos" type="text" name="Apellidos" id="Apellidos"
								placeholder="Apellidos" value="${usuarioElegido.getApellidos()}">
						</div>
					</div>
					<!-- fecha nacimiento -->
					<div class="row">
						<div class="col-lg-3">
							<p>Fecha de nacimiento</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="date" name="FechaNac" id="FechaNac"
								value="${usuarioElegido.getFechaNac()}">

						</div>
					</div>
					<!-- edad -->
                         <div class="row">
                              <div class="col-lg-3">
							<p>Edad</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="text" name="EdadUsu" id="EdadUsu"
								placeholder="Edad" value="${usuarioElegido.getEdad()}">
						</div>
                         </div>
					<!-- correo -->
					<div class="row">
						<div class="col-lg-3">
							<p>Correo electrónico</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="text" name="Correo" id="Correo"
								placeholder="Correo electrónico" value="${usuarioElegido.getCorreo()}">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<p>Nombre de usuario</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="text" name="NombreUs" id="NombreUs"
								placeholder="Nombre de usuario" value="${usuarioElegido.getNomUsuario()}">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<p>Contraseña</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="password" name="Contraseña"
								id="Contraseña" placeholder="********">
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<p>Confirmar contraseña</p>
						</div>
						<div class="col-lg-3">
							<input class="campos" type="password" name="ConfirmarCon"
								id="ConfirmarCon" placeholder="********">
						</div>
					</div>

				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div class="row">

						<div style="text-align: center;" class="col-lg-12">
							<p>Seleccione la imagen de perfil:</p>
							<div style="text-align: justify;" class="col-lg-7">
								<input type="file" onchange="readURL(this);" name="ImagenUs" id="ImagenUs">
								<img id="Imagenseleccionada" src="#"
						alt="" />
							</div>
						</div>
					</div>

				</div>

			</div>
			<br>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 text-center">
					<input class="botones" type="submit" value="Confirmar cambios">
				</div>
			</div>
		</div>
		</form>
	</section>

	<!-- PREGUNTA 1 -->
	<c:forEach var="iPregunta" items="${lista10Preguntas}">
		<div class="pregunta_inicio">
			<div class="container main_pregunta_perfil text-center">
				<div class="row">
					<div class="col-12">
						<!-- Pregunta 1 -->
						<section>
							<div class="container">
								<a href="Perfil.jsp" class="imagen_nombre_usuario"> 
								<p style="border-bottom: solid; margin: 0;">
									<img class="imagen_usu_inicio" src="GeneralServlet?Imagen=Usuario&Id=${iPregunta.getIdUsuario()}"
										alt="">${iPregunta.getNomUsuarioPregunta()}
								</p>
								</a>

								<a href="PreguntaRespuesta?IdPregunta=${iPregunta.getId()}&numeroPagina=1" class="titulo_pregunta"> 
								<p class="pregunta"
									style="margin-bottom: 0; border-bottom: solid;">${iPregunta.getTitulo()}</p>
								</a>
							</div>
						</section>
					</div>

				</div>
			</div>
		</div>
	</c:forEach>

	<!-- RESPUESTA -->
	<div class="respuesta_perfil">
			<div class="container main_respuesta_perfil text-center">
				<div class="row">
					<div class="col-12">
						<!-- Pregunta 1 -->
						<section>
							<div class="container">
								<a href="Perfil.jsp" class="imagen_nombre_usuario"> 
								<p style="border-bottom: solid; margin: 0;">
									<img class="imagen_usu_inicio" src="GeneralServlet?Imagen=Usuario&Id=${iPregunta.getIdUsuario()}"
										alt="">${iPregunta.getNomUsuarioPregunta()}
								</p>
								</a>

								<a href="PreguntaRespuesta?IdPregunta=${iPregunta.getId()}&numeroPagina=1" class="titulo_respuesta"> 
								<p class="respuesta"
									style="margin-bottom: 0; border-bottom: solid;">${iPregunta.getTitulo()}</p>
								</a>
							</div>
						</section>
					</div>

				</div>
			</div>
	</div>

	<!-- CONTAINER PARA LA PAGINACION -->
	<div class="container paginacion_inicio">
		<div class="row">
			<div class="col-5"></div>
			<div class="col-5">
				<!-- PAGINACION -->
				<div class="container-fluid">
					<br> <br>
					<nav>
						<ul class="pagination ">
							<li class="page-item disabled"><a class="page-link" href="#"><img
									class="paginacionimg" src="Imagenes/pagina_anterior.png" alt=""></a></li>
							<li class="page-item active"><a class="page-link"
								id="numeropagina" href="">1</a></li>
							<li class="page-item"><a class="page-link" id="adelante"
								href="#"><img class="paginacionimg"
									src="Imagenes/pagina_siguiente.png" alt=""></a></li>

						</ul>
					</nav>
				</div>
			</div>
			<div class="col-2"></div>
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
	<script src="js/SelecImg.js"></script>
	
</body>


<!-- FOOTER DE LA PÁGINA -->
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
               <p class="col-4" id="Luis">Luis Alejandro Galvan Ortiz <label for="Luis">1813703</label></p>
               <p class="col-4" id="Miguel">Miguel Angel Villanueva Infante <label for="Miguel">1841237</label></p>

          </div>
	</div>
	<!-- Grid container -->


</footer>
<!-- TERMINA FOOTER DE LA PÁGINA -->
</html>