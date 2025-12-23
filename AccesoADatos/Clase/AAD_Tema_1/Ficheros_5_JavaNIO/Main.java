package Ficheros_5_JavaNIO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	// Lectura con la calse Files de java.nio.
	public static void LeerFiles() {
		// Parámetros URL o ruta.
		Path ruta = Paths.get("AccesoADatos/Clase/Tema1/Ficheros_5_JavaNIO/Planetas.txt");

		try {
			// Leo todo el contenido del fichero.
			String contenido = Files.readString(ruta);
			
			System.out.println(contenido);
			System.out.println("---------------------------------");

			// Leer linea por línea de un fichero.
			List<String> listaContenido = Files.readAllLines(ruta);

			for(String linea : listaContenido) {
				System.out.println("Linea: " + linea);
			}
		} catch(IOException ioe) {
			ioe.getMessage();
		}
	}

	public static void EscribirFiles() {
		Path ruta = Paths.get("AccesoADatos/Clase/Tema1/Ficheros_5_JavaNIO/FicheroEscritura.txt");

		try {
			String contenido = "Hola, esta es mi primera escritura";
			Files.write(ruta, contenido.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Copia el contenido de un fichero en otro; si el fichero destino no existe, lo crea.
	public static void CopiarFiles() {
		Path rutaOrigen = Paths.get("AccesoADatos/Clase/Tema1/Ficheros_5_JavaNIO/FicheroEscritura.txt");
		Path rutaDestino = Paths.get("AccesoADatos/Clase/Tema1/Ficheros_5_JavaNIO/FicheroCopia");

		try {
			Files.copy(rutaOrigen, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Lista los contenidos de un directorio.
	public static void ListarContenidos() {
		// Path.of = Path.get;
		Path directorio = Path.of(".");

		try {
			Stream<Path> flujo = Files.list(directorio);
			flujo.forEach(archivo -> System.out.println(archivo.getFileName()));

			flujo.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Lee las propiedades de un archivo
	public static void PropiedadesFiles() {
		Path ruta = Path.of("AccesoADatos/Clase/Tema1/Ficheros_5_JavaNIO/Primos.txt");

		System.out.println("Fichero existe: " + Files.exists(ruta));
		System.out.println("Fichero es directorio: " + Files.isDirectory(ruta));
	}

	// Borra un archivo
	public static void BorrarFiles() {
		Path ruta = Path.of("AccesoADatos/Clase/Tema1/Ficheros_5_JavaNIO/Primos.txt");

		try {
			Files.deleteIfExists(ruta);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}


	public static void main(String [] args) {
		LeerFiles();
		EscribirFiles();
		CopiarFiles();
		ListarContenidos();
		PropiedadesFiles();
		BorrarFiles();
	}
}
