package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.ClienteDaoImpl;
import integrador.model.Cliente;
import integrador.model.Usuario;
import integrador.negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	private ClienteDaoImpl dao = new ClienteDaoImpl();

	@Override
	public ArrayList<Cliente> GetAllClientes(String estado) {
		try {
			ArrayList<Cliente> clientesFiltrados = new ArrayList<Cliente>();
			for (Cliente cliente : dao.GetAllClientes()) {
				if (estado.equals(cliente.getEstado()))
					clientesFiltrados.add(cliente);
			}

			return clientesFiltrados;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean CrearCliente(Cliente nuevoCliente, Usuario nuevoUsuario) {
		try {
			if (dao.CrearCliente(nuevoCliente, nuevoUsuario))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean ModificarCliente(Cliente clienteModificado, Usuario usuarioModificado) {
		System.out.println("Entro a ModificarCliente del Negocio");
		try {
			if (dao.ModificarCliente(clienteModificado, usuarioModificado))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean EliminarCliente(Cliente clienteAeliminar) {
		try {

			if (dao.EliminarCliente(clienteAeliminar))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
