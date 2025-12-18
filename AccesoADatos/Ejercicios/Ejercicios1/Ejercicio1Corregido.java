package Ejercicios1;

import java.util.*;
import java.io.*;

public class Ejercicio1Corregido {
	// Programa que liste elementos de un directorio.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Introducción de ruta que vamos a comprobar.
		System.out.println("Introduzca el directorio del que quieras imprimir los elementos:");
		String ruta = sc.nextLine();
		File directorio = new File("AccesoADatos/Ejercicios1/" + ruta);
		
		// Comprobar si la ruta es un directorio.
		if(directorio.exists() && directorio.isDirectory()) {
			String [] lista = directorio.list();
			// Puede ocurrir dos casos que no hay que recorrer.
			// 1.- Lista = null: no tiene elementos.
			// 2.- Podría ocurrir que tenga longitud 0.
			
			if(lista != null) {
				for(String elemento : lista) {
					System.out.println("Elementos de la lista " + elemento + ", con tamaño: " + elemento.length());
				}
			} else {
				System.out.println("Directorio vacío.");
			}
		} else {
			System.out.println("No existe o no es una dirección.");
		}

		sc.close();
	}
}
