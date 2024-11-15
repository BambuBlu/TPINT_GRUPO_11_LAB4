package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.SolicitudPrestamoDaoImpl;
import integrador.model.SolicitudPrestamo;
import integrador.negocio.SolicitudPrestamoNegocio;

public class SolicitudPrestamoNegocioImpl implements SolicitudPrestamoNegocio {

	private SolicitudPrestamoDaoImpl dao = new SolicitudPrestamoDaoImpl();
	
	@Override
	public ArrayList<SolicitudPrestamo> GetAllSolicitudesPrestamo() {
		try {
			return dao.GetAllSolicitudes();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<SolicitudPrestamo>();
		}
	}

}
