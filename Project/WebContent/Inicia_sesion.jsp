<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<title>Inicio de sesi�n</title>
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
</head>
<body>


	<div
		style="padding-top: 15px; padding-bottom: 15px; width: 100%; background: #e0740e;">
		<a href="Inicio_.jsp"> <img align="left"
			style="height: 50px; width: 70px; border-radius: 15px; margin-left: 20px;"
			src="Imagenes/que.png" alt="Logo">
		</a>
		<h1 style="text-align: center; font-size: 30px;">Iniciar sesi�n</h1>
	</div>

	<section class="Formulario_Inicio_sesion">
		<form id=form_inicio_sesion action="" method="post"> 
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-12">
					<p>Nombre de usuario</p>
				</div>
				<div class="col-lg-7 col-md-12">
					<input class="campos" type="text" name="Nombre_Usuario"
						id="Nombre_Usu" placeholder="Nombre de usuario">
				</div>
			</div>
			<div class="row">
				<div class="col-lg-5 col-md-12">
					<p>Contrase�a</p>
				</div>
				<div class="col-lg-7 col-md-12">
					<input class="campos" type="password" name="Contra_Usuario"
						id="Contra_Usu" placeholder="Contrase�a">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-4"></div>
				<div class="col-4">
					<input onclick="window.location='Perfil.jsp'" class="botones"
						type="submit" value="Ingresar">
				</div>
			</div>

		</div>
		
		</form>

	</section>

	<div class="container" style="margin-bottom: auto;">

		<div class="row">

			<div class="col-12 text-center">
				<p>
					�A�n no tienes cuenta? <a href="Registro.jsp">Reg�strate</a>
				</p>
			</div>
		</div>
	</div>





	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
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
			<p class="col-12 text-center">� 2021 Copyright</p>
		</div>
	</div>

</footer>
</html>