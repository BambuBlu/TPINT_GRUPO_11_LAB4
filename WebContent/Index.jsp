<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
        height: 100vh;
    }

    /* Contenedor principal */
    .container {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        width: 100%;
        max-width: 350px;
        text-align: center;
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

    /* Campos de txt y contraseña */
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-top: 8px;
        border: 2px solid #b2dfdb;
        border-radius: 6px;
        font-size: 1em;
        box-sizing: border-box;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus, input[type="password"]:focus {
        border-color: #00796b;
        outline: none;
    }

    /* Btn Envío */
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

    /* Enlaces */
    a {
        display: block;
        margin-top: 20px;
        color: #00796b;
        text-decoration: none;
        font-size: 0.9em;
    }

    a:hover {
        color: #004d40;
        text-decoration: underline;
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
    <div class="container">
        <h1>Iniciar sesión</h1>
<!-- Codigo comentado para posterior uso
        <%/// String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <%// if (errorMessage != null) {%>
           <div class="error-message"><%//=//errorMessage%></div> 
        <%// } %>
-->
		<!-- Por ahora se dejara de placeholder este div -->
		<div class="error-message">Mensaje de error aquí</div>
		
        <form action="servletCliente" method="post">
            <div class="form-group">
                <label for="txtNombre">Nombre de Usuario</label>
                <input type="text" id="txtNombre" name="txtNombre" required maxlength="50">
            </div>
            <div class="form-group">
                <label for="txtcontrasenia">Contraseña</label>
                <input type="password" id="txtcontrasenia" name="txtcontrasenia" required>
            </div>
            <div class="form-group">
                <input type="submit" name="btnInicioSesion" value="Iniciar sesión" class="btn">
            </div>
            <div class="form-group">
                <a href="Register.jsp">Crear usuario</a>
            </div>
        </form>
    </div>
</body>
</html>