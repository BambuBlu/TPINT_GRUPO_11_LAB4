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

import integrador.model.Cuenta;
import integrador.model.Movimiento;
import integrador.model.SolicitudDeAlta;
import integrador.model.TipoCuenta;
import integrador.model.TipoMovimiento;
import integrador.negocioimpl.CuentaNegocioImpl;
import integrador.negocioimpl.MovimientoNegocioImpl;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
    	   System.out.println("Entro en Habilitar");
    	   habilitarCuenta(cuentaNumero, response);
       } else if ("altaCuenta".equals(accion)) {
    	   try {
    		   System.out.println("Entro en altaCuenta");
			ActivarCuenta(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
       }
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
}
