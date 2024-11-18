<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="integrador.model.Usuario, integrador.model.Cliente, integrador.negocioimpl.ClienteNegocioImpl"%>
<%@ page import="java.util.ArrayList" %>

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
	
<!-- PARA PAGINACION Y FILTRADO -->
<link rel="stylesheet" href="/DataTables/datatables.css" />
 
<script src="/DataTables/datatables.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$('#tableUserList_idS').DataTable();
	});
</script>

<!--  -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Clientes</title>
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
	border-radius: 10px;
	padding: 30px;
	box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
	width: 100%;
	max-width: auto;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	align-items: center;
	height: 300px
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
	margin: 0px 100px 0 100px;
}

tr {
	background-color: #595C5F;
}
</style>
</head>
<body>

    <div class="body">
    	<%@include file="Layout/MainLayout.jsp"%>
        <div class="contenedor">

            <!-- Mostrar t�tulo y lista de clientes dependiendo de la selecci�n -->
            <%
                String listaClientes = request.getParameter("listaClientes");
                if ("listaClientesActivos".equals(listaClientes)) {
            %>
                <h1>Listado de Clientes Activos</h1>
            <%
                } else if ("listaClientesInactivos".equals(listaClientes)) {
            %>
                <h1>Listado de Clientes Inactivos</h1>
            <%
                }

                // Obtener lista de clientes
                ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
                String estado = (listaClientes != null && listaClientes.equals("listaClientesActivos")) ? "A" : "I";
                ArrayList<Cliente> clientes = clienteNegocio.GetAllClientes(estado);
            %>

            <div class="table-container">
                <table class="tabla" id="tableUserList_id">
                    <thead>
                        <tr class="text-center fw-bolder fs-5">
                            <th>Dni Cliente</th>
                            <th>Nombre Apellido</th>
                            <th>Estado</th>
                            <th>Funcionalidades</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (clientes != null) {
                                for (Cliente cliente : clientes) {
                        %>
                            <tr class="text-center">
                                <td><%= cliente.getDni() %></td>
                                <td><%= cliente.getNombre() + " " + cliente.getApellido() %></td>
                                <td><%= cliente.getEstado() %></td>
								<td>
								    <%
								        if ("A".equals(cliente.getEstado())) {
								    %>
								        <form method="post" action="ServletClienteABM">
								            <input type="hidden" name="clienteId" value="<%= cliente.getDni() %>" />
								            <input type="hidden" name="accion" value="darBaja" />
								            <button type="submit" class="btn btn-danger shadow-lg fw-bolder">Dar de baja</button>
								        </form>
								    <%
								        } else {
								    %>
								        <form method="post" action="ServletClienteABM">
								            <input type="hidden" name="clienteId" value="<%= cliente.getDni() %>" />
								            <input type="hidden" name="accion" value="habilitar" />
								            <button type="submit" class="btn btn-success shadow-lg fw-bolder">Habilitar</button>
								        </form>
								    <%
								        }
								    %>
								    
							    	<form method="post" action="ServletClienteABM">
							            <input type="hidden" name="clienteId" value="<%= cliente.getDni() %>" />
							            <input type="hidden" name="clienteEstado" value="<%= cliente.getEstado() %>" />
							            <input type="hidden" name="accion" value="modificar" />
							            <button type="submit" class="btn btn-warning shadow-lg fw-bolder text-white">Modificar datos</button>
							        </form>
								    
								</td>
                            </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</body>
</html>