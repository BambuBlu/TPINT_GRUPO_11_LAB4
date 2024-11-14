package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.PaisDao;
import integrador.model.Pais;
import integrador.utils.DataAccess;

public class PaisDaoImpl implements PaisDao {
	
	// Implemeto la logica del Dao de Pais
		public PaisDaoImpl() {

		}
		
	@Override
	public ArrayList<Pais> GetAllPais() {
		String query = "SELECT id, nombre FROM paises";
	    ResultSet resultquery = DataAccess.executeQuery(query);
	    ArrayList<Pais> paises = new ArrayList<>();

	    try {
	        while (resultquery.next()) {
	            int id = resultquery.getInt("id");
	            String nombre = resultquery.getString("nombre");

	            Pais pais = new Pais(id, nombre);
	            paises.add(pais);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultquery != null) resultquery.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return paises;
	}

	
	
}
