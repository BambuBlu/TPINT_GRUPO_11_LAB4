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
import java.sql.Connection;
import java.sql.Statement;


public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public ArrayList<Usuario> GetAllUsuarios() {
	    String query = "select id, dni_cliente, usuario, contraseņa, tipo_usuario, estado from usuarios";
	    ArrayList<Usuario> usuarios = new ArrayList<>();
	    ClienteNegocioImpl clientenegocio = new ClienteNegocioImpl();
	  /*  Connection conn = null;
	    Statement stmt = null;
	    ResultSet resultquery = null;
    	
	       ( conn = DataAccess.GetConnection();
	        stmt = conn.createStatement();
	        resultquery = stmt.executeQuery(query) );*/
	       
	    try  // utilizo try with rousources para cierre de la conexion
	    	(Connection conn = DataAccess.GetConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet resultquery = stmt.executeQuery(query)){


	        while (resultquery.next()) {
	            int id = resultquery.getInt("id");
	            String dniCliente = resultquery.getString("dni_cliente");
	            String nombreUsuario = resultquery.getString("usuario");
	            String contraseņa = resultquery.getString("contraseņa");
	            Roles tipo_Usuario = Roles.valueOf(resultquery.getString("tipo_usuario").toUpperCase());
	            boolean estado = "I".equals(resultquery.getString("estado"));

	            Cliente clienteXUsuario = null;
	            for (Cliente cliente : clientenegocio.GetAllClientes("A")) {
	                if (cliente.getDni().equals(dniCliente)) {
	                    clienteXUsuario = new Cliente(cliente);
	                }
	            }

	            Usuario usuario = new Usuario(id, nombreUsuario, contraseņa, clienteXUsuario, tipo_Usuario, estado);
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
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


	@Override
	public Usuario obtenerUsuario(String dni) {
		ArrayList<Usuario> listUsuarios = this.GetAllUsuarios();
		for (Usuario usuario : listUsuarios) {
			if (usuario.getCliente().getDni().contentEquals(dni)) {
				return usuario;
			}

		}
		return new Usuario();
	}

	
}
