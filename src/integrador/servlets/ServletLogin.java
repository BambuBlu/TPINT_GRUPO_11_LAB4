package integrador.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import integrador.model.Usuario;
import integrador.enums.Roles;
import integrador.model.Cliente;


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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("btnInicioSesion") != null) {
			IniciarSesionUsuario(request, response);
		}
	}
	
	public void IniciarSesionUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("txtNombre");
		String contraseniaUsuario = request.getParameter("txtContrasenia");
		
		//Validacion de valores nulos o vacios 
		if ((nombreUsuario != null || nombreUsuario != "") && (contraseniaUsuario != null || contraseniaUsuario != "")) {
			//Logica con la base de datos para verificar si existe y esta hablitado de pasar el filtro de seguridad, si todo esta en orden, se crea el objeto y se guarda en Session 
			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setContrasena(contraseniaUsuario);
			usuario.setRol(Roles.CLIENTE);
			//por ahora para pasar el filtro, falta logica
			usuario.setBaja(false);
			
			//Hay que recoctar todos los datos personales del cliente y almacenarlos en esta propiedad
			usuario.setCliente(new Cliente ());
			
			if (!usuario.getBaja()) {
				request.getSession().setAttribute("SessionActual", usuario);
				if (usuario.getRol() == Roles.CLIENTE)
					request.getRequestDispatcher("MainPage.jsp").forward(request, response); 
				else if (usuario.getRol() == Roles.ADMIN)
					request.getRequestDispatcher("AdminMainPage.jsp").forward(request, response); 
					
			}	
		}
		else {
			request.setAttribute("SessionActual", "Por favor, complete todos los campos.");
	        request.getRequestDispatcher("index.jsp").forward(request, response); 
		}	
	}

	

}
