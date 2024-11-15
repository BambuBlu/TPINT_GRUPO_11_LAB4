package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.LocalidadDao;
import integrador.model.Localidad;
import integrador.model.Pais;
import integrador.model.Provincia;
import integrador.negocio.ProvinciaNegocio;
import integrador.negocioimpl.PaisNegocioImpl;
import integrador.negocioimpl.ProvinciaNegocioImpl;
import integrador.utils.DataAccess;

public class LocalidadDaoImpl implements LocalidadDao {

	// Implemeto la logica del Dao de Localidad
	public LocalidadDaoImpl() {

	}

	@Override
	public ArrayList<Localidad> GetAllLocalidad() throws SQLException {

		String query = "SELECT id, nombre, id_provincia FROM localidades";
		ResultSet resultquery = DataAccess.executeQuery(query);
		ArrayList<Localidad> localidades = new ArrayList<>();

		try {
			ProvinciaDaoImpl provinciaDaoImpl = new ProvinciaDaoImpl();
			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String nombre = resultquery.getString("nombre");
				int id_provincia = resultquery.getInt("id_provincia");

				// Busco su provincia correspondiente a la localidad
				Provincia provinciasXLocalidad = null;
				for (Provincia provincia : provinciaDaoImpl.GetAllProvincia()) {
					if (provincia.getId() == id_provincia) {
						provinciasXLocalidad = new Provincia(provincia.getId(), provincia.getNombre(),
								provincia.getPais());
						break;
					}
				}
				if (provinciasXLocalidad == null)
					provinciasXLocalidad = new Provincia();

				// Una vez encontrado, creo el objeto y lo agrego a la lista
				Localidad localidad = new Localidad(id, nombre, provinciasXLocalidad);

				localidades.add(localidad);
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

		return localidades;
	}

}
