package integrador.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import integrador.dao.ProvinciaDao;
import integrador.model.Pais;
import integrador.model.Provincia;
import integrador.negocioimpl.PaisNegocioImpl;
import integrador.utils.DataAccess;

public class ProvinciaDaoImpl implements ProvinciaDao{

	// Implemeto la logica del Dao de Provincia
			public ProvinciaDaoImpl() {

			}
			
	@Override
	public ArrayList<Provincia> GetAllProvincia() {
		String query = "SELECT id, nombre, id_pais FROM provincias";
	    ResultSet resultquery = DataAccess.executeQuery(query);
	    ArrayList<Provincia> provincias = new ArrayList<>();

	    try {
	        while (resultquery.next()) {
	            int id = resultquery.getInt("id");
	            String nombre = resultquery.getString("nombre");
	            int id_pais = resultquery.getInt("id_pais");	
	            
	            Pais pais = new Pais();
	            PaisNegocioImpl paisNegocioImpl = new PaisNegocioImpl();
	            
	          // paisNegocioImpl.  // Debo buscar el ID y generar el objeto FALTA TERMINAR
	            pais.setId(id_pais);
	            pais.setNombre("ejemplopais");
	            

	            Provincia provincia = new Provincia(id, nombre, pais); // FALTA TERMINAR CUANDO ESTE CREADA LA FUNCION PARA BUSCAR EL PAIS
	            provincias.add(provincia);
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

	    return provincias;
	}

	
	
}
