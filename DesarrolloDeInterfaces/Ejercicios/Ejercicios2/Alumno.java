package Ejercicios2;

import java.util.Arrays;

enum Sexo{MASCULINO, FEMENINO};

public class Alumno extends Persona {
	String f_nacimiento;
	Sexo sexo;
	boolean repetidor;
	Modulo [] modulos;
	
	public String getF_nacimiento() {
		return f_nacimiento;
	}
	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public boolean isRepetidor() {
		return repetidor;
	}
	public void setRepetidor(boolean repetidor) {
		this.repetidor = repetidor;
	}
	
	public Modulo[] getModulos() {
		return modulos;
	}
	public void setModulos(Modulo[] modulos) {
		this.modulos = modulos;
	}

	public Alumno(String dni, String nombre, String apellidos, String f_nacimiento, Sexo sexo, boolean repetidor, Modulo[] modulos) {
		super(dni, nombre, apellidos);
		this.f_nacimiento = f_nacimiento;
		this.sexo = sexo;
		this.repetidor = repetidor;
		this.modulos = modulos;
	}
	
	@Override
	public String toString() {
		return "Alumno [DNI = " + dni + ", Nombre = " + nombre + ", Apellidos = " + apellidos + ", Fecha de nacimiento = " + f_nacimiento + ", Sexo = " + sexo + ", Repetidor = " + repetidor + ", Modulos = " + Arrays.toString(modulos) + "]";
	}
}
