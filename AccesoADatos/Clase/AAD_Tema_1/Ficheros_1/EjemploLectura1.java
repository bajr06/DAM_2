package Ficheros_1;

import java.io.*;

public class EjemploLectura1 {
	public static void main(String[] args) {
		// Dentro de new File se pone la ruta del archivo o fichero.
		File fichero = new File("AccesoADatos/Clase/Tema1/Ficheros_1/FicheroEjemplo1_0.txt");
		
		if(!fichero.exists()) {
			try {
				// Crear el fichero
				fichero.createNewFile();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}

		System.out.println("Nombre fichero: " + fichero.getName());
		System.out.println("Ruta: " + fichero.getPath());
		System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
		System.out.println("Permiso lectura: " + fichero.canRead());
		System.out.println("Tamaño: " + fichero.length());

		try {
			// FileWriter escritura = new FileWriter(fichero);
			BufferedWriter pw = new BufferedWriter(new FileWriter(fichero, true));

			for (int i = 0; i < 10; i++) {
				pw.write("Linea " + i);
				pw.newLine();
			}

			pw.close();

			// Devuelve caracter a caracter
			FileReader lector = new FileReader(fichero);

			// Lleno el buffer de los caráctwewa y leo líneas.
			BufferedReader buffer = new BufferedReader(lector);
			String linea;

			while((linea = buffer.readLine()) != null) {
				System.out.println(linea);
			}

			lector.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("Adios");
		}
	}
}
