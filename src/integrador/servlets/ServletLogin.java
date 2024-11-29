package integrador.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import integrador.model.Cliente;
import integrador.model.Cuenta;
import integrador.model.Usuario;
import integrador.negocioimpl.ClienteNegocioImpl;
import integrador.negocioimpl.CuentaNegocioImpl;
import integrador.negocioimpl.UsuarioNegocioImpl;
import integrador.enums.Roles;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        if ("logout".equals(request.getParameter("action"))) {
            realizarLogout(request, response);
        } else {
            response.getWriter().append("Served at: ").append(request.getContextPath());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnInicioSesion") != null) {
			try {
				IniciarSesionUsuario(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void IniciarSesionUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    String nombreUsuario = request.getParameter("txtNombre");
	    String contraseniaUsuario = request.getParameter("txtcontrasenia");

	    // Validacion de valores nulos o vacios
	    if ((nombreUsuario != null && !nombreUsuario.isEmpty())
	            && (contraseniaUsuario != null && !contraseniaUsuario.isEmpty()))
			// Logica con la base de datos para verificar si existe y esta hablitado de
			// pasar el filtro de seguridad, si todo esta en orden, se crea el objeto y se
			// guarda en Session
	    	{

	        UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
	        ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

	        for (Usuario usuario : usuarioNegocio.GetAllUsuariosActivos()) {
	            if (usuario.getNombreUsuario().equals(nombreUsuario)
	                    && usuario.getContrasena().equals(contraseniaUsuario)) {
	                request.getSession().setAttribute("SessionActual", usuario);
	                
	                if (usuario.getRol() == Roles.CLIENTE) {
	                	Cliente clienteActual = clienteNegocio.obtenerCliente(usuario.getCliente().getDni());
	                	CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
	                	
	                	if (clienteActual != null) {
	    					ArrayList<Cuenta> cuentasActivas = cuentaNegocio.GetAllActiveCuentasOfCliente(clienteActual.getDni());
	    					ArrayList<Cuenta> cuentasInactivas = cuentaNegocio.GetAllInactiveCuentasOfCliente(clienteActual.getDni());

	    					clienteActual.setCuentas(new ArrayList<Cuenta>());
	    					clienteActual.getCuentas().addAll(cuentasActivas);
	    					clienteActual.getCuentas().addAll(cuentasInactivas);

	    					request.getSession().setAttribute("cliente", clienteActual);
	    					request.getSession().setAttribute("cuentasActivas", cuentasActivas);
	    					request.getSession().setAttribute("cuentasInactivas", cuentasInactivas);
	    				}
	    				
	                    request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	                    return;
	                    
	                } else if (usuario.getRol() == Roles.ADMIN) {
	                	Cliente clienteActual = clienteNegocio.obtenerCliente(usuario.getCliente().getDni());
	    				request.getSession().setAttribute("cliente", clienteActual);
	                    request.getRequestDispatcher("AdminMainPage.jsp").forward(request, response);
	                    return;
	                }
	            }
	        }

	        // Usuario no encontrado
	        request.setAttribute("errorMessage", "Usuario inexistente, intentelo de nuevo...");
	        request.getRequestDispatcher("/Index.jsp").forward(request, response);

	    } else {
	        // Campos vacíos
	        request.setAttribute("errorMessage", "Por favor, complete todos los campos.");
	        request.getRequestDispatcher("/Index.jsp").forward(request, response);
	    }
	}
	
    private void realizarLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("Index.jsp");
    }

}
