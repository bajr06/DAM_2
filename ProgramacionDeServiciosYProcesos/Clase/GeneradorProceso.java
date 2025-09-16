// package Ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneradorProceso {
	public void ejecutar(String rutaDirectorio, String nombreEjecutable) {
		List<String> nombreArgumentos = new ArrayList<>();
		nombreArgumentos.add(nombreEjecutable);

		File directorio = new File(rutaDirectorio);
		ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);

		// comand ==> nombre del ejecutable
		pb.comand(nombreEjecutable);

		// directory ==> ruta como objeto de la clase file.
		pb.directory(directorio);

		try {
			// Process proceso = pb.start();
			pb.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
