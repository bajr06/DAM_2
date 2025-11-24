package Ejercicios1;

import java.util.Scanner;
import java.io.*;

public class Ejercicio4 {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String [] args) {
		File fichero = new File("AccesoADatos/Ejercicios1/Prueba4");

		if(fichero.isDirectory()) {
			File [] ficheros = fichero.listFiles();

			for(int i = 0; i < ficheros.length; i++) {
				System.out.println(i + " " + ficheros[i].getName());
			}
		} else {
			System.out.println("No existe el directorio.");
		}
	}
}
