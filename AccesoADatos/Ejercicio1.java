/ package AccesoADatos1;

import java.util.Scanner;
import java.io.*;

public class Ejercicio1 {
	static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Bienvenido.");
		System.out.println("Introduzca el nombre del directorio al que le quieres ver que ficheros tiene: ");
		String directorio = entrada.nextLine();
		
		File fichero = new File(directorio);
		
		if(fichero.exists()) {
			File [] ficheros = fichero.listFiles();
			
			for(int i = 0; i < 2; i++) {
				System.out.println(ficheros[i].getName());
			}
		} else {
			fichero.mkdir();
			System.out.println("No existita, pero ahora si.");
		}
	}
}
