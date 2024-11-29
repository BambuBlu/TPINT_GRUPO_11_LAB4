package integrador.negocio;

import integrador.model.Movimiento;

public interface MovimientoNegocio {

	void agregarMovimiento(Movimiento movimiento);

	java.util.List<Movimiento> getMovimientos();

}
