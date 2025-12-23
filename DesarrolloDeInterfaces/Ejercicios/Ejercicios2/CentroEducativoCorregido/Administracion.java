package Ejercicios2.CentroEducativoCorregido;

public class Administracion extends Persona {
	// 1. Atributos
	private String estudios;
	private int antiguedad;

	// 2. Método constructor
	public Administracion(String dni, String nombre, String apellidos, double salario, String estudios, int antiguedad) {
		super(dni, nombre, apellidos, salario);
		this.estudios = estudios;
		this.antiguedad = antiguedad;
	}

	// 3. Métodos get & set
	public String getEstudios() {
		return estudios;
	}
	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	@Override
	public String toString() {
		return "Administracion [Estudios = " + estudios + ", Antiguedad = " + antiguedad + ", DNI = " + dni +
		", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + "]";
	}
}
