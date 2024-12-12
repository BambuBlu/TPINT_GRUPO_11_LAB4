package integrador.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	public ArrayList<Cuenta> GetAllInactiveCuentas() {
		ArrayList<Cuenta> listCuenta = new ArrayList<>();

		String query = "SELECT c.numero_de_cuenta, c.dni_cliente, c.fecha_de_creacion, "
				+ "c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, " + "c.cbu, c.saldo, c.estado " + "FROM cuentas c "
				+ "INNER JOIN TipoDeCuenta tc ON c.id_tipo_cuenta = tc.id " + "WHERE c.estado = 'I'";

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
	public ArrayList<Cuenta> GetAllInactiveCuentasOfCliente(String dniCliente) {

		ArrayList<Cuenta> cuentas = this.GetAllInactiveCuentas();
		ArrayList<Cuenta> cuentas_cliente = new ArrayList<Cuenta>();

		for (Cuenta cuenta : cuentas) {
			System.out.println(cuenta);
			if (cuenta.getDni().contentEquals(dniCliente)) {
				cuentas_cliente.add(cuenta);
			}
		}
		return cuentas_cliente;
	}

	@Override
	public ArrayList<Cuenta> GetAllActiveCuentasOfCliente(String dniCliente) {

		ArrayList<Cuenta> cuentas = this.GetAllActiveCuentas();
		ArrayList<Cuenta> cuentas_cliente = new ArrayList<Cuenta>();
		
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getDni().contentEquals(dniCliente)) 
			{
				cuentas_cliente.add(cuenta);
			}
		}
		return cuentas_cliente;
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
	
	@Override
	public void AgregarCuenta(Cuenta cuenta) {

		String query = "INSERT INTO Cuentas (dni_cliente, fecha_de_creacion, id_tipo_cuenta, cbu, saldo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DataAccess.GetConnection(); PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setString(1, cuenta.getDni());
			pst.setDate(2, cuenta.getFechaDeCreacion());
			pst.setInt(3, cuenta.getTipoCuenta().getId());
			pst.setInt(4, cuenta.getCbu());
			pst.setDouble(5, cuenta.getSaldo());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int obtenerUltimoCBU() throws SQLException 
	{		
		String query = "SELECT cbu FROM cuentas";
		Connection conn = DataAccess.GetConnection();
		int ultimo = 0;
		try (CallableStatement stmtCuenta = conn.prepareCall(query);
				ResultSet resultquery = stmtCuenta.executeQuery()) 
		{
			while (resultquery.next()) 
			{
				ultimo = resultquery.getInt("cbu");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ultimo;
	}
	
	
	@Override
	public Cuenta buscarPorCBU(int cbu) {

		ArrayList<Cuenta> cuentas = this.GetAllActiveCuentas();

		for (Cuenta cuenta : cuentas) {
			if (cuenta.getCbu() == cbu) {
				return cuenta;
			}
		}
		return new Cuenta();
	}
	
	@Override
	public int modificarSaldoCuenta(Cuenta cuenta) {
		String query = "{CALL ModificarSaldoCuenta(?, ?)}";
		int filas = 0;

		try (Connection connection = DataAccess.GetConnection(); CallableStatement stmtCuenta = connection.prepareCall(query)) {

			stmtCuenta.setInt(1, cuenta.getNumeroDeCuenta());
			stmtCuenta.setDouble(2, cuenta.getSaldo());

			filas = stmtCuenta.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	@Override
	public float GetPromedioSaldos() {
	    ArrayList<Cuenta> cuentas = this.GetAllActiveCuentas();
	    
	    if (cuentas == null || cuentas.isEmpty()) {
	        return 0;
	    }
	    
	    float sumaSaldos = 0;
	    
	    for (Cuenta cuenta : cuentas) {
	        sumaSaldos += cuenta.getSaldo();
	    }
	    
	    float promedio = sumaSaldos / cuentas.size();
	    
	    return promedio;
	}
	
	@Override
	public float GetPorcentajeCuentasActivas() {
	    ArrayList<Cuenta> cuentasActivas = this.GetAllActiveCuentas();
	    ArrayList<Cuenta> cuentasInactivas = this.GetAllInactiveCuentas();

	    int totalCuentas = cuentasActivas.size() + cuentasInactivas.size();
	    
	    // Evitar division por cero
	    if (totalCuentas == 0) {
	        return 0;
	    }

	    float porcentaje = ((float) cuentasActivas.size() * 100) / totalCuentas;
	    
	    return porcentaje;
	}
	
	@Override
	public float GetPorcentajeCuentasInactivas() {
		ArrayList<Cuenta> cuentasActivas = this.GetAllActiveCuentas();
	    ArrayList<Cuenta> cuentasInactivas = this.GetAllInactiveCuentas();

	    int totalCuentas = cuentasActivas.size() + cuentasInactivas.size();
	    
	    // Evitar division por cero
	    if (totalCuentas == 0) {
	        return 0;
	    }

	    float porcentaje = ((float) cuentasInactivas.size() * 100) / totalCuentas;
	    
	    return porcentaje;
	}
}
