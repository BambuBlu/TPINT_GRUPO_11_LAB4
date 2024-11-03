<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="integrador.model.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Clientes Activos</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<style>
    /* Body */
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #e0f7fa;
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
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        width: 100%;
        max-width: 1500px;
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

    table, th, td {
        border: 1px solid #ccc;
    }

    th, td {
        padding: 10px;
        text-align: left;
    }

    th {
        background-color: #f4f4f9; 
    }
</style>
</head>
<body>
	<nav>
		<%@include file="Layout/MainLayout.jsp"%>
	</nav>
	
    <div class="contenedor">
        <h1>Listado de Clientes Activos</h1>

        <div class="usuario">
            <a href="#">Nombre Usuario </a>
            <button class="btn">Cerrar sesión</button>
        </div>

        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Usuario</th>
                    <th>Estado </th>
                    <th>Funcionalidades</th>
                </tr>
            </thead>

            <tbody>
                        <%  Usuario usuarios = new Usuario();
                        
                       //  for (let i = 0; i < usuarios.length; i++) {  %>
                        
                        
                      <%// } %>
                <!-- SIN DATOS AUN -->
                <tr>
                    <td>1</td>
                    <td>12345678</td>
                    <td>Con Deuda</td>
                    <td><button>Dar de baja</button> <button>Modificar datos</button></td>
                </tr>
                <tr>
                     <td>2</td>
                    <td>910111213</td>
                    <td>Sin Deuda</td>
                    <td><button>Dar de baja</button> <button>Modificar datos</button></td>
                </tr>
                
            </tbody>
        </table>
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