package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Localidad;

public interface LocalidadNegocio {
	ArrayList<Localidad> GetAllLocalidades();
	Localidad Find(int id_Localidad);
}
