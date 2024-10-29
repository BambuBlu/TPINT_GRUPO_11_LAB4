package integrador.model;

/**
 * La clase TipoCuenta representa un tipo de cuenta con un identificador y una descripción.
 */
public class TipoCuenta {

    private int id;                 // Identificador del tipo de cuenta
    private String descripcion;     // Descripción del tipo de cuenta

    /**
     * Constructor completo para inicializar un tipo de cuenta con todos los atributos.
     *
     * @param id Identificador del tipo de cuenta
     * @param descripcion Descripción del tipo de cuenta
     */
    public TipoCuenta(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Constructor por defecto para crear un tipo de cuenta vacío.
     */
    public TipoCuenta() {
        // Constructor vacío
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve una representación en cadena del tipo de cuenta, mostrando los atributos principales.
     *
     * @return Cadena representativa del tipo de cuenta
     */
    @Override
    public String toString() {
        return "TipoCuenta [id=" + id + ", descripcion=" + descripcion + "]";
    }
}