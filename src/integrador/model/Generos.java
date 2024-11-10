package integrador.model;

/**
 * La clase Generos representa un género con un identificador y una descripción.
 */
public class Generos {

    private int id;                      // Identificador del género
    private String descripcion;          // Descripción del género // 1 hombre, 2 mujer, 3 otro

    /**
     * Constructor con parámetros para crear un género.
     *
     * @param id Identificador del género
     * @param descripcion Descripción del género
     */
    public Generos(int id, String descripcion) {
        super();
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Constructor por defecto para crear un género vacío.
     */
    public Generos() {
        // Constructor vacío
    }

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

    @Override
    public String toString() {
        return "Generos [id=" + id + ", descripcion=" + descripcion + "]";
    }

    /**
     * Método estático que convierte una cadena a un objeto Generos.
     *
     * @param upperCase La cadena que representa un género
     * @return Un objeto Generos correspondiente a la cadena, o null si no se encuentra
     */
    public static Generos valueOf(String upperCase) {
        // TODO: Implementar lógica para convertir una cadena a un objeto Generos
        return null;
    }
}