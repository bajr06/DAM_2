package Ejercicios2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EjerciciosCorregidos {
	// EJERCICIOS DE LECTURA
	public static void ejercicioLectura1() {
		try(FileReader fichero = new FileReader("AccesoADatos/Ejercicios/Ejercicios2/fichero.txt")) {
			int caracter;

			while((caracter = fichero.read()) != -1) {
				if(caracter != 32) {
					System.out.print((char) caracter);
				}
			}

			IO.println();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void ejercicioLectura2() {
		int total = 0, vocales = 0;
		Pattern patron = Pattern.compile("[aeiouAEIOU]");

		try(BufferedReader br = new BufferedReader(new FileReader("AccesoADatos/Ejercicios/Ejercicios2/fichero.txt"))) {
			String linea;

			while((linea = br.readLine()) != null) {
				total += linea.length();

				Matcher match = patron.matcher(linea);

				while(match.find()) {
					vocales++;
				}
			}

			System.out.println("Total carácteres: " + total + ", vocales: " + vocales);
		} catch(IOException ioe) {
			ioe.getMessage();
		}
	}

	public static void ejercicioLectura3() {
		try(BufferedReader br = new BufferedReader(new FileReader("AccesoADatos/Ejercicios/Ejercicios2/Restaurants.csv"))) {
			String linea;
			String [] encabezados;

			// Primera lectura para leer los encabezados
			linea = br.readLine();
			encabezados = linea.split(",");

			while((linea = br.readLine()) != null) {
				int indiceComillaDoble = linea.indexOf('"');

				if(indiceComillaDoble != -1) {
					int segundoIndiceComillaDoble = linea.indexOf('"', indiceComillaDoble + 1);
					
					String parte1 = linea.substring(0, indiceComillaDoble);
					String direccion = linea.substring(indiceComillaDoble + 1, segundoIndiceComillaDoble);
					String nuevaDireccion = direccion.replace(',', ' ');
					String parte2 = linea.substring(segundoIndiceComillaDoble + 1, linea.length());

					linea = parte1 + nuevaDireccion + parte2;
				}

				// Caso estándar
				String [] partes = linea.split(",");

				for(int i = 0; i < encabezados.length; i++) {
					System.out.println("- " + encabezados[i] + ": " + partes[i]);
				}

				System.out.println("--------------------");
			}
		} catch(IOException ioe) {
			ioe.getMessage();
		}
	}

	public static void visualizar(ArrayList<String> palabra, ArrayList<Integer> veces) {
		int i = 0;

		while(i < palabra.size()) {
			System.out.println(palabra.get(i) + ": " + veces.get(i));
			i++;
		}
	}

	public static void ejercicioLectura4() {
		ArrayList<String> palabra = new ArrayList<>();
		ArrayList<Integer> veces = new ArrayList<>();

		try(BufferedReader ficheroFrutas = new BufferedReader(new FileReader("AccesoADatos/Ejercicios/Ejercicios2/frutas.txt"))) {
			String linea;

			while((linea = ficheroFrutas.readLine()) != null) {
				// En cada linea tenemos una palabra, iguala todas las palabras independientemente de como esten escritas.
				String fruta = linea.trim().toLowerCase();

				// Si existe, devuelve la posición; caso contrario, devuelve -1.
				int indice = palabra.indexOf(fruta);

				if(indice == -1) {
					// Primera vez que encuentro una palabra
					palabra.add(fruta);
					veces.add(1);
				} else {
					// Palabra está en el array e incremento las veces que aparece
					int cantidad = veces.get(indice) + 1;
					veces.set(indice, cantidad);
				}
			}

			visualizar(palabra, veces);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void ejercicioLectura5() {
		try(BufferedReader reader = new BufferedReader(new FileReader("AccesoADatos/Ejercicios/Ejercicios2/planetas.txt"))) {
			String linea;
			List<String> planetas = new ArrayList<>();

			while((linea = reader.readLine()) != null) {
				planetas.add(linea);
			}

			Collections.sort(planetas);

			for(String planeta: planetas) {
				System.out.println(planeta);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static boolean esPrimo(int numero) {
		boolean primo = true;

		if(numero > 2) {
			for(int i = 2; i < Math.sqrt(numero); i++) {
				if(numero % i == 0) {
					primo = false;
				}
			}
		} else if(primo) {
			System.out.println("Número " + numero);
		}

		return primo;
	}

	// EJERCICIOS DE ESCRITURA
	public static void ejercicioEscribir1() {
		String rutaFichero = "AccesoADatos/Ejercicios/Ejercicios2/primos.txt";
		File fichero = new File(rutaFichero);

		try {
			// En el caso de no existir el fichero
			if(!fichero.exists()) {
				fichero.createNewFile();
			}

			// Se que existe el fichero
			FileWriter aperturaEscritura = new FileWriter(fichero);
			BufferedWriter ficheroEscritura = new BufferedWriter(aperturaEscritura);

			for(int i = 2; i < 500; i++) {
				if(esPrimo(i)) {
					ficheroEscritura.write(i + "");
					ficheroEscritura.newLine();
				}
			}

			ficheroEscritura.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void ejercicioEscribir2() {
		Scanner scanner = new Scanner(System.in);

		try(BufferedWriter escritura = new BufferedWriter(new FileWriter("AccesoADatos/Ejercicios/Ejercicios2/registroDeUsuario.txt"))) {
			String frase = "";
			
			System.out.println("Introduce tus frases e introduce fin para terminar: ");
			frase = scanner.nextLine();

			while (!frase.equalsIgnoreCase("fin")) {
				escritura.write(frase + "\n");
				frase = scanner.nextLine();
			}

			System.out.println("Las frases se han guardado en 'registroDeUsuario.txt'.");

			escritura.close();
			scanner.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void ejercicioEscribir3() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Introduce la cantidad de números aleatorios a generar: ");
		int cantidad = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer del scanner.

		System.out.print("Introduce la ruta del fichero: ");
		String rutaFichero = scanner.nextLine();

		try(BufferedWriter writer = new BufferedWriter(new FileWriter("AccesoADatos/Ejercicios/Ejercicios2/" + rutaFichero))) {
			Random random = new Random();

			for(int i = 0; i < cantidad; i ++) {
				writer.write(random.nextInt(1000) + 1 + "\n"); // Genera números entre 1 y 1000
			}

			System.out.println("Se han generado y guardado los números aleatorios en el fichero.");
			
			scanner.close();
		} catch(IOException ioe) {
			System.err.println("Error al escribir el fichero: " + ioe.getMessage());
		}
	}

	public static void escribirFicheros(String linea, File ficheroEscritura) {
		// Abrir fichero para escritura
		try {
			FileWriter fw = new FileWriter(ficheroEscritura, true);
			BufferedWriter escritura = new BufferedWriter(fw);

			escritura.write(linea);

			// Inserta salto de línea
			escritura.newLine();

			escritura.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void lecturaFicheros(File fichero, File ficheroEscritura) {
		try {
			// Abro primero el fichero para lectura
			FileReader lecturaf1 = new FileReader(fichero);
			BufferedReader bufferLectura = new BufferedReader(lecturaf1);

			String linea;
			while ((linea = bufferLectura.readLine()) != null) {
				// Leo las líneas
				System.out.println(linea);

				// La llamada a la escritura del fichero.
				escribirFicheros(linea, ficheroEscritura);
			}

			bufferLectura.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void ejercicioEscribir4() {
		// Pido los datos al usuario.
		Scanner sc = new Scanner(System.in);

		IO.println("Introduce el nombre del fichero 1: ");
		String fichero1 = sc.nextLine();

		IO.println("Introduce el nombre del fichero 2: ");
		String fichero2 = sc.nextLine();

		IO.println("Introduce el nombre de la ruta ");
		String ruta = sc.nextLine();

		// Comprobar que el directorio existe.
		File directorio = new File("AccesoADatos/Ejercicios/" + ruta);

		if(directorio.isDirectory() && directorio.exists()) {
			// Creamos los objetos de tipo File.
			File fichero1Prog = new File(directorio, fichero1);
			File fichero2Prog = new File(directorio, fichero2);
			String nombreNuevo = fichero1 + "_" + fichero2;
			File ficheroNuevo = new File("AccesoADatos/Ejercicios/Ejercicios2/" + nombreNuevo);

			// Comprobar que fichero1 y fichero2 existen y son archivos
			if((fichero1Prog.exists() && fichero1Prog.isFile()) && (fichero2Prog.exists() && fichero2Prog.isFile())) {
				try {
					// Creamos el nuevo fichero
					if(!ficheroNuevo.exists()) {
						ficheroNuevo.createNewFile();
					}

					// Abrir el fichero para lectura (con las funciones anteriores).
					lecturaFicheros(fichero1Prog, ficheroNuevo);
					lecturaFicheros(fichero2Prog, ficheroNuevo);
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			} else {
				System.out.println("Los ficheros no existen o son directorios.");
			}
		} else {
			System.out.println("El directorio introducido no existe.");
		}

		sc.close();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		IO.println("Introduce el número del ejercicio a ejecutar: ");
		int opcion = sc.nextInt();

		switch (opcion) {
			case 1 -> ejercicioLectura1();
			case 2 -> ejercicioLectura2();
			case 3 -> ejercicioLectura3();
			case 4 -> ejercicioLectura4();
			case 5 -> ejercicioLectura5();
			case 6 -> ejercicioEscribir1();
			case 7 -> ejercicioEscribir2();
			case 8 -> ejercicioEscribir3();
			case 9 -> ejercicioEscribir4();
			default -> IO.println("Opción no válida");
		}

		sc.close();
	}
}
