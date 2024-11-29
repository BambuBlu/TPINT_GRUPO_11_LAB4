package integrador.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integrador.dao.GeneroDao;
import integrador.model.Generos;
import integrador.utils.DataAccess;

public class GeneroDaoImpl implements GeneroDao {

	// Implemeto la logica del Dao de Genero
	public GeneroDaoImpl() {

	}

	@Override
	public ArrayList<Generos> GetAllGeneros() throws SQLException {
		String query = "SELECT id, descripcion FROM generos";
		ArrayList<Generos> generos = new ArrayList<>();

		try (Connection con = DataAccess.GetConnection();
			 Statement stmt = con.createStatement();
			 ResultSet resultquery = stmt.executeQuery(query);) {
			
			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String descripcion = resultquery.getString("descripcion");

				Generos genero = new Generos(id, descripcion);
				generos.add(genero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return generos;
	}

}
