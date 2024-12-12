package integrador.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ParseConversionEvent;

import integrador.model.Cliente;
import integrador.model.Cuenta;
import integrador.model.Movimiento;
import integrador.model.Prestamo;
import integrador.model.SolicitudDeAlta;
import integrador.model.TipoCuenta;
import integrador.model.TipoMovimiento;
import integrador.model.Usuario;
import integrador.negocioimpl.ClienteNegocioImpl;
import integrador.negocioimpl.CuentaNegocioImpl;
import integrador.negocioimpl.MovimientoNegocioImpl;
import integrador.negocioimpl.PrestamoNegocioImpl;
import integrador.negocioimpl.SolicitudesDeCuentaNegocioImpl;

/**
 * Servlet implementation class ServletCuentaABM
 */
@WebServlet("/ServletCuentaABM")
public class ServletCuentaABM extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCuentaABM() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if ("solicitarPrestamos".equals(accion)) {
			SolicitarPrestamos(request, response);
		} else if ("Prestamos".equals(accion)) {
	    	   try {
	    		   CargarPrestamos(request, response);
	    	   } catch (SQLException e) {
	    		   e.printStackTrace();
	    	   }
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
        String cuentaNumero = request.getParameter("cuentaNumero");
        String tipoCuentaId = request.getParameter("tipoCuentaId");
        
		if ("Modificar".equals(accion)) {
    	   modificarTipo(cuentaNumero, tipoCuentaId, response);	
    	   
       } else if ("darBaja".equals(accion)) {
    	   darBajaCuenta(cuentaNumero, response);
    	   
       } else if ("habilitar".equals(accion)) {
    	   habilitarCuenta(cuentaNumero, response);
       } else if ("altaCuenta".equals(accion)) {
    	   try {
    		   ActivarCuenta(request, response);
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    	   }
       } else if ("BuscarCBU".equals(accion)) {
    	   BuscarCBU(request, response);
       } else if ("Transferir".equals(accion)) {
    	   Transferir(request, response);
       } else if ("AprobarPrestamo".equals(accion)) {
    	   try {
    		   AprobarPrestamo(request, response, 0);
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    	   }
       } else if ("RechazarPrestamo".equals(accion)) {
    	   try {
    		   AprobarPrestamo(request, response, 1);
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    	   }
       } else if ("PagarCuota".equals(accion)) {
    	   try {
    		   PagarCuota(request, response);
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    	   }
       } else if ("ConfirmarPrestamo".equals(accion)) {
		   ConfirmarPrestamo(request, response);
		} else if ("btnActualizarCuentaEnSesion".equals(accion)) {
			try {
				actualizarCuentaEnSesion(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private void actualizarCuentaEnSesion(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		  
		HttpSession session = request.getSession();
		//Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
		String usuarioDNI  =   request.getParameter("usuarioActualDNI");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		
		//Cliente clienteActual = clienteNegocio.obtenerCliente(usuario.getCliente().getDni());
		Cliente clienteActual = clienteNegocio.obtenerCliente(usuarioDNI);
    	CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		
		ArrayList<Cuenta> cuentasActivas = cuentaNegocio.GetAllActiveCuentasOfCliente(clienteActual.getDni());
		request.getSession().setAttribute("cuentasActivas", cuentasActivas);
		
		request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	    return;
	    
	}
	
    
	

	private void modificarTipo(String cuentaNumero, String tipoCuentaId, HttpServletResponse response) 
    		throws IOException {
        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        Cuenta cuenta = new Cuenta();
        TipoCuenta tipoCuenta = new TipoCuenta();
        if("1".equals(tipoCuentaId)) {
        	tipoCuenta.setId(2);
        } else {
        	tipoCuenta.setId(1);
        }
        
        cuenta.setNumeroDeCuenta(Integer.parseInt(cuentaNumero));
        cuenta.setTipoCuenta(tipoCuenta);

        cuentaNegocio.ModificarTipoCuenta(cuenta);
        response.sendRedirect("ServletClienteABM?accion=listarCuentas");
		
	}

	/**
     * Baja logica
     */
    private void darBajaCuenta(String cuentaNumero, HttpServletResponse response)
    		throws IOException {
        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroDeCuenta(Integer.parseInt(cuentaNumero));
        cuenta.setEstado("I");
        
        cuentaNegocio.ModificarCuenta(cuenta);
        response.sendRedirect("ServletClienteABM?accion=listarCuentas");
    }

    /**
     * Habilitacion de cuenta
     */
    private void habilitarCuenta(String cuentaNumero, HttpServletResponse response)
    		throws IOException {
        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroDeCuenta(Integer.parseInt(cuentaNumero));
        cuenta.setEstado("A");
        
        cuentaNegocio.ModificarCuenta(cuenta);
        response.sendRedirect("ServletClienteABM?accion=listarCuentas");
    }
    
    public void ActivarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	SolicitudesDeCuentaNegocioImpl solicitudesNegocio = new SolicitudesDeCuentaNegocioImpl();
    	CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
    	MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
    	
		int solicitudId = Integer.parseInt(request.getParameter("solicitudId"));

		SolicitudDeAlta solicitud = solicitudesNegocio.obtenerSolicitudPorId(solicitudId);
		if (solicitud != null) {

			Cuenta nuevaCuenta = new Cuenta();

			nuevaCuenta.setDni(solicitud.getCliente().getDni());
			nuevaCuenta.setFechaDeCreacion(new Date(System.currentTimeMillis()));

			TipoCuenta tipoCuenta = new TipoCuenta();
			tipoCuenta.setId(solicitud.getTipoCuenta().getId());
			tipoCuenta.setDescripcion(solicitud.getTipoCuenta().getDescripcion());
			nuevaCuenta.setTipoCuenta(tipoCuenta);
			int cbu = 0;
			cbu = cuentaNegocio.obtenerUltimoCBU();

			nuevaCuenta.setCbu(cbu + 1);
			nuevaCuenta.setSaldo(10000.00);

			cuentaNegocio.AgregarCuenta(nuevaCuenta);
			solicitudesNegocio.eliminarSolicitud(solicitudId);

			nuevaCuenta = cuentaNegocio.buscarPorCBU(nuevaCuenta.getCbu());

			Movimiento movimiento = new Movimiento();

			movimiento.setCuenta(nuevaCuenta);
			movimiento.setFecha(new Date(System.currentTimeMillis()));
			movimiento.setDetalle("Alta de cuenta");
			movimiento.setImporte(10000);

			TipoMovimiento tipoMovimiento = new TipoMovimiento();
			tipoMovimiento.setId(1);
			tipoMovimiento.setDescripcion("Alta de cuenta");

			movimiento.setTipoMovimiento(tipoMovimiento);
			movimiento.setCuentaDestino(nuevaCuenta.getNumeroDeCuenta());

			movimientoNegocio.agregarMovimiento(movimiento);

			ArrayList<SolicitudDeAlta> solicitudes = (ArrayList<SolicitudDeAlta>) solicitudesNegocio
					.obtenerTodasLasSolicitudesActivas();
			request.setAttribute("solicitudes", solicitudes);
			RequestDispatcher rd = request.getRequestDispatcher("AdminAccountApplicationList.jsp");
			rd.forward(request, response);
		}
	}
    
    public void BuscarCBU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cliente clienteLogeado = (Cliente) session.getAttribute("cliente");
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

		if (clienteLogeado != null) {
			try {
				int cbu = Integer.parseInt(request.getParameter("txtCBU"));

				Cuenta cuentaDestino = cuentaNegocio.buscarPorCBU(cbu);
				Cliente clienteDestino = clienteNegocio.obtenerCliente(cuentaDestino.getDni());

				// Obtener todas las cuentas activas
				ArrayList<Cuenta> cuentasActivas = cuentaNegocio.GetAllActiveCuentas();
				ArrayList<Cuenta> cuentasCliente = new ArrayList<>();

				for (Cuenta c : cuentasActivas) {
					if (Integer.valueOf(clienteLogeado.getDni()).equals(Integer.valueOf(c.getDni()))) {
						cuentasCliente.add(c);
					}
				}

				if (!cuentasCliente.isEmpty()) {
					request.setAttribute("cliente", clienteLogeado);
					request.setAttribute("cuentaDestino", cuentaDestino);
					request.setAttribute("cuentasCliente", cuentasCliente);
					request.setAttribute("clienteDestino", clienteDestino);
					request.getRequestDispatcher("Transfers.jsp").forward(request, response);
					return;
				} else {
					request.setAttribute("mensaje", "El cliente no tiene cuentas activas.");
				}
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "CBU inválido.");
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al buscar la cuenta: " + e.getMessage());
			}
		} else {
			request.setAttribute("mensaje", "No se encontró el cliente logeado.");
		}
		request.getRequestDispatcher("Transfers.jsp").forward(request, response);
	}
    
    
	public void Transferir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();

		String detalle = request.getParameter("concepto");

		// Obtener cuenta origen seleccionada por el usuario
		String cbuOrigen = request.getParameter("cuentaOrigen");
		Cuenta cuentaOrigen = cuentaNegocio.buscarPorCBU(Integer.parseInt(cbuOrigen));

		// Obtener cuenta destino
		String cbuDestino = request.getParameter("cuentaDestino");
		Cuenta cuentaDestinoObj = cuentaNegocio.buscarPorCBU(Integer.parseInt(cbuDestino));

		// Obtener monto de la transferencia
		String montoStr = request.getParameter("txtMonto");
		double monto = 0.0;

		if (montoStr != null && !montoStr.isEmpty()) {
			try {
				monto = Double.parseDouble(montoStr);
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "El monto ingresado no es válido.");
				request.getRequestDispatcher("Transfers.jsp").forward(request, response);
				return;
			}
		} else {
			request.setAttribute("mensaje", "Debe ingresar un monto válido.");
			request.getRequestDispatcher("Transfers.jsp").forward(request, response);
			return;
		}

		// Verificar saldo suficiente en la cuenta origen
		if (cuentaOrigen.getSaldo() < monto) {
			request.setAttribute("mensaje", "Saldo insuficiente en la cuenta origen.");
			request.getRequestDispatcher("Transfers.jsp").forward(request, response);
			return;
		}

		// Realizar la transferencia
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
		cuentaDestinoObj.setSaldo(cuentaDestinoObj.getSaldo() + monto);

		int filasActualizadas = cuentaNegocio.modificarSaldoCuenta(cuentaOrigen);
		filasActualizadas += cuentaNegocio.modificarSaldoCuenta(cuentaDestinoObj);

		if (filasActualizadas == 2) {
			MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
			Movimiento movimientoOrigen = new Movimiento();
			Movimiento movimientoDestino = new Movimiento();

			movimientoOrigen.setCuenta(cuentaOrigen);
			movimientoOrigen.setFecha(new Date(System.currentTimeMillis()));
			movimientoOrigen.setDetalle(detalle);
			movimientoOrigen.setImporte(monto);

			TipoMovimiento tm = new TipoMovimiento();
			tm.setId(4);
			tm.setDescripcion("Transferencia");

			movimientoOrigen.setTipoMovimiento(tm);
			movimientoOrigen.setCuentaOrigen(cuentaOrigen.getNumeroDeCuenta());
			movimientoOrigen.setCuentaDestino(cuentaDestinoObj.getNumeroDeCuenta());

			movimientoDestino.setCuenta(cuentaDestinoObj);
			movimientoDestino.setFecha(new Date(System.currentTimeMillis()));
			movimientoDestino.setDetalle(detalle);
			movimientoDestino.setImporte(monto);
			movimientoDestino.setTipoMovimiento(tm);
			movimientoDestino.setCuentaOrigen(cuentaOrigen.getNumeroDeCuenta());
			movimientoDestino.setCuentaDestino(cuentaDestinoObj.getNumeroDeCuenta());

			movimientoNegocio.agregarMovimiento(movimientoOrigen);
			movimientoNegocio.agregarMovimiento(movimientoDestino);

			request.setAttribute("mensaje", "Transferencia realizada con éxito.");
		} else {
			request.setAttribute("mensaje", "Error al realizar la transferencia. Inténtelo nuevamente.");
		}

		request.getRequestDispatcher("Transfers.jsp").forward(request, response);
	}
	
	
	public void ConfirmarPrestamo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { //DoPost

		String montoSolicitado = request.getParameter("txtMontoSolicitado");
		String cantidadCuotas = request.getParameter("txtCantidadDeCuotas");
		String cuentaDestino = request.getParameter("txtCuentaDestino");

		// Validar que los parámetros no sean nulos ni estén vacíos
		if (montoSolicitado == null || cantidadCuotas == null || cuentaDestino == null || montoSolicitado.isEmpty()
				|| cantidadCuotas.isEmpty() || cuentaDestino.isEmpty()) {
			System.out.println("Error: Parámetros faltantes o vacíos.");
			return;
		}

		// Verificar que el cliente esté logueado
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");

		if (clienteLogueado == null) {
			System.out.println("Error: Cliente no logueado.");
			return;
		}

		try {
			double montoPedido = Double.parseDouble(montoSolicitado);
			int cantidadCuotasInt = Integer.parseInt(cantidadCuotas);
			int cuotaPagar;

		/*	if (cantidadCuotasInt == 1) {
				cuotaPagar = 3;
			} else if (cantidadCuotasInt == 2) {
				cuotaPagar = 6;
			} else {
				cuotaPagar = 12;
			}*/

			if (cantidadCuotasInt == 3) { // ya desde el jsp se envia la cantidad de cuotas en el value del option
					cuotaPagar = 3;
				} else if (cantidadCuotasInt == 6) {
					cuotaPagar = 6;
				} else {
					cuotaPagar = 12;
				}

			double montoConInteres = montoPedido * 1.1;
			double cuotaConInteres = montoConInteres / cuotaPagar;

			// Crear objeto de préstamo y setear los valores
			CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
			PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
			Prestamo prestamo = new Prestamo();
			prestamo.setCliente(clienteLogueado);
			Cuenta cuenta = cuentaNegocio.buscarPorCBU(Integer.parseInt(cuentaDestino));

			prestamo.setCuenta(cuenta);

			prestamo.setFecha(new Date(System.currentTimeMillis()));
			prestamo.setImporteConIntereses(montoConInteres);
			prestamo.setImportePedido(montoPedido);
			prestamo.setPlazoDePagoEnMeses(cuotaPagar);
			prestamo.setMontoMensual(cuotaConInteres);
			prestamo.setCuotasPagadas(cuotaPagar);
			prestamo.setEstado("I"); // I para inactivo, A para activo.
			prestamo.setPrestamoSaldado("N"); // N para No, S para Sí 

			prestamoNegocio.solicitarPrestamo(prestamo);

			this.SolicitarPrestamos(request, response);

		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
			e.printStackTrace();
		}
	}
	
	
	public void SolicitarPrestamos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cliente clienteLogeado = (Cliente) session.getAttribute("cliente");
		
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		
		ArrayList<Cuenta> cuentasActivas = cuentaNegocio.GetAllActiveCuentasOfCliente(clienteLogeado.getDni());
		
		request.setAttribute("ListCuentasActivas", cuentasActivas);
		
		RequestDispatcher requestDispatch = request.getRequestDispatcher("ApplyLoan.jsp");
		requestDispatch.forward(request, response);
	}
	
	public void AprobarPrestamo(HttpServletRequest request, HttpServletResponse response, int p)
			throws ServletException, IOException, SQLException { // doPost
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		String id = request.getParameter("prestamoSeleccionadoId");
		int idInt = Integer.parseInt(id);
		
		Prestamo prestamo = prestamoNegocio.getPrestamos(idInt,1);
		System.out.println( prestamo.toString());
		if(p == 0) {
			prestamoNegocio.activarPrestamo(prestamo.getCuenta().getNumeroDeCuenta(),idInt,prestamo.getCuenta().getCbu(),prestamo.getImportePedido());
		}
		else if (p == 1) {
			prestamoNegocio.rechazarPrestamo(idInt);
		}
		

		ArrayList<Prestamo> listaPrestamosInactivos = (ArrayList<Prestamo>) prestamoNegocio.getPrestamosInactivos();
		request.setAttribute("listaPrestamosInactivos", listaPrestamosInactivos);

		RequestDispatcher rd = request.getRequestDispatcher("LoanApplicationList.jsp");
		rd.forward(request, response);
	}
	
	public void CargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");

		// cuentas activas por dni
		ArrayList<Cuenta> cuentasActivas = cuentaNegocio.GetAllActiveCuentasOfCliente(clienteLogueado.getDni());
		request.setAttribute("ListCuentasActivas", cuentasActivas);
		
		// Prestamos activos por cliente
		ArrayList<Prestamo> prestamosActivos = prestamoNegocio.getPrestamosXCliente(clienteLogueado);
		request.setAttribute("prestamosActivos", prestamosActivos);
		
		RequestDispatcher rd = request.getRequestDispatcher("Loans.jsp");
		rd.forward(request, response);
		
	}
	
	public void PagarCuota(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		String idPrestamo = request.getParameter("txtPrestamosActivo");
		String cbu = request.getParameter("txtcuentaADebitar");

		int idPrestamoInt = Integer.parseInt(idPrestamo);
		int cbuInt = Integer.parseInt(cbu);
		
		Cuenta cuenta = cuentaNegocio.buscarPorCBU(cbuInt);		
		int numCuenta = cuenta.getNumeroDeCuenta();
				
		Prestamo prestamo = prestamoNegocio.getPrestamos(idPrestamoInt,0);							
		
		double importe = prestamo.getMontoMensual();
		
		 // Verificar si el saldo es suficiente para realizar el pago
	    if (importe <= cuenta.getSaldo()) 
	    {
	        // Realizar el pago del préstamo
	    	prestamoNegocio.pagarPrestamo(idPrestamoInt, numCuenta, cbuInt, importe);
	    	
	    } 
	    else 
	    {
	        // Si el saldo no es suficiente, configurar un mensaje de error
	        request.setAttribute("mensajeError", "Saldo insuficiente para pagar el préstamo.");
	    }
		CargarPrestamos(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("Loans.jsp");
		rd.forward(request, response);
	}
}
