package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Provincia;

public interface ProvinciaDao {

	ArrayList<Provincia> GetAllProvincia() throws SQLException;
	
}
