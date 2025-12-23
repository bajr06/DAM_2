package Ejercicios2.CentroEducativo;

enum Turno{MANANA, TARDE};

public class Directivo extends Persona {
	boolean salesiano;
	Turno turno;

	public boolean isSalesiano() {
		return salesiano;
	}
	public void setSalesiano(boolean salesiano) {
		this.salesiano = salesiano;
	}
	
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Directivo(String dni, String nombre, String apellidos, double salario, boolean salesiano, Turno turno) {
		super(dni, nombre, apellidos, salario);
		this.salesiano = salesiano;
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Directivo [DNI = " + dni + ", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + ", Es Salesiano = " + salesiano + ", Turno = " + turno + "]";
	}	
}
