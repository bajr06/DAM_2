package JavaNIO;

import java.nio.*;

public class Main {
	public static void LeerFiles() {
		Path ruta = Paths.get("planetas.txt");

		try {
			String contenido = Files.readString(ruta);
			
			System.out.println(contenido);


			List<String> listaContenido = File.readAllLines(ruta);

			for(String linea : listaContenido) {
				System.out.println("Linea: " + linea);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void EscribirFiles() {
		Path ruta = Paths.get("FicheroEscritua.txt");

		try {
			String contenido = "Hola, esta es mi primera escritura";
			Files.write(ruta, contenido.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void CopiarFiles() {
		Path rutaOrigen = Paths.get("FicheroEscritura.txt");
		Path rutaDestino = Paths.get("FicheroCopia");

		try {
			Files.copy(rutaOrigen, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOExceptio ioe) {
			ioe.printStackTrace();
		}
	}

	public static void ListarContenidos() {
		Path directorio = Path.of(".");

		try {
			Stream<Path> flujo = Files.list(directorio);
			flujo.forEach(archivo -> System.out.println(archivo.getFileName()));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void PropiedadesFiles() {
		Path ruta = Path.of("primos.txt");

		System.out.println("Fichero existe: " + Files.exists(ruta));
		System.out.println("Fichero es directorio: " + Files.isDirectory(ruta));
	}

	public static void BorrarFiles() {
		Path ruta = Path.of("primos.txt");

		try {
			Files.deleteIfExists(ruta)
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
