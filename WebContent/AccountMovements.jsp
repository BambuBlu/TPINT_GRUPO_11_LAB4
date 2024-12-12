<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="integrador.model.Cliente, integrador.model.Movimiento, integrador.model.Cuenta, integrador.negocioimpl.MovimientoNegocioImpl" %>
	<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
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
	
	<!-- PARA PAGINACION Y FILTRADO -->
<link rel="stylesheet" href="/DataTables/datatables.css" />
 
<script src="/DataTables/datatables.js"></script>

<!--  -->


<meta charset="UTF-8">
<title>Movimientos Caja de Ahorro</title>
<style>
/* Estilos de la página */
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

h2 {
	color: #004d40;
	font-size: 1.5em;
	margin-top: 20px;
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
	background-color: #e0f7fa;
	border-radius: 5px;
	transition: background-color 0.3s;
}

.menu a:hover {
	background-color: #b2dfdb;
}

/* Usuario y botón */
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

/* Tabla de movimientos */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #b2dfdb;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #e0f7fa;
	color: #004d40;
}

td {
	color: #333;
}
</style>
</head>
<body>

	<div class="body">
		<nav>
			<%@include file="Layout/MainLayout.jsp"%>
		</nav>
		<% 
                Cliente cliente = (Cliente) session.getAttribute("cliente");
            %>

		<div class="contenedor">
			<h1>Movimientos de cuentas</h1>
			
			<div>
<label>Desde: <input type="text" id="min" placeholder="Valor mínimo"></label>
<label>Hasta: <input type="text" id="max" placeholder="Valor máximo"></label>
</div>

			<table class="tabla" id="tableMovementsList_id">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Detalles</th>
                    <th>Monto</th>
                </tr>
            </thead>
            <tbody>
                   <% 
                ArrayList<Movimiento> movimientosCliente = (ArrayList<Movimiento>) request.getAttribute("movimientosCliente");
                if (movimientosCliente != null && !movimientosCliente.isEmpty()) {
                    for (Movimiento movimiento : movimientosCliente) {
            %>
                        <tr>
                            <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(movimiento.getFecha()) %></td>
                            <td><%= movimiento.getDetalle() %></td>
                             <td>
						        $ 
						        <% 
						            if (movimiento.getCuentaOrigen() == movimiento.getCuenta().getNumeroDeCuenta()) { 
						                out.print("-");
						            }
						        %> 
						        <%= movimiento.getImporte() %>
						    </td>
                        </tr>
            <% 
                    }
                } else {
            %>
                    <tr>
                        <td colspan="3">No hay movimientos para mostrar.</td>
                    </tr>
            <% 
                }
            %>
            </tbody>
        </table>
		</div>
	</div>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</body>

<script type="text/javascript">
	$(document).ready(function() { //  Inicializo tabla
		var table = $('#tableMovementsList_id').DataTable({
	        scrollY: 'auto',
	        paging: true,
	        searching: true,
	        info: false
	    });

	 // Función de filtrado personalizada (rangos)
    $.fn.dataTable.ext.search.push( // genero filtro personalizado.
        function(settings, data, dataIndex) {
            var min = parseFloat($('#min').val()); // Lee la variacion de los inputs
            var max = parseFloat($('#max').val()); // Lee la variacion de los inputs
            var amount = parseFloat(data[2].replace(/[^\d.-]/g, '')) || 0; // Con signo negativo
           // var amount = parseFloat(data[2].replace(/[\$,]/g, '')) || 0;
           // var amount = parseFloat(data[2]) || 0; // Columna 2 (monto)

            if (
                (isNaN(min) && isNaN(max)) || // Sin rango
                (isNaN(min) && amount <= max) || // Solo máximo
                (min <= amount && isNaN(max)) || // Solo mínimo
                (min <= amount && amount <= max) // Dentro del rango
            ) {
                return true;
            }
            return false;
        }
    );

    // Evento para aplicar el filtro cuando cambian los valores
    $('#min, #max').on('keyup change', function() {
        table.draw();
    });
});

	new DataTable('#tableMovementsList_id', {
	    order: [[3, 'asc']],
	    rowGroup: {
	        dataSrc: (row) => {
	            let base = Math.floor(row[3] / 10);
	 
	            return base + '0 - ' + base + '9';
	        }
	    }
	});
	
</script>

</html>
