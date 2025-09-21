import java.util.Scanner;
import java.io.*;
import java.io.IOException;

public class Ejercicio3 {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String [] args) {
		System.out.println("¡Bienvenidos al creador de Directorios!");
		System.out.println("Introduce el nombre del directorio en donde quieres crear el fichero:");
		String nombreDirectorio = entrada.nextLine();

		System.out.println("Ahora introduzca el nombre del fichero que quieres que cree:");
		String nuevoFichero = entrada.nextLine();

		File directorio = new File(nombreDirectorio);

		if(directorio.exists()) {
			File fichero = new File(nombreDirectorio + "/" + nuevoFichero);

			try {
				System.out.println("Se ha creado el fichero (" + fichero.createNewFile() + ")");
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No se ha encontrado el directorio.");
		}
	}
}
