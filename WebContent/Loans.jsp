<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.ArrayList"%>
	<%@ page import="integrador.model.Cuenta, integrador.model.Prestamo"%>
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
<title>Préstamos</title>
<style>
/* Body */
.body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #79d7e7;
	margin: 0;
	padding: 0;
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
	box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
	width: 100%;
	max-width: 600px;
	box-sizing: border-box;
}

/* Títulos */
h1, h2 {
	text-align: center;
	color: #00796b;
	margin-bottom: 20px;
}

/* Menú */
.menu {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-bottom: 20px;
}

/* Enlaces del menú */
.menu a {
	color: #00796b;
	text-decoration: none;
	padding: 10px 15px;
	border-radius: 6px;
	transition: background-color 0.3s;
	background-color: #b2dfdb;
}

.menu a:hover {
	background-color: #80cbc4;
}

/* Usuario */
.usuario {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	margin-top: 20px;
	border-top: 1px solid #ccc;
}

.usuario a {
	color: #00796b;
	text-decoration: none;
}

.usuario a:hover {
	text-decoration: underline;
}

.usuario form {
	margin: 0;
}

/* Botón */
.btn {
	background-color: #00796b;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 6px;
	cursor: pointer;
	font-size: 1em;
	text-align: center;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: #004d40;
}

/* Campos de formulario */
.form-group {
	margin-bottom: 25px;
}

/* Campos de selección */
.form-control {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 2px solid #b2dfdb;
	border-radius: 6px;
	box-sizing: border-box;
	transition: border-color 0.3s;
	font-size: 1em;
}

.form-control:focus {
	border-color: #00796b;
	outline: none;
}
</style>
</head>
<body>

	<div class="body">
		<nav>
			<%@include file="Layout/MainLayout.jsp"%>
		</nav>

		<div class="contenedor">
			<h1>Préstamos</h1>

			<h2>Pagar Cuota Préstamos</h2>
				<div class="menu">
				<% 
	            ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("ListCuentasActivas");
	            ArrayList<Prestamo> prestamosCliente = (ArrayList<Prestamo>) request.getAttribute("prestamosActivos");
	            %>
	            
	            
	             <!-- Mostrar mensaje de error si existe -->
	            <c:if test="${not empty requestScope.mensajeError}">
	                <p style="color: red;">${requestScope.mensajeError}</p>
	            </c:if>
				<form action="ServletCuentaABM" method="post">
                <div class="form-group">
                    <label for="txtPrestamosActivo">Seleccione el prestamo a pagar</label>
                    <select id="txtPrestamosActivo" name="txtPrestamosActivo" class="form-control">
                        <% for (Prestamo prestamo : prestamosCliente) { %>
                            <option value="<%= prestamo.getId() %>">                    
                                $<%= prestamo.getImporteConIntereses() %> - CUOTAS RESTANTES: <%= prestamo.getCuotasPagadas() %> - VALOR DE CUOTA: $ <%= prestamo.getMontoMensual() %>
                            </option>
                        <% } %>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="cuentaADebitar">Seleccionar cuenta a debitar:</label>
                    <select id="cuentaADebitar" name="txtcuentaADebitar" class="form-control">
                        <% for (Cuenta cuenta : cuentasCliente) { %>
                            <option value="<%= cuenta.getCbu() %>">
                                <%= cuenta.getTipoCuenta().getDescripcion() %> - CBU: <%= cuenta.getCbu() %> - Numero de Cuenta: <%= cuenta.getNumeroDeCuenta() %> - SALDO: $ <%= cuenta.getSaldo() %>
                            </option>
                        <% } %>
                    </select>
                </div>
                                                           
                <div class="form-group">
                    <!-- Boton de submit para transferir -->
                    <input type="submit" name="accion" value="PagarCuota" class="btn btn-primary">   
                </div>
            </form>
			</div>
		</div>
	</div>
</body>
</html>