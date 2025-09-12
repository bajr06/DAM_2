// package Ejercicios;

public class GeneradorProceso {
	public void ejecutar(String ruta) {
		ProcessBuilder pb = new ProcessBuilder(ruta); // Objeto que tiene la capacidad de construir/generar un proceso.
		
		try {
			// Process proceso = pb.start();
			pb.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
