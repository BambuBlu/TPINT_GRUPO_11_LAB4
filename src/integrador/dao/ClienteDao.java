package integrador.dao;

import java.util.ArrayList;

import integrador.model.Cliente;
import integrador.model.Usuario;


//Funcionalidades principales de acceso a datos a tabla Clientes
public interface ClienteDao {

	ArrayList<Cliente> GetAllClientes();
	boolean CrearCliente(Cliente nuevoCliente, Usuario nuevoUsuario);
	boolean ModificarCliente(Cliente clienteModificado, Usuario usuarioModificado);
	boolean EliminarCliente(Cliente clienteAeliminar);
}
