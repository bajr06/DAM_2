package Ejercicios4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Ejercicios3.Persona;

public class Ejercicio3 {
	static Scanner entrada = new Scanner(System.in);

	private static int menu() {
		System.out.println("Escriba la opción que quieres realizar:");
		System.out.println("1. Añadir persona.");
		System.out.println("2. Visualizar.");
		System.out.println("3. Salir.");

		return entrada.nextInt();
	}

	private static Persona pedirPersona() {
		entrada.nextLine();
		
		System.out.println("Escriba el nombre de la persona a guardar:");
		String nombre = entrada.nextLine();

		System.out.println("Escriba la edad de la persona a guardar:");
		int edad = entrada.nextInt();

		return new Persona(nombre, edad);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios3/Datos1.dat");

		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}

		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero)); ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
			int primeraSeleccion = menu();
			int seleccion = primeraSeleccion;

			do {
				switch (seleccion) {
					case 1:
						ArrayList<Persona> personaNueva = new ArrayList<>();
						personaNueva.add(pedirPersona());
						primeraSeleccion = 0;

						oos.writeObject(personaNueva);
						break;
					case 2:
						if(primeraSeleccion == 2) {
							IO.println("Tienes que añadir primero una persona");
						} else {
							ArrayList<Persona> personas = (ArrayList<Persona>) ois.readObject();

							System.out.println("Las personas almacenadas en el fichero son:");
							for(Persona p: personas) {
								System.out.println(p.toString());
							}
							System.out.println();

						}
						break;
					default:
						IO.println("Nuevamente; o has escogido algo que no existe, o has salido del programa...");
						break;
				}

				seleccion = menu();
			} while(seleccion != 3);

			IO.println("Pues si, has salido. ¡Hasta la próxima!");
		} catch(IOException | ClassNotFoundException cnfioe) {
			cnfioe.printStackTrace();
		}
	}
}
