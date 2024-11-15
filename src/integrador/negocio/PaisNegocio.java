package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Pais;

public interface PaisNegocio {
	ArrayList<Pais> GetAllPaises();
	Pais Find(int id_Pais);
}
