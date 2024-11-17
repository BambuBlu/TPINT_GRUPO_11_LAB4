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
	<div class="body">
		<%@include file="Layout/MainLayout.jsp"%>

	    <div class="container">
	        <h2>Modificar Cliente</h2>
	        
	        <%
	        	System.out.println("Entro al jsp de Modificar Cliente");
				Cliente cliente = (Cliente) request.getAttribute("clienteModificar");
				Usuario usuario = (Usuario) request.getAttribute("usuarioModificar");
				
				System.out.println("Valor de [cliente] = " + cliente);
				System.out.println("Valor de [request.setAttribute(\"clienteModificar\")] = " + request.getAttribute("clienteModificar"));
				System.out.println("Valor de [usuario] = " + usuario);
				System.out.println("Valor de [request.setAttribute(\"usuarioModificar\")] = " + request.getAttribute("usuarioModificar"));
			%>
	
	        <!-- Formulario de modificación de cliente -->
	        <form action="servletClienteABM" method="post">
	            <label for="txtDNI">DNI</label> 
				<input type="text" id="txtDNI" name="txtDNI" value="<%=cliente.getDni()%>" required maxlength="8" readonly> 
				
				<label for="txtCUIL">CUIL</label> 
				<input type="text" id="txtCUIL" name="txtCUIL" value="<%=cliente.getCuil()%>" required maxlength="11"> 
				
				<label for="txtNombre">Nombre</label> 
				<input type="text" id="txtNombre" name="txtNombre" value="<%=cliente.getNombre()%>" required>
				
				<label for="txtApellido">Apellido</label> 
				<input type="text" id="txtApellido" name="txtApellido" value="<%=cliente.getApellido()%>" required>
				
				<label for="txtSexo">Sexo</label>
				<select id="txtSexo" name="txtSexo" required>
					<option value="<%=cliente.getSexo().getId()%>"><%=cliente.getSexo().getDescripcion()%></option>
					<%
						System.out.println("Entro en el genero.");
						GeneroNegocioImpl generoNegocio = new GeneroNegocioImpl();
						System.out.println("CHECK----------");
						ArrayList<Generos> listaGeneros = generoNegocio.GetAllGeneros();
						if (listaGeneros != null && !listaGeneros.isEmpty()) {
							System.out.println("CHECK----------");
							for (Generos genero : listaGeneros) {
					%>
					<option value="<%=genero.getId()%>"><%=genero.getDescripcion()%></option>
					<%
						}
						} else {
					%>
					<option value="">No hay géneros disponibles</option>
					<%
						}
					%>
				</select> 
				
				<label for="txtNacionalidad">Nacionalidad</label> 
				<input type="text" id="txtNacionalidad" name="txtNacionalidad" value="<%=cliente.getNacionalidad()%>" required> 
				
				<label for="txtFecNac">Fecha de Nacimiento</label> 
				<input type="date" id="txtFecNac" value="<%=cliente.getFechaNacimiento()%>" name="txtFecNac" required> 
				
				<label for="txtDireccion">Dirección</label>
				<input type="text" id="txtDireccion" value="<%=cliente.getDireccion()%>" name="txtDireccion" required>
				
				<label for="txtLocalidad">Localidad</label> 
				<select id="txtLocalidad"
					name="txtLocalidad" required>
					<option value="<%=cliente.getLocalidad().getId()%>"><%=cliente.getLocalidad().getNombre()%></option>
					<%
						System.out.println("Entro en Localidad.");
						LocalidadNegocioImpl localidadNegocio = new LocalidadNegocioImpl();
						System.out.println("CHECK----------");
						ArrayList<Localidad> localidades = localidadNegocio.GetAllLocalidades();
						if (localidades != null) {
							System.out.println("CHECK----------");
							for (Localidad localidad : localidades) {
					%>
					<option value="<%=localidad.getId()%>"><%=localidad.getNombre()%></option>
					<%
						}
						} else {
					%>
					<option value="">No hay localidades disponibles</option>
					<%
						}
					%>
				</select>
				
				<label for="txtEmail">Email</label> 
				<input type="email" id="txtEmail" value="<%=cliente.getEmail()%>" name="txtEmail" required>
				
				<label for="txtTelefono">Teléfono</label> 
				<input type="text" id="txtTelefono" name="txtTelefono" value="<%=cliente.getTelefono()%>" required> 
				
				<label for="txtContrasenia">Contraseña</label> 
				<input type="text" id="txtContrasenia" name="txtContrasenia" value="<%=usuario.getContrasena()%>" required> 
	
	            <!-- Botones Aceptar y Cancelar -->
	            <div class="button-container">
	                <button type="submit" class="button" name="accion" value="submitEdit">Aceptar</button>
	                <button type="button" class="button cancel" onclick="window.location.href='AdminMainPage.jsp'">Cancelar</button>
	            </div>
	        </form>
	    </div>
    </div>
</body>
</html>