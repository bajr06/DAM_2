// package Ejercicios;

// import java.util.List;
// import java.util.ArrayList;

public class GeneradorProceso2 {
	public void ejecutar(String ruta) {
		// List<String> nombredeargumentos = new ArrayList<>();
		ProcessBuilder pb = new ProcessBuilder(ruta); // Objeto que tiene la capacidad de construir/generar un proceso.
		
		try {
			// Process proceso = pb.start();
			pb.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
