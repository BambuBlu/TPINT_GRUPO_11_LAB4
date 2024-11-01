package integrador.model;

import integrador.enums.Roles;

/*
Esta clase representa el usuario del cliente. 
Cuenta con el dni como referencia al cliente como clave unica y sus respectivos atributos
*/
public class Usuario {
	private int id_Usaurio;
	private String nombreUsuario;
	private String contrasena;
	private Cliente cliente;
	private Roles Rol;
	private boolean baja;
	

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public boolean getBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	public Roles getRol() {
		return Rol;
	}
	public void setRol(Roles rol) {
		Rol = rol;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getId_Usaurio() {
		return id_Usaurio;
	}
	public void setId_Usaurio(int id_Usaurio) {
		this.id_Usaurio = id_Usaurio;
	}
	
}
