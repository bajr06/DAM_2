package Ejercicios1;

import java.util.Scanner;
import java.io.*;

public class Ejercicio6 {
	static Scanner entrada = new Scanner(System.in);
	
	public static void main(String [] args) {
		System.out.println("Buenas noches, escriba el nombre del fichero a buscar:");
		String nombreFichero = entrada.nextLine();
		
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios1/" + nombreFichero);
		
		if(fichero.exists()) {
			System.out.println("Ya existe. La dirección del fichero es: " + fichero.getAbsolutePath());
		} else {
			try {
				fichero.createNewFile();
				fichero.setReadOnly();
				System.out.println("No existia, pero ahora ya existe.");
			} catch(IOException e) {
				e.getStackTrace();
			}
		}
	}
}
