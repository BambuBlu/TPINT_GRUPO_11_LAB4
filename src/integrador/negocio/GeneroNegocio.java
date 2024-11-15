package integrador.negocio;

import java.util.ArrayList;

import integrador.model.Generos;

public interface GeneroNegocio {

	ArrayList<Generos> GetAllGeneros();
	Generos Find(int id_Genero);
}
