package Ejercicios2.CentroEducativo;

enum Estudios{BAJO, MEDIO, AVANZADO};

public class Administracion extends Persona {
	Estudios estudios;
	int antiguedad;
	
	public Estudios getEstudios() {
		return estudios;
	}
	public void setEstudios(Estudios estudios) {
		this.estudios = estudios;
	}
	
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Administracion(String dni, String nombre, String apellidos, double salario, Estudios estudios, int antiguedad) {
		super(dni, nombre, apellidos, salario);
		this.estudios = estudios;
		this.antiguedad = antiguedad;
	}

	@Override
	public String toString() {
		return "Administracion [DNI = " + dni + ", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Salario = " + salario + ", Estudios = " + estudios + ", Antigüedad = " + antiguedad + "]";
	}	
}
