package integrador.model;

/**
 * La clase Pais representa un pa�s con un identificador y un nombre.
 */
public class Pais {

    private int id;               // Identificador del pa�s
    private String nombre;        // Nombre del pa�s

    /**
     * Constructor por defecto para crear un pa�s vac�o.
     */
    public Pais() {
        // Constructor vac�o
    }

    /**
     * Constructor completo para inicializar un pa�s con todos los atributos.
     *
     * @param id Identificador del pa�s
     * @param nombre Nombre del pa�s
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
     * Devuelve una representaci�n en cadena del pa�s, mostrando los atributos principales.
     *
     * @return Cadena representativa del pa�s
     */
    @Override
    public String toString() {
        return "Pais [id=" + id + ", nombre=" + nombre + "]";
    }
}
