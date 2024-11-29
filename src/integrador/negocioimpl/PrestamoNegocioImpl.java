package integrador.negocioimpl;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.daoimpl.PrestamoDaoImpl;
import integrador.model.Cliente;
import integrador.model.Prestamo;
import integrador.negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	
	private PrestamoDaoImpl dao = new PrestamoDaoImpl();
	
	@Override
	public void solicitarPrestamo(Prestamo prestamo) {
		dao.solicitarPrestamo(prestamo);
	}
	
	@Override
	public ArrayList<Prestamo> getPrestamosInactivos() throws SQLException {
		return dao.getPrestamosInactivos();
	}

	@Override
	public Prestamo getPrestamos(int id,int p) throws SQLException {
		return dao.getPrestamos(id,p);
	}
	
	@Override
	public void activarPrestamo(int cuenta,int id, int cbu,double importe_pedido) throws SQLException {
		dao.activarPrestamo(cuenta,id,cbu,importe_pedido);
		
	}
	
	@Override
	public void rechazarPrestamo(int idInt) {
		dao.rechazarPrestamo(idInt);
	}
	
	@Override
	public ArrayList<Prestamo> getPrestamosXCliente(Cliente cliente) throws SQLException {
		return dao.getPrestamosXCliente(cliente);
	}
	
	@Override
	public void pagarPrestamo(int idPrestamoInt, int numCuenta, int cbuInt, double importe) throws SQLException {
		dao.pagarPrestamo(idPrestamoInt,numCuenta,cbuInt,importe);
	}

}
