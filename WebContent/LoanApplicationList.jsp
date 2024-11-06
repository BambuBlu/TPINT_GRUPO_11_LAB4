<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de solicitud de prestamos</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
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

.contenedor {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
	width: 1700px;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	align-items: center;
}

h1 {
	text-align: center;
	color: #00796b;
	font-size: 1.8em;
	margin-bottom: 20px;
}

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

.usuario {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	margin-top: 20px;
	border-top: 1px solid #ccc;
	width: 100%;
}

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

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.table-container {
	border-radius: 10px;
	border: 4px solid #2196F3;
	width: 100%;
	height: 100%;
	overflow: hidden;
	background-color: #595C5F;
}

.tabla {
	width: 100%;
	background-color: #595C5F;
	color: white;
	font-size: 15px;
	border-collapse: collapse;
	height: inherit;
}

tr {
	background-color: #595C5F;
}
</style>
</head>
<body>

	<div class="body">
		<nav> <%@include file="Layout/MainLayout.jsp"%></nav>

		<div class="contenedor">
			<h1>Listado de solicitud de prestamos</h1>

			<div class="table-container ">
				<table id="table_id" class="tabla">
					<thead>
						<tr>
							<th>Id</th>
							<th>Dni cliente</th>
							<th>Cbu</th>
							<th>Fecha</th>
							<th>Importe con intereses</th>
							<th>Importe pedido</th>
							<th>Plazo de pago en meses</th>
							<th>Monto mensual</th>
							<th>Estado</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- Ejemplo de filas sin datos funcionales -->
						<tr>
							<td>1</td>
							<td>12345678</td>
							<td>123456789012345678</td>
							<td>2024-10-29</td>
							<td>$10,000</td>
							<td>$8,000</td>
							<td>12</td>
							<td>$833.33</td>
							<td>Pendiente</td>
							<td><button class="btn btn-success fw-semibold">Aprobar</button></td>
							<td><button class="btn btn-danger fw-semibold">Rechazar</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</body>
</html>