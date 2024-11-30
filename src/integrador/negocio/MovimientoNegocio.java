package integrador.negocio;


import java.sql.SQLException;

import integrador.model.Movimiento;

public interface MovimientoNegocio {

	void agregarMovimiento(Movimiento movimiento);

	java.util.List<Movimiento> getMovimientos();

	double GetCantidadMovida(java.util.Date fechaDesdeDate, java.util.Date fechaHastaDate);

	double GetCantidadSemana() throws SQLException;

}
