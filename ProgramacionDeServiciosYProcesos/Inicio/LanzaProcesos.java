// package Ejercicios;

public class LanzaProcesos{
	public static void main(String [] args) {
		String ruta = "C:/Users/DAM/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/System Tools";
		
		GeneradorProceso lanzador = new GeneradorProceso();
		lanzador.ejecutar(ruta);
		
		System.out.println("Proceso ejecutado.");
	}
}
