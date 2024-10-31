package integrador.model;

/**
 * La clase Provincia representa una provincia con un identificador, nombre y un país asociado.
 */
public class Provincia {

    private int id;                // Identificador de la provincia
    private String nombre;         // Nombre de la provincia
    private Pais pais;             // País al que pertenece la provincia

    /**
     * Constructor por defecto para crear una provincia vacía.
     */
    public Provincia() {
        // Constructor vacío
    }

    /**
     * Constructor completo para inicializar una provincia con todos los atributos.
     *
     * @param id Identificador de la provincia
     * @param nombre Nombre de la provincia
     * @param pais País al que pertenece la provincia
     */
    public Provincia(int id, String nombre, Pais pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * Devuelve una representación en cadena de la provincia, mostrando los atributos principales.
     *
     * @return Cadena representativa de la provincia
     */
    @Override
    public String toString() {
        return "Provincia [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
    }
}
