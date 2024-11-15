package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Provincia;

public interface ProvinciaNegocio {
	
	ArrayList<Provincia> GetAllProvincias();
	Provincia Find(int id_Provincia);
}
