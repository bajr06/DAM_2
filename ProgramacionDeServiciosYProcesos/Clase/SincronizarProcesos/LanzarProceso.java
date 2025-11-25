package SincronizarProcesos;

public class LanzarProceso {
	public static void main(String [] args) {
		GeneradorProceso lanzador = new GeneradorProceso();
		String ruta = "notepad.exe";

		lanzador.ejecutarYDestruir(ruta);
		System.out.println("Cmd cerrado.");
	}
}
