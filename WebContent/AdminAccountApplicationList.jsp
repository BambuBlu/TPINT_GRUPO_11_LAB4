<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="integrador.model.SolicitudDeAlta"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de cuentas</title>
<style>
/* Body */
.body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #79d7e7;
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
    border-radius: 10px;
    padding: 30px;
    box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
    width: 80%;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
}

/* Título */
h1 {
    text-align: center;
    color: #00796b;
    font-size: 1.8em;
    margin-bottom: 20px;
}

/* Menú */
.menu {
    display: flex;
    justify-content: center;
    gap: 15px;
    margin-bottom: 20px;
}

.menu a {
    color: #00796b;
    text-decoration: none;
    padding: 10px 15px;
    background-color: white;
    border-radius: 5px;
    transition: background-color 0.3s;
}

.menu a:hover {
    background-color: #f0f0f0;
}

/* Tabla */
.table-container {
    border-radius: 10px;
    border: 4px solid #2196F3;
    width: 100%;
    overflow-x: auto;
    background-color: #595C5F;
}

.tabla {
    width: 100%;
    table-layout: fixed;
    background-color: #595C5F;
    color: white;
    font-size: 15px;
    border-collapse: collapse;
}

.tabla th,
.tabla td {
    text-align: center;
    vertical-align: middle;
    padding: 10px;
    word-wrap: break-word;
    white-space: normal;
}

.tabla th {
    font-weight: bold;
    background-color: #00796b;
    color: white;
}

.tabla tr:nth-child(even) {
    background-color: #4f4f4f;
}

.tabla tr:nth-child(odd) {
    background-color: #595C5F;
}

/* Botones */
.btn {
    background-color: #00796b;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
}

.btn:hover {
    background-color: #004d40;
}

.btn-danger {
    background-color: #dc3545;
}

.btn-success {
    background-color: #28a745;
}

.btn-warning {
    background-color: #ffc107;
    color: #212529;
}
</style>
</head>
<body>
	<div class="body">
		<%@include file="Layout/MainLayout.jsp"%>
		<div class="contenedor">
            <h1>Solicitudes de Alta de Cuentas</h1>
			<div class="table-container">
				<table class="tabla">
					<thead>
						<tr class="text-center fw-bolder fs-5">
							<th>ID</th>
							<th>DNI</th>
							<th>Tipo de cuenta</th>
							<th>Estado</th>
						</tr>
					</thead>
					<tbody>
					<% 
						List<SolicitudDeAlta> solicitudes = (List<SolicitudDeAlta>) request.getAttribute("solicitudes");
						for (SolicitudDeAlta solicitud : solicitudes) {
					%>
							<tr>
								<td><%= solicitud.getId() %></td>
								<td><%= solicitud.getCliente().getDni() %></td> 
								<td><%= solicitud.getTipoCuenta().getDescripcion() %></td>
								<td><%= solicitud.getEstado() %></td>
								<td>
							        <form onsubmit="return confirm('¿Estás seguro de ejecutar esta accion?');" method="post" action="ServletCuentaABM">
							            <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>" />
							            <input type="hidden" name="accion" value="altaCuenta" />
							            <button type="submit" class="btn btn-success shadow-lg fw-bolder">Habilitar</button>
							        </form>
								</td>
							</tr>
						
			            <% } %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>