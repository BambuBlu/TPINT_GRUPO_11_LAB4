package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.GeneroDaoImpl;
import integrador.model.Generos;
import integrador.negocio.GeneroNegocio;

public class GeneroNegocioImpl implements GeneroNegocio {

	private GeneroDaoImpl dao = new GeneroDaoImpl();
	
	@Override
	public ArrayList<Generos> GetAllGeneros() {
		try {
			return dao.GetAllGeneros();	
		} catch (Exception e) {
			return null;
		}
	}

}
