<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Estadísticas</title>
<style>
.body {
	font-family: Arial, sans-serif;
	background-color: #79d7e7;
	margin: 0;
	padding: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.contenedor {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
	width: 100%;
	max-width: 1000px;
	box-sizing: border-box;
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

.menu {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-bottom: 20px;
}

.menu a {
	color: #007bff;
	text-decoration: none;
	padding: 10px 15px;
	background-color: white;
	border-radius: 5px;
	transition: background-color 0.3s;
}

.menu a:hover {
	background-color: #f0f0f0;
}

.usuario {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	margin-top: 20px;
	border-top: 1px solid #ccc;
}

.usuario a, .cuenta a {
	color: #007bff;
	text-decoration: none;
}

.usuario a:hover, .cuenta a:hover {
	text-decoration: underline;
}

.btn {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	text-align: center;
}

.btn:hover {
	background-color: #0056b3;
}

input[type="number"], .hidden {
	width: 100%;
	padding: 8px 10px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	display: none;
}

.resultado {
	margin-top: 20px;
	padding: 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	background-color: #f9f9f9;
	color: #333;
	display: none;
	text-align: center;
}
</style>
<script type="text/javascript">
function onRadioChange() {
    var option3 = document.getElementById("option3");
    var dateInput = document.getElementById("dateInput");
    if (option3.checked) {
        dateInput.classList.remove("hidden");
    } else {
        dateInput.classList.add("hidden");
    }
}
</script>
</head>
<body>

	<div class="body">
		<nav>
			<%@include file="Layout/MainLayout.jsp"%>
		</nav>

		<div class="contenedor">
			<h1>Estadísticas</h1>

			<form action="ServletClienteABM" method="post">
				<div class="opciones">
					<label for="option1">
						<input type="radio" id="option1" name="estadistica" value="option1" onchange="onRadioChange()" checked>
						Promedio de saldos entre todas las cuentas
					</label>
					<br>
					<label for="option2">
						<input type="radio" id="option2" name="estadistica" value="option2" onchange="onRadioChange()">
						Porcentaje de cuentas Activas / Inactivas
					</label>
					<br>
					<label for="option3">
						<input type="radio" id="option3" name="estadistica" value="option3" onchange="onRadioChange()">
						Importe monetario movilizado
					</label>
					<br>
					<label for="option4">
						<input type="radio" id="option4" name="estadistica" value="option4" onchange="onRadioChange()">
						Movimientos realizados esta semana
					</label>
				</div>
	
				<div id="dateInput" class="hidden">
				    <label for="fechaDesde">Desde:</label>
				    <input type="date" id="fechaDesde" name="fechaDesde" required>
				    <label for="fechaHasta">Hasta:</label>
				    <input type="date" id="fechaHasta" name="fechaHasta" required>
				</div>
	
				<input type="submit" class="btn" value="Solicitar" name="accion">
			</form>
	
			<div>
				<%
					if(request.getAttribute("promedioSaldos") != null){
						String promedioSaldos = (String) request.getAttribute("promedioSaldos");
				%>
						<label>Promedio de saldos entre todas las cuentas: $<%= promedioSaldos %></label>
				<%
					}
					else if(request.getAttribute("porcentajeCuentasActivas") != null && request.getAttribute("porcentajeCuentasInactivas") != null){
						String porcentajeCuentasActivas = (String) request.getAttribute("porcentajeCuentasActivas");
						String porcentajeCuentasInactivas = (String) request.getAttribute("porcentajeCuentasInactivas");
				%>
						<label>Porcentaje de cuentas Activas: <%= porcentajeCuentasActivas %></label>
						<label>Porcentaje de cuentas Inactivas: <%= porcentajeCuentasInactivas %></label>
				<%
					}
					else if(request.getAttribute("importeMovido") != null){
						String importeMovido = (String) request.getAttribute("importeMovido");
				%>
						<label>Importe monetario movilizado: $<%= importeMovido %></label>
				<%
					}
					else if(request.getAttribute("importeMovidoSemana") != null){
						String importeMovidoSemana = (String) request.getAttribute("importeMovidoSemana");
				%>
						<label>Importe monetario movilizado esta ultima semana: $<%= importeMovidoSemana %></label>
				<%
					}
				%>
				<%
				    if (request.getAttribute("error") != null) {
				        String error = (String) request.getAttribute("error");
				%>
				        <div class="error"><%= error %></div>
				<%
				    }
				%>
			</div>
		</div>
	</div>
</body>
</html>