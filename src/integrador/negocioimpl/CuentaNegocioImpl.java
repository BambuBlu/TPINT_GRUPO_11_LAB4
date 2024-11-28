package integrador.negocioimpl;

import java.sql.SQLException;
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
	public ArrayList<Cuenta> GetAllActiveCuentas() {
		try {
			return dao.GetAllActiveCuentas();

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
	
	@Override
	public ArrayList<Cuenta> GetAllInactiveCuentasOfCliente(String dniCliente) {
		// TODO Auto-generated method stub
		return dao.GetAllInactiveCuentasOfCliente(dniCliente);
	}

	@Override
	public ArrayList<Cuenta> GetAllActiveCuentasOfCliente(String dniCliente) {
		return dao.GetAllActiveCuentasOfCliente(dniCliente);
	}
	
	@Override
	public int obtenerUltimoCBU() throws SQLException {
		return dao.obtenerUltimoCBU();
	}
	
	@Override
	public Cuenta buscarPorCBU(int cbu) {
		return dao.buscarPorCBU(cbu);
	}
	
	@Override
	public void AgregarCuenta(Cuenta cuenta) {
		dao.AgregarCuenta(cuenta);
	}
	
	@Override
	public int modificarSaldoCuenta(Cuenta cuenta) {
		return dao.modificarSaldoCuenta(cuenta);
	}
}
