<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Inicio Admin</title>
<style>
/* Body */
.body {
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
.contenedor {
    background-color: #ffffff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 30px 50px 160px 49px rgba(0, 0, 0, 0.3);
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

/* Saldo */
.saldo-container {
    margin-top: 20px;
}

.saldo-label {
    font-size: 1.2em;
    color: #00796b;
    font-weight: 500;
}

.saldo-value {
    font-size: 1.5em;
    font-weight: bold;
    color: #333;
}
</style>
</head>
<body>
    <div class="body">
        <%@include file="Layout/MainLayout.jsp"%>

        <div class="contenedor">
            <h1>Inicio Cliente</h1>
            
            <!-- Contenedor de saldo -->
            <div class="saldo-container">
                <!-- Saldo en caja de ahorro -->
                <div class="mb-3">
                    <label class="saldo-label">Saldo en caja de ahorro:</label>
                    <div class="saldo-value">$10,000</div> <!-- Placeholder de saldo -->
                </div>

                <!-- Saldo en cuenta corriente -->
                <div class="mb-3">
                    <label class="saldo-label">Saldo en cuenta corriente:</label>
                    <div class="saldo-value">$5,000</div> <!-- Placeholder de saldo -->
                </div>
            </div>
        </div>
    </div>
    <footer></footer>
</body>
</html>