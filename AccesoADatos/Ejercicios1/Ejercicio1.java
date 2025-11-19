// package Ejercicios1;

import java.util.Scanner;
import java.io.*;

public class Ejercicio1 {
	static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Bienvenido.");
		System.out.println("Introduzca el nombre del directorio al que le quieres ver que ficheros tiene: ");
		
		String directorio = entrada.nextLine();
		File fichero = new File("AccesoADatos/Ejercicios1/" + directorio);
		
		if(fichero.exists() && fichero.isDirectory()) {
			File [] ficheros = fichero.listFiles();

			for(int i = 0; i < ficheros.length; i++) {
				System.out.println(ficheros[i].getName());
			}
		} else {
			fichero.mkdir();
			System.out.println("No existita, pero ahora si.");
		}
	}
}
