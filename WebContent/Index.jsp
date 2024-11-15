<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style>
    /* Estilos generales */
    body {
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
        max-width: 350px;
        text-align: center;
        box-sizing: border-box;
    }

    /* Título */
    h1 {
        color: #00796b;
        font-size: 1.8em;
        margin-bottom: 20px;
    }

    /* Campos de formulario */
    .form-group {
        margin: 15px 0;
    }

    /* Campos de texto y contraseña */
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #b2dfdb;
        border-radius: 4px;
        font-size: 1em;
        box-sizing: border-box;
    }

    input[type="text"]:focus, input[type="password"]:focus {
        border-color: #00796b;
        outline: none;
    }

    /* Botón de envío */
    input[type="submit"] {
        background-color: #00796b;
        color: #ffffff;
        border: none;
        padding: 12px;
        margin-top: 20px;
        border-radius: 6px;
        cursor: pointer;
        font-size: 1em;
        width: 100%;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #004d40;
    }
    
        /* Mensaje de error */
    .error-message {
        color: #d32f2f;
        font-size: 0.9em;
        margin: 10px 0;
    }
</style>
</head>
<body>
    <div class="contenedor">
        <h1>Iniciar Sesión</h1>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) {%>
           <div class="error-message"><%= errorMessage%></div> 
        <% } %>
        <form action="ServletLogin" method="post">
            <div class="form-group">
                <label for="txtNombre">Usuario:</label>
                <input type="text" id="txtNombre" name="txtNombre" required maxlength="50">
            </div>
            <div class="form-group">
                <label for="txtcontrasenia">Contraseña:</label>
                <input type="password" id="txtContrasenia" name="txtcontrasenia" required>
            </div>
            <input type="submit" name="btnInicioSesion" value="Ingresar" class="btn">
			<div class="form-group">
                <a href="Register.jsp">Crear usuario</a>
            </div>
        </form>
    </div>
</body>
</html>