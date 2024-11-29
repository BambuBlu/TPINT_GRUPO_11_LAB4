package integrador.negocioimpl;

import java.util.List;

import integrador.daoimpl.MovimientoDaoImpl;
import integrador.model.Movimiento;
import integrador.negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {
	
	private MovimientoDaoImpl movimientoDao = new MovimientoDaoImpl();

	@Override
	public void agregarMovimiento(Movimiento movimiento)
	{
		movimientoDao.agregarMovimiento(movimiento);
	}
	
	@Override
	public List<Movimiento> getMovimientos() {
		return movimientoDao.getMovimientos();
	}
	
}
