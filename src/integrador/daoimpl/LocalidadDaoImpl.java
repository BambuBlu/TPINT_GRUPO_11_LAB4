package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.LocalidadDao;
import integrador.model.Localidad;
import integrador.model.Pais;
import integrador.model.Provincia;
import integrador.negocio.ProvinciaNegocio;
import integrador.negocioimpl.PaisNegocioImpl;
import integrador.negocioimpl.ProvinciaNegocioImpl;
import integrador.utils.DataAccess;

public class LocalidadDaoImpl implements LocalidadDao{

	// Implemeto la logica del Dao de Genero
	public LocalidadDaoImpl() {

	}

	
	@Override
	public ArrayList<Localidad> GetAllLocalidad() {
	
		String query = "SELECT id, nombre, id_provincia FROM localidades";
	    ResultSet resultquery = DataAccess.executeQuery(query);
	    ArrayList<Localidad> localidades = new ArrayList<>();

	    try {
	        while (resultquery.next()) {
	            int id = resultquery.getInt("id");
	            String nombre = resultquery.getString("nombre");
	            int id_provincia = resultquery.getInt("id_provincia");
	            
	            Provincia provincia = new Provincia();
	            ProvinciaNegocioImpl provinciaNegocioImpl = new ProvinciaNegocioImpl();
	            
	            Pais pais = new Pais();
	            PaisNegocioImpl paisNegocioImpl = new PaisNegocioImpl();
	            
	          // paisNegocioImpl.  // Debo buscar el ID y generar el objeto FALTA TERMINAR
	            pais.setId(1);
	            pais.setNombre("ejemplopais");
	           // provinciaNegocioImpl.  // Debo buscar el ID y generar el objeto FALTA TERMINAR
	            provincia.setId(0);
	            provincia.setNombre("ejemploprovincia");
	            provincia.setPais(pais);
	            
	            Localidad localidad = new Localidad(id, nombre, provincia); // FALTA TERMINAR ENVIAR OBJETO PROVINCIA
	            localidades.add(localidad);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultquery != null) resultquery.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return localidades;	
	}

	
}
