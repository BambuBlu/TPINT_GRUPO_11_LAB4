package integrador.dao;

import java.util.ArrayList;

import integrador.model.Cliente;


//Funcionalidades principales de acceso a datos a tabla Clientes
public interface ClienteDao {

	ArrayList<Cliente> GetAllClientes();
	boolean CrearCliente(Cliente nuevoCliente);
	boolean ModificarCliente(Cliente clienteModificado);
	boolean EliminarCliente(Cliente clienteAeliminar);
}
