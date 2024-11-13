package integrador.daoimpl;

import java.util.ArrayList;

import integrador.dao.ClienteDao;
import integrador.model.Cliente;

// Implemeto la logica del Dao de Cliente
public class ClienteDaoImpl implements ClienteDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "Clientes";
	
	public ClienteDaoImpl() {
		
	}
	
	@Override
	public ArrayList<Cliente> GetAllClientes() {
	
		return null;
	}

	@Override
	public boolean CrearCliente(Cliente nuevoCliente) {
		return false;
	}

	@Override
	public boolean ModificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean EliminarCliente(Cliente clienteAeliminar) {
		// TODO Auto-generated method stub
		return false;
	}

}
