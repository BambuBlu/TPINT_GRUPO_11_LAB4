
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<style>
    /* Estilo general */
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

    /* Contenedor principal */
    .contenedor {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        width: 100%;
        max-width: 600px;
        box-sizing: border-box;
        text-align: center;
    }

    /* Título */
    h1 {
        color: #00796b;
        font-size: 1.8em;
        margin-bottom: 20px;
    }

    /* Menú de navegación */
    .menu {
        display: flex;
        justify-content: center;
        gap: 10px;
        margin-bottom: 20px;
        flex-wrap: wrap;
    }

    .menu a {
        color: #00796b;
        text-decoration: none;
        padding: 10px 15px;
        background-color: white;
        border-radius: 6px;
        transition: background-color 0.3s;
        flex: 1;
        text-align: center;
        border: 2px solid #b2dfdb;
        font-size: 0.9em;
    }

    .menu a:hover {
        background-color: #e0f7fa;
        color: #004d40;
    }

    /* Detalles del usuario */
    .usuario {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 10px;
        margin-top: 20px;
        border-top: 1px solid #b2dfdb;
    }

    .usuario a {
        color: #00796b;
        text-decoration: none;
        margin-bottom: 10px;
    }

    .usuario a:hover {
        text-decoration: underline;
        color: #004d40;
    }

    /* Botón de cierre de sesión */
    .usuario form {
        margin: 0;
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .btn {
        background-color: #00796b;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 6px;
        cursor: pointer;
        text-align: center;
        transition: background-color 0.3s;
        font-size: 1em;
    }

    .btn:hover {
        background-color: #004d40;
    }

    /* Información del usuario */
    .user-details {
        margin-top: 20px;
        text-align: left;
    }

    .user-details h2 {
        color: #00796b;
        font-size: 1.5em;
        margin-bottom: 10px;
        text-align: center;
    }

    .user-details label {
        display: block;
        font-weight: bold;
        color: #333;
        margin-top: 10px;
    }

    .user-details span {
        display: block;
        margin-top: 5px;
        color: #555;
    }

    /* Responsividad */
    @media (max-width: 600px) {
        .contenedor {
            padding: 15px;
        }
        .menu a {
            flex: 100%;
        }
        .btn {
            width: 100%;
        }
    }
</style>
<title>Modificacion del Cliente</title>
</head>
<body>
	<nav>
		<%@include file="Layout/MainLayout.jsp"%>
	</nav>

    <div class="contenedor">
        <h1>Modificacion del Cliente</h1>

        <div class="usuario">
            <a href="#">Nombre Apellido</a>
            <form action="#" method="post">
                <input type="submit" class="btn" value="Cerrar sesión">
            </form>
        </div>

        <div class="user-details">
            <h2>Información del Usuario</h2>
            <label>DNI:</label><span>12345678</span>
            <label>CUIL:</label><span>20-12345678-9</span>
            <label>Nombre:</label><span>Nombre</span>
            <label>Apellido:</label><span>Apellido</span>
            <label>Sexo:</label><span>Masculino</span>
            <label>Fecha de Nacimiento:</label><span>01/01/2000</span>
            <label>Nacionalidad:</label><span>Argentina</span>
            <label>País:</label><span>Argentina</span>
            <label>Provincia:</label><input type="text" id="provincia" name="provincia" value="<%= request.getAttribute("provincia") %>">
            <label>Localidad:</label><input type="text" id="localidad" name="localidad" value="<%= request.getAttribute("localidad") %>">
            <label>Dirección:</label><input type="text" id="direccion" name="direcicon" value="<%= request.getAttribute("direccion") %>">
            <label>Email:</label><input type="email" id="email" name="email" value="<%= request.getAttribute("email") %>">
            <label>Teléfono:</label><input type="text" id="phone" name="phone" value="<%= request.getAttribute("phoneNumber") %>">
            <label for="password">Contraseña:</label><input type="password" id="password" name="password" value="<%= request.getAttribute("password") %>">
        </div>
        	
        	   <div class="usuario">
            <form action="actualizarDatos" method="post" style="width: 340px; height: 52px">
                <input type="button" class="btn" value="Actualizar" style="width: 303px; ">
            </form>

           <form action="bajaCuenta" method="post" style="width: 340px; height: 52px; font-size: 16px;">
    <input type="button" 
           style="background-color: red; color: white; border: none; padding: 10px 15px; cursor: pointer; width: 303px; font-size: 16px;" 
           value="Solicitar baja de cuenta" 
           onclick="darBajaLogica()">
</form>
         </div>
        
    </div>  
</body>
</html>