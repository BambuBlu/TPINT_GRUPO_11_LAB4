package integrador.model;

/**
 * La clase Localidad representa una localidad que pertenece a una provincia.
 */
public class Localidad {

    private int id;                     // Identificador de la localidad
    private String nombre;              // Nombre de la localidad
    //Falta implementacion
    private Provincia provincia;         // Provincia a la que pertenece la localidad

    /**
     * Constructor por defecto para crear una localidad vacía.
     */
    public Localidad() {
        // Constructor vacío
    }

    /**
     * Constructor con parámetros para crear una localidad.
     *
     * @param id Identificador de la localidad
     * @param nombre Nombre de la localidad
     * @param provincia Provincia a la que pertenece la localidad
     */
    public Localidad(int id, String nombre, Provincia provincia) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Localidad [id=" + id + ", nombre=" + nombre + ", provincia=" + provincia + "]";
    }
}
