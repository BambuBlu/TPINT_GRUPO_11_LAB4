<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="integrador.model.Cliente, integrador.model.Cuenta, integrador.negocioimpl.CuentaNegocioImpl" %>
<%@ page import="java.util.ArrayList" %>
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
		<% 
			Cliente cliente = (Cliente) session.getAttribute("cliente");
		
			ArrayList<Cuenta> cuentasActivas = (ArrayList<Cuenta>) session.getAttribute("cuentasActivas");
		%>
        <div class="contenedor">
            <h1>Inicio Cliente</h1>
            
            <div class="saldo-container">
              <% int numCuentas = 0; %>
              
				<% if (cuentasActivas != null && !cuentasActivas.isEmpty()) { %>
	            <div class="informacion-cuenta">
	               
	                
	               
	                
	                <% for (Cuenta cuenta : cuentasActivas) { %>
	                	
	                	 <% numCuentas++; %>
	                	 
	                    <% if ("Caja de ahorro".equals(cuenta.getTipoCuenta().getDescripcion())) { %>
	                        
	                      
	                        <div class="cuenta">
	                        	<span>Cbu [<%= cuenta.getCbu() %>] - </span>
	                           <a href="ServletClienteABM?movimiento=1&NumCuenta=<%= cuenta.getNumeroDeCuenta() %>">Caja de ahorro</a>
	                            <span>$ <%= cuenta.getSaldo() %></span>
	                        </div>

	                    <% } else if ("Cuenta corriente".equals(cuenta.getTipoCuenta().getDescripcion())) { %>
	                        
	                        <div class="cuenta">
	                        	<span>Cbu [<%= cuenta.getCbu() %>] - </span>
	                           <a href="ServletClienteABM?movimiento=1&NumCuenta=<%= cuenta.getNumeroDeCuenta() %>">Cuenta corriente</a>
	                            <span>$ <%= cuenta.getSaldo() %></span>
	                        </div>
	                        
	                    <% } %>
	                    
	                <% } %>
	                                         
	                  
	                <%} %>
	                
	                 <% System.out.println("numero de cuentas " + numCuentas );
	                if (numCuentas < 3) { %>
	                    <div class="informacion-cuenta">
	                        <a href="ServletClienteABM?Caja=1&clienteDNI=<%= cliente.getDni() %>">Solicitar caja de ahorro</a> 
	                    </div>               
	                    <div class="informacion-cuenta">
	                        <a href="ServletClienteABM?Cuenta=1&clienteDNI=<%= cliente.getDni() %>">Solicitar cuenta corriente</a> 
	                    </div>     
	            </div>                    
	        	<% } %>       
            </div>
        </div>
    </div>
    <footer></footer>
</body>
</html>