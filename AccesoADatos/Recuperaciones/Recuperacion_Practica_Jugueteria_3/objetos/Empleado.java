package objetos;

import java.sql.Date;

public class Empleado {
	private int idEmpleado;
	private String nombre;
	private String cargo;
	private Date fechaIngreso;

	public Empleado() {
	}

	public Empleado(int idEmpleado, String nombre, String cargo, Date fechaIngreso) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fechaIngreso = fechaIngreso;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}
