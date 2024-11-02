<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Estadísticas</title>
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
    max-width: 1000px;
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

.usuario a, .cuenta a {
    color: #007bff;
    text-decoration: none;
}

.usuario a:hover, .cuenta a:hover {
    text-decoration: underline;
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

input[type="number"], .hidden {
    width: 100%;
    padding: 8px 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    display: none;
}

.resultado {
    margin-top: 20px;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
    color: #333;
    display: none;
    text-align: center;
}
</style>
<script type="text/javascript">
function onRadioChange() {
    var option3 = document.getElementById("option3");
    var dateInput = document.getElementById("dateInput");
    dateInput.style.display = option3.checked ? "block" : "none";
}

function mostrarResultado() {
    // Oculta todos los resultados primero
    document.getElementById("resultado1").style.display = "none";
    document.getElementById("resultado2").style.display = "none";
    document.getElementById("resultado3").style.display = "none";
    document.getElementById("resultado4").style.display = "none";

    // Muestra solo el resultado de la opción seleccionada
    if (document.getElementById("option1").checked) {
        document.getElementById("resultado1").style.display = "block";
    } else if (document.getElementById("option2").checked) {
        document.getElementById("resultado2").style.display = "block";
    } else if (document.getElementById("option3").checked) {
        const desde = document.getElementById("desde").value || "no especificado";
        const hasta = document.getElementById("hasta").value || "no especificado";
        
        const resultado3 = document.getElementById("resultado3");
        resultado3.innerHTML = `Importe monetario movilizado desde ${desde} hasta ${hasta}: $5000`;
        resultado3.style.display = "block";
    } else if (document.getElementById("option4").checked) {
        document.getElementById("resultado4").style.display = "block";
    }
}
</script>
</head>
<body>
	<nav>
		<%@include file="Layout/MainLayout.jsp"%>
	</nav>
	
    <div class="contenedor">
        <h1>Estadísticas</h1>

        <div class="usuario">
            <a href="#">Nombre Apellido</a>
            <button class="btn">Cerrar sesión</button>
        </div>

        <!-- Formulario para seleccionar opción -->
        <form onsubmit="mostrarResultado(); return false;">
            <div class="opciones">
                <label><input type="radio" id="option1" name="estadistica" onchange="onRadioChange()" checked> Promedio de saldos entre todas las cuentas</label><br>
                <label><input type="radio" id="option2" name="estadistica" onchange="onRadioChange()"> Porcentaje de cuentas Activas / Inactivas</label><br>
                <label><input type="radio" id="option3" name="estadistica" onchange="onRadioChange()"> Importe monetario movilizado</label><br>
                <label><input type="radio" id="option4" name="estadistica" onchange="onRadioChange()"> Movimientos realizados esta semana</label>
            </div>

            <!-- Campos "Desde" y "Hasta" solo para la opción "Importe monetario movilizado" -->
            <div id="dateInput" class="hidden">
                <label>Desde:</label>
                <input type="number" id="desde" placeholder="Ingrese el monto"><br>
                <label>Hasta:</label>
                <input type="number" id="hasta" placeholder="Ingrese el monto">
            </div>

            <button type="submit" class="btn">Aceptar</button>
        </form>

        <!-- Contenedor de resultados -->
        <div class="resultado" id="resultado1">Promedio de saldos entre todas las cuentas: $1000</div>
        <div class="resultado" id="resultado2">Porcentaje de cuentas Activas: 60% / Inactivas: 40%</div>
        <div class="resultado" id="resultado3">Importe monetario movilizado: $5000</div>
        <div class="resultado" id="resultado4">Movimientos realizados esta última semana: $1200</div>
    </div>
</body>
</html>