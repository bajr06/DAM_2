package Ejercicios2.CentroEducativoCorregido;

public class Modulo {
	// 1. Atributos
	private String nombre;
	private int horas;
	private Profesor tutor;
	private boolean convalidable;

	// 2. Método constructor
	public Modulo(String nombre, int horas, Profesor tutor, boolean convalidable) {
		this.nombre = nombre;
		this.horas = horas;
		this.tutor = tutor;
		this.convalidable = convalidable;
	}

	// 3. Métodos get & set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Profesor getTutor() {
		return tutor;
	}
	public void setTutor(Profesor tutor) {
		this.tutor = tutor;
	}

	public boolean isConvalidable() {
		return convalidable;
	}
	public void setConvalidable(boolean convalidable) {
		this.convalidable = convalidable;
	}

	// Método toString
	@Override
	public String toString() {
		return "Modulo [Nombre = " + nombre + ", Horas = " + horas + ", Tutor = " + tutor + ", Convalidable = " + convalidable + "]";
	}
}
