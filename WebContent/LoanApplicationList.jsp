<%@page import="java.util.List"%>
<%@page import="integrador.model.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listado de solicitud de prestamos</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
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
            width: 90%;
            max-width: 1700px;
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

        .table-container {
            border-radius: 10px;
            border: 4px solid #2196F3;
            width: 100%;
            overflow: hidden;
            background-color: #595C5F;
        }

        .tabla {
            width: 100%;
            background-color: #595C5F;
            color: white;
            font-size: 15px;
            border-collapse: collapse;
        }

        .tabla th, .tabla td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .tabla th {
            background-color: #00796b;
            color: white;
        }
    </style>
</head>
<body>
    <div class="body">
        <nav><%@include file="Layout/MainLayout.jsp"%></nav>

        <div class="contenedor">
            <h1>Listado de solicitud de prestamos</h1>

            <div class="table-container">
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
							<th>Aprobar</th>
							<th>Rechazar</th>                  
                        </tr>
                    </thead>
                    <tbody>
                       <%
						ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
							if (request.getAttribute("listaPrestamosInactivos") != null) {
								 listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamosInactivos");
							} 
							
							if (listaPrestamos != null && !listaPrestamos.isEmpty()) {
								for (Prestamo prestamo : listaPrestamos) {
									%>
									<tr>
										<td><%=prestamo.getId()%></td>
										<td><%=prestamo.getCliente().getDni()%></td>
										<td><%=prestamo.getCuenta().getCbu() %></td>
										<td><%=prestamo.getFecha()%></td>
										<td>$<%=prestamo.getImporteConIntereses()%></td>
										<td>$<%=prestamo.getImportePedido()%></td>
										<td><%=prestamo.getPlazoDePagoEnMeses()%></td>
										<td>$<%=prestamo.getMontoMensual()%></td>
										<td><%=prestamo.getEstado()%></td>
										
										<td>
										<form action="ServletCuentaABM" method="post">
												<input type="hidden" name="prestamoSeleccionadoId" value="<%= prestamo.getId()%>">
												
												<input type="submit" name="accion" value="AprobarPrestamo">
											</form>
										</td>
										<td>
										<form action="ServletCuentaABM" method="post">
												<input type="hidden" name="prestamoSeleccionadoId" value="<%= prestamo.getId()%>">
											
												<input type="submit" name="accion" value="RechazarPrestamo">
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_id').DataTable({
				"dom" : '<"top"i>rt<"bottom"lp><"clear">',
			});
		});
	</script>
</body>
</html>
