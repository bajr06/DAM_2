// package AccesoADatos1;

import java.io.*;

public class Ejercicio2 {
	public static void main(String [] args) {
		File fichero = new File("Prueba.txt");

		if(fichero.exists()) {
			fichero.delete();
		} else {
			System.out.println("No existe el archivo.");
		}
	}
}
