package Ejercicios2.CentroEducativoCorregido;

public class Persona {
	// 1. Atributos
	protected String dni;
	protected String nombre;
	protected String apellidos;
	protected double salario;
	
	// 2. Método constructor
	public Persona(String dni, String nombre, String apellidos, double salario) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
	}

	// 3. Métodos get & set
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

	// 4. Método toString
	@Override
	public String toString() {
		return "Persona [Dni = " + dni + ", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + "]";
	}
}
