package integrador.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integrador.dao.TipoDeCuentaDao;
import integrador.model.TipoCuenta;
import integrador.utils.DataAccess;

public class TipoDeCuentaDaoImpl implements TipoDeCuentaDao {

	@Override
	public ArrayList<TipoCuenta> GetAllTipoDeCuenta() throws SQLException {
		String query = "select id, descripcion from tipodecuenta";
		ArrayList<TipoCuenta> lista = new ArrayList<TipoCuenta>();

		try (Connection con = DataAccess.GetConnection();
			 Statement stmt = con.createStatement();
			 ResultSet resultquery = stmt.executeQuery(query);) {

			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String descripcion = resultquery.getString("descripcion");

				TipoCuenta tipoDeCuenta = new TipoCuenta(id, descripcion);

				lista.add(tipoDeCuenta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
