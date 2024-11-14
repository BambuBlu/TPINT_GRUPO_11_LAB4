package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.LocalidadDaoImpl;
import integrador.model.Localidad;
import integrador.negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio{

	private LocalidadDaoImpl dao = new LocalidadDaoImpl();
	
	@Override
	public ArrayList<Localidad> GetAllLocalidades() {
		try {
			return dao.GetAllLocalidad();
		} catch (Exception e) {
			return null;
		}
	}

}
