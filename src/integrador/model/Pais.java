package integrador.model;

/**
 * La clase Pais representa un país con un identificador y un nombre.
 */
public class Pais {

    private int id;               // Identificador del país
    private String nombre;        // Nombre del país

    /**
     * Constructor por defecto para crear un país vacío.
     */
    public Pais() {
        // Constructor vacío
    }

    /**
     * Constructor completo para inicializar un país con todos los atributos.
     *
     * @param id Identificador del país
     * @param nombre Nombre del país
     */
    public Pais(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters

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

    /**
     * Devuelve una representación en cadena del país, mostrando los atributos principales.
     *
     * @return Cadena representativa del país
     */
    @Override
    public String toString() {
        return "Pais [id=" + id + ", nombre=" + nombre + "]";
    }
}
