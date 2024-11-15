package integrador.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import integrador.model.Usuario;
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
			IniciarSesionUsuario(request, response);
		}
	}
	
	public void IniciarSesionUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String nombreUsuario = request.getParameter("txtNombre");
	    String contraseniaUsuario = request.getParameter("txtcontrasenia");

	    // Validacion de valores nulos o vacios
	    if ((nombreUsuario != null && !nombreUsuario.isEmpty())
	            && (contraseniaUsuario != null && !contraseniaUsuario.isEmpty()))
			// Logica con la base de datos para verificar si existe y esta hablitado de
			// pasar el filtro de seguridad, si todo esta en orden, se crea el objeto y se
			// guarda en Session
	    	{

	        UsuarioNegocioImpl negocio = new UsuarioNegocioImpl();

	        for (Usuario usuario : negocio.GetAllUsuariosActivos()) {
	            if (usuario.getNombreUsuario().equals(nombreUsuario)
	                    && usuario.getContrasena().equals(contraseniaUsuario)) {
	                request.getSession().setAttribute("SessionActual", usuario);
	                
	                if (usuario.getRol() == Roles.CLIENTE) {
	                    request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	                    return;
	                } else if (usuario.getRol() == Roles.ADMIN) {
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
