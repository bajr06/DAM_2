// package LanzarProceso;

public class Principal {
	public static void main(String[] args) {
		String ruta = "ipconfig";
		String [] parametros = {
			"/all"
		}
		GeneradorProceso lanzador = new GeneradorProceso();
		
		lanzador.ejecutar(ruta, parametros); 
		System.out.println("Proceso ejecutado");
	}
}
