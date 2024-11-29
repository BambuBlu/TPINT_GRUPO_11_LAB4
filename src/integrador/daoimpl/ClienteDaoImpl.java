package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import integrador.dao.ClienteDao;
import integrador.model.Cliente;
import integrador.model.Generos;
import integrador.model.Localidad;
import integrador.model.Provincia;
import integrador.model.Usuario;
import integrador.utils.DataAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;

// Implemeto la logica del Dao de Cliente
public class ClienteDaoImpl implements ClienteDao {

	public ClienteDaoImpl() {

	}

	@Override
	public ArrayList<Cliente> GetAllClientes() throws SQLException {
	    String query = "SELECT c.dni, c.cuil, c.nombre, c.apellido, c.sexo_id, g.descripcion AS genero_descripcion, " +
                "c.nacionalidad, c.fecha_nacimiento, c.direccion, c.localidad_id, l.nombre AS localidad_nombre, " +
                "p.id AS provincia_id, p.nombre AS provincia_nombre, c.mail, c.telefono, c.estado " +
                "FROM clientes c " +
                "INNER JOIN generos g ON c.sexo_id = g.id " +
                "INNER JOIN localidades l ON c.localidad_id = l.id " +
                "INNER JOIN provincias p ON l.id_provincia = p.id";
	    ArrayList<Cliente> clientes = new ArrayList<>();

	    try (Connection conn = DataAccess.GetConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet resultquery = stmt.executeQuery(query)) {

	        while (resultquery.next()) {
	            String dni = resultquery.getString("dni");
	            String cuil = resultquery.getString("cuil");
	            String nombre = resultquery.getString("nombre");
	            String apellido = resultquery.getString("apellido");
	            Generos sexo = new Generos();
	            sexo.setId(resultquery.getInt("sexo_id"));
	            sexo.setDescripcion(resultquery.getString("genero_descripcion"));
	            String nacionalidad = resultquery.getString("nacionalidad");
	            Date fechaNacimiento = resultquery.getDate("fecha_nacimiento");
	            String direccion = resultquery.getString("direccion");
	            Localidad localidad = new Localidad();
	            localidad.setId(resultquery.getInt("localidad_id"));
	            localidad.setNombre(resultquery.getString("localidad_nombre"));
	            Provincia provincia = new Provincia();
	            provincia.setId(resultquery.getInt("provincia_id"));
	            provincia.setNombre(resultquery.getString("provincia_nombre"));
	            localidad.setProvincia(provincia);
	            String email = resultquery.getString("mail");
	            String telefono = resultquery.getString("telefono");
	            String estado = resultquery.getString("estado");

	            Cliente cliente = new Cliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, email, telefono, estado);
	            clientes.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    return clientes;
	}
	
	@Override
	public ArrayList<Cliente> GetAllActiveClientes() throws SQLException {

		ArrayList<Cliente> listaClientes = new ArrayList<>();

		String query = "SELECT * FROM clientes where estado = 'A'";

		Connection conn = DataAccess.GetConnection();
		try (CallableStatement stmtCliente = conn.prepareCall(query); ResultSet rs = stmtCliente.executeQuery()) {
			GeneroDaoImpl generoDaoImpl = new GeneroDaoImpl();
			ArrayList<Generos> listaGeneros = generoDaoImpl.GetAllGeneros();
			LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();
			ArrayList<Localidad> listaLocalidades = localidadDaoImpl.GetAllLocalidad();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));

				for (Generos genero : listaGeneros) {
					if (genero.getId() == rs.getInt("sexo_id")) {
						cliente.setSexo(genero);
					}
				}

				cliente.setNacionalidad(rs.getString("nacionalidad"));

				for (Localidad localidad : listaLocalidades) {
					if (localidad.getId() == rs.getInt("localidad_id")) {
						cliente.setLocalidad(localidad);
					}
				}

				cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				cliente.setDireccion(rs.getString("direccion"));

				cliente.setEmail(rs.getString("mail"));
				cliente.setTelefono(rs.getString("telefono"));

				listaClientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	
	@Override
	public boolean CrearCliente(Cliente nuevoCliente, Usuario nuevoUsuario) {

		try {
			DataAccess.executeStoredProcedure("CrearCliente", nuevoCliente.getDni(), nuevoCliente.getCuil(),
					nuevoCliente.getNombre(), nuevoCliente.getApellido(), nuevoCliente.getSexo().getId(),
					nuevoCliente.getNacionalidad(), nuevoCliente.getFechaNacimiento(),
					nuevoCliente.getDireccion(), nuevoCliente.getLocalidad().getId(), nuevoCliente.getEmail(),
					nuevoCliente.getTelefono(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getContrasena());
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	public boolean ModificarEstadoCliente(Cliente clienteModificado, Usuario usuarioModificado) throws SQLException {
	    String sqlCliente = "{CALL ModificarEstadoCliente(?, ?)}";
	    String sqlUsuario = "{CALL ModificarEstadoUsuario(?, ?)}";
	    
	    Connection conn = null;
	    conn = DataAccess.GetConnection();
	    try (CallableStatement stmtCliente = conn.prepareCall(sqlCliente); CallableStatement stmtUsuario = conn.prepareCall(sqlUsuario)) {
	        
	        stmtCliente.setString(1, clienteModificado.getDni());
	        stmtCliente.setString(2, clienteModificado.getEstado());
		    
	        int rowsAffectedCliente = stmtCliente.executeUpdate();
	        
	        if (rowsAffectedCliente > 0) {
	            stmtUsuario.setLong(1, usuarioModificado.getId_Usaurio());
	            stmtUsuario.setString(2, usuarioModificado.getBaja() ? "A" : "I");
			    
	            int rowsAffectedUsuario = stmtUsuario.executeUpdate();
	            
	            if (rowsAffectedUsuario > 0) {
	                return true;
	            }
	        }
	        return false;
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar los procedimientos: " + e.getMessage());
	        return false;
	    }
	}
	
	@Override
	public boolean ModificarCliente(Cliente clienteModificado, Usuario usuarioModificado) throws SQLException {
		String sqlCliente = "{CALL ModificarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	    
	    /*Connection conn = null;
	    conn = DataAccess.GetConnection();
	    try (CallableStatement stmtCliente = conn.prepareCall(sqlCliente))*/
	    try (Connection conn = DataAccess.GetConnection(); 
	            CallableStatement stmtCliente = conn.prepareCall(sqlCliente)) // lo genero como TRY-WITH-RESOURCES para cierre auto
	    {
	        
	        stmtCliente.setString(1, clienteModificado.getDni());
	        stmtCliente.setString(2, clienteModificado.getCuil());
	        stmtCliente.setString(3, clienteModificado.getNombre());
	        stmtCliente.setString(4, clienteModificado.getApellido());
	        stmtCliente.setInt(5, clienteModificado.getSexo().getId());
	        stmtCliente.setString(6, clienteModificado.getNacionalidad());
	        stmtCliente.setDate(7, clienteModificado.getFechaNacimiento());
	        stmtCliente.setString(8, clienteModificado.getDireccion());
	        stmtCliente.setInt(9, clienteModificado.getLocalidad().getId());
	        stmtCliente.setString(10, clienteModificado.getEmail());
	        stmtCliente.setString(11, clienteModificado.getTelefono());
	        stmtCliente.setString(12, usuarioModificado.getContrasena());
		    
	        int rowsAffectedCliente = stmtCliente.executeUpdate();
	        
	        if (rowsAffectedCliente > 0) {
	        	return true;
	        }
	        return false;
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar los procedimientos: " + e.getMessage());
	        
	        e.printStackTrace(); // Imprime el error completo en la consola
	        System.out.println("Código SQL Error: " + e.getErrorCode());
	        System.out.println("Estado SQL: " + e.getSQLState());
	        
	        return false;
	    }
	}

	
	@Override
	public boolean EliminarCliente(Cliente clienteAeliminar) {
		try {
			DataAccess.executeStoredProcedure("ModificarEstadoCliente", clienteAeliminar.getDni(), "I");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Cliente obtenerCliente(String dni) throws SQLException {
		ArrayList<Cliente> listClientes = this.GetAllClientes();
		for (Cliente cliente : listClientes) {
			if (cliente.getDni().contentEquals(dni)) {
				return cliente;
			}

		}
		return new Cliente();
	}
}
