package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Usuario;

public interface UsuarioNegocio {

	ArrayList<Usuario> GetAllUsuariosActivos();

	Usuario obtenerUsuario(String dni);
}
