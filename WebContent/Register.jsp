<%@page import="java.util.ArrayList"%>
<%@page import="integrador.model.Generos"%>
<%@page import="integrador.negocioimpl.GeneroNegocioImpl"%>
<%@page import="integrador.model.Localidad"%>
<%@page import="integrador.negocioimpl.LocalidadNegocioImpl"%>
<%@page import="integrador.negocioimpl.ProvinciaNegocioImpl"%>
<%@page import="integrador.negocioimpl.PaisNegocioImpl"%>
<%@page import="integrador.model.Pais"%>
<%@page import="integrador.model.Provincia"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear nuevo Cliente</title>
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

        /* Botón de envío */
        input[type="submit"] {
            background-color: #00796b;
            color: #ffffff;
            border: none;
            padding: 12px;
            margin-top: 20px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1.2em;
            width: 100%;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Crear Usuario</h2>
        <form action="ServletClienteABM" method="post">
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
                <% 
                    GeneroNegocioImpl generoNegocio = new GeneroNegocioImpl();
            		ArrayList<Generos> generos = generoNegocio.GetAllGeneros();
                    for (Generos genero : generos) {
                        if (genero.getId() != -1 && genero.getDescripcion() != null && !genero.getDescripcion().isEmpty()) { 
                %>
                    <option value="<%= genero.getId() %>"><%= genero.getDescripcion() %></option>
                <% 
                        } 
                    }
                %>
            </select>

            <label for="txtFecNac">Fecha de Nacimiento</label>
            <input type="date" id="txtFecNac" name="txtFecNac" required>

            <label for="txtPais">País</label>
            <select id="txtPais" name="txtPais" required>
                <option value="">Seleccione un país</option>
                <% 
                    PaisNegocioImpl paisNegocio = new PaisNegocioImpl();
            		ArrayList<Pais> paises = paisNegocio.GetAllPaises();
                    if (paises != null) {
                        for (Pais pais : paises) { 
                %>
                    <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                <% 
                        }
                    }
                %>
            </select>

            <label for="txtProvincia">Provincia</label>
            <select id="txtProvincia" name="txtProvincia" required>
                <option value="">Seleccione una provincia</option>
                <% 
                    ProvinciaNegocioImpl provinciaNegocio = new ProvinciaNegocioImpl();
            		ArrayList<Provincia> provincias = provinciaNegocio.GetAllProvincias();
                    if (provincias != null) {
                        for (Provincia provincia : provincias) { 
                %>
                    <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
                <% 
                        }
                    }
                %>
            </select>

            <label for="txtLocalidad">Localidad</label>
            <select id="txtLocalidad" name="txtLocalidad" required>
                <option value="">Seleccione una localidad</option>
                <% 
                    LocalidadNegocioImpl localidadNegocio = new LocalidadNegocioImpl();
                    ArrayList<Localidad> localidades = localidadNegocio.GetAllLocalidades();
                    if (localidades != null) {
                        for (Localidad localidad : localidades) {
                            if (localidad.getId() != -1 && localidad.getNombre() != null && !localidad.getNombre().isEmpty()) { 
                %>
                    <option value="<%= localidad.getId() %>"><%= localidad.getNombre() %></option>
                <% 
                            }
                        }
                    }
                %>
            </select>

            <label for="txtDireccion">Dirección</label>
            <input type="text" id="txtDireccion" name="txtDireccion" required>

            <label for="txtEmail">Email</label>
            <input type="email" id="txtEmail" name="txtEmail" required>

            <label for="txtTelefono">Teléfono</label>
            <input type="text" id="txtTelefono" name="txtTelefono" required>

            <label for="txtUsuario">Nombre de Usuario</label>
            <input type="text" id="txtUsuario" name="txtUsuario" required>

            <label for="txtContrasenia">Contraseña</label>
            <input type="password" id="txtContrasenia" name="txtContrasenia" required>

            <label for="txtRepeContrasenia">Repetir la contraseña</label>
            <input type="password" id="txtRepeContrasenia" name="txtRepeContrasenia" required>

            <input type="submit" value="Aceptar" name="btnCrearCliente">
        </form>
    </div>
</body>
</html>