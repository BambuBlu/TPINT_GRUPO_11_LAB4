package integrador.dao;

import java.sql.SQLException;
import java.util.List;

import integrador.model.Movimiento;

public interface MovimientoDao {

	List<Movimiento> getMovimientos();

	void agregarMovimiento(Movimiento movimiento);

	double GetCantidadMovida(java.util.Date fechaDesdeDate, java.util.Date fechaHastaDate);

	double GetCantidadSemana() throws SQLException;

}
