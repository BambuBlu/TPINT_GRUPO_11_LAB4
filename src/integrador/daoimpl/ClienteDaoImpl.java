package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import integrador.dao.ClienteDao;
import integrador.model.Cliente;
import integrador.model.Generos;
import integrador.model.Localidad;
import integrador.model.Pais;
import integrador.model.Usuario;
import integrador.utils.DataAccess;

// Implemeto la logica del Dao de Cliente
public class ClienteDaoImpl implements ClienteDao {

	public ClienteDaoImpl() {

	}

	@Override
	public ArrayList<Cliente> GetAllClientes() {

		String query = "SELECT dni, cuil, nombre, apellido, sexo_id, nacionalidad, fecha_nacimiento, direccion, localidad_id, mail, telefono, estado FROM clientes";
	    ResultSet resultquery = DataAccess.executeQuery(query);
	    ArrayList<Cliente> clientes = new ArrayList<>();

	    try {
	        while (resultquery.next()) {
	            String dni = resultquery.getString("dni");
	            String cuil = resultquery.getString("cuil");
	            String nombre = resultquery.getString("nombre");
	            String apellido = resultquery.getString("apellido");
	            
	            //Faltaria bindear los ID con el resto de los datos de la entidad Genero
	            Generos sexo = new Generos(); 
	            sexo.setId(resultquery.getInt("sexo_id"));	
	                  
	            String nacionalidad = resultquery.getString("nacionalidad");
	            
	            Date fechaNacimiento = resultquery.getDate("fecha_nacimiento");
	            String direccion = resultquery.getString("direccion");
	            
	            Localidad localidad = new Localidad();
	            localidad.setId(resultquery.getInt("localidad_id"));
	            
	            String email = resultquery.getString("mail");
	            String telefono = resultquery.getString("telefono");
	            String estado = resultquery.getString("estado");

	            Cliente cliente = new Cliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, email, telefono, estado);
	            clientes.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultquery != null) resultquery.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return clientes;	
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

	@Override
	public boolean ModificarCliente(Cliente clienteModificado, Usuario usuarioModificado) {
		try {
			DataAccess.executeStoredProcedure("ModificarCliente", clienteModificado.getDni(), clienteModificado.getCuil(),
					clienteModificado.getNombre(), clienteModificado.getApellido(), clienteModificado.getSexo().getId(),
					clienteModificado.getNacionalidad(), clienteModificado.getFechaNacimiento(),
					clienteModificado.getDireccion(), clienteModificado.getLocalidad().getId(), clienteModificado.getEmail(),
					clienteModificado.getTelefono(), usuarioModificado.getContrasena());
			
			return true;
		} catch (Exception e) {
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

}
