package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.SolicitudesPrestamoDao;
import integrador.dao.TipoDeCuentaDao;
import integrador.model.Cliente;
import integrador.model.Pais;
import integrador.model.Provincia;
import integrador.model.SolicitudPrestamo;
import integrador.model.TipoCuenta;
import integrador.utils.DataAccess;

public class SolicitudPrestamoDaoImpl implements SolicitudesPrestamoDao {

	@Override
	public ArrayList<SolicitudPrestamo> GetAllSolicitudes() throws SQLException {
		String query = "select id, dniCliente, tipoDeCuenta, estado from solicituddealta";
		ResultSet resultquery = DataAccess.executeQuery(query);
		ArrayList<SolicitudPrestamo> lista = new ArrayList<SolicitudPrestamo>();
		try {
			ClienteDaoImpl daoCliente = new ClienteDaoImpl();
			TipoDeCuentaDaoImpl daoTipoCuenta = new TipoDeCuentaDaoImpl();
			
			while (resultquery.next()) {
				int id = resultquery.getInt("id");
				String dni_Cliente = resultquery.getString("dniCliente");
				int id_TipoCuenta = resultquery.getInt("tipoDeCuenta");
	            boolean estado = "I".equals(resultquery.getString("estado"));

	            TipoCuenta tipoCuentaXSolicitud = new TipoCuenta();
	            Cliente clienteXSolicitud = new Cliente();
				for (TipoCuenta tipoCuenta : daoTipoCuenta.GetAllTipoDeCuenta()) {
					if (tipoCuenta.getId() == id_TipoCuenta) {
						tipoCuentaXSolicitud = tipoCuenta;
						break;
					}
				}
				
				for (Cliente cliente : daoCliente.GetAllClientes()) {
					if (cliente.getDni().equals(dni_Cliente)) {
						clienteXSolicitud = cliente;
						break;
					}
					
				}
				
				SolicitudPrestamo solicitud = new SolicitudPrestamo(id, clienteXSolicitud, tipoCuentaXSolicitud, estado);
				lista.add(solicitud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultquery != null)
					resultquery.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

}
