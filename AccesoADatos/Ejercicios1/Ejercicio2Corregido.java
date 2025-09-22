package Ejercicio1;

import java.io.*;

public class Ejercicio2 {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce nombre del fichero:");
		File fichero = new File(sc.nextLine());

		if(fichero.exists() && fichero.isFile()) {
			if(fichero.delete()) {
				System.out.println("Se ha eliminado el fichero.");
			} else {
				System.out.println("No se ha eliminado el fichero.");
			}
		} else {
			System.out.println("No existe el archivo.");
		}
	}
}
