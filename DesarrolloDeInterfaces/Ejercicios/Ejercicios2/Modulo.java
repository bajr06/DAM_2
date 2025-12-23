package Ejercicios2;

public class Modulo {
	String nombre;
	int horas;
	Profesor profesor;
	boolean convalidacion;
	
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
	
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	public boolean isConvalidacion() {
		return convalidacion;
	}
	public void setConvalidacion(boolean convalidacion) {
		this.convalidacion = convalidacion;
	}
	
	public Modulo(String nombre, int horas, Profesor profesor, boolean convalidacion) {
		this.nombre = nombre;
		this.horas = horas;
		this.profesor = profesor;
		this.convalidacion = convalidacion;
	}
	
	@Override
	public String toString() {
		return "Modulo [Nombre = " + nombre + ", Horas = " + horas + ", Profesor = " + profesor + ", Convalidacion = " + convalidacion + "]";
	}	
}
