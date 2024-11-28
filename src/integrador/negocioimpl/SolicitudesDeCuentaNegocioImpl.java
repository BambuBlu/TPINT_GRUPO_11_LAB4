package integrador.negocioimpl;

import java.sql.SQLException;
import java.util.List;

import integrador.daoimpl.SolicitudesDeCuentaDaoImpl;
import integrador.model.SolicitudDeAlta;
import integrador.negocio.SolicitudesDeCuentaNegocio;

public class SolicitudesDeCuentaNegocioImpl implements SolicitudesDeCuentaNegocio {
	
	private SolicitudesDeCuentaDaoImpl dao = new SolicitudesDeCuentaDaoImpl();
	
	@Override
	public void agregarSolicitud(SolicitudDeAlta solicitud) throws SQLException {
		dao.agregarSolicitud(solicitud);
	}

	@Override
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas() {
		return dao.obtenerTodasLasSolicitudesActivas();
	}
	
	@Override
	public SolicitudDeAlta obtenerSolicitudPorId(int id) {
		return dao.obtenerSolicitudPorId(id);
	}
	
	@Override
	public void eliminarSolicitud(int id) {
		dao.eliminarSolicitud(id);
	}

}
