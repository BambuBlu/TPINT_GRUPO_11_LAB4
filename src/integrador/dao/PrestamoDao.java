package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cliente;
import integrador.model.Prestamo;

public interface PrestamoDao {

	void solicitarPrestamo(Prestamo prestamo);

	ArrayList<Prestamo> getPrestamosInactivos() throws SQLException;

	ArrayList<Prestamo> getPrestamos() throws SQLException;

	Prestamo getPrestamos(int id, int p) throws SQLException;

	ArrayList<Prestamo> getPrestamosActivos() throws SQLException;

	void activarPrestamo(int cuenta, int id, int cbu, double importe_pedido) throws SQLException;

	void rechazarPrestamo(int idInt);

	ArrayList<Prestamo> getPrestamosXCliente(Cliente cliente) throws SQLException;

	void pagarPrestamo(int idPrestamoInt, int numCuenta, int cbuInt, double importe) throws SQLException;

}
