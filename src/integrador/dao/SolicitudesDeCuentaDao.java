package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.SolicitudDeAlta;

public interface SolicitudesDeCuentaDao {

	void agregarSolicitud(SolicitudDeAlta solicitud) throws SQLException;

	ArrayList<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas();

	SolicitudDeAlta obtenerSolicitudPorId(int id);

	void eliminarSolicitud(int id);

}
