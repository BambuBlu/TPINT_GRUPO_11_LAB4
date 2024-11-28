<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<%@ page import="integrador.model.Cuenta"%>
	<%@ page import="java.util.ArrayList" %>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Solicitud de Préstamos</title>
	<style>
		/* Estilos generales del cuerpo y contenedor */
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
			background-color: #79d7e7;
		}
		
		.contenedor {
			background-color: #ffffff;
			padding: 30px;
			border-radius: 10px;
			box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
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
			width: 100%;
		}

		label {
			font-weight: bold;
			color: #00796b;
			text-align: left;
		}
		
		input[type="text"], input[type="number"], select {
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

		/* Menú superior */
		.usuario {
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 10px;
			margin-top: 20px;
			border-top: 1px solid #ccc;
		}
		
		.usuario a, .usuario form {
			color: #007bff;
			text-decoration: none;
		}
		
		.usuario form {
			margin: 0;
		}
		
		.usuario a:hover {
			text-decoration: underline;
		}
	</style>
</head>
<body>
	<nav>
		<%@include file="Layout/MainLayout.jsp"%>
	</nav>

	<div class="main">
		<div class="contenedor">
			<h1>Solicitud de Préstamos</h1>
			
			<% ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("ListCuentasActivas"); %>
			<!-- Formulario de solicitud de préstamo -->
			<form action="ServletCuentaABM" method="post" class="formulario-prestamo">
				<label for="txtMontoSolicitado">Monto del Préstamo</label>
				<input type="text" id="txtMontoSolicitado" name="txtMontoSolicitado" placeholder="Ingrese el monto" required> 

				<label for="txtCantidadDeCuotas">Cantidad de Cuotas</label>
				<select id="txtCantidadDeCuotas" name="txtCantidadDeCuotas" required>
					<option value="">Seleccione cantidad de cuotas</option>                
					<option value="3">3 Cuotas</option>
					<option value="6">6 Cuotas</option>
					<option value="12">12 Cuotas</option>    
				</select>
				
				<label for="cuentaDestino">Seleccionar cuenta de destino:</label>
            	<select id="cuentaDestino" name="txtCuentaDestino">
                <% for (Cuenta cuenta : cuentasCliente) { %>
                    <option value="<%= cuenta.getCbu() %>">
                        <%= cuenta.getTipoCuenta().getDescripcion() %> - CBU: <%= cuenta.getCbu() %> - Numero de Cuenta: <%= cuenta.getNumeroDeCuenta() %>
                    </option>
                <% } %>
            	</select>
				 
				<button type="submit" name="accion" value="ConfirmarPrestamo" class="btn-submit">Solicitar Préstamo</button>   
			</form>
		</div>
	</div>

	<footer>© 2024, Aplicación de Banco</footer>
</body>
</html>