<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Usuario</title>
    <style>
        /* Body */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #79d7e7;
            margin: 0;
            padding: 20px;
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
            box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 800px;
            box-sizing: border-box;
        }

        /* Título */
        h2 {
            color: #00796b;
            font-size: 2em;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Campos de formulario */
        label {
            margin-top: 10px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"], input[type="date"], input[type="email"], input[type="password"], select {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border: 2px solid #b2dfdb;
            border-radius: 6px;
            font-size: 1em;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus, input[type="date"]:focus, input[type="email"]:focus, input[type="password"]:focus, select:focus {
            border-color: #00796b;
            outline: none;
        }

        /* Botones */
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .button {
            background-color: #00796b;
            color: #ffffff;
            border: none;
            padding: 12px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1.2em;
            width: 48%;
            transition: background-color 0.3s;
            text-align: center;
        }

        .button:hover {
            background-color: #004d40;
        }

        .button.cancel {
            background-color: #e57373;
        }

        .button.cancel:hover {
            background-color: #c62828;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Modificar Usuario</h2>
        <!-- Formulario para cargar datos mediante ID -->
        <form id="searchForm" action="servletBuscarCliente" method="get">
            <label for="txtID">ID de Cliente</label>
            <input type="text" id="txtID" name="txtID" placeholder="Ingrese ID para cargar datos" required>
            <button type="submit" class="button">Cargar Datos</button>
        </form>

        <!-- Formulario de modificación de cliente -->
        <form action="servletCliente" method="post">
            <label for="txtDNI">DNI</label>
            <input type="text" id="txtDNI" name="txtDNI" required maxlength="8">

            <label for="txtCUIL">CUIL</label>
            <input type="text" id="txtCUIL" name="txtCUIL" required maxlength="11">

            <label for="txtNombre">Nombre</label>
            <input type="text" id="txtNombre" name="txtNombre" required>

            <label for="txtApellido">Apellido</label>
            <input type="text" id="txtApellido" name="txtApellido" required>

            <label for="txtSexo">Sexo</label>
            <select id="txtSexo" name="txtSexo" required>
                <option value="">Seleccione un género</option>
                <option value="1">Masculino</option>
                <option value="2">Femenino</option>
            </select>

            <label for="txtFecNac">Fecha de Nacimiento</label>
            <input type="date" id="txtFecNac" name="txtFecNac" required>

            <label for="txtNacionalidad">Nacionalidad</label>
            <input type="text" id="txtNacionalidad" name="txtNacionalidad" required>

            <label for="txtPais">País</label>
            <select id="txtPais" name="txtPais" required>
                <option value="">Seleccione un país</option>
            </select>

            <label for="txtProvincia">Provincia</label>
            <select id="txtProvincia" name="txtProvincia" required>
                <option value="">Seleccione una provincia</option>
            </select>

            <label for="txtLocalidad">Localidad</label>
            <select id="txtLocalidad" name="txtLocalidad" required>
                <option value="">Seleccione una localidad</option>
            </select>

            <label for="txtDireccion">Dirección</label>
            <input type="text" id="txtDireccion" name="txtDireccion" required>

            <label for="txtEmail">Email</label>
            <input type="email" id="txtEmail" name="txtEmail" required>

            <label for="txtTelefono">Teléfono</label>
            <input type="text" id="txtTelefono" name="txtTelefono" required>

            <label for="txtUsuario">Nombre de Usuario</label>
            <input type="text" id="txtUsuario" name="txtUsuario" required>

            <label for="txtContraseña">Contraseña</label>
            <input type="password" id="txtContrasenia" name="txtContrasenia" required>

            <label for="txtRepeContraseña">Repetir la contraseña</label>
            <input type="password" id="txtRepeContrasenia" name="txtRepeContrasenia" required>

            <!-- Botones Aceptar y Cancelar -->
            <div class="button-container">
                <button type="submit" class="button">Aceptar</button>
                <button type="button" class="button cancel" onclick="window.location.href='paginaAnterior.jsp'">Cancelar</button>
            </div>
        </form>
    </div>
</body>
</html>