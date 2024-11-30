package integrador.negocioimpl;

import java.sql.SQLException;
import java.util.List;

import integrador.daoimpl.MovimientoDaoImpl;
import integrador.model.Movimiento;
import integrador.negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {
	
	private MovimientoDaoImpl dao = new MovimientoDaoImpl();

	@Override
	public void agregarMovimiento(Movimiento movimiento)
	{
		dao.agregarMovimiento(movimiento);
	}
	
	@Override
	public List<Movimiento> getMovimientos() {
		return dao.getMovimientos();
	}
	
	@Override
	public double GetCantidadMovida(java.util.Date fechaDesdeDate, java.util.Date fechaHastaDate) {
		return dao.GetCantidadMovida(fechaDesdeDate, fechaHastaDate);
	}
	
	@Override
	public double GetCantidadSemana() throws SQLException {
		return dao.GetCantidadSemana();
	}
	
}
