package Ejercicio1;

import java.util.Scanner;
import java.io.*;

public class Ejercicio1Corregido {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca el nombre del directorio al que le quieres ver que ficheros tiene: ");
		String directorio = entrada.nextLine();
		
		File fichero = new File(directorio);
		
		if(fichero.exists() && directorio.isDirectory()) {
			String [] lista = fichero.list();
			
			if(lista != null) {
				for(String elemento : lista) {
					System.out.println(lista[elemento].getName());
				}
			} else {
				System.out.println("No hay ficheros dentro del directorio.");
			}
		} else {
			fichero.mkdir();
			System.out.println("No existita, pero ahora si.");
		}
	}
}
