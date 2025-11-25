package Temporal;
// package LanzarProceso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneradorProceso {
	public void ejecutar(String ruta) {
		List<String> nombreArgumentos = new ArrayList<>();

		if (ruta == null || ruta.isEmpty()) {
			System.out.println("Falta elnombre del comando");
			System.exit(1);
		}

		// File directorio = new File(ruta);
		nombreArgumentos.add(ruta);// EXAMPLE OF A PROGRAM PATH
		
		ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);
		pb.command(nombreArgumentos); // COMAND IS THE NAME OF THE EXECUTABLE
		// Esto hace que el proceso herede la entrada salida estandar del proceso padre. Así podemos ver el resulado del comando.
		//(ipconfig en este caso)
		
		pb.inheritIO();

		try {
			Process proceso = pb.start(); //es lo mismo que pb.start();
			// Ahora toca obtener el codigo de retorno para saber si se ha ejecutado bien el programa y para ello esperamos a que se 
			
			// acabe el proceso, por eso debemos de esperar a que acabe con:
			int codigoRetorno = proceso.waitFor();
			
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("El comando devuelve " + codigoRetorno);
			
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (codigoRetorno==0) {
				System.out.println("Ejecucion correcta.");
			} else {
				System.out.println("Ejecucion con errores");
			}
		} catch(IOException ioe) { // La minima excepcion que podemos tratar, engloba a todas las excepciones.
			System.out.println("Error durante la ejecución del programa");
			System.out.println("INFORMACION ADICIONAL");
			
			ioe.printStackTrace(); // Con esto sacamos la traza del error, también podemos poner nosotros un mensaje
			System.exit(2);
		} catch(InterruptedException ie) { // Captura la traza de error y la eniamos a el STDERR a la salida estandar de error.
			System.err.println("Proceso interrumpido. ");
			System.exit(3);
		}
	}
}
