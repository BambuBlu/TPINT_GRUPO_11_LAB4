package integrador.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import integrador.model.Cuenta;
import integrador.model.TipoCuenta;
import integrador.negocioimpl.CuentaNegocioImpl;

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
    	   habilitarCuenta(cuentaNumero, response);
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
        response.sendRedirect("AdminListAccounts.jsp");
		
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
        response.sendRedirect("AdminListAccounts.jsp");
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
        response.sendRedirect("AdminListAccounts.jsp");
    }
}
