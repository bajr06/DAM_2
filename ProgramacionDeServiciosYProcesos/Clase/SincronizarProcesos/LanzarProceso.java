package SincronizarProceso;

public class LanzarProceso {
	public class void main(String [] args) {
		GenerarProceso lanzador = new GeneradorProceso();
		String ruta = "notepad.exe";

		lanzador.ejecutarYDestruir(ruta);
		System.out.println("Cmd cerrado.");
	}
}
