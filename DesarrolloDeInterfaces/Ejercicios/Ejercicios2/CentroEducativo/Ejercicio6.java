package Ejercicios2.CentroEducativo;

import java.util.Scanner;

public class Ejercicio6 {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String [] args) {
		Profesor p1 = new Profesor("02254654N", "Santiago", "Abascal", 1500.8, 5, false);
		Profesor p2 = new Profesor("024656456P", "Alberto", "Nunez Feijo", 1300.6, 3, true);
	
		// Administracion a1 = new Administracion("02634578B", "Jose Maria", "Gonzalez Pacheco", 1700.7, Estudios.MEDIO, 8);
		// Administracion a2 = new Administracion("021454464G", "Sofia", "Lopez del Sastre", 1750.6, Estudios.AVANZADO, 5);
	
		// Directivo d1 = new Directivo("02574596V", "Leonardo", "Marescutti Mariñas", 2000.0, true, Turno.MANANA);
		// Directivo d2 = new Directivo("16878654G", "Ainhoa", "Leonor Matesanz", 4567.4, false, Turno.TARDE);
	
		Modulo m1 = new Modulo("Informatica", 120, p2, true);
		Modulo m2 = new Modulo("Historia", 80, p1, false);
	
		Modulo [] ms1 = {m1, m2};
		// Modulo [] ms2 = {m1};
		Modulo [] ms3 = {m2};
	
		Alumno al1 = new Alumno("02555657N", "Bryan Andreu", "Jimenez Rojas", "07/06/2006", Sexo.MASCULINO, false, ms1);
		Alumno al2 = new Alumno("02458743P", "Ainhoa Catherine", "Jimenez Rojas", "01/09/2003", Sexo.FEMENINO, true, ms3);
	
		System.out.println(al1.toString());
		System.out.println(al2.toString());
	}

	/* private static Alumno altaAlumno() {
		System.out.println("Introduzca los datos pedidos siguiendo el siguiente orden (dando enter cada vez que rellenes uno).");
		System.out.println("DNI, Nombres, Apellidos, Fecha de Nacimiento, Sexo, Si es repetidor o no, y los módulos en los que está");

		String dni = entrada.nextLine();
		String nombres = entrada.nextLine();
		String apellidos = entrada.nextLine();
		String f_nacimiento = entrada.nextLine();
		String sexo
	}
	*/
}
