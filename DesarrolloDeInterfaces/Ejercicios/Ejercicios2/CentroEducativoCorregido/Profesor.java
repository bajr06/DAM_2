package Ejercicios2.CentroEducativoCorregido;

public class Profesor extends Persona {
	// 1. Atributos
	private int asignaturas;
	private boolean tutor;

	// 2. Método constructor
	public Profesor(String dni, String nombre, String apellidos, double salario, int asignaturas, boolean tutor) {
		super(dni, nombre, apellidos, salario);
		this.asignaturas = asignaturas;
		this.tutor = tutor;
	}

	// 3. Métodos get & set
	protected int getAsignaturas() {
		return asignaturas;
	}
	protected void setAsignaturas(int asignaturas) {
		this.asignaturas = asignaturas;
	}

	protected boolean isTutor() {
		return tutor;
	}
	protected void setTutor(boolean tutor) {
		this.tutor = tutor;
	}

	// 4. Método toString
	@Override
	public String toString() {
		return "Profesor [Asignaturas = " + asignaturas + ", Tutor = " + tutor + ", DNI = " + dni +
		", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + "]";
	}
}
