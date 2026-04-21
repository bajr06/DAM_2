package ManejoFicheros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Objetos.Empleado;

public class Comprobacion {
	public static void comprobarDirectorios(File [] directorios) {
		for(int i = 0; i < directorios.length; i++) {
			if(!directorios[i].exists()) {
				directorios[i].mkdir();
			}
		}
	}

	public static File [] comprobarFicheros(File [] ficheros) throws IOException {
		for(int i = 0; i < ficheros.length; i++) {
			if(!ficheros[i].exists()) {
				ficheros[i].createNewFile();
			}
		}

		return ficheros;
	}

	private static Object[] controlEntrada(Scanner s) throws NoSuchElementException {
		Object[] entrada = new Object[2];

		IO.print("Introduzca su identificacion de usuario: ");
		entrada[0] = s.nextInt();

		s.nextLine();
		
		IO.print("Introduzca su contraseña: ");
		entrada[1] = s.nextLine();

		return entrada;
	}

	public static Empleado comprobacionCredenciales(ArrayList<Empleado> empleados) {
		try {
			Object [] datos = controlEntrada(new Scanner(System.in));

			for(Empleado e: empleados) {
				if(e.getIdentificacion() == (int)datos[0] && e.getContrasenya().equals((String)datos[1])) {
					System.out.println("\nSesión iniciado correctamente, bienvenid@ " + e.getNombre());							
					return e;
				}
			}

			IO.println("Usuario no existente, intentelo de nuevo\n");
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta ha devuelto " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		}

		return null;
	}
}
