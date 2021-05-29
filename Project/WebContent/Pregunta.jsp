<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<title>A�adir Pregunta</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/pregunta.css">
</head>

<body>

	<!-- BARRA DE NAVEGACI�N -->
	<nav class="navbar navbar-expand-md navbar-light">

		<ul class="navbar-nav mr-auto">

			<img class="logopag " src="Imagenes/que.png" alt="Logo">

		</ul>

		<!-- Boton que aparece cuando colapsas la navbar en tama�o md es la "palanca" (toggle) que expande los elementos en el div con id:navbarmenu -->
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarmenu" aria-controls="navbarmenu"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Opciones de en medio -->
		<div class="collapse navbar-collapse" id="navbarmenu">
			<ul class="navbar-nav mx-auto">

				<li class="nav-item"><a href="Inicio_.jsp" class="nav-link">Inicio</a>
				</li>

				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" id="Categoriasnavbar"
					data-toggle="dropdown">Categor�a</a>
					<div class="dropdown-menu" aria-labelledby="Categoriasnavbar">
						<a href="#" class="dropdown-item">Todas</a> <a href="#"
							class="dropdown-item">Comida</a> <a href="#"
							class="dropdown-item">Deportes</a> <a href="#"
							class="dropdown-item">Juegos</a> <a href="#"
							class="dropdown-item">Amor</a> <a href="#" class="dropdown-item">Trabajo</a>
					</div></li>


				<!-- Busqueda dropdown -->
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" id="Busquedanavbar"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">B�squeda</a>
					<!-- dropdown del link B�squeda -->
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
							<label for="categoriasbusca">Categor�a:</label> <select
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

						<!-- Filtro personas que marcaron �til -->
						<label style="margin-left: 10px;" for="NutilBusca"> N�mero
							de personas que les pareci� �til </label> <input type="checkbox"
							disabled="disabled" name="NutilBusca" id="NutilBusca">

						<!-- Filtro personas que marcaron favorita -->
						<label style="margin-left: 10px;" for="NfavoritaBusca">
							N�mero de personas que marcaron favoritas </label> <input type="checkbox"
							disabled="disabled" name="NfavoritaBusca" id="NfavoritaBusca">
					</div></li>

				<li class="nav-item"><a class="nav-link" href="Pregunta.jsp">A�adir
						Pregunta</a></li>
			</ul>
		</div>

		<!-- Inicio sesi�n (der) -->
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a href="Inicia_sesion.jsp"
				class="nav-link"> <img style="width: 40px; height: 40px"
					src="Imagenes/Perfil.png" alt=""> Iniciar sesi�n
			</a></li>
		</ul>

	</nav>

	<p class="tituloa�adir">Haz tu pregunta</p>


	<!-- FORMULARIO DE PREGUNTA -->
	<section class="formulario_pregunta">
		<div class="container">
			<div class="row">
				<div class="col-7">
					<input class="PreguntaS" id="Pregunta" type="text"
						placeholder="Escribe aqu� tu pregunta">

					<form class="CategoriaS" action="">
						<label for="SelCategoria">Categor�a:</label> <select
							name="SelCategoria" id="SelCategoria">
							<option value="Comida">Comida</option>
							<option value="Deportes">Deportes</option>
							<option value="Juegos">Juegos</option>
							<option value="Amor">Amor</option>
							<option value="Trabajo">Trabajo</option>
						</select>
					</form>

					<textarea class="DescripcionS" rows="5" placeholder="Descripci�n"
						name="" id=""></textarea>

				</div>
				<div class="col-lg-2 col-sm-0"></div>
				<div class="col-lg-2 col-sm-3">
					<!-- <input type="image" src="" alt=""> -->

					<input class="Seleccionimagen" type='file'
						onchange="readURL(this);" /> <img id="Imagenseleccionada" src="#"
						alt="" />
				</div>

			</div>
		</div>


	</section>


	<div style="margin-top: 30px;"></div>




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
	<script src="js/inicio_.js"></script>
	<script src="js/SelecImg.js"></script>

</body>

<footer class="text-lg-start" style="background-color: #f28825;">
	<div class="container p-4">
		<div class="row">
			<div class="col-lg-6 col-sm-6">
				<h5 class="text-center">
					<img src="Imagenes/que.png" class="logopag" alt="">
					Queuestions
				</h5>
				<p class="text-center">Foro para conversar sobre temas variados.</p>

			</div>
			<div class="col-lg-6 col-sm-6 text-center">
				<h5 class="mb-0">Estamos en:</h5>
				<ul class="list-unstyled">
					<a target="_blank"
						href="https://www.facebook.com/Queuestions-101688152055852"
						class="text-dark">Facebook</a>

				</ul>
			</div>

		</div>

		<div class="row">
			<p class="col-12 text-center">� 2021 Copyright</p>
		</div>
	</div>
</footer>
</html>