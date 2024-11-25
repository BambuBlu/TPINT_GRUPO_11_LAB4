package integrador.servlets;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import integrador.negocioimpl.PaisNegocioImpl;
import integrador.negocioimpl.ProvinciaNegocioImpl;
import integrador.negocioimpl.UsuarioNegocioImpl;
import integrador.enums.Roles;
import integrador.model.Cliente;
import integrador.model.Cuenta;
import integrador.model.Generos;
import integrador.model.Localidad;
import integrador.model.Movimiento;
import integrador.model.Pais;
import integrador.model.Provincia;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entro a doPost");
		
        String accion = request.getParameter("accion");
        String clienteId = request.getParameter("clienteId");
        String clienteEstado = request.getParameter("clienteEstado");
        
        
        System.out.println("Valor de [accion] = " + accion);
        System.out.println("Valor de [clienteId] = " + clienteId);
        System.out.println("Valor de [clienteEstado] = " + clienteEstado);
		
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
    	   System.out.println("Entro a submitEdit");
    	   SubmitModificarCliente(clienteEstado, request, response);
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
		String nacionalidad = request.getParameter("txtNacionalidad");

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
			RequestDispatcher rd = request.getRequestDispatcher("/CrearCliente.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		if (!contraseña.equals(repetirContraseña)) {
			System.out.println("Las contraseñas no coinciden");
			request.setAttribute("error", "Las contraseñas no coinciden");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearCliente.jsp");
			rd.forward(request, response);
			return;
		}

		
		Cliente nuevoCliente = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad, (java.sql.Date) fechaNacimiento,
				direccion, ob_localidad, email, telefono);

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
				movimiento.setFecha((java.sql.Date) new Date(System.currentTimeMillis()));
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
    }
   
    /**
     * Baja logica
     */
    private void darBajaCliente(String clienteId, HttpServletResponse response)
    		throws IOException {
    	System.out.println("Entro a darBajaCliente");
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
    private void habilitarCliente(String clienteId, HttpServletResponse response)
    		throws IOException {
    	System.out.println("Entro a habilitarCliente");
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
    
	
    
    private void ModificarCliente(String clienteId, String clienteEstado, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException, SQLException {
    	System.out.println("Entro a ModificarCliente");
    	ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
        Cliente cliente = clienteNegocio.obtenerCliente(clienteId);
        UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		Usuario usuario = usuarioNegocio.obtenerUsuario(cliente.getDni());

		request.setAttribute("clienteModificar", cliente);
		request.setAttribute("usuarioModificar", usuario);
		request.setAttribute("estadoUsuario", clienteEstado);
		
		System.out.println("Valor de [cliente] = " + cliente);
		System.out.println("Valor de [request.setAttribute(\"clienteModificar\")] = " + request.getAttribute("clienteModificar"));
		System.out.println("Valor de [usuario] = " + usuario);
		System.out.println("Valor de [request.setAttribute(\"usuarioModificar\")] = " + request.getAttribute("usuarioModificar"));
		
		
		RequestDispatcher rd = request.getRequestDispatcher("UserModify.jsp");
		rd.forward(request, response);
	}
    
    
    private void SubmitModificarCliente(String clienteEstado, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	System.out.println("Entro a SubmitModificarCliente");
    	
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
    	Cliente cliente = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad, (java.sql.Date) fechaNacimiento, direccion,
				ob_localidad, email, telefono, clienteEstado);
    	
    	UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		Usuario usuario = usuarioNegocio.obtenerUsuario(cliente.getDni());
		usuario.setContrasena(contraseña);
		
		
    	clienteNegocio.ModificarCliente(cliente, usuario);
    	
        if("A".equals(clienteEstado)) {
        	response.sendRedirect("AdminUserList.jsp?listaClientes=listaClientesInactivos");
        } else {
        	response.sendRedirect("AdminUserList.jsp?listaClientes=listaClientesActivos");
        }
	}
}
