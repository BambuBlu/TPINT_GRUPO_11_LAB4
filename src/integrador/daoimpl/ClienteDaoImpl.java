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
		String query = "Insert into (Dni, Cuil, Nombre, Apellido, ID_Genero, ID_Nacionalidad, FechaDeNacimiento, Direccion, ID_Localidad, Email, Telefono, Estado) "
				+ "Values ()";
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
