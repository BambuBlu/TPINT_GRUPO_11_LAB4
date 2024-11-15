package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.ProvinciaDao;
import integrador.model.Pais;
import integrador.model.Provincia;
import integrador.negocioimpl.PaisNegocioImpl;
import integrador.utils.DataAccess;

public class ProvinciaDaoImpl implements ProvinciaDao {

	// Implemeto la logica del Dao de Provincia
	public ProvinciaDaoImpl() {

	}

	@Override
	public ArrayList<Provincia> GetAllProvincia() throws SQLException {
		String query = "SELECT id, nombre, id_pais FROM provincias";
		ResultSet resultquery = DataAccess.executeQuery(query);
		ArrayList<Provincia> provincias = new ArrayList<>();

		try {
			PaisDaoImpl daoPais = new PaisDaoImpl();

			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String nombre = resultquery.getString("nombre");
				int id_pais = resultquery.getInt("id_pais");

				// Ubicar el pais correspondiente
				Pais paisXprovincia = null;
				for (Pais pais : daoPais.GetAllPais()) {
					if (pais.getId() == id_pais)
						paisXprovincia = new Pais(pais.getId(), pais.getNombre());
				}

				// Una vez encontrado el pais correspondiente, le asigno a al atributo de
				// provincia
				Provincia provincia = new Provincia(id, nombre, paisXprovincia);																			
				provincias.add(provincia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultquery != null)
					resultquery.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return provincias;
	}

}
