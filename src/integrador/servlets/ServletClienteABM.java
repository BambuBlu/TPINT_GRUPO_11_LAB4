package integrador.servlets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import integrador.model.Usuario;
import integrador.negocioimpl.ClienteNegocioImpl;
import integrador.negocioimpl.CuentaNegocioImpl;
import integrador.negocioimpl.GeneroNegocioImpl;
import integrador.negocioimpl.LocalidadNegocioImpl;
import integrador.negocioimpl.MovimientoNegocioImpl;
import integrador.negocioimpl.PrestamoNegocioImpl;
import integrador.negocioimpl.SolicitudesDeCuentaNegocioImpl;
import integrador.negocioimpl.UsuarioNegocioImpl;
import integrador.daoimpl.UsuarioDaoImpl;
import integrador.excepciones.ExisteDNIUsuarioException;
import integrador.excepciones.ExisteNombreUsuarioException;
import integrador.model.Cliente;
import integrador.model.Cuenta;
import integrador.model.Generos;
import integrador.model.Localidad;
import integrador.model.Movimiento;
import integrador.model.Prestamo;
import integrador.model.SolicitudDeAlta;
import integrador.model.TipoCuenta;
import integrador.model.TipoMovimiento;

/**
 * Servlet implementation class ServletClienteABM
 */
@WebServlet("/ServletClienteABM")
public class ServletClienteABM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletClienteABM() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");

		if (request.getParameter("movimiento") != null) {
			CargarMovimientos(request, response);
		} else if (request.getParameter("Caja") != null) {
			try {
				SolicitarCajaDeAhorro(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("Cuenta") != null) {
			try {
				SolicitarCuentaCorriente(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("listarCuentas".equals(accion)) {
			ListarCuentasClientes(request, response);
		} else if ("altaCuentas".equals(accion)) {
			ListarAltasCuentas(request, response);
		} else if ("listarPrestamos".equals(accion)) {
			try {
				ListarSolicitudPrestamos(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("estadisticas".equals(accion)) {
			Estadisticas(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros inválidos");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String clienteId = request.getParameter("clienteId");
		String clienteEstado = request.getParameter("clienteEstado");

		if (request.getParameter("btnCrearCliente") != null) {
			try {
				crearCliente(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("darBaja".equals(accion)) {
			darBajaCliente(clienteId, response);
		} else if ("habilitar".equals(accion)) {
			habilitarCliente(clienteId, response);
		} else if ("modificar".equals(accion)) {
			try {
				ModificarCliente(clienteId, clienteEstado, request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("submitEdit".equals(accion)) {
			SubmitModificarCliente(clienteEstado, request, response);
		} else if ("Solicitar".equals(accion)) {
			try {
				EstadisticasVisualizar(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create Cliente
	 */

	private void crearCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		LocalidadNegocioImpl localidadNegocio = new LocalidadNegocioImpl();
		GeneroNegocioImpl generoNegocio = new GeneroNegocioImpl();
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

		Localidad ob_localidad;
		Generos ob_genero;

		Usuario usuarioEncontradoNombreDuplicado = new Usuario();
		Usuario usuarioEncontradoDNIDuplicado = new Usuario();
		//Usuario usuarioEncontrado = null;
		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
		
		int id_sexo;
		int id_localidad;

		String dni = request.getParameter("txtDNI");
		String cuil = request.getParameter("txtCUIL");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String sexo = request.getParameter("txtSexo");
		String fechaNacimientoStr = request.getParameter("txtFecNac");
		String direccion = request.getParameter("txtDireccion");
		String localidad = request.getParameter("txtLocalidad");
		String email = request.getParameter("txtEmail");
		String telefono = request.getParameter("txtTelefono");
		String nacionalidad = request.getParameter("txtPais");

		String usuario = request.getParameter("txtUsuario");
		String contraseña = request.getParameter("txtContrasenia");
		String repetirContraseña = request.getParameter("txtRepeContrasenia");

		Date fechaNacimiento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			java.util.Date parsedDate = sdf.parse(fechaNacimientoStr);
			fechaNacimiento = new java.sql.Date(parsedDate.getTime());
			id_sexo = Integer.parseInt(sexo);
			id_localidad = Integer.parseInt(localidad);

			ob_localidad = localidadNegocio.Find(id_localidad);
			ob_genero = generoNegocio.Find(id_sexo);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Formato de fecha de nacimiento inválido");
			RequestDispatcher rd = request.getRequestDispatcher("/ErrorRegister.jsp");
			rd.forward(request, response);
			return;
		}

		if (!contraseña.equals(repetirContraseña)) {
			System.out.println("Las contraseñas no coinciden");
			request.setAttribute("error", "Las contraseñas no coinciden");
			RequestDispatcher rd = request.getRequestDispatcher("/ErrorRegister.jsp");
			rd.forward(request, response);
			return;
		}
		

//Verifico que no exista el DNI ni el nombre de usuario ya registrado
		
		ArrayList<Usuario> listausuariobuscados = usuarioDaoImpl.GetAllUsuariosActivosInactivos(); // Lo debo hacer para todos los usuarios, activos / inactivos.
		for (Usuario usuarioBuscado : listausuariobuscados) {
			System.out.println("Usuario buscado : " + usuarioBuscado.getNombreUsuario());
			
			
			if  (usuarioBuscado.getNombreUsuario().equals(usuario)) {
				usuarioEncontradoNombreDuplicado = usuarioBuscado;
				System.out.println("Usuario encontrado. Nombre duplicado de usuario : " + usuarioEncontradoNombreDuplicado.getNombreUsuario());
				break;
			}
			
			if  (usuarioBuscado.getCliente().getDni().equals(dni)) {
				// usuarioEncontradoDNIDuplicado = new Usuario(usuarioBuscado);
				usuarioEncontradoDNIDuplicado = usuarioBuscado;
				System.out.println("Usuario encontrado. Dni duplicado de usuario : " + usuarioEncontradoDNIDuplicado.getNombreUsuario());
				break;
			}
			
				
		}		
		try {
			
			if (usuarioEncontradoNombreDuplicado.getId_Usaurio() > 0 ) { // No funcionaba correctamente comparando usuario con null.
				ExisteNombreUsuarioException excNombreUsuario  = new ExisteNombreUsuarioException();
				   throw excNombreUsuario;
				   // existe usuario
			}
			if (usuarioEncontradoDNIDuplicado.getId_Usaurio() > 0 ) { // No funcionaba correctamente comparando usuario con null.
				ExisteDNIUsuarioException excDNIUsuario  = new ExisteDNIUsuarioException();
				   throw excDNIUsuario;
				   // existe dni
			}
			
			
		Cliente nuevoCliente = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad,
				(java.sql.Date) fechaNacimiento, direccion, ob_localidad, email, telefono);

		Usuario nuevoUsuario = new Usuario(usuario, contraseña);

		clienteNegocio.CrearCliente(nuevoCliente, nuevoUsuario);

		Cliente clienteAgregado = clienteNegocio.obtenerCliente(dni);
		if (clienteAgregado != null) {
			Movimiento movimiento = new Movimiento();
			CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
			MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
			ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

			cuentas = cuentaNegocio.GetAllActiveCuentas();
			Cuenta nuevaCuenta = new Cuenta();
			for (Cuenta cuenta : cuentas) {
				if (cuenta.getDni().equals(clienteAgregado.getDni())) {
					nuevaCuenta = cuenta;
					break;
				}
			}
			if (nuevaCuenta != null) {
				movimiento.setCuenta(nuevaCuenta);
				movimiento.setFecha(new java.sql.Date(System.currentTimeMillis()));
				movimiento.setDetalle("Alta de cuenta");
				movimiento.setImporte(10000);

				TipoMovimiento tipoMovimiento = new TipoMovimiento();
				tipoMovimiento.setId(1);
				tipoMovimiento.setDescripcion("Alta de cuenta");

				movimiento.setTipoMovimiento(tipoMovimiento);
				movimiento.setCuentaDestino(nuevaCuenta.getNumeroDeCuenta());

				movimientoNegocio.agregarMovimiento(movimiento);

				response.sendRedirect("SuccessRegister.jsp");
				}
			}
		} catch(ExisteNombreUsuarioException e)
		{
			 request.setAttribute("error", e.getMessage());
		     RequestDispatcher rd = request.getRequestDispatcher("/ErrorRegister.jsp");
		     rd.forward(request, response);
		        
			System.out.println("El nombre de usuario que intentas registrar ya existe. ");
			e.printStackTrace();
			return;
		}
		
		catch(ExisteDNIUsuarioException e)
		{
			 request.setAttribute("error", e.getMessage());
		     RequestDispatcher rd = request.getRequestDispatcher("/ErrorRegister.jsp");
		     rd.forward(request, response);
		        
			System.out.println("El DNI del usuario que intentas registrar ya existe. ");
			e.printStackTrace();
			return;
		}
		
		
		catch (Exception e) {
		    e.printStackTrace();
		    request.setAttribute("error", "Ocurrió un error inesperado. Por favor, intenta más tarde.");
		    RequestDispatcher rd = request.getRequestDispatcher("/ErrorRegister.jsp");
		    rd.forward(request, response);
		    return;
		}
		}
		


	/**
	 * Baja logica
	 */
	private void darBajaCliente(String clienteId, HttpServletResponse response) throws IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		Usuario usuario = new Usuario();
		cliente.setDni(clienteId);
		cliente.setEstado("I");
		usuario.setId_Usaurio(Integer.parseInt(clienteId));
		usuario.setBaja(false);

		clienteNegocio.ModificarEstadoCliente(cliente, usuario);
		response.sendRedirect("AdminUserList.jsp?listaClientes=listaClientesActivos");
	}

	/**
	 * Habilitacion de cliente
	 */
	private void habilitarCliente(String clienteId, HttpServletResponse response) throws IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		Usuario usuario = new Usuario();
		cliente.setDni(clienteId);
		cliente.setEstado("A");
		usuario.setId_Usaurio(Integer.parseInt(clienteId));
		usuario.setBaja(true);

		clienteNegocio.ModificarEstadoCliente(cliente, usuario);
		response.sendRedirect("AdminUserList.jsp?listaClientes=listaClientesInactivos");
	}

	private void ModificarCliente(String clienteId, String clienteEstado, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = clienteNegocio.obtenerCliente(clienteId);
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		
		System.out.println(" DNI recibido al serv" + clienteId);
		System.out.println(" Ingreso en modificar cliente. DNI " + cliente.getDni());
		try {
		Usuario usuario = usuarioNegocio.obtenerUsuario(cliente.getDni());

		request.setAttribute("clienteModificar", cliente);
		request.setAttribute("usuarioModificar", usuario);
		request.setAttribute("estadoUsuario", clienteEstado);
	

		RequestDispatcher rd = request.getRequestDispatcher("UserModify.jsp");
		rd.forward(request, response);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error acceso BD");
			RequestDispatcher rd = request.getRequestDispatcher("UserModify.jsp");
			rd.forward(request, response);
			return;
		} 
		
	}

	private void SubmitModificarCliente(String clienteEstado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("txtDNI");
		String cuil = request.getParameter("txtCUIL");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String sexo = request.getParameter("txtSexo");
		String nacionalidad = request.getParameter("txtNacionalidad");
		String fechaNacimientoStr = request.getParameter("txtFecNac");
		String direccion = request.getParameter("txtDireccion");
		String localidad = request.getParameter("txtLocalidad");
		String email = request.getParameter("txtEmail");
		String telefono = request.getParameter("txtTelefono");
		String contraseña = request.getParameter("txtContrasenia");
		Localidad ob_localidad;
		Generos ob_genero;

		Date fechaNacimiento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			java.util.Date parsedDate = sdf.parse(fechaNacimientoStr);
			fechaNacimiento = new java.sql.Date(parsedDate.getTime());

		} catch (java.text.ParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Formato de fecha de nacimiento inválido");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearCliente.jsp");
			rd.forward(request, response);
			return;
		}

		LocalidadNegocioImpl localidadNegocio = new LocalidadNegocioImpl();
		GeneroNegocioImpl genero = new GeneroNegocioImpl();
		ob_localidad = localidadNegocio.Find(Integer.parseInt(localidad));
		ob_genero = genero.Find(Integer.parseInt(sexo));

		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad,
				(java.sql.Date) fechaNacimiento, direccion, ob_localidad, email, telefono, clienteEstado);

		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		Usuario usuario = usuarioNegocio.obtenerUsuario(cliente.getDni());
		usuario.setContrasena(contraseña);

		System.out.println("cliente a modificar es " + cliente.getApellido());

		clienteNegocio.ModificarCliente(cliente, usuario);

		if ("A".equals(clienteEstado)) {
			response.sendRedirect("AdminUserList.jsp?listaClientes=listaClientesActivos");
		} else {
			response.sendRedirect("AdminUserList.jsp?listaClientes=listaClientesInactivos");
		}
	}

	public void CargarMovimientos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();

		String numCuentaStr = request.getParameter("NumCuenta");

		if (numCuentaStr == null || numCuentaStr.isEmpty()) {
			request.setAttribute("mensaje", "No se proporcionó un número de cuenta válido.");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		int numCuenta = Integer.parseInt(numCuentaStr);

		ArrayList<Movimiento> movimientosCliente = new ArrayList<>();
		ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) movimientoNegocio.getMovimientos();

		for (Movimiento m : movimientos) {
			if (m.getCuenta().getNumeroDeCuenta() == numCuenta) {
				movimientosCliente.add(m);
			}
		}
		request.setAttribute("movimientosCliente", movimientosCliente);
		request.getRequestDispatcher("AccountMovements.jsp").forward(request, response);
	}

	public void SolicitarCuentaCorriente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		SolicitudesDeCuentaNegocioImpl solicitudesNegocio = new SolicitudesDeCuentaNegocioImpl();
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

		String dni = request.getParameter("clienteDNI");
		if (dni != null && !dni.isEmpty()) {
			ArrayList<SolicitudDeAlta> solicitudesActivas = (ArrayList<SolicitudDeAlta>) solicitudesNegocio
					.obtenerTodasLasSolicitudesActivas();
			// Verificar si ya tiene una solicitud activa para caja de ahorro
			boolean tieneSolicitudActiva = false;

			for (SolicitudDeAlta solicitud : solicitudesActivas) {
				if (solicitud.getCliente().getDni().equals(dni)) {
					if (solicitud.getTipoCuenta().getId() == 2 && solicitud.getEstado().equals("A")) {
						tieneSolicitudActiva = true;
						break;
					}
				}
			}

			if (!tieneSolicitudActiva) {
				// Crear una instancia de Cliente con el DNI recibido
				Cliente cliente = clienteNegocio.obtenerCliente(dni);

				// Crear una instancia de TipoCuenta para Caja de Ahorro
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(2);
				tipoCuenta.setDescripcion("Cuenta corriente");

				// Crear una nueva solicitud
				SolicitudDeAlta solicitud = new SolicitudDeAlta();
				solicitud.setCliente(cliente);
				solicitud.setTipoCuenta(tipoCuenta);
				solicitud.setEstado("A");

				// Agregar la solicitud a la base de datos
				solicitudesNegocio.agregarSolicitud(solicitud);
			}
			try {
				response.sendRedirect("MainPage.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void SolicitarCajaDeAhorro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		SolicitudesDeCuentaNegocioImpl solicitudesNegocio = new SolicitudesDeCuentaNegocioImpl();
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

		String dni = request.getParameter("clienteDNI");
		if (dni != null && !dni.isEmpty()) {
			ArrayList<SolicitudDeAlta> solicitudesActivas = (ArrayList<SolicitudDeAlta>) solicitudesNegocio
					.obtenerTodasLasSolicitudesActivas();
			// Verificar si ya tiene una solicitud activa para caja de ahorro
			boolean tieneSolicitudActiva = false;

			for (SolicitudDeAlta solicitud : solicitudesActivas) {
				if (solicitud.getCliente().getDni().equals(dni)) {
					if (solicitud.getTipoCuenta().getId() == 1 && solicitud.getEstado().equals("A")) {
						tieneSolicitudActiva = true;
						break;
					}
				}
			}

			if (!tieneSolicitudActiva) {
				// Crear una instancia de Cliente con el DNI recibido
				Cliente cliente = clienteNegocio.obtenerCliente(dni);

				// Crear una instancia de TipoCuenta para Caja de Ahorro
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(1);
				tipoCuenta.setDescripcion("Caja de Ahorro");

				// Crear una nueva solicitud
				SolicitudDeAlta solicitud = new SolicitudDeAlta();
				solicitud.setCliente(cliente);
				solicitud.setTipoCuenta(tipoCuenta);
				solicitud.setEstado("A");

				// Agregar la solicitud a la base de datos
				solicitudesNegocio.agregarSolicitud(solicitud);
			}
			try {
				response.sendRedirect("MainPage.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void ListarCuentasClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();

		ArrayList<Cuenta> cuentas = cuentaNegocio.GetAllCuentas();
		request.setAttribute("cuentas", cuentas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminListAccounts.jsp");
		dispatcher.forward(request, response);
	}

	public void ListarAltasCuentas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SolicitudesDeCuentaNegocioImpl solicitudesNegocio = new SolicitudesDeCuentaNegocioImpl();

		// Obtener todas las solicitudes activas
		ArrayList<SolicitudDeAlta> solicitudesActivas = (ArrayList<SolicitudDeAlta>) solicitudesNegocio
				.obtenerTodasLasSolicitudesActivas();

		// Guardar las solicitudes en el request para pasarlas a la página JSP
		request.setAttribute("solicitudes", solicitudesActivas);

		// Redirigir a la página de alta de cuentas
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminAccountApplicationList.jsp");
		dispatcher.forward(request, response);
	}

	public void ListarSolicitudPrestamos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();

		ArrayList<Prestamo> listaPrestamosInactivos = (ArrayList<Prestamo>) prestamoNegocio.getPrestamosInactivos();
		request.setAttribute("listaPrestamosInactivos", listaPrestamosInactivos);

		RequestDispatcher rd = request.getRequestDispatcher("LoanApplicationList.jsp");
		rd.forward(request, response);
	}
	
	public void Estadisticas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Statistics.jsp");
		rd.forward(request, response);
	}
	
	public void EstadisticasVisualizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
		
		double promedioSaldos = 0;
		float porcentajeCuentasActivas = 0;
		float porcentajeCuentasInactivas = 0;
		double importeMovido = 0;
		double importeMovidoSemana = 0;

		String opcionSeleccionada = request.getParameter("estadistica");

		// Procesa las fechas en caso de ser necesario
		String fechaDesdeStr = null;
		String fechaHastaStr = null;
		Date fechaDesdeDate = new Date(0);
		Date fechaHastaDate = new Date(0);

		if ("option3".equals(opcionSeleccionada)) {
			fechaDesdeStr = request.getParameter("fechaDesde");
			fechaHastaStr = request.getParameter("fechaHasta");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				java.util.Date fechaDesdeParsed = sdf.parse(fechaDesdeStr);
				fechaDesdeDate = new java.sql.Date(fechaDesdeParsed.getTime());

				java.util.Date fechaHastaParsed = sdf.parse(fechaHastaStr);
				fechaHastaDate = new java.sql.Date(fechaHastaParsed.getTime());

			} catch (java.text.ParseException e) {
				e.printStackTrace();
				request.setAttribute("error", "Formato de fecha inválido");
				RequestDispatcher rd = request.getRequestDispatcher("/Statistics.jsp");
				rd.forward(request, response);
				return;
			}

		}

		// Dependiendo de la seleccion del usuario, se ejecuta una u otra función
		switch (opcionSeleccionada) {
		case "option1":
			promedioSaldos = cuentaNegocio.GetPromedioSaldos();

			String strpromedioSaldos = Double.toString(promedioSaldos);

			request.setAttribute("promedioSaldos", strpromedioSaldos);
			break;

		case "option2":
			porcentajeCuentasActivas = cuentaNegocio.GetPorcentajeCuentasActivas();
			porcentajeCuentasInactivas = cuentaNegocio.GetPorcentajeCuentasInactivas();

			// HAY QUE PARSEAR A STRING PARA MANDARLO COMO ATRIBUTO!
			String strPorcentajeCuentasActivas = Float.toString(porcentajeCuentasActivas);
			String strPorcentajeCuentasInactivas = Float.toString(porcentajeCuentasInactivas);

			request.setAttribute("porcentajeCuentasActivas", strPorcentajeCuentasActivas);
			request.setAttribute("porcentajeCuentasInactivas", strPorcentajeCuentasInactivas);
			break;

		case "option3":
			if (fechaDesdeDate != null && fechaHastaDate != null) {
				importeMovido = movimientoNegocio.GetCantidadMovida(fechaDesdeDate, fechaHastaDate);

				String strImporteMovido = Double.toString(importeMovido);

				request.setAttribute("importeMovido", strImporteMovido);
			}
			break;

		case "option4":
			importeMovidoSemana = movimientoNegocio.GetCantidadSemana();

			String strImporteMovidoSemana = Double.toString(importeMovidoSemana);

			request.setAttribute("importeMovidoSemana", strImporteMovidoSemana);
			break;

		default:
			// Nunca debería entrar en el default porque siempre hay una opción
			// seleccionada.
			break;
		}

		request.getRequestDispatcher("/Statistics.jsp").forward(request, response);
	}
}
