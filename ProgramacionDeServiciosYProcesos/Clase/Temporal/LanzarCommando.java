package Temporal;
// package LanzarProceso;

public class LanzarCommando {
	public static void main(String[] args) {
		String ruta = "ipconfig";
		GeneradorProceso lanzador = new GeneradorProceso();
		
		lanzador.ejecutar(ruta); 
		System.out.println("Proceso ejecutado!!!");
	}
}
