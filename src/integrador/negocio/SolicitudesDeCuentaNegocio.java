package integrador.negocio;

import java.sql.SQLException;
import java.util.List;

import integrador.model.SolicitudDeAlta;

public interface SolicitudesDeCuentaNegocio {

	void agregarSolicitud(SolicitudDeAlta solicitud) throws SQLException;

	List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas();

	SolicitudDeAlta obtenerSolicitudPorId(int id);

	void eliminarSolicitud(int id);

}
