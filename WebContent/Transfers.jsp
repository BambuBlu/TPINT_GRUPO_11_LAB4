<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="integrador.model.Cuenta, integrador.model.Cliente"%>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencias</title>
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
<style>
/* Estilos generales */
.body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #79d7e7;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
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
h1 {
	text-align: center;
	color: #00796b;
	font-size: 1.8em;
	margin-bottom: 20px;
}

h2, h3 {
	color: #004d40;
	margin-top: 20px;
}

/* Botón */
.btn {
	background-color: #00796b;
	color: white;
	border: none;
	padding: 12px 20px;
	border-radius: 6px;
	cursor: pointer;
	text-align: center;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: #004d40;
}

/* Formularios y campos de entrada */
form {
	display: flex;
	flex-direction: column;
}

label {
	margin-top: 10px;
	font-weight: bold;
}

input[type="text"],
input[type="number"],
select {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #b2dfdb;
	border-radius: 4px;
	box-sizing: border-box;
}

.alert {
	margin-top: 15px;
}

.container {
	margin-top: 20px;
	padding: 20px;
	border: 1px solid #e0f7fa;
	border-radius: 8px;
	box-sizing: border-box;
}
</style>
</head>
<body>
	<div class="body">
		<nav>
			<%@include file="Layout/MainLayout.jsp"%>
		</nav>

		<div class="container">
            <h2>Nueva transferencia</h2>
            
            <!-- Formulario para buscar CBU -->
            <%
                Cliente clienteEncontrado = (Cliente) request.getAttribute("cliente");
                Cuenta cuentaDestino = (Cuenta) request.getAttribute("cuentaDestino");
                if (clienteEncontrado == null || cuentaDestino == null) {
            %>
            <form action="ServletCuentaABM" method="post">
                <label for="txtCBU">CBU</label>
                <input type="text" id="txtCBU" name="txtCBU" required maxlength="22">
                <input type="submit" name="accion" value="BuscarCBU" class="btn">
            </form>
            <% } %>
            
            <!-- Mostrar mensaje de error si existe -->
            <% String mensaje = (String) request.getAttribute("mensaje");
               if (mensaje != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= mensaje %>
                </div>
            <% } %>
            
           <!-- Mostrar detalles si se encontro el cliente y cuenta -->
            <%
                ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("cuentasCliente");
                Cliente clienteDestino = (Cliente) request.getAttribute("clienteDestino");
            %>
            <% if (clienteEncontrado != null && cuentaDestino != null && cuentasCliente != null) { %>
                <div class="container">
                    <h2>Detalles del Cliente y Cuenta</h2>
                    <hr>
                    <h3>Los datos ingresados corresponden a <%= clienteDestino.getNombre() %> <%= clienteDestino.getApellido() %></h3>
                    <p>Tipo de cuenta destino: <%= cuentaDestino.getTipoCuenta().getDescripcion() %></p>
                    <p>CBU destino: <%= cuentaDestino.getCbu() %></p>
                    
                    <!-- Formulario de transferencia -->
                    <form action="ServletCuentaABM" method="post">
                        <input type="hidden" name="cuentaDestino" value="<%= cuentaDestino.getCbu() %>">
                        
                        <!-- Desplegable con opciones de las cuentas del cliente logeado -->
                        <label for="cuentaOrigen">Seleccionar cuenta de origen:</label>
                        <select id="cuentaOrigen" name="cuentaOrigen">
                            <% for (Cuenta cuenta : cuentasCliente) { %>
                                <option value="<%= cuenta.getCbu() %>">
                                    <%= cuenta.getTipoCuenta().getDescripcion() %> - CBU: <%= cuenta.getCbu() %> - Saldo: <%= cuenta.getSaldo() %>
                                </option>
                            <% } %>
                        </select>
                        <br><br>
                        
                         <!-- Desplegable con opciones de concepto de transferencia -->                         
			            <label for="concepto">Concepto:</label>
			            <select id="concepto" name="concepto">
			                <option value="Alquiler">Alquiler</option>
			                <option value="Expensas">Expensas</option>
			                <option value="Factura">Factura</option>
			                <option value="Seguro">Seguro</option>
			                <option value="Honorarios">Honorarios</option>
			                <option value="Varios">Varios</option>
			            </select>
			            <br><br>
                        <!-- Campo de texto para ingresar valor -->
                        <label for="txtMonto">Monto:</label>
                        <input type="text" id="txtMonto" name="txtMonto" pattern="^\d+(\.\d{1,2})?$" title="Monto" required>
                        <br><br>
                        
                        <!-- Boton de submit para transferir -->
                        <input type="submit" name="accion" value="Transferir" class="btn">
                    </form>
                </div>
            <% } %>       
        </div>
	</div>
</body>
</html>