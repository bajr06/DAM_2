package Objetos;

import java.io.Serializable;

public class Empleado implements Serializable {
	private static final long serialVersionUID = -6208250307443975431L;
	private int identificacion;
	private String nombre;
	private String contrasenya;
	private String cargo;


	public int getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public Empleado(int identificacion, String nombre, String contrasenya, String cargo) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.cargo = cargo;
	}


	@Override
	public String toString() {
		return "Empleado [Identificacion = " + identificacion + ", Nombre = " + nombre + ", Contraseña = " + contrasenya + ", Cargo = " + cargo + "]";
	}
}
