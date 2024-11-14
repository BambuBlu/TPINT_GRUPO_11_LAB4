package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.PaisDaoImpl;
import integrador.model.Pais;
import integrador.negocio.PaisNegocio;

public class PaisNegocioImpl implements PaisNegocio {

	private PaisDaoImpl dao = new PaisDaoImpl();
	
	@Override
	public ArrayList<Pais> GetAllPaises() {
		try {
			return dao.GetAllPais();
		} catch (Exception e) {
			return null;
		}
	}

}
