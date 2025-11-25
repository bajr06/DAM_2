package SincronizarProcesos;

import java.io.*;

public class GeneradorProceso {
	public void ejecutarYDestruir(String ruta) {
		Process proceso = null;
		ProcessBuilder pb = new ProcessBuilder();

		try {
			proceso = pb.start();
			System.out.println("Se ha lanzado el proceso.");
			System.out.println("El proceso padre espera a que el hijo termine su ejecución.");
		} catch(IOException e) {
			e.printStackTrace();
		}

		try {
			proceso.waitFor();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(proceso.exitValue());
		} catch (IllegalThreadStateException e) {
			System.out.print(e);
		}

		if(proceso != null) {
			proceso.destroy();
			System.out.println("El proceso hijo se destruye.");
		}
	}
}
