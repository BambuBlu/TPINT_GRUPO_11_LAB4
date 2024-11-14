package integrador.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.GeneroDao;
import integrador.model.Generos;
import integrador.utils.DataAccess;

public class GeneroDaoImpl implements GeneroDao {

	// Implemeto la logica del Dao de Genero
	public GeneroDaoImpl() {

	}


	public ArrayList<Generos> GetAllGeneros() {
		String query = "SELECT id, descripcion FROM generos";
	    ResultSet resultquery = DataAccess.executeQuery(query);
	    ArrayList<Generos> generos = new ArrayList<>();

	    try {
	        while (resultquery.next()) {
	            int id = resultquery.getInt("id");
	            String descripcion = resultquery.getString("descripcion");

	            Generos genero = new Generos(id, descripcion);
	            generos.add(genero);
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

	    return generos;	
	}
	
	
	
	
}
