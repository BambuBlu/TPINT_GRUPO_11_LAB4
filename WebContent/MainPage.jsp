<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Movimientos Caja de Ahorro</title>
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
    <style>
        /* Estilos de la página */
        body, html {
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
            max-width: 600px;
            box-sizing: border-box;
            text-align: center;
        }

        /* Títulos */
        h1 {
            text-align: center;
            color: #00796b;
            font-size: 1.8em;
            margin-bottom: 20px;
        }

        h2 {
            color: #004d40;
            font-size: 1.5em;
            margin-top: 20px;
        }

        /* Botones */
        .btn {
            background-color: #00796b;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 6px;
            cursor: pointer;
            text-align: center;
            transition: background-color 0.3s;
            margin: 10px 0;
        }

        .btn:hover {
            background-color: #004d40;
        }

        /* Tabla de movimientos */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #b2dfdb;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #e0f7fa;
            color: #004d40;
        }

        td {
            color: #333;
        }

        /* Footer */
        footer {
            background-color: #e0f7fa;
            padding: 10px;
            text-align: center;
            border-top: 1px solid #ccc;
            color: #555;
            font-size: 0.9em;
        }
    </style>
</head>
<body>

    <div class="contenedor">
        <h1>Inicio</h1>

        <!-- Info de cuentas -->
        <div class="informacion-cuenta">
            <div class="cuenta">
                <a href="#">Caja de ahorro</a> <span>$ Saldo</span>
            </div>
            <div class="cuenta">
                <a href="#">Cuenta corriente</a> <span>$ Saldo</span>
            </div>
            <div class="cuenta-extra">
                <a href="#">Solicitar caja de ahorro</a>
            </div>
            <div class="cuenta-extra">
                <a href="#">Solicitar cuenta corriente</a>
            </div>
        </div>

        <!-- Sección de movimientos recientes -->
        <h2>Movimientos recientes</h2>
        <table>
            <thead>
                <tr>
                    <th>Descripción</th>
                    <th>Monto</th>
                    <th>Fecha</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Depósito</td>
                    <td>$5000</td>
                    <td>01/11/2024</td>
                </tr>
                <tr>
                    <td>Pago de servicios</td>
                    <td>$1000</td>
                    <td>30/10/2024</td>
                </tr>
                <tr>
                    <td>Transferencia recibida</td>
                    <td>$2000</td>
                    <td>28/10/2024</td>
                </tr>
            </tbody>
        </table>

        <!-- Botones para solicitar préstamo y transferir dinero -->
        <button class="btn" onclick="solicitarPrestamo()">Solicitar Préstamo</button>
        <button class="btn" onclick="transferirDinero()">Transferir Dinero</button>
    </div>

    <footer>© 2024, Aplicación de Banco</footer>

    <script>
        function solicitarPrestamo() {
            // Lógica para redirigir a la página de solicitud de préstamo
            window.location.href = "solicitarPrestamo.jsp";
        }

        function transferirDinero() {
            // Lógica para redirigir a la página de transferencia de dinero
            window.location.href = "transferirDinero.jsp";
        }
    </script>
</body>

</html>