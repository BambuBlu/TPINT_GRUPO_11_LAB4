package integrador.servlets;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import integrador.model.Usuario;
import integrador.enums.Roles;
import integrador.model.Cliente;
import integrador.model.Generos;
import integrador.model.Localidad;

/**
 * Servlet implementation class ServletClienteABM
 */
@WebServlet("/ServletClienteABM")
public class ServletClienteABM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * ACCIONES DE CREAR, ELIMINAR Y ACTUALIZAR SERAN LLAMADAS DESDE EL doPost, dependiendo que cual se solicite.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "crear":
                crearCliente(request, response);
                break;
          /*  case "eliminar":
                eliminarCliente(request, response);
                break;
            case "actualizar":
                actualizarCliente(request, response);
                break;*/
  
        }
    }
	
    public ServletClienteABM() {
        super();
        // TODO Auto-generated constructor stub
    }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @see HttpServlet#HttpServlet()
     */

    
    /**
     * Create Cliente
     */
    
    private void crearCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Genero
    	String sexoValue = request.getParameter("txtSexo");
    	Generos sexo = new Generos();
    	if (sexoValue != null && !sexoValue.isEmpty()) {
    	    if (sexoValue.equals("1")) {
    	        sexo.setDescripcion("Masculino");
    	    } else if (sexoValue.equals("2")) {
    	        sexo.setDescripcion("Femenino");
    	    }
    	}
    	//Fecha nacimiento
    	  String fechaNacimientoStr = request.getParameter("fechaNacimiento");

    	    // Define the expected date format
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Convierto formato
    	    java.util.Date utilDate = null;
    	    try {
    	        // string a date
    	    	 utilDate = sdf.parse(fechaNacimientoStr);
    	    } catch (Exception e) {
    	        e.printStackTrace(); // 
    	    }
    	    // Convierto java.util.Date a java.sql.Date (el usado en la clase)
    	    java.sql.Date fechaNacimiento = new java.sql.Date(utilDate.getTime());
        
        // PAIS, PROVINCIA, LOCALIDAD
    	//Localidad localidad = new Localidad();
    	//localidad.set
    	
    	Usuario usuario = new Usuario();
    	 
        Cliente cliente = new Cliente();
        cliente.setDni(request.getParameter("txtDNI"));
        cliente.setCuil(request.getParameter("txtCUIL"));
        cliente.setNombre(request.getParameter("txtNombre"));
        cliente.setApellido(request.getParameter("txtApellido"));
        cliente.setSexo(sexo);  
        cliente.setNacionalidad(request.getParameter("txtNacionalidad"));
        //****** FALTA REIVISAR VER PAIS, PROVINCIA Y LOCALIDAD
        cliente.setFechaNacimiento(fechaNacimiento); // 
        cliente.setDireccion(request.getParameter("txtDireccion"));
        cliente.setEmail(request.getParameter("txtEmail"));
        cliente.setTelefono(request.getParameter("txtTelefono"));
        
        // Creo usuario
        usuario.setCliente(cliente);
        usuario.setNombreUsuario("txtUsuario");
        usuario.setContrasena("txtContrasenia");


        // 
        ////ClienteDAO clienteDAO = new ClienteDAO();  FALTA CREAR BD
        ////clienteDAO.create(cliente);

        response.sendRedirect("Index.jsp"); // Redirige a pagina principal para Loguearse
    }
    
    
    



}
