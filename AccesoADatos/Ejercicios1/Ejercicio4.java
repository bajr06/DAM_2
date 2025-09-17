import java.util.Scanner;
import java.io*;

public class Ejercicio3 {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String [] args) {
		File fichero = new fichero(HolaMundo);

		if(fichero.isDirectory()) {
			File [] ficheros = fichero.listFiles();
			int contador = 0;

			while(fichero != null) {
				System.out.println(contador + " " + ficheros[contador].getName());
				contador++;
			}
		} else {
			System.out.println("No existe el directorio.");
		}
	}
}
