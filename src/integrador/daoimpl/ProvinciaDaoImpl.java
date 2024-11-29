package integrador.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PaisDaoImpl daoPais = new PaisDaoImpl();
		ArrayList<Pais> paises = daoPais.GetAllPais();
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();

		try (Connection con = DataAccess.GetConnection();
			 Statement stmt = con.createStatement();
			 ResultSet resultquery = stmt.executeQuery(query);) {

			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String nombre = resultquery.getString("nombre");
				int id_pais = resultquery.getInt("id_pais");

				// Ubicar el pais correspondiente
				Pais paisXprovincia = new Pais();
				for (Pais pais : paises) {
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
		}

		return provincias;
	}

}
