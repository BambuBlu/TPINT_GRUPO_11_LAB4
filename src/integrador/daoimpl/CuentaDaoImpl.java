package integrador.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integrador.dao.CuentaDao;
import integrador.model.Cuenta;
import integrador.model.TipoCuenta;
import integrador.utils.DataAccess;

public class CuentaDaoImpl implements CuentaDao {
	
	public CuentaDaoImpl() {

	}

	@Override
	public ArrayList<Cuenta> GetAllCuentas() throws SQLException {
		String query = "SELECT c.numero_de_cuenta, c.dni_cliente, c.fecha_de_creacion, "
				+ "c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, " + "c.cbu, c.saldo, c.estado " + "FROM cuentas c "
				+ "INNER JOIN TipoDeCuenta tc ON c.id_tipo_cuenta = tc.id ";
	    ArrayList<Cuenta> Cuentas = new ArrayList<>();

	    try (Connection conn = DataAccess.GetConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet resultquery = stmt.executeQuery(query)) {

	        while (resultquery.next()) {
	            int numeroDeCuenta = resultquery.getInt("numero_de_cuenta");
	            String dni = resultquery.getString("dni_cliente");
	            Date fechaDeCreacion = resultquery.getDate("fecha_de_creacion");
	            int cbu = resultquery.getInt("cbu");
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultquery.getInt("id_tipo_cuenta"));
				tipoCuenta.setDescripcion(resultquery.getString("tipo_cuenta"));
	            double saldo = resultquery.getInt("saldo");
	            String estado = resultquery.getString("estado");

	            Cuenta Cuenta = new Cuenta(numeroDeCuenta, dni, fechaDeCreacion, tipoCuenta, cbu, saldo, estado);
	            Cuentas.add(Cuenta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    return Cuentas;
	}
	
	@Override
	public ArrayList<Cuenta> GetAllActiveCuentas() {
		ArrayList<Cuenta> listCuenta = new ArrayList<>();

		String query = "SELECT c.numero_de_cuenta, c.dni_cliente, c.fecha_de_creacion, "
				+ "c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, " + "c.cbu, c.saldo, c.estado " + "FROM cuentas c "
				+ "INNER JOIN TipoDeCuenta tc ON c.id_tipo_cuenta = tc.id " + "WHERE c.estado = 'A'";

		try (Connection conn = DataAccess.GetConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultquery = stmt.executeQuery(query)) {

			while (resultquery.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroDeCuenta(resultquery.getInt("numero_de_cuenta"));
				cuenta.setDni(resultquery.getString("dni_cliente"));
				cuenta.setFechaDeCreacion(resultquery.getDate("fecha_de_creacion"));

				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(resultquery.getInt("id_tipo_cuenta"));
				tipoCuenta.setDescripcion(resultquery.getString("tipo_cuenta"));
				cuenta.setTipoCuenta(tipoCuenta);

				cuenta.setCbu(resultquery.getInt("cbu"));
				cuenta.setSaldo(resultquery.getDouble("saldo"));
				cuenta.setEstado(resultquery.getString("estado"));

				listCuenta.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCuenta;
	}
	
	@Override
	public boolean ModificaEstadoCuenta(Cuenta cuentaModificada) throws SQLException {
	    String sqlCuenta = "{CALL ModificarEstadoCuenta(?, ?)}";
	    
	    Connection conn = null;
	    conn = DataAccess.GetConnection();
	    try (CallableStatement stmtCuenta = conn.prepareCall(sqlCuenta)) {
	    	stmtCuenta.setLong(1, cuentaModificada.getNumeroDeCuenta());
	    	stmtCuenta.setString(2, cuentaModificada.getEstado());
		    
	        int rowsAffectedCliente = stmtCuenta.executeUpdate();
	        
	        if (rowsAffectedCliente > 0) {
	        	return true;
	        }
	        return false;
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar los procedimientos: " + e.getMessage());
	        return false;
	    }
	}

	@Override
	public boolean ModificaTipoCuenta(Cuenta cuentaModificada) throws SQLException {		
	    String sqlCuenta = "{CALL ModificarTipoCuenta(?, ?)}";
	    
	    Connection conn = null;
	    conn = DataAccess.GetConnection();
	    try (CallableStatement stmtCuenta = conn.prepareCall(sqlCuenta)) {
	        
	    	stmtCuenta.setLong(1, cuentaModificada.getNumeroDeCuenta());
	    	stmtCuenta.setLong(2, cuentaModificada.getTipoCuenta().getId());
		    
	        int rowsAffectedCliente = stmtCuenta.executeUpdate();
	        
	        if (rowsAffectedCliente > 0) {
	        	return true;
	        }
	        return false;
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar los procedimientos: " + e.getMessage());
	        return false;
	    }
	}
}
