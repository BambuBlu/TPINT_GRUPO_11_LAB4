package integrador.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import integrador.dao.MovimientoDao;
import integrador.model.Cuenta;
import integrador.model.Movimiento;
import integrador.model.TipoMovimiento;
import integrador.utils.DataAccess;

public class MovimientoDaoImpl implements MovimientoDao{

	@Override
	public List<Movimiento> getMovimientos() {

		List<Movimiento> movimientos = new ArrayList<>();
		String query = "SELECT * FROM movimientos";

		try (Connection conn = DataAccess.GetConnection();
			 CallableStatement stmtCuenta = conn.prepareCall(query);
			 ResultSet resultSet = stmtCuenta.executeQuery()) {
			while (resultSet.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setId(resultSet.getInt("id"));

				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroDeCuenta(resultSet.getInt("numero_de_cuenta"));
				movimiento.setCuenta(cuenta);

				movimiento.setFecha(resultSet.getDate("fecha"));
				movimiento.setDetalle(resultSet.getString("detalle"));
				movimiento.setImporte(resultSet.getDouble("importe"));

				TipoMovimiento tipoMovimiento = new TipoMovimiento();
				tipoMovimiento.setId(resultSet.getInt("id_tipo_movimiento"));
				movimiento.setTipoMovimiento(tipoMovimiento);

				movimiento.setCuentaOrigen(resultSet.getInt("cuenta_origen"));
				movimiento.setCuentaDestino(resultSet.getInt("cuenta_destino"));

				movimientos.add(movimiento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movimientos;
	}
	
	@Override
	public void agregarMovimiento(Movimiento movimiento) {
		try {
			String sqlCuenta = "INSERT INTO Movimientos (numero_de_cuenta, fecha, detalle, importe, id_tipo_movimiento, cuenta_origen, cuenta_destino) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			Connection conn = DataAccess.GetConnection();
			try (CallableStatement stmtCuenta = conn.prepareCall(sqlCuenta)) {
				stmtCuenta.setInt(1, movimiento.getCuenta().getNumeroDeCuenta());
				stmtCuenta.setDate(2, new java.sql.Date(movimiento.getFecha().getTime()));
				stmtCuenta.setString(3, movimiento.getDetalle());
				stmtCuenta.setDouble(4, movimiento.getImporte());
				stmtCuenta.setInt(5, movimiento.getTipoMovimiento().getId());
				stmtCuenta.setInt(6, movimiento.getCuentaOrigen());
				stmtCuenta.setInt(7, movimiento.getCuentaDestino());

		        stmtCuenta.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double GetCantidadMovida(java.util.Date fechaDesdeDate, java.util.Date fechaHastaDate) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String fechaDesde = sdf.format(fechaDesdeDate);
	    String fechaHasta = sdf.format(fechaHastaDate);
	    double cantidadMovida = 0;
	    
	    String query = "SELECT SUM(importe) AS total_importe FROM Movimientos WHERE fecha >= " + fechaDesde + " AND fecha <= " + fechaHasta + ";";
	    
	    try {
	    	Connection conn = DataAccess.GetConnection();
	        try (CallableStatement cs = conn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					cantidadMovida = rs.getDouble("total_importe");
				}
			}
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return cantidadMovida;
	}
	
	@Override
	public double GetCantidadSemana() throws SQLException {
		String query = "SELECT SUM(importe) AS total_importe FROM Movimientos WHERE fecha >= CURDATE() - INTERVAL 7 DAY;";
		
		double importeSemanal = 0;
		
		Connection conn = DataAccess.GetConnection();
		try (CallableStatement cs = conn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
			while (rs.next()) {
				importeSemanal = rs.getDouble("total_importe");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return importeSemanal;
	}

}
