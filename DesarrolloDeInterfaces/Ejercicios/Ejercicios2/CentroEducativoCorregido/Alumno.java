package Ejercicios2.CentroEducativoCorregido;

import java.util.Date;

public class Alumno {
	// 1. Atributos
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private char sexo;
	private boolean repetidor;
	// private List<Modulo> modulos;
	private Modulo unModulo;
	
	// 2. Método constructor
	public Alumno(String dni, String nombre, String apellidos, Date fechaNacimiento, char sexo, boolean repetidor, Modulo unModulo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.repetidor = repetidor;
		this.unModulo = unModulo;
		// new Modulo("DAM", 1200, new Profesor("123T", "Fernando", "Mata", 1000.5, true), true);
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isRepetidor() {
		return repetidor;
	}
	public void setRepetidor(boolean repetidor) {
		this.repetidor = repetidor;
	}

	public Modulo getUnModulo() {
		return unModulo;
	}
	public void setUnModulo(Modulo unModulo) {
		this.unModulo = unModulo;
	}

	// 4. Método toString
	@Override
	public String toString() {
		return "Alumno [DNI = " + dni + ", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Fecha de Nacimiento = "
		+ fechaNacimiento + ", Sexo = " + sexo + ", Repetidor = " + repetidor + ", Modulo = " + unModulo + "]";
	}
}
