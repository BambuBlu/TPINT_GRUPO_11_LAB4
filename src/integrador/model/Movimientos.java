package integrador.model;

import java.sql.Date;

/**
 * La clase Movimiento representa un movimiento o transacción de una cuenta bancaria.
 * Incluye información sobre la cuenta, la fecha, el importe, el concepto y el tipo de movimiento.
 */
public class Movimientos {

    private int idMovimiento;           // Identificador único del movimiento
    private String numeroDeCuenta;      // Número de la cuenta asociada
    private Date fechaMovimiento;       // Fecha del movimiento
    private double importe;             // Importe del movimiento
    private String concepto;            // Concepto o descripción del movimiento
    private int tipoMovimiento; // Tipo de movimiento (Depósito o Extracción)  // VER SI GENERAMOS UNA CLASE PROPIA

    /**
     * Constructor con parámetros para crear un movimiento.
     *
     * @param idMovimiento Identificador único del movimiento
     * @param numeroDeCuenta Número de la cuenta asociada
     * @param fechaMovimiento Fecha del movimiento
     * @param importe Importe del movimiento
     * @param concepto Concepto o descripción del movimiento
     * @param tipoMovimiento Tipo de movimiento (Depósito o Extracción)
     */
    public Movimientos(int idMovimiento, String numeroDeCuenta, Date fechaMovimiento, double importe, String concepto, int tipoMovimiento) {
        this.idMovimiento = idMovimiento;
        this.numeroDeCuenta = numeroDeCuenta;
        this.fechaMovimiento = fechaMovimiento;
        this.importe = importe;
        this.concepto = concepto;
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * Constructor por defecto para crear un movimiento vacío.
     */
    public Movimientos() {
        this.idMovimiento = 0;
        this.numeroDeCuenta = "";
        this.fechaMovimiento = new Date(0);
        this.importe = 0.0;
        this.concepto = "";
        this.tipoMovimiento = 1; // Tipo inicial: Depósito
    }

    // Getters y Setters
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    @Override
    public String toString() {
        return "Movimiento idMovimiento=" + idMovimiento + ", numeroDeCuenta=" + numeroDeCuenta + ", fechaMovimiento=" + fechaMovimiento + 
               ", importe=" + importe + ", concepto=" + concepto + ", tipoMovimiento=" + tipoMovimiento;
    }
}
