package integrador.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cliente;
import integrador.model.Usuario;

public interface ClienteNegocio {
	
	ArrayList<Cliente> GetAllClientes(String estado);
	boolean CrearCliente(Cliente nuevoCliente, Usuario nuevoUsuario);
	boolean ModificarEstadoCliente(Cliente clienteModificado, Usuario usuarioModificado);
	boolean ModificarCliente(Cliente clienteModificado, Usuario usuario);
	boolean EliminarCliente(Cliente clienteAeliminar);
	Cliente obtenerCliente(String dni) throws SQLException;
}
