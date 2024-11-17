package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Cuenta;

public interface CuentaNegocio {

	ArrayList<Cuenta> GetAllCuentas();

	boolean ModificarCuenta(Cuenta cuentaModificada);
	boolean ModificarTipoCuenta(Cuenta cuentaModificada);
}
