// package Ejercicios1;

import java.util.*;
import java.io.*;

public class Ejercicio2Corregido {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);

		// Introducir la ruta
		System.out.println("Introduce nombre del fichero:");
		File fichero = new File("AccesoADatos/Ejercicios1/" + sc.nextLine());

		if(fichero.exists() && fichero.isFile()) {
			if(fichero.delete()) {
				System.out.println("Se ha eliminado el fichero.");
			} else {
				System.out.println("No se ha podido eliminar el fichero.");
			}
		} else {
			System.out.println("No existe el archivo.");
		}

		sc.close();
	}
}
