<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>

<%
String usuarioIncorrecto = (String) request.getAttribute("usuarioIncorrecto");
pageContext.setAttribute("usuarioIncorrecto", usuarioIncorrecto);
%>

<!doctype html>
<html lang="en">
<head>
<title>Inicio de sesión</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/iniciosesion.css">
<link rel="stylesheet" href="css/validacion.css">
<link rel="stylesheet" href="css/jquery.sweet-modal.min.css">

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/validaciones.js"></script>

</head>

<body>


	<div
		style="padding-top: 15px; padding-bottom: 15px; width: 100%; background: #e0740e;">
		<a href="Inicio_.jsp"> <img align="left"
			style="height: 50px; width: 70px; border-radius: 15px; margin-left: 20px;"
			src="Imagenes/que.png" alt="Logo">
		</a>
		<h1 style="text-align: center; font-size: 30px;">Iniciar sesión</h1>
	</div>

	<section class="Formulario_Inicio_sesion">
		<form id="form_inicio_sesion" action="./Login" method="post"> 
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-12">
					<p>Nombre de usuario</p>
				</div>
				<div class="col-lg-7 col-md-12">
					<input class="campos" type="text" name="Nombre_Usu"
						id="Nombre_Usu" placeholder="Nombre de usuario">
				</div>
			</div>
			<div class="row">
				<div class="col-lg-5 col-md-12">
					<p>Contraseña</p>
				</div>
				<div class="col-lg-7 col-md-12">
					<input class="campos" type="password" name="Contra_Usu"
						id="Contra_Usu" placeholder="Contraseña">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-4"></div>
				<div class="col-4">
					<button id="idIngresarUsuario" form="form_inicio_sesion" class="botones" type="submit">Ingresar</button>
					<%-- <% if(usuarioEncontrado != null){ %>
					<% } %> --%>

					<c:if test="${usuarioIncorrecto != null}"> 
					<label for="idIngresarUsuario" class="error">No se encontró el usuario ingresado</label>
					</c:if> 
				</div>
			</div>

		</div>
		
		</form>

	</section>

	<div class="container" style="margin-bottom: auto;">

		<div class="row">

			<div class="col-12 text-center">
				<p>
					¿Aún no tienes cuenta? <a href="Registro.jsp">Regístrate</a>
				</p>
			</div>
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
</body>

<!-- FOOTER -->
<footer class="text-lg-start" style="background-color: #f28825;">
	<div class="container p-4">
		<div class="row">
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
			<div class="col-lg-6 col-sm-6 text-center">
				<h5 class=" mb-0">Estamos en:</h5>

				<ul class="list-unstyled">
					<li><a target="_blank"
						href="https://www.facebook.com/Queuestions-101688152055852"
						class="text-dark">Facebook</a></li>


				</ul>
			</div>

		</div>

		<div class="row">
			<p class="col-12 text-center">© 2021 Copyright</p>
		</div>
	</div>

</footer>
</html>