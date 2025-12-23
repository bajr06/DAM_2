package Ejercicios2.CentroEducativoCorregido;

public class Directivo extends Persona {
	// 1. Atributos
	private boolean salesiano;
	private char turno; // M --> mañana, T --> Tarde;
	
	// 2. Método constructor
	public Directivo(String dni, String nombre, String apellidos, double salario, boolean salesiano, char turno) {
		super(dni, nombre, apellidos, salario);
		this.salesiano = salesiano;
		this.turno = turno;
	}

	// 3. get & set
	protected boolean isSalesiano() {
		return salesiano;
	}
	protected void setSalesiano(boolean salesiano) {
		this.salesiano = salesiano;
	}

	protected char getTurno() {
		return turno;
	}
	protected void setTurno(char turno) {
		this.turno = turno;
	}

	// 4. toString
	@Override
	public String toString() {
		return "Directivo [Salesiano = " + salesiano + ", Turno = " + turno + ", DNI = " + dni +
		", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + "]";
	}
}
