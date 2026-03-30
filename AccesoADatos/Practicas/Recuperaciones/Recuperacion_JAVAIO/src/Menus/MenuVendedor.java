package Menus;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuVendedor {
	private static Scanner s = new Scanner(System.in);
	
	public static int seleccionOpcionV() throws NoSuchElementException {
		IO.println("Seleccione una de las siguientes opciones:");
		IO.println("1. Ver catálogo");
		IO.println("2. Realizar acción");
		IO.println("3. Buscar ticket");
		IO.println("Cualquier otro número para salir");

		return s.nextInt();
	}

	public static int seleccionAccionV() throws NoSuchElementException {
		IO.println("¿Qué acción deseas hacer?");
		IO.println("1. Venta");
		IO.println("2. Devolucion");

		return s.nextInt();
	}

	public static boolean confirmar() throws NoSuchElementException {
		s.nextLine();

		IO.println("¿Está seguro de realizar la acción?");
		String respuesta = s.nextLine();

		if(respuesta.equalsIgnoreCase("Si")) {
			return true;
		} else {
			return false;
		}
	}

	public static int seleccionPlantaV() throws NoSuchElementException {
		IO.println("¿Qué planta vas a vender?");

		return s.nextInt();
	}

	public static int seleccionCantidadV() throws NoSuchElementException {
		IO.println("¿Qué cantidad vas a vender?");

		return s.nextInt();
	}

	public static int cantidadPlantasVenta() throws NoSuchElementException {
		IO.println("¿Qué cantidad de plantas vas a vender?");

		return s.nextInt();
	}

	public static int seleccionTicket() throws NoSuchElementException {
		IO.println("Introduzca el número del ticket:");

		return s.nextInt();
	}
}
