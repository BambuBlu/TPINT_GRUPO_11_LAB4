package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.UsuarioDao;
import integrador.enums.Roles;
import integrador.model.Cliente;
import integrador.model.Usuario;
import integrador.negocioimpl.ClienteNegocioImpl;
import integrador.utils.DataAccess;

public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public ArrayList<Usuario> GetAllUsuarios() {
		String query = "select id, dni_cliente, usuario, contrase�a, tipo_usuario, estado from usuarios";
	    ResultSet resultquery = DataAccess.executeQuery(query);
	    ArrayList<Usuario> usuarios = new ArrayList<>();
	    ClienteNegocioImpl clientenegocio = new ClienteNegocioImpl();
	    try {
	        while (resultquery.next()) {
	        	int id = resultquery.getInt("id");
	            String dniCliente = resultquery.getString("dni_cliente");
	            String nombreUsuario = resultquery.getString("usuario");
	            String contrase�a = resultquery.getString("contrase�a");
	            Roles tipo_Usuario = Roles.valueOf(resultquery.getString("tipo_usuario").toUpperCase());
	            boolean estado = "I".equals(resultquery.getString("estado"));
	            
	            Cliente clienteXUsuario = null;
	            for (Cliente cliente : clientenegocio.GetAllClientes("A")) {
					if (cliente.getDni().equals(dniCliente)) {
						clienteXUsuario = new Cliente(cliente);
					}
						
				}
	            Usuario usuario = new Usuario(id, nombreUsuario, contrase�a, clienteXUsuario, tipo_Usuario, estado);
	            
	            usuarios.add(usuario);
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

	    return usuarios;	
	}

	@Override
	public boolean CrearUsuario(Usuario nuevoUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ModificarUsuario(Usuario usuarioModificado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean EliminarUsuario(Usuario usuarioAeliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
