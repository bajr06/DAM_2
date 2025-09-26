package SincronizarProcesos;

import java.io.*;
import java.util.*;

public class GeneradorProceso {
	public static void ejecutarYDestruir(String ruta) {
		Process proceso = null;
		ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);

		try {
			proceso = pb.start();
			System.out.println("Se ha lanzado el proceso.");
			System.out.println("El proceso padre espera a que el hijo termine su ejecución.");
		} catch(IOException e) {
			e.printStackTrace();
		}

		try {
			proceso.waitFor();
		} catch(InterrupedException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(proceso.exitValue());
		} catch (IllegalThreadStatException e) {
			System.out.print(e);
		}

		if(proceso != null) {
			proceso.destroy();
			System.out.println("El proceso hijo se destruye.");
		}
	}
}
