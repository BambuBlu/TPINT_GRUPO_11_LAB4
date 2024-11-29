package integrador.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cliente;
import integrador.model.Prestamo;

public interface PrestamoNegocio {

	void solicitarPrestamo(Prestamo prestamo);

	ArrayList<Prestamo> getPrestamosInactivos() throws SQLException;

	Prestamo getPrestamos(int id, int p) throws SQLException;

	void activarPrestamo(int cuenta, int id, int cbu, double importe_pedido) throws SQLException;

	void rechazarPrestamo(int idInt);

	ArrayList<Prestamo> getPrestamosXCliente(Cliente cliente) throws SQLException;

	void pagarPrestamo(int idPrestamoInt, int numCuenta, int cbuInt, double importe) throws SQLException;

}
