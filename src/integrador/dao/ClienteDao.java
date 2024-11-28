package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cliente;
import integrador.model.Usuario;


//Funcionalidades principales de acceso a datos a tabla Clientes
public interface ClienteDao {

	ArrayList<Cliente> GetAllClientes() throws SQLException;
	boolean CrearCliente(Cliente nuevoCliente, Usuario nuevoUsuario);
	boolean ModificarEstadoCliente(Cliente clienteModificado, Usuario usuarioModificado) throws SQLException;
	boolean ModificarCliente(Cliente clienteModificado, Usuario usuarioModificado) throws SQLException;
	boolean EliminarCliente(Cliente clienteAeliminar);
	Cliente obtenerCliente(String dni) throws SQLException;
	ArrayList<Cliente> GetAllActiveClientes() throws SQLException;
}
