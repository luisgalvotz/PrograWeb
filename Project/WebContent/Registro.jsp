<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String usernameEncontrado = "false";
if(request.getAttribute("usernameEncontrado") != null)
	usernameEncontrado = request.getAttribute("usernameEncontrado").toString();
%>
    
<!doctype html>
<html lang="en">
<head>
<title>Registro</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/registro.css">
<link rel="stylesheet" href="css/validacion.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.sweet-modal.min.js"></script>
<script src="js/ImagenRegistro.js"></script>
<script src="js/validaciones.js"></script>
</head>
<body>
	<div class="BarraTopRegistro">
		<a href="IndexPreguntas"> <img align="left"
			style="height: 50px; width: 70px; border-radius: 15px; margin-left: 20px;"
			src="Imagenes/que.png" alt="Logo">
		</a>
		<h1 style="text-align: center; font-size: 30px;">Registro</h1>
	</div>

	<div class="container" style="margin-bottom: auto;">
	<form id="form_registro" action="RegistroUsuario" method="POST" enctype="multipart/form-data">
		<div class="row">
			<div class="col-lg-7 col-sm-12">
				<section class="Formulario_registro ">
					<div class="container">
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Nombre(s):</p>

							</div>
							<div class="col-lg-7 col-md-12">
								<input class="campos" type="text" name="Nombre_" id="Nombre_"
									placeholder="Nombre(s)">

							</div>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Apellidos:</p>

							</div>
							<div class="col-lg-7 col-md-12">
								<input class="campos" type="text" name="Apellidos_Usu"
									id="Apellidos_Usu" placeholder="Apellidos">

							</div>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Fecha de nacimiento:</p>

							</div>
							<div class="col-7">
								<input class="campos" type="date" name="FechaNac_Usu"
									id="FechaNac_Usu">

							</div>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Correo electrónico:</p>

							</div>
							<div class="col-7">
								<input class="campos" type="email" name="Correo_Usu"
									id="Correo_Usu" placeholder="Correo electrónico">

							</div>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Nombre de usuario:</p>

							</div>
							<div class="col-7">
								<input class="campos" type="text" name="Nombre_Usu"
									id="Nombre_Usu" placeholder="Nombre de usuario">

							</div>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Contraseña:</p>

							</div>
							<div class="col-7">
								<input class="campos" type="password" name="Contra_Usu"
									id="Contra_Usu" placeholder="Contraseña">

							</div>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-12">
								<p class="etiquetas">Confirmar contraseña:</p>

							</div>
							<div class="col-7">
								<input class="campos" type="password" name="ContraC_Usu"
									id="ContraC_Usu" placeholder="Contraseña">

							</div>
						</div>


					</div>



					<div class="container">
						<div class="row">
							<div class="col-lg-4 col-sm-4"></div>
							<div class="col-lg-4 col-sm-4">
								<input class="botones" type="submit" value="Aceptar">
							</div>

						</div>
					</div>

					<div class="container">
						<div class="row">
							<div class="col-lg-3 col-sm-1"></div>
							<div class="col-lg-7 col-sm-11">
								<p style="padding-left: 5px;">
									¿Ya tiene cuenta? <a href="Inicia_sesion.jsp">Click aquí</a>
								</p>

							</div>

						</div>
					</div>

				</section>

			</div>

			<div class="col-lg-5 col-sm-12">
				<div class="container seleccionimagen">
					<div class="row">
						<div class="col-12 ">
							<p style="text-align: center; margin-top: 15px;">Seleccione
								la imagen de perfil:</p>

						</div>
					</div>
					<div class="row">
						<div class="col-lg-3 col-sm-4"></div>
						<div class="col-lg-6 col-sm-6">
							<input class="inputdeimagen" type="file" name="Imag_Usu"
								id="Imag_Usu" onchange="readURL(this);"> <img
								id="Imagen_Usu" src="#" alt=""
								style="margin-top: 10px; margin-bottom: 10px;">
						</div>

					</div>
				</div>
			</div>

		</form>
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

<footer class="text-lg-start" style="background-color: #f28825;">
	<div class="container p-4">
		<div class="row">
			<div class="col-lg-6 col-sm-6">
				<h5 class="text-center">
					<img style="width: 70px; height: 50px; border-radius: 15px;"
						src="Imagenes/que.png" alt=""> Queuestions
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