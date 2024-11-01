<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Inicio</title>
<style>
/* Body */
body, html {
	margin: 0;
	padding: 0;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #e0f7fa;
}

/* Contenedor principal */
.main {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.contenedor {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	width: 100%;
	max-width: 600px;
	box-sizing: border-box;
	text-align: center;
}

/* Título */
h1 {
	color: #00796b;
	font-size: 1.8em;
	margin-bottom: 20px;
}

/* Menú de navegación */
.menu {
	display: flex;
	justify-content: center;
	gap: 20px;
	margin-bottom: 20px;
}

.menu a {
	color: #00796b;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s;
}

.menu a:hover {
	color: #004d40;
	text-decoration: underline;
}

/* Info de usuario y cierre de sesión */
.usuario {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

.cerrar-sesion {
	text-align: right;
}

.btn {
	background-color: #00796b;
	color: #ffffff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: #004d40;
}

/* Info de cuenta */
.informacion-cuenta {
	text-align: center;
	margin: 15px 0;
}

.cuenta {
	margin-bottom: 15px;
}

.cuenta a {
	color: #00796b;
	text-decoration: none;
}

.cuenta a:hover {
	color: #004d40;
	text-decoration: underline;
}

.cuenta-extra {
	text-align: center;
	margin: 15px 0;
}

/* Footer */
footer {
	background-color: #e0f7fa;
	padding: 10px;
	text-align: center;
	border-top: 1px solid #ccc;
	color: #555;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<nav>
		<%@include file="Layout/MainLayout.jsp"%>
	</nav>

	<div class="main">
		<div class="contenedor">
			<h1>Inicio</h1>

			<!-- Info de usuario y cierre de sesión -->
			<div class="usuario">
				<a href="#">Nombre Apellido</a>
				<div class="cerrar-sesion">
					<button class="btn">Cerrar sesión</button>
				</div>
			</div>

			<!-- Info de cuentas -->
			<div class="informacion-cuenta">
				<div class="cuenta">
					<a href="#">Caja de ahorro</a> <span>$ Saldo</span>
				</div>
				<div class="cuenta">
					<a href="#">Cuenta corriente</a> <span>$ Saldo</span>
				</div>
				<div class="cuenta-extra">
					<a href="#">Solicitar caja de ahorro</a>
				</div>
				<div class="cuenta-extra">
					<a href="#">Solicitar cuenta corriente</a>
				</div>
			</div>
		</div>
	</div>

	<footer> © 2024, Aplicación de Banco </footer>
</body>
</html>