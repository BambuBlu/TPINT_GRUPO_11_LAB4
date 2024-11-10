package integrador.model;

/**
 * La clase Generos representa un g�nero con un identificador y una descripci�n.
 */
public class Generos {

    private int id;                      // Identificador del g�nero
    private String descripcion;          // Descripci�n del g�nero // 1 hombre, 2 mujer, 3 otro

    /**
     * Constructor con par�metros para crear un g�nero.
     *
     * @param id Identificador del g�nero
     * @param descripcion Descripci�n del g�nero
     */
    public Generos(int id, String descripcion) {
        super();
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Constructor por defecto para crear un g�nero vac�o.
     */
    public Generos() {
        // Constructor vac�o
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
     * M�todo est�tico que convierte una cadena a un objeto Generos.
     *
     * @param upperCase La cadena que representa un g�nero
     * @return Un objeto Generos correspondiente a la cadena, o null si no se encuentra
     */
    public static Generos valueOf(String upperCase) {
        // TODO: Implementar l�gica para convertir una cadena a un objeto Generos
        return null;
    }
}