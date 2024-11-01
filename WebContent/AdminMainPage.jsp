<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Inicio Admin</title>
<style>
/* Body */
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #e0f7fa;
	margin: 0;
	padding: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

/* Contenedor principal */
.contenedor {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	width: 100%;
	max-width: 1000px;
	box-sizing: border-box;
}

/* Título */
h1 {
	text-align: center;
	color: #00796b;
	font-size: 2em;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<header>
		<%@include file="Layout/MainLayout.jsp"%>
	</header>
	
	<main>
	<div class="contenedor">
		<h1>Inicio Administrador</h1>
	</div>
	
	</main>
	<footer> </footer>
</body>
</html>