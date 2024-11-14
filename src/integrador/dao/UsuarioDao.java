package integrador.dao;

import java.util.ArrayList;

import integrador.model.Usuario;

public interface UsuarioDao {
	ArrayList<Usuario> GetAllUsuarios();
	boolean CrearUsuario(Usuario nuevoUsuario);
	boolean ModificarUsuario(Usuario usuarioModificado);
	boolean EliminarUsuario(Usuario usuarioAeliminar);
}