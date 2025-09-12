// package Ejercicio_Repaso_2;

public class Persona {
	String dni;
	String nombre;
	String apellidos;
	double salario;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public Persona(String dni, String nombre, String apellidos, double salario) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
	}
	
	public Persona(String dni, String nombre, String apellidos) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	@Override
	public String toString() {
		return "Persona [DNI = " + dni + ", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + "]";
	}	
}
