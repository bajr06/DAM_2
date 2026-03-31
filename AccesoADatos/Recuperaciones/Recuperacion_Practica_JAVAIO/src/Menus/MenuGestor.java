package Menus;

import java.util.NoSuchElementException;
import java.util.Scanner;

import Acciones.AccionesGestor;

public class MenuGestor {
	private static Scanner s = new Scanner(System.in);

	public static int seleccionOpcionG() throws NoSuchElementException {
		IO.println("\nSeleccione una de las siguientes opciones:");
		IO.println("1. Realizar acción en plantas");
		IO.println("2. Realizar acción en empleados");
		IO.println("3. Ver estadísticas");
		IO.println("Cualquier otro número para salir");

		return s.nextInt();
	}

	public static int seleccionOpcionGP() throws NoSuchElementException {
		IO.println("\nSeleccione la opción a realizar sobre las plantas:");
		IO.println("1. Dar de alta");
		IO.println("2. Dar de baja");
		IO.println("3. Modificar campos");

		return s.nextInt();
	}
	
	public static int preguntaAltaPlantas() throws NoSuchElementException {
		IO.println("\nSeleccione que opción de alta desea ejecutar");
		IO.println("1. Añadir nueva planta");
		IO.println("2. Seleccionar planta dada de baja anteriormente");
		
		return s.nextInt();
	}
	
	public static Object [] peticionDatosPlanta() throws NoSuchElementException {
		Object [] datos = new Object[6];
		
		IO.print("Escriba el identificador de la nueva planta: ");
		datos[0] = s.nextInt();
		
		s.nextLine();
		
		IO.print("Escriba el nombre: ");
		datos[1] = s.nextLine();

		IO.print("Escriba el nombre de la foto de la planta: ");
		datos[2] = s.nextLine();

		IO.print("Escriba su descripción: ");
		datos[3] = s.nextLine();

		IO.print("Escriba su precio: ");
		datos[4] = s.nextFloat();

		IO.print("Escriba su cantidad: ");
		datos[5] = s.nextInt();

		s.nextLine();

		return datos;
	}
	
	public static int peticionAltaPlanta() throws NoSuchElementException {
		IO.print("\nIndique el codigo de la planta que desea volver a dar de alta: ");
		
		return s.nextInt();
	}
	
	public static int peticionCantidadNueva() throws NoSuchElementException {
		IO.print("\nIndique la nueva cantidad de la planta ");;
		
		return s.nextInt();
	}
	
	public static int peticionBajaPlanta() throws NoSuchElementException {
		IO.print("\nIndique el codigo de la planta que desea dar de Baja: ");
		
		return s.nextInt();
	}
	
	public static int peticionPlantaMoficar() throws NoSuchElementException {
		IO.print("\nEscriba el identificador de la planta que desea modificar: ");
		
		return s.nextInt();
	}
	
	public static int preguntaParteModificarPlanta() throws NoSuchElementException {
		IO.println("\n¿Cúal de estas partes de la planta desea modificar?");
		IO.println("1. Codigo");
		IO.println("2. Nombre");
		IO.println("3. Fichero de la foto");
		IO.println("4. Descripcion");
		IO.println("5. Precio");
		IO.println("6. Cantidad");
		
		return s.nextInt();
	}
	
	public static int modificarCodigo() throws NoSuchElementException {
		IO.println("¿Qué nuevo código desea darle a la planta?");
		
		return s.nextInt();
	}
	
	public static String modificarNombre() throws NoSuchElementException {
		IO.println("¿Qué nuevo nombre desea darle a la planta?");
		
		return s.nextLine();
	}
	
	public static String modificarFoto() throws NoSuchElementException {
		IO.println("¿Qué nueva foto desea darle a la planta?");
		
		return s.nextLine();
	}
	
	public static String modificarDescripcion() throws NoSuchElementException {
		IO.println("¿Qué nueva descripción desea darle a la planta?");
		
		return s.nextLine();
	}
	
	public static float modificarPrecio() throws NoSuchElementException {
		IO.println("¿Qué nuevo precio desea darle a la planta?");
		
		return s.nextFloat();
	}
	
	public static int modificarCantidad() throws NoSuchElementException {
		IO.println("¿Qué nueva cantidad desea darle a la planta?");
		
		return s.nextInt();
	}

	public static int seleccionOpcionGE() throws NoSuchElementException {
		IO.println("\nSeleccione la opción a realizar sobre los empleados:");
		IO.println("1. Dar de alta");
		IO.println("2. Dar de baja");

		return s.nextInt();
	}

	public static int preguntaAltaEmpleados() throws NoSuchElementException {
		IO.println("\nSeleccione que opción de alta desea ejecutar");
		IO.println("1. Añadir nuevo empleado");
		IO.println("2. Seleccionar empleado dado de baja anteriormente");
		
		return s.nextInt();
	}

	public static Object [] peticionDatosEmpleado() {
		Object [] datos = new Object[6];
		boolean comprobacion;

		datos[0] = (int) Math.round(1000 + Math.random() * 9000);
		s.nextLine();
				
		IO.print("Escriba el nombre del nuevo empleado: ");
		datos[1] = s.nextLine();

		IO.print("Escriba su contraseña (tiene que contener mínimo 3 mayúsculas, 3 minúsculas y 3 números): ");
		datos[2] = s.nextLine();
		comprobacion = AccionesGestor.comprobarContrasenya(String.valueOf(datos[2]));
		while(comprobacion == false) {
			IO.print("Escriba una contraseña que contenga mínimo 3 mayúsculas, 3 minúsculas y 3 números: ");
			datos[2] = s.nextLine();

			comprobacion = AccionesGestor.comprobarContrasenya(String.valueOf(datos[2]));
		}
		
		datos[3] = "vendedor";

		return datos;
	}

	public static int peticionAltaEmpleado() throws NoSuchElementException {
		IO.print("\nIndique el codigo del empleado que desea volver a dar de alta: ");
		
		return s.nextInt();
	}

	public static int peticionBajaEmpleado() throws NoSuchElementException {
		IO.print("\nIndique el identificador del empleado que desea dar de baja: ");
		
		return s.nextInt();
	}
}
