package integrador.model;

public class SolicitudPrestamo {

	private int id_Solicitud; 
	private Cliente cliente; 
	private TipoCuenta tipo_Cuenta;
	private boolean estado;
	
	public SolicitudPrestamo() {
		
	}
	
	
	public SolicitudPrestamo(int id_Solicitud, Cliente cliente, TipoCuenta tipo_Cuenta, boolean estado) {
		super();
		this.id_Solicitud = id_Solicitud;
		this.cliente = cliente;
		this.tipo_Cuenta = tipo_Cuenta;
		this.estado = estado;
	}
	
	public int getId_Solicitud() {
		return id_Solicitud;
	}
	public void setId_Solicitud(int id_Solicitud) {
		this.id_Solicitud = id_Solicitud;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoCuenta getTipo_Cuenta() {
		return tipo_Cuenta;
	}
	public void setTipo_Cuenta(TipoCuenta tipo_Cuenta) {
		this.tipo_Cuenta = tipo_Cuenta;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}
