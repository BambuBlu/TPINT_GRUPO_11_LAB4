package integrador.model;

import java.sql.Date;

/**
 * La clase Cuota representa una cuota de un préstamo.
 * Incluye información sobre el préstamo al que pertenece, la fecha de pago y el monto.
 */
public class Cuota {

    private int idCuota;           // Identificador único de la cuota
    private int idPrestamo;        // Identificador del préstamo al que pertenece la cuota
    private Date fechaPago;        // Fecha de pago de la cuota
    private double monto;          // Monto de la cuota

    /**
     * Constructor con parámetros para crear una cuota.
     *
     * @param idCuota Identificador único de la cuota
     * @param idPrestamo Identificador del préstamo al que pertenece la cuota
     * @param fechaPago Fecha de pago de la cuota
     * @param monto Monto de la cuota
     */
    public Cuota(int idCuota, int idPrestamo, Date fechaPago, double monto) {
        this.idCuota = idCuota;
        this.idPrestamo = idPrestamo;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    /**
     * Constructor por defecto para crear una cuota vacía.
     */
    public Cuota() {
        this.idCuota = 0;
        this.idPrestamo = 0;
        this.fechaPago = new Date(0);
        this.monto = 0.0;
    }

    // Getters y Setters
    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Cuota idCuota=" + idCuota + ", idPrestamo=" + idPrestamo + ", fechaPago=" + fechaPago + ", monto=" + monto ;
    }
}
