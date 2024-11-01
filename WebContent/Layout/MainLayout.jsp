<%@ page import="integrador.enums.Roles"%>
<%@ page import="integrador.model.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- Esta pagina master va a servir para poder utilizarlo mas comodamente sobre las distintas paginas para matener una navegacion limpia  -->
	<div
		class="fixed-top w-100 bg-dark bg-opacity-75 shadow-lg d-flex justify-content-between align-items-center">
		<img width="68" height="68"
			src="https://img.icons8.com/external-flat-circular-vectorslab/136/external-Bank-business-and-finance-flat-circular-vectorslab.png"
			alt="Logo Banco" />

		<%
			Usuario usuarioActual = (Usuario) session.getAttribute("SessionActual");
			if (usuarioActual != null && usuarioActual.getRol() == Roles.ADMIN) {
		%>
		<div class="d-flex justify-content-center align-items-center gap-3">
			<a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Lista Clientes Activos">
				<p>Lista Clientes Activos</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Lista Clientes Inactivos">
				<p>Lista de Clientes Inactivos</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Crear Cliente">
				<p>Crear Cliente y Cuentas</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Listar Cuentas">
				<p>Listar Cuentas</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Solicitudes de Préstamos">
				<p>Listar Solicitudes de Préstamos</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Estadística">
				<p>Ver Estadística</p>
			</a>
		</div>
		<%
			} else if (usuarioActual != null
					&& (usuarioActual.getRol() == Roles.CLIENTE || usuarioActual.getRol() == Roles.ADMIN)) {
		%>
		<div class="d-flex justify-content-center align-items-center gap-3">
			<a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Transferencias">
				<p>Transferencias</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Solicitar Préstamo">
				<p>Préstamos</p>
			</a> <a href="#" class="btn-main"> <img
				src="https://img.icons8.com/parakeet/48/checklist.png"
				alt="Solicitar Préstamo">
				<p>Solicitar Préstamos</p>
			</a>
		</div>
		<%
			}
		%>
		<img width="70" height="70"
			src="https://img.icons8.com/3d-fluency/94/user-male-circle.png"
			alt="Imagen Usuario" />
	</div>
</body>
</html>