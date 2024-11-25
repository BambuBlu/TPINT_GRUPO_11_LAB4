package integrador.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;

import integrador.model.Movimiento;
import integrador.utils.DataAccess;

public class MovimientoDaoImpl {

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

}
