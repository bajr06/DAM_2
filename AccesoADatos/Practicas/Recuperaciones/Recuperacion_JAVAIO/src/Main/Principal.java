package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ManejoFicheros.Carga;
import ManejoFicheros.Comprobacion;
import ManejoFicheros.Modificacion;
import Menus.MenuGeneral;
import Objetos.Empleado;
import Objetos.Planta;

public class Principal {
	public final static int EXIT_SUCCESS = 0, EXIT_FAILURE = 1;
	public static File [] directorios = {
		new File("Plantas"),
		new File("Empleados"),
		new File("Tickets"),
		new File("Devoluciones")
	};

	public static File [] ficheros = {
		new File("Plantas/plantas.xml"),
		new File("Plantas/plantas.dat"),
		new File("Plantas/plantasBaja.xml"),
		new File("Plantas/plantasBaja.dat"),
		new File("Empleados/empleados.dat"),
		new File("Empleados/empleadosBaja.dat")
	};
	
	private static ArrayList<Planta> plantas;
	private static ArrayList<Empleado> empleados;

	private static void cargaDatos()  {
		try {
			Comprobacion.comprobarDirectorios(directorios);
			Comprobacion.comprobarFicheros(ficheros);
		
			plantas = Carga.cargaPlantasXML(ficheros[0]);
			Carga.cargaPlantasDAT(plantas, ficheros[1]);

			empleados = Carga.cargaEmpleadosDAT(ficheros[4]);
		} catch(IOException ioe) {
			System.err.println("Error en la carga de los ficheros.\n Valor devuelto: \""
			+ ioe.getMessage() + "\"\n Cerrando programa\n");
			System.exit(EXIT_FAILURE);
		}
	}

	private static void ejecucionMenu() {
		IO.println("¡Bienvenido al vivero C+#!");

		Empleado tipoUsuario;

		do {
			tipoUsuario = Comprobacion.comprobacionCredenciales(empleados);
		} while(tipoUsuario == null);

		MenuGeneral.ejecutarMenu(tipoUsuario, plantas, empleados, ficheros, directorios);
	}

	private static void modificacionDatos() {
		try {
			Modificacion.modificarPlantasXML(ficheros, plantas);
			Modificacion.modificarPlantasDAT(ficheros, plantas);
			Modificacion.modificarEmpleadoDAT(ficheros[4], empleados);
		} catch(IOException ioe) {
			System.err.println("Error en la carga de los datos cambiados en la aplicación: \""
			+ ioe.getMessage() + "\"\n Cerrando Programa.\n");
			System.exit(EXIT_FAILURE);
		}
	}

	public static void main(String[] args) {
		cargaDatos();

		ejecucionMenu();

		modificacionDatos();
	}
}

