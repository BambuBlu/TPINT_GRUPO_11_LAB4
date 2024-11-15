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
import integrador.negocioimpl.ClienteNegocioImpl;
import integrador.negocioimpl.LocalidadNegocioImpl;
import integrador.negocioimpl.PaisNegocioImpl;
import integrador.negocioimpl.ProvinciaNegocioImpl;
import integrador.enums.Roles;
import integrador.model.Cliente;
import integrador.model.Generos;
import integrador.model.Localidad;
import integrador.model.Pais;
import integrador.model.Provincia;

/**
 * Servlet implementation class ServletClienteABM
 */
@WebServlet("/ServletClienteABM")
public class ServletClienteABM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * METODO do Post / ACCIONES DE CREAR, ELIMINAR Y ACTUALIZAR, dependiendo que
	 * cual se solicite.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("btnCrearCliente") != null) {
			crearCliente(request, response);
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
    	
    	// HardCodeo la Localidad a causa  de error en el negocio (Solucionar)
    	Localidad localidad = new Localidad(1, "Capital Federal", new Provincia(1, "Buenos Aires", new Pais(1, "Argentina")));
    	
    	//Pais
    	PaisNegocioImpl negociopais = new PaisNegocioImpl();
    	String nacionalidad = negociopais.Find(Integer.parseInt(request.getParameter("txtPais"))).getNombre(); 
    	
    	//Localidad (Forma correcta de Get)
    	//LocalidadNegocioImpl negociolocalidad = new LocalidadNegocioImpl();
    	//Localidad localidadSeleccionada = negociolocalidad.Find(Integer.parseInt(request.getParameter("txtLocalidad")));
    	
    	//Provincia
    	ProvinciaNegocioImpl provinciaNegocio = new ProvinciaNegocioImpl();
    	Provincia provinciaSeleccionada = provinciaNegocio.Find(Integer.parseInt(request.getParameter("txtProvincia")));
    	
    	Usuario usuario = new Usuario();
    	 
        Cliente cliente = new Cliente();
        cliente.setDni(request.getParameter("txtDNI"));
        cliente.setCuil(request.getParameter("txtCUIL"));
        cliente.setNombre(request.getParameter("txtNombre"));
        cliente.setApellido(request.getParameter("txtApellido"));
        cliente.setSexo(sexo);  
        cliente.setFechaNacimiento(fechaNacimiento); 
        cliente.setNacionalidad(nacionalidad);
        //Hay que agregar el parametro provincia en clase y en base de datos
        //cliente.setProvincia(provinciaSeleccionada)
        cliente.setLocalidad(localidad);
        
        cliente.setDireccion(request.getParameter("txtDireccion"));
        cliente.setEmail(request.getParameter("txtEmail"));
        cliente.setTelefono(request.getParameter("txtTelefono"));
        
        // Creo usuario
        usuario.setCliente(cliente);
        usuario.setNombreUsuario(request.getParameter("txtUsuario").toString());
        usuario.setContrasena(request.getParameter("txtContrasenia").toString());
        usuario.setRol(Roles.CLIENTE);
        usuario.setBaja(false);

        ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
        clienteNegocio.CrearCliente(cliente, usuario);

        response.sendRedirect("Index.jsp");
    }

}
