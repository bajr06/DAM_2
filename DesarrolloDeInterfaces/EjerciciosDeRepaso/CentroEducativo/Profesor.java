// package Ejercicio_Repaso_2;

public class Profesor extends Persona{
	int n_asignaturas;
	boolean tutor;
	
	public int getN_asignaturas() {
		return n_asignaturas;
	}
	public void setN_asignaturas(int n_asignaturas) {
		this.n_asignaturas = n_asignaturas;
	}

	public boolean isTutor() {
		return tutor;
	}
	public void setTutor(boolean tutor) {
		this.tutor = tutor;
	}

	public Profesor(String dni, String nombre, String apellidos, double salario, int n_asignaturas, boolean tutor) {
		super(dni, nombre, apellidos, salario);
		this.n_asignaturas = n_asignaturas;
		this.tutor = tutor;
	}
	
	@Override
	public String toString() {
		return "Profesor [DNI = " + dni + ", Nombre=" + nombre + ", Apellidos=" + apellidos + ", Salario=" + salario + "Nº de Asignaturas = " + n_asignaturas + ", Es tutor = " + tutor + "]";
	}	
}
