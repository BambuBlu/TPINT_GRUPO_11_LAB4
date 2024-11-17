package integrador.negocioimpl;

import java.util.ArrayList;

import integrador.daoimpl.CuentaDaoImpl;
import integrador.model.Cuenta;
import integrador.negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
	
	private CuentaDaoImpl dao = new CuentaDaoImpl();

	@Override
	public ArrayList<Cuenta> GetAllCuentas() {
		try {
			return dao.GetAllCuentas();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean ModificarCuenta(Cuenta cuentaModificada) {
		try {
			if (dao.ModificaEstadoCuenta(cuentaModificada))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean ModificarTipoCuenta(Cuenta cuentaModificada) {
		try {
			if (dao.ModificaTipoCuenta(cuentaModificada))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
