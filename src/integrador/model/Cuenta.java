package integrador.model;

import java.sql.Date;

/**
 * La clase Cuenta representa una cuenta bancaria con varios atributos como el número de cuenta, DNI del titular, 
 * fecha de creación, tipo de cuenta, CBU, saldo y estado.
 */
public class Cuenta {

    private int numeroDeCuenta;        // Número de cuenta
    private String dni;                // DNI del titular
    private Date fechaDeCreacion;      // Fecha de creación de la cuenta
    private TipoCuenta tipoCuenta;     // Tipo de cuenta (enum o clase)
    private int cbu;                   // Clave Bancaria Uniforme
    private double saldo;              // Saldo de la cuenta
    private String estado;             // Estado de la cuenta (activa, inactiva, etc.)

    /**
     * Constructor que recibe solo el DNI para crear una cuenta parcial.
     *
     * @param dni El DNI del titular de la cuenta
     */
    public Cuenta(String dni) {
        this.dni = dni;
    }

    /**
     * Constructor completo para inicializar una cuenta con todos los atributos.
     *
     * @param numeroDeCuenta Número de cuenta
     * @param dni DNI del titular
     * @param fechaDeCreacion Fecha de creación de la cuenta
     * @param tipoCuenta Tipo de cuenta
     * @param cbu Clave Bancaria Uniforme
     * @param saldo Saldo de la cuenta
     * @param estado Estado de la cuenta
     */
    public Cuenta(int numeroDeCuenta, String dni, Date fechaDeCreacion,
    			  TipoCuenta tipoCuenta, int cbu, double saldo, String estado) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.dni = dni;
        this.fechaDeCreacion = fechaDeCreacion;
        this.tipoCuenta = tipoCuenta;
        this.cbu = cbu;
        this.saldo = saldo;
        this.estado = estado;
    }

    /**
     * Constructor por defecto para crear una cuenta vacía.
     */
    public Cuenta() {
        // Constructor vacío
    }

    // Getters y Setters

    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getCbu() {
        return cbu;
    }

    public void setCbu(int cbu) {
        this.cbu = cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación en cadena de la cuenta, mostrando los atributos principales.
     *
     * @return Cadena representativa de la cuenta
     */
    @Override
    public String toString() {
        return "Cuenta [numeroDeCuenta=" + numeroDeCuenta + ", dni=" + dni + ", fechaDeCreacion=" + fechaDeCreacion
                + ", tipoCuenta=" + tipoCuenta + ", cbu=" + cbu + ", saldo=" + saldo + ", estado=" + estado + "]";
    }
}