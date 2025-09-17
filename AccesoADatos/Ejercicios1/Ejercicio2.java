// package AccesoADatos1;

public class Ejercicio2 {
	public static void main(String [] args) {
		File fichero = new File();

		if(fichero.exists()) {
			fichero.delete();
		} else {
			System.out.println("No existe el archivo.");
		}
	}
}
