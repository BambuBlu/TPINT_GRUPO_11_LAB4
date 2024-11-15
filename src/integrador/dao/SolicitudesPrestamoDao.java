package integrador.dao;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.SolicitudPrestamo;

public interface SolicitudesPrestamoDao {

	ArrayList<SolicitudPrestamo> GetAllSolicitudes() throws SQLException;
	
}
