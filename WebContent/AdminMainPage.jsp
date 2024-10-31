<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio Admin</title>
    <style>
        /* Body */
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
            max-width: 1000px;
            box-sizing: border-box;
        }

        /* Título */
        h1 {
            text-align: center;
            color: #00796b;
            font-size: 2em;
            margin-bottom: 20px;
        }

        /* Menú de opciones */
        .menu {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 10px;
            margin-bottom: 20px;
        }

        /* Enlaces de menú */
        .menu a {
            color: #00796b;
            text-decoration: none;
            padding: 12px 18px;
            background-color: #e0f2f1;
            border-radius: 8px;
            font-size: 1em;
            transition: background-color 0.3s;
        }

        .menu a:hover {
            background-color: #b2dfdb;
        }

        /* Sección del usuario */
        .usuario {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-top: 20px;
            border-top: 1px solid #ccc;
            margin-top: 20px;
        }

        /* Enlace del usuario */
        .usuario a {
            color: #00796b;
            text-decoration: none;
            font-weight: 500;
        }

        .usuario a:hover {
            text-decoration: underline;
        }

        /* Botón de logout */
        .btn {
            background-color: #00796b;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>
    <div class="contenedor">
        <h1>Inicio Administrador</h1>
        <div class="menu">
            <a href="#">Lista de Clientes Activos</a>
            <a href="#">Lista de Clientes Inactivos</a>
            <a href="#">Crear Cliente</a>
            <a href="#">Altas de Cuentas</a>
            <a href="#">Listar Cuentas</a>
            <a href="#">Listar Solicitudes de Préstamos</a>
            <a href="#">Ver Estadísticas</a>
        </div>
        
        <div class="usuario">
            <a href="#">Nombre Apellido</a>
            <button class="btn">Cerrar sesión</button>
        </div>
    </div>
</body>
</html>