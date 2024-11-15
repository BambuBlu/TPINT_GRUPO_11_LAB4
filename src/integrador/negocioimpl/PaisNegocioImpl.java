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

	@Override
	public Pais Find(int id_Pais) {
		try {
			for (Pais pais : dao.GetAllPais()) {
				if (pais.getId() == id_Pais)
					return pais;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
