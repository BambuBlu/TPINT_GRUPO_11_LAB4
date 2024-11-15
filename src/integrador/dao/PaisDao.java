package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Pais;


public interface PaisDao {

	ArrayList<Pais> GetAllPais() throws SQLException;
	
}
