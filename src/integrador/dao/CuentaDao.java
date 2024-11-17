package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cuenta;

public interface CuentaDao {

	ArrayList<Cuenta> GetAllCuentas() throws SQLException;
	boolean ModificaEstadoCuenta(Cuenta cuentaModificada) throws SQLException;
	boolean ModificaTipoCuenta(Cuenta cuentaModificada) throws SQLException;
}
