package integrador.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.Cuenta;

public interface CuentaNegocio {

	ArrayList<Cuenta> GetAllCuentas();

	boolean ModificarCuenta(Cuenta cuentaModificada);
	boolean ModificarTipoCuenta(Cuenta cuentaModificada);

	ArrayList<Cuenta> GetAllActiveCuentas();

	ArrayList<Cuenta> GetAllInactiveCuentasOfCliente(String dniCliente);

	ArrayList<Cuenta> GetAllActiveCuentasOfCliente(String dniCliente);

	int obtenerUltimoCBU() throws SQLException;

	Cuenta buscarPorCBU(int cbu);

	void AgregarCuenta(Cuenta cuenta);

	int modificarSaldoCuenta(Cuenta cuenta);

	float GetPromedioSaldos();

	float GetPorcentajeCuentasActivas();

	float GetPorcentajeCuentasInactivas();
}
