package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.UsuarioDaoImpl;
import integrador.model.Usuario;
import integrador.negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{

	private UsuarioDaoImpl dao = new UsuarioDaoImpl();
	@Override
	public ArrayList<Usuario> GetAllUsuariosActivos() {
		try {
			ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
			for (Usuario usuario : dao.GetAllUsuarios()) {
				if (!usuario.getBaja())
					usuariosActivos.add(usuario);
			}
			
			return usuariosActivos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
