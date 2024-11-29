package integrador.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integrador.dao.PaisDao;
import integrador.model.Pais;
import integrador.utils.DataAccess;

public class PaisDaoImpl implements PaisDao {

	// Implemeto la logica del Dao de Pais
	public PaisDaoImpl() {

	}

	@Override
	public ArrayList<Pais> GetAllPais() throws SQLException {
		String query = "SELECT id, nombre FROM paises";
		ArrayList<Pais> paises = new ArrayList<>();

		try (Connection con = DataAccess.GetConnection();
			 Statement stmt = con.createStatement();
			 ResultSet resultquery = stmt.executeQuery(query);) {

			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String nombre = resultquery.getString("nombre");

				Pais pais = new Pais(id, nombre);
				paises.add(pais);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}

}
