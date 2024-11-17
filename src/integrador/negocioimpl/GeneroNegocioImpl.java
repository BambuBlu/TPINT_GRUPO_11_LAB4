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
			e.printStackTrace();
	        return new ArrayList<>();
		}
	}

	@Override
	public Generos Find(int id_Genero) {
		try {
			for (Generos genero : dao.GetAllGeneros()) {
				if (genero.getId() == id_Genero)
					return genero;
			}
			return null;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
