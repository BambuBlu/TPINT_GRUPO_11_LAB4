<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio</title>
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

        /* NavBar */
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
            background-color: #ffffff;
            border: 2px solid #b2dfdb;
            border-radius: 5px;
            transition: background-color 0.3s, border-color 0.3s;
        }
        .menu a:hover {
            background-color: #b2dfdb;
            border-color: #00796b;
        }

        /* Info de usuario */
        .usuario {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin-top: 20px;
            border-top: 1px solid #ccc;
        }
        .usuario a {
            color: #00796b;
            text-decoration: none;
        }
        .usuario a:hover {
            color: #004d40;
            text-decoration: underline;
        }

        /* Info de cuenta */
        .informacion-cuenta {
            text-align: center;
            margin: 15px 0;
        }
        .cuenta {
            margin-bottom: 15px;
        }
        .cuenta a {
            color: #00796b;
            text-decoration: none;
        }
        .cuenta a:hover {
            color: #004d40;
            text-decoration: underline;
        }

        /* Btn de cierre de sesión */
        .btn {
            background-color: #00796b;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>
    <div class="contenedor">
        <h1>Inicio</h1>
        
        <!-- NavBar -->
        <div class="menu">
            <a href="#">Transferencias</a>
            <a href="#">Préstamos</a>
            <a href="#">Solicitar Préstamos</a>
        </div>
        
        <!-- Info de usuario y cierre de sesión -->
        <div class="usuario">
            <a href="#">Nombre Apellido</a>
            <button class="btn">Cerrar sesión</button>
        </div>

        <!-- Info de cuentas -->
        <div class="informacion-cuenta">
            <div class="cuenta">
                <a href="#">Caja de ahorro</a>
                <span>$ Saldo</span>
            </div>
            <div class="cuenta">
                <a href="#">Cuenta corriente</a>
                <span>$ Saldo</span>
            </div>
            <div class="informacion-cuenta">
                <a href="#">Solicitar caja de ahorro</a>
            </div>
            <div class="informacion-cuenta">
                <a href="#">Solicitar cuenta corriente</a>
            </div>
        </div>
    </div>
</body>
</html>