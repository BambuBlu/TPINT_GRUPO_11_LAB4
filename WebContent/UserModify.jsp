<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="integrador.model.Cliente, integrador.model.Usuario, integrador.model.Generos, integrador.model.Localidad, integrador.negocioimpl.GeneroNegocioImpl"%>
<%@ page import="integrador.negocioimpl.LocalidadNegocioImpl"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Usuario</title>
    <style>
        /* Estilo similar al anterior */
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

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 800px;
            box-sizing: border-box;
        }

        h2 {
            color: #00796b;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border: 2px solid #b2dfdb;
            border-radius: 6px;
            font-size: 1em;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input:focus, select:focus {
            border-color: #00796b;
            outline: none;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .button {
            background-color: #00796b;
            color: white;
            padding: 12px;
            border-radius: 6px;
            cursor: pointer;
            text-align: center;
            flex: 1;
            margin: 0 5px;
        }

        .button.cancel {
            background-color: #e57373;
        }

        .button:hover {
            background-color: #004d40;
        }

        .button.cancel:hover {
            background-color: #c62828;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Modificar Cliente</h2>
        <%
            Cliente cliente = (Cliente) request.getAttribute("clienteModificar");
            Usuario usuario = (Usuario) request.getAttribute("usuarioModificar");
        %>
        <form action="ServletClienteABM" method="post">
            <label for="txtDNI">DNI</label>
            <input type="text" id="txtDNI" name="txtDNI" value="<%= cliente.getDni() %>" readonly>

            <label for="txtCUIL">CUIL</label>
            <input type="text" id="txtCUIL" name="txtCUIL" value="<%= cliente.getCuil()  %>" readonly>

            <label for="txtNombre">Nombre</label>
            <input type="text" id="txtNombre" name="txtNombre" value="<%= cliente.getNombre() %>">

            <label for="txtApellido">Apellido</label>
            <input type="text" id="txtApellido" name="txtApellido" value="<%= cliente.getApellido() %>">

            <label for="txtSexo">Sexo</label>
            <select id="txtSexo" name="txtSexo">
                <option value="<%= cliente.getSexo().getId() %>"><%= cliente.getSexo().getDescripcion() %></option>
                <%
                    GeneroNegocioImpl generoNegocio = new GeneroNegocioImpl();
                    ArrayList<Generos> listaGeneros = generoNegocio.GetAllGeneros();
                    for (Generos genero : listaGeneros) {
                %>
                <option value="<%= genero.getId() %>"><%= genero.getDescripcion() %></option>
                <% } %>
            </select>

            <label for="txtNacionalidad">Nacionalidad</label>
            <input type="text" id="txtNacionalidad" name="txtNacionalidad" value="<%= cliente.getNacionalidad() %>">

            <label for="txtFecNac">Fecha de Nacimiento</label>
            <input type="date" id="txtFecNac" name="txtFecNac" value="<%= cliente.getFechaNacimiento() %>">

            <label for="txtDireccion">Dirección</label>
            <input type="text" id="txtDireccion" name="txtDireccion" value="<%= cliente.getDireccion() %>">

            <label for="txtLocalidad">Localidad</label>
            <select id="txtLocalidad" name="txtLocalidad">
                <option value="<%= cliente.getLocalidad().getId() %>"><%= cliente.getLocalidad().getNombre() %></option>
                <%
                    LocalidadNegocioImpl localidadNegocio = new LocalidadNegocioImpl();
                    ArrayList<Localidad> localidades = localidadNegocio.GetAllLocalidades();
                    for (Localidad localidad : localidades) {
                %>
                <option value="<%= localidad.getId() %>"><%= localidad.getNombre() %></option>
                <% } %>
            </select>

            <label for="txtEmail">Email</label>
            <input type="email" id="txtEmail" name="txtEmail" value="<%= cliente.getEmail() %>">

            <label for="txtTelefono">Teléfono</label>
            <input type="text" id="txtTelefono" name="txtTelefono" value="<%= cliente.getTelefono() %>">

            <label for="txtContrasenia">Contraseña</label>
            <input type="password" id="txtContrasenia" name="txtContrasenia" value="<%= usuario.getContrasena() %>">

            <div class="button-container">
                <button type="submit" class="button" name="accion" value="submitEdit">Aceptar</button>
                <button type="button" class="button cancel" onclick="window.location.href='AdminMainPage.jsp'">Cancelar</button>
            </div>
        </form>
    </div>
</body>
</html>
