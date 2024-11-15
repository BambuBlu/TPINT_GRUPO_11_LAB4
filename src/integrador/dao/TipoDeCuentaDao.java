package integrador.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import integrador.model.TipoCuenta;

public interface TipoDeCuentaDao {

	ArrayList<TipoCuenta> GetAllTipoDeCuenta() throws SQLException;
}
