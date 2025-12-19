package Ejercicios3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
	static Scanner entrada = new Scanner(System.in);

	private static int menu() {
		System.out.println("1. Almacenar productos.");
		System.out.println("2. Visualizar productos (la lista entera).");
		System.out.println("3. Visualizar los datos de un producto en concreto, basado en su ID.");
		System.out.println("4. Borrar productos dado un ID.");
		System.out.println("5. Modificar los campos de un producto.");
		int seleccion = entrada.nextInt();

		return seleccion;
	}

	private static int pedirId() {
		System.out.println("Introduzca el ID del producto: ");
		int id = entrada.nextInt();

		return id;
	}

	private static int pedirStock() {
		System.out.println("Introduzca el Stock del producto: ");
		int stock = entrada.nextInt();

		return stock;
	}

	private static double pedirPrecio() {
		System.out.println("Introduzca el Precio del producto: ");
		double precio = entrada.nextDouble();

		return precio;
	}

	public static void main(String[] args) {
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios3/productos1.dat");

		try {
			if(!fichero.exists()) {
				fichero.createNewFile();
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("Seleccione la opción que quieres realizar: ");
		int opcion = menu();

		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

			switch(opcion) {
				/*
				case 1: Tengo que terminar esto.
				*/
			}

			raf.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
