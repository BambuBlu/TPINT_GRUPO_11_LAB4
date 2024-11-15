package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Localidad;

public interface LocalidadDao {

	ArrayList<Localidad> GetAllLocalidad() throws SQLException;
	
}
