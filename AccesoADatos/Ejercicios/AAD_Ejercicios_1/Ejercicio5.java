package AAD_Ejercicios_1;

import java.util.Scanner;
import java.io.*;

public class Ejercicio5 {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String [] args) {
		System.out.println("Introduzca el nombre del fichero");
		String nombreFichero = entrada.nextLine();

		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios1/" + nombreFichero);

		boolean respuestaPermisos = comprobarPermisos(fichero);

		if(respuestaPermisos) {
			cambiarPermisos(fichero);
		}
	}

	private static boolean comprobarPermisos(File fichero) {
		if(fichero.exists()) {
			char lectura = permisoLectura(fichero);
			char escritura = permisoEscritura(fichero);
			char ejecucion = permisoEjecucion(fichero);
			String permisos = lectura + "" + escritura + "" + ejecucion;

			System.out.println("Los permisos que tiene el fichero son: " + permisos);

			return true;
		} else {
			System.out.println("No existe el fichero.");

			return false;
		}
	}

	private static char permisoLectura(File fichero) {
		if(fichero.canRead()) {
			return 'r';
		} else {
			return '_';
		}
	}

	private static char permisoEscritura(File fichero) {
		if(fichero.canWrite()) {
			return 'w';
		} else {
			return '_';
		}
	}

	private static char permisoEjecucion(File fichero) {
		if(fichero.canExecute()) {
			return 'x';
		} else {
			return '_';
		}
	}

	private static void cambiarPermisos(File fichero) {
		System.out.println("¿Qué permiso le quieres cambiar?");
		String decision = entrada.nextLine();

		switch(decision) {
			case "r":
				fichero.setReadable(false);
				break;
			case "w":
				fichero.setWritable(false);
				break;
			case "x":
				fichero.setExecutable(false);
				break;
			default:
				System.out.println("Error al realizarlo.");
		}
	}
}
