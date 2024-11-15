package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Generos;

public interface GeneroDao {
	
	ArrayList<Generos> GetAllGeneros() throws SQLException;
}
