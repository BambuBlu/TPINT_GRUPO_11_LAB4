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
	    String query = "select id, dni_cliente, usuario, contraseña, tipo_usuario, estado from usuarios";
	    ArrayList<Usuario> usuarios = new ArrayList<>();
	    ClienteNegocioImpl clientenegocio = new ClienteNegocioImpl();
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet resultquery = null;

	    try {
	        conn = DataAccess.GetConnection();
	        stmt = conn.createStatement();
	        resultquery = stmt.executeQuery(query);

	        while (resultquery.next()) {
	            int id = resultquery.getInt("id");
	            String dniCliente = resultquery.getString("dni_cliente");
	            String nombreUsuario = resultquery.getString("usuario");
	            String contraseña = resultquery.getString("contraseña");
	            Roles tipo_Usuario = Roles.valueOf(resultquery.getString("tipo_usuario").toUpperCase());
	            boolean estado = "I".equals(resultquery.getString("estado"));

	            Cliente clienteXUsuario = null;
	            for (Cliente cliente : clientenegocio.GetAllClientes("A")) {
	                if (cliente.getDni().equals(dniCliente)) {
	                    clienteXUsuario = new Cliente(cliente);
	                }
	            }

	            Usuario usuario = new Usuario(id, nombreUsuario, contraseña, clienteXUsuario, tipo_Usuario, estado);
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultquery != null) resultquery.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
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
