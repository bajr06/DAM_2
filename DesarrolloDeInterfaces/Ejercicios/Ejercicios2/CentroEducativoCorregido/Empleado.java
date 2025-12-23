package Ejercicios2.CentroEducativoCorregido;

public class Empleado {
	// 1. Atributos
	String dni;
	String nombreApellidos;
	double salario;
	
	// 2. Constructor
	public Empleado(String dni, String nombreApellidos, double salario) {
		this.dni = dni;
		this.nombreApellidos = nombreApellidos;
		this.salario = salario;
	}

	// 3. get & set
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreApellidos() {
		return nombreApellidos;
	}
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	// 4. toString
	@Override
	public String toString() {
		return "Empleado [DNI = " + dni + ", Nombres y Apellidos = " + nombreApellidos + ", Salario = " + salario + "]";
	}
}
