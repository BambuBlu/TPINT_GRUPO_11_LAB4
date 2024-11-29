package integrador.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integrador.dao.LocalidadDao;
import integrador.model.Generos;
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
		ArrayList<Localidad> localidades = new ArrayList<>();

		ProvinciaDaoImpl provinciaDaoImpl = new ProvinciaDaoImpl();
		ArrayList<Provincia> provincias = provinciaDaoImpl.GetAllProvincia();

		try (Connection con = DataAccess.GetConnection();
			 Statement stmt = con.createStatement();
			 ResultSet resultquery = stmt.executeQuery(query)) {

			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String nombre = resultquery.getString("nombre");
				int id_provincia = resultquery.getInt("id_provincia");

				// Busco provincia correspondiente a la localidad
				Provincia provinciasXLocalidad = new Provincia();

				for (Provincia provincia : provincias) {
					if (provincia.getId() == id_provincia) {
						provinciasXLocalidad = new Provincia(provincia.getId(), provincia.getNombre(),
								provincia.getPais());
						break;
					}
				}

				// Una vez encontrado, creo el objeto y lo agrego a la lista
				Localidad localidad = new Localidad(id, nombre, provinciasXLocalidad);
				localidades.add(localidad);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return localidades;
	}

}
