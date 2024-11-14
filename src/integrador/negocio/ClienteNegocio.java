package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Cliente;
import integrador.model.Usuario;

public interface ClienteNegocio {
	
	ArrayList<Cliente> GetAllClientes(String estado);
	boolean CrearCliente(Cliente nuevoCliente, Usuario nuevoUsuario);
	boolean ModificarCliente(Cliente clienteModificado, Usuario usuarioModificado);
	boolean EliminarCliente(Cliente clienteAeliminar);
}
