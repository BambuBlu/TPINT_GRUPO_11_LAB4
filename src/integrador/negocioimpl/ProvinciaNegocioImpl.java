package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.ProvinciaDaoImpl;
import integrador.model.Provincia;
import integrador.negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{

	private ProvinciaDaoImpl dao = new ProvinciaDaoImpl();
	
	@Override
	public ArrayList<Provincia> GetAllProvincias() {
		try {
			return dao.GetAllProvincia();
		} catch (Exception e) {
			return null; 
		}
	}

}
