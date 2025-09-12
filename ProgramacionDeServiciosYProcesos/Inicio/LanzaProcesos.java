// package Ejercicios;

public class LazaProcesos{
	public static void main(String [] args) {
		String ruta = "C:/windows/system32/notepad.exe";
		
		GeneradorProceso lanzador = new GeneradorProceso();
		lanzador.ejecutar(ruta);
		
		System.out.println("Proceso ejecutado.");
	}
}
