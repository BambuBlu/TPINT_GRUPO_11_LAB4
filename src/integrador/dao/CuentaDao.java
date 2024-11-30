package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cuenta;

public interface CuentaDao {

	ArrayList<Cuenta> GetAllCuentas() throws SQLException;
	boolean ModificaEstadoCuenta(Cuenta cuentaModificada) throws SQLException;
	boolean ModificaTipoCuenta(Cuenta cuentaModificada) throws SQLException;
	ArrayList<Cuenta> GetAllActiveCuentas();
	ArrayList<Cuenta> GetAllInactiveCuentasOfCliente(String dniCliente);
	ArrayList<Cuenta> GetAllActiveCuentasOfCliente(String dniCliente);
	ArrayList<Cuenta> GetAllInactiveCuentas();
	int obtenerUltimoCBU() throws SQLException;
	Cuenta buscarPorCBU(int cbu);
	void AgregarCuenta(Cuenta cuenta);
	int modificarSaldoCuenta(Cuenta c);
	float GetPromedioSaldos();
	float GetPorcentajeCuentasActivas();
	float GetPorcentajeCuentasInactivas();
}
