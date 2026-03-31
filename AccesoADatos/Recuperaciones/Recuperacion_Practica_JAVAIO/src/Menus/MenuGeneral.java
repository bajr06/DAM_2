package Menus;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import Acciones.AccionesGestor;
import Acciones.AccionesVendedor;
import Objetos.Empleado;
import Objetos.Planta;

public class MenuGeneral {
	private static void ejecutarAccionesVendedor(Empleado empleado, int seleccion, ArrayList<Planta> plantas, File [] ficheros, File [] directorios) {
		if(seleccion == 1) {
			AccionesVendedor.realizarVenta(empleado, plantas, ficheros, directorios);
		} else if(seleccion == 2) {
			AccionesVendedor.realizarDevolucion(plantas, ficheros, directorios);
		} else {
			System.out.println("Opción no existente, intentelo de nuevo\n");
		}
	}

	private static void ejecutarOpcionesVendedor(Empleado empleado, ArrayList<Planta> plantas, File [] ficheros, File[] directorios) {		
		try {
			int seleccion = MenuVendedor.seleccionOpcionV();

			do {
				if(seleccion == 1) {
					AccionesVendedor.mostrarPlantas(plantas);
				} else if(seleccion == 2) {
					ejecutarAccionesVendedor(empleado, MenuVendedor.seleccionAccionV(), plantas, ficheros, directorios);
				} else if(seleccion == 3) {
					AccionesVendedor.buscarTicket(ficheros);
				}
	
				seleccion = MenuVendedor.seleccionOpcionV();
			} while(seleccion >= 1 && seleccion <= 3);
			
			System.out.println("\nCerrando programa\n¡Hasta la próxima!");
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta ha devuelto " + nsee.getMessage() + "\nRecuerde siempre insertar los datos que se le pida\nCerrando programa.");
			System.exit(1);
		}
	}
	
	private static void ejecutarAccionesPGestor(int seleccion, ArrayList<Planta> plantas, File [] ficheros, File [] directorios) {
		if(seleccion == 1) {
			AccionesGestor.darAltaPlanta(plantas, ficheros);
		} else if(seleccion == 2) {
			AccionesGestor.darBajaPlanta(plantas, ficheros);
		} else if(seleccion == 3) {
			AccionesGestor.modificarDatosPlanta(plantas);
		} else {
			System.out.println("Opción no existente, intentelo de nuevo\n");
		}
	}
	
	private static void ejecutarAccionesEGestor(int seleccion,  ArrayList<Empleado> empleados, File [] ficheros, File [] directorios) {
		if(seleccion == 1) {
			AccionesGestor.darAltaEmpleado(empleados, ficheros);
		} else if(seleccion == 2) {
			AccionesGestor.darBajaEmpleado(empleados, ficheros);
		} else {
			System.out.println("Opción no existente, intentelo de nuevo\n");
		}
	}

	private static void ejecutarOpcionesMenuGestor(ArrayList<Planta> plantas, ArrayList<Empleado> empleados, File [] ficheros, File [] directorios) {
		try {
			int seleccion = MenuGestor.seleccionOpcionG();
			
			do {
				if(seleccion == 1) {
					ejecutarAccionesPGestor(MenuGestor.seleccionOpcionGP(), plantas, ficheros, directorios);
				} else if(seleccion == 2) {
					ejecutarAccionesEGestor(MenuGestor.seleccionOpcionGE(), empleados, ficheros, directorios);
				} else if(seleccion == 3) {
					AccionesGestor.mostrarEstadisticas(directorios);
				}
	
				seleccion = MenuGestor.seleccionOpcionG();
			} while(seleccion >= 1 && seleccion <= 3);
			
			System.out.println("\nCerrando programa\n¡Hasta la próxima!");
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta ha devuelto " + nsee.getMessage() + "\nRecuerde siempre insertar los datos que se le pida\nCerrando programa.");
			System.exit(1);
		}
	}

	public static void ejecutarMenu(Empleado tipoUsuario, ArrayList<Planta> plantas, ArrayList<Empleado> empleados, File [] ficheros, File [] directorios) {
		switch (tipoUsuario.getCargo()) {
			case "vendedor":
				ejecutarOpcionesVendedor(tipoUsuario, plantas, ficheros, directorios);					
				break;
			case "gestor":
				ejecutarOpcionesMenuGestor(plantas, empleados, ficheros, directorios);
				break;
			default:
				System.err.println("El empleado no tiene categoría existente, no se puede ejecutar.");
				break;
		}
	}
}
