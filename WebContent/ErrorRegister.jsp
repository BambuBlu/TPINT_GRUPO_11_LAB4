<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error Registro Cliente </title>
    <style>
        /* Body */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e0f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Contenedor principal */
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
            text-align: center;
        }

        /* Título */
        h2 {
            color: #00796b;
            font-size: 1.8em;
            margin-bottom: 10px;
        }

        /* Mensaje de confirmación */
        p {
            color: #333;
            font-size: 1em;
            margin-top: 0;
        }

        /* Botón de redirección */
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            background-color: #00796b;
            color: #ffffff;
            text-decoration: none;
            border-radius: 6px;
            font-size: 1em;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>
    <div class="container">
       <h2>Error al procesar la solicitud</h2>
        <p><%= request.getAttribute("error") %></p> 
        <a href="Index.jsp">Volver al Login</a>
      <!-- <a href="Register.jsp">Volver a la página anterior</a>  -->
      <a href="javascript:history.back()">Volver a la página anterior</a>
    </div>
</body>
</html>