<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencias</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
    .contenedor {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 600px;
        box-sizing: border-box;
    }
    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
    }
    .menu {
        display: flex;
        justify-content: center;
        gap: 15px;
        margin-bottom: 20px;
    }
    .menu a {
        color: #007bff;
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
    }
    .usuario a {
        color: #007bff;
        text-decoration: none;
    }
    .usuario a:hover {
        text-decoration: underline;
    }
    .usuario form {
        margin: 0;
    }
    .btn {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
        text-align: center; 
    }
    .btn:hover {
        background-color: #0056b3;
    }
    h2 {
        text-align: center;
        color: #333;
    }
    .container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
        box-sizing: border-box;
    }
    form {
        display: flex;
        flex-direction: column;
    }
    label {
        margin-top: 10px;
        font-weight: bold;
    }
    input[type="text"],
    input[type="number"] {
        width: 100%;
        padding: 8px 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    select {
        width: 100%;
        padding: 8px 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
</style>
</head>
<body>
	<nav>
		<%@include file="Layout/MainLayout.jsp"%>
	</nav>

    <div class="contenedor">
        <h1>Transferencias</h1>
        
        <div class="usuario">
            <a href="#">Nombre de Usuario</a>
            <form action="#" method="post">
                <input type="submit" class="btn" value="Cerrar sesi�n" name="btnLogout">
            </form>
        </div>
        
        <div class="container">
            <h2>Nueva transferencia</h2>
            
            <form action="#" method="post">
                <label for="txtCBU">CBU</label>
                <input type="text" id="txtCBU" name="txtCBU" required maxlength="22">
                <input type="submit" name="btnBuscarCBU" value="Buscar CBU" class="btn">
            </form>
            
            <div class="alert alert-danger" role="alert">
                <!-- Mensaje de error si existe -->
                Mensaje de error aqu�.
            </div>
            
            <div class="container">
                <h2>Detalles del Cliente y Cuenta</h2>
                <hr>
                <h3>Los datos ingresados corresponden a Nombre Apellido</h3>
                <p>Tipo de cuenta destino: Tipo de cuenta aqu�.</p>
                <p>CBU destino: CBU aqu�.</p>
                
                <form action="#" method="post">
                    <label for="cuentaOrigen">Seleccionar cuenta de origen:</label>
                    <select id="cuentaOrigen" name="cuentaOrigen">
                        <option value="CUENTA1">Tipo Cuenta - CBU1 - Saldo</option>
                        <option value="CUENTA2">Tipo Cuenta - CBU2 - Saldo</option>
                    </select>
                    <br><br>
                    
                    <label for="concepto">Concepto:</label>
                    <select id="concepto" name="concepto">
                        <option value="Alquiler">Alquiler</option>
                        <option value="Expensas">Expensas</option>
                        <option value="Factura">Factura</option>
                        <option value="Seguro">Seguro</option>
                        <option value="Honorarios">Honorarios</option>
                        <option value="Varios">Varios</option>
                    </select>
                    <br><br>
                    <label for="txtMonto">Monto:</label>
                    <input type="text" id="txtMonto" name="txtMonto" pattern="^\d+(\.\d{1,2})?$" title="Monto" required>
                    <br><br>
                    
                    <input type="submit" name="btnTransferir" value="Transferir" class="btn" disabled>
                </form>
            </div>       
        </div>
    </div>
</body>
</html>