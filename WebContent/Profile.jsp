<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="integrador.model.Cliente"%>
<%@page import="integrador.daoimpl.ClienteDaoImpl"%>
<%@page import="integrador.daoimpl.UsuarioDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="integrador.model.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/styleProfile.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Layout/MainLayout.jsp"%>

	<div class="modal-user">

		<a class="card-user human-resources" href="#">
			<div class="overlay"></div>
			<div class="circle">
				<img
					src="https://img.icons8.com/external-flat-circular-vectorslab/136/external-Bank-business-and-finance-flat-circular-vectorslab.png"
					width="30" height="30" alt="Alternate Text" />
				<svg xmlns:xlink="http://www.w3.org/1999/xlink"
					xmlns="http://www.w3.org/2000/svg" version="1.1"
					viewBox="1855 26 66 77" height="77px" width="66px"> <desc>Created
				with Sketch.</desc> <defs></defs> <g
					transform="translate(1855.000000, 26.000000)" fill-rule="evenodd"
					fill="none" stroke-width="1" stroke="none" id="Page-1"> <path
					fill="#AFCEFF" id="Fill-8"
					d="M4.28872448,42.7464904 C4.28872448,39.3309774 5.4159227,33.7621426 6.40576697,30.4912557 C10.5920767,32.1098991 14.3021264,35.1207513 18.69596,35.1207513 C30.993618,35.1207513 42.5761396,28.7162991 49.9992251,17.9014817 C56.8027248,23.8881252 60.8188351,33.0463165 60.8188351,42.7464904 C60.8188351,60.817447 47.6104607,76.6693426 32.5537798,76.6693426 C17.4970989,76.6693426 4.28872448,60.817447 4.28872448,42.7464904"></path>
				<path fill="#3B6CB7" id="Fill-10"
					d="M64.3368879,31.1832696 L62.8424171,46.6027478 L60.6432609,46.7824348 L59.8340669,34.6791304 L47.6573402,25.3339478 C44.2906753,34.068487 34.3459503,40.2903304 24.4684093,40.2903304 C17.7559812,40.2903304 10.046244,37.4168 5.80469412,32.8004522 L5.80469412,34.6791304 L5.80469412,46.6027478 L4.28932167,46.6027478 L1.30187314,27.8802435 C1.30187314,20.9790957 3.52342407,15.5432 7.27229127,11.3578087 C13.132229,4.79558261 21.8124018,0.0492173913 30.5672235,0.342852174 C37.4603019,0.569286957 42.6678084,2.72991304 50.8299179,0.342852174 C51.4629405,1.44434783 51.8615656,3.00455652 51.5868577,5.22507826 C51.4629405,6.88316522 51.2106273,7.52302609 50.8299179,8.45067826 C58.685967,14.1977391 64.3368879,20.7073739 64.3368879,31.1832696"></path>
				<path fill="#568ADC" id="Fill-13"
					d="M58.9405197,54.5582052 C62.0742801,54.8270052 65.3603242,52.60064 65.6350321,49.5386574 C65.772386,48.009127 65.2617876,46.5570226 64.3182257,45.4584487 C63.3761567,44.3613357 62.0205329,43.6162922 60.4529062,43.4818922 L58.9405197,54.5582052 Z"></path>
				<path fill="#568ADC" id="Fill-15"
					d="M6.32350389,54.675367 C3.18227865,54.8492104 0.484467804,52.4957496 0.306803449,49.4264626 C0.217224782,47.8925496 0.775598471,46.4579757 1.75200594,45.3886191 C2.7284134,44.3192626 4.10792487,43.6165843 5.67853749,43.530393 L6.32350389,54.675367 Z"></path>
				</g> </svg>
			</div> <%
 	Usuario usuarioActual1 = (Usuario) session.getAttribute("SessionActual");

 %>

			<p><%=usuarioActual1.getNombreUsuario()%></p>
			
			

			<div class="d-flex justify-content-between align-items-center mt-3">
					<button class="button-user mx-2" style="background-color: red; border: 3px solid #DF0025;"
    					onclick="window.location.href='ServletLogin?action=logout'">
						Log out
						<svg class="icon" viewBox="0 0 24 24" fill="currentColor"> <path
							fill-rule="evenodd"
							d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm4.28 10.28a.75.75 0 000-1.06l-3-3a.75.75 0 10-1.06 1.06l1.72 1.72H8.25a.75.75 0 000 1.5h5.69l-1.72 1.72a.75.75 0 101.06 1.06l3-3z"
							clip-rule="evenodd"></path> </svg>
					</button>
			</div>
		</a>
	</div>
</body>
</html>