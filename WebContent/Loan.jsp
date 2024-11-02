<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Solicitud de Prestamo</title>
	<style>
		/* Estilos del cuerpo y contenedor */
		body, html {
			margin: 0;
			padding: 0;
			font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
			background-color: #e0f7fa;
		}
		
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
		
		/* Estilo del título */
		h1 {
			color: #00796b;
			font-size: 1.8em;
			margin-bottom: 20px;
		}
		
		/* Estilos del formulario */
		.formulario-prestamo {
			display: flex;
			flex-direction: column;
			gap: 15px;
			margin-top: 20px;
		}

		label {
			font-weight: bold;
			color: #00796b;
			text-align: left;
		}
		
		input[type="number"], select {
			padding: 10px;
			border: 1px solid #ccc;
			border-radius: 5px;
			width: 100%;
			box-sizing: border-box;
		}
		
		.btn-submit {
			background-color: #00796b;
			color: #ffffff;
			border: none;
			padding: 10px 20px;
			border-radius: 5px;
			cursor: pointer;
			transition: background-color 0.3s;
		}
		
		.btn-submit:hover {
			background-color: #004d40;
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
			<h1>Solicitud de Préstamo</h1>

			<!-- Formulario de préstamo -->
			<form class="formulario-prestamo">
				<label for="monto">Monto del Préstamo</label>
				<input type="number" id="monto" name="monto" placeholder="Ingrese el monto" min="1000" step="100" required>

				<label for="cuotas">Número de Cuotas</label>
				<select id="cuotas" name="cuotas" required>
					<option value="6">6 Cuotas</option>
					<option value="12">12 Cuotas</option>
					<option value="18">18 Cuotas</option>
					<option value="24">24 Cuotas</option>
					<option value="36">36 Cuotas</option>
				</select>

				<button type="submit" class="btn-submit">Solicitar Préstamo</button>
			</form>
		</div>
	</div>

	<footer> © 2024, Aplicación de Banco </footer>

</body>
</html>