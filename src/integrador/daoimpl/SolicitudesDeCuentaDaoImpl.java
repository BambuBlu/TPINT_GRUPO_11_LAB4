package integrador.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.SolicitudesDeCuentaDao;
import integrador.model.Cliente;
import integrador.model.SolicitudDeAlta;
import integrador.model.TipoCuenta;
import integrador.utils.DataAccess;

public class SolicitudesDeCuentaDaoImpl implements SolicitudesDeCuentaDao {
	
	@Override
	public void agregarSolicitud(SolicitudDeAlta solicitud) throws SQLException {

		String query = "INSERT INTO solicitudDeAlta (dniCliente, tipoDeCuenta, estado) VALUES (?, ?, ?)";

		Connection conn = DataAccess.GetConnection();
		try (CallableStatement stmtCliente = conn.prepareCall(query)) {
			stmtCliente.setString(1, solicitud.getCliente().getDni());
			stmtCliente.setInt(2, solicitud.getTipoCuenta().getId());
			stmtCliente.setString(3, solicitud.getEstado());

			stmtCliente.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas() {

		ArrayList<SolicitudDeAlta> solicitudes = new ArrayList<>();
		String query = "SELECT * FROM solicituddealta WHERE estado = 'A'";

		try (Connection connection = DataAccess.GetConnection();
				CallableStatement stmtCliente = connection.prepareCall(query);
				ResultSet resultquery = stmtCliente.executeQuery()) {
			while (resultquery.next()) {
				SolicitudDeAlta solicitud = new SolicitudDeAlta();
				solicitud.setId(resultquery.getInt("id"));

				// Crear una nueva instancia de Cliente para cada solicitud
				Cliente cliente = new Cliente();
				cliente.setDni(resultquery.getString("dniCliente"));
				solicitud.setCliente(cliente);

				// Crear una nueva instancia de TipoCuenta para cada solicitud
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(resultquery.getInt("tipoDeCuenta"));

				// Comparar y asignar la descripcion del tipo de cuenta
				if (tipoCuenta.getId() == 1) {
					tipoCuenta.setDescripcion("Caja de ahorro");
				} else {
					tipoCuenta.setDescripcion("Cuenta corriente");
				}

				solicitud.setTipoCuenta(tipoCuenta);
				solicitud.setEstado(resultquery.getString("estado"));

				solicitudes.add(solicitud);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return solicitudes;
	}
	
	
	@Override
	public SolicitudDeAlta obtenerSolicitudPorId(int id) {
		ArrayList<SolicitudDeAlta> lista = this.obtenerTodasLasSolicitudesActivas();

		for (SolicitudDeAlta solicitud : lista) {
			if (solicitud.getId() == id) {
				return solicitud;
			}
		}

		return new SolicitudDeAlta();
	}
	
	@Override
	public void eliminarSolicitud(int id) {
		String query = "UPDATE solicituddealta SET estado = 'I' WHERE id = ?";

		try (Connection connection = DataAccess.GetConnection(); PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
