package ComunicacionProcesos;

public class Principal {
	public static void main(String [] args) {
		String ruta = "ping" + " google.es";
		GeneradorComunicacionProceso lanzador = new GeneradorComunicacionProceso();

		lanzador.ejecutar(ruta);
		System.out.println("Proceso ejecutado, ¿Por qué? Porque la copia tenia más vida que el campeón");
	}
}
