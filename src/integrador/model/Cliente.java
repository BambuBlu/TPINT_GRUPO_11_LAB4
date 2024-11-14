package integrador.model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * La clase Cliente representa un cliente del banco.
 * Incluye los datos personales y las cuentas asignadas.
 */
public class Cliente {

    private String dni;                    // Documento Nacional de Identidad
    private String cuil;                   // Código Único de Identificación Laboral
    private String nombre;                 // Nombre del cliente
    private String apellido;               // Apellido del cliente
    private Generos sexo;                  // Género del cliente
    private String nacionalidad;           // Nacionalidad del cliente
    private Date fechaNacimiento;          // Fecha de nacimiento
    private String direccion;               // Dirección del cliente
    private Localidad localidad;           // Localidad del cliente
    private String email;                  // Correo electrónico del cliente
    private String telefono;               // Teléfono de contacto del cliente
    private String estado;                 // Estado del cliente (activo/inactivo)
    private ArrayList<Cuenta> cuentas;     // Lista de cuentas del cliente

    /**
     * Constructor con parámetros para crear un cliente.
     *
     * @param dni Documento Nacional de Identidad
     * @param cuil Código Único de Identificación Laboral
     * @param nombre Nombre del cliente
     * @param apellido Apellido del cliente
     * @param sexo Género del cliente
     * @param nacionalidad Nacionalidad del cliente
     * @param fechaNacimiento Fecha de nacimiento
     * @param direccion Dirección del cliente
     * @param localidad Localidad del cliente
     * @param email Correo electrónico del cliente
     * @param telefono Teléfono de contacto del cliente
     */
    public Cliente(String dni, String cuil, String nombre, String apellido, Generos sexo, String nacionalidad,
                   Date fechaNacimiento, String direccion, Localidad localidad, String email, String telefono, String estado) {
        super();
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.localidad = localidad;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.cuentas = new ArrayList<>(); // Inicializa la lista de cuentas
    }

    /**
     * Constructor por defecto para crear un cliente vacío.
     */
    public Cliente() {
        this.dni = "";
        this.cuil = "";
        this.nombre = "";
        this.apellido = "";
        this.sexo = new Generos();
        this.nacionalidad = "";
        this.fechaNacimiento = new Date(0);
        this.direccion = "";
        this.localidad = new Localidad();
        this.email = "";
        this.telefono = "";
        this.estado = "A"; // Estado inicial: Activo
        this.cuentas = new ArrayList<>(); // Inicializa la lista de cuentas
    }
    
    public Cliente(Cliente cliente) {
        this.dni = cliente.getDni();
        this.cuil = cliente.getCuil();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.sexo = cliente.getSexo();
        this.nacionalidad = cliente.getNacionalidad();
        this.fechaNacimiento = cliente.getFechaNacimiento();
        this.direccion = cliente.getDireccion();
        this.localidad = cliente.getLocalidad();
        this.email = cliente.getEmail();
        this.telefono = cliente.getTelefono();
        this.estado = cliente.getEstado();
        this.cuentas = cliente.getCuentas(); 
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Generos getSexo() {
        return sexo;
    }

    public void setSexo(Generos sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Cliente [dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo="
                + sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", direccion="
                + direccion + ", localidad=" + localidad + ", email=" + email + ", telefono=" + telefono + ", estado="
                + estado + ", cuentas=" + cuentas + "]";
    }
}