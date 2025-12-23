package Ficheros_1;

import java.io.*;

public class EjemploEscritura1 {
	public static void main(String[] args) {
		// Dentro de 'new File' se pone la ruta del archivo o fichero.
		File fichero = new File("AccesoADatos/Clase/Tema1/Ficheros_1/FicheroEjemplo1.txt");
		
		if(!fichero.exists()) {
			try {
				// Crear el fichero
				fichero.createNewFile();

				System.out.println("Nombre de fichero: " + fichero.getName());
				System.out.println("Ruta: " + fichero.getPath());
				System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
				System.out.println("Permiso lectura: " + fichero.canRead());
				System.out.println("Tamaño: " + fichero.length());

				FileWriter escritura = new FileWriter(fichero);
				// PrintWriter pw = new PrintWriter(escritura);
				BufferedWriter pw = new BufferedWriter(escritura);

				for(int i = 0; i < 10; i++) {
					//pw.println("Linea " + i);

					pw.write("Linea: " + i);
					pw.newLine();
				}

				pw.close();

				/*
					// Devuelve caracter a caracter.
					FileReader lector = new FileReader(fichero);
				
					// Llena el buffer de caracteres y lee líneas.
					BufferedReader buffer = new BufferedReader(lector);
				
					String linea;
					while((linea = buffer.readLine()) != null) {
						System.out.println(linea);
					}
				*/
				
			} catch(IOException e) {
				e.getMessage();
			}
			
		} else {
			System.out.println("No existe el fichero.");
			
			try {
				fichero.createNewFile();
			} catch(IOException io) {
				io.printStackTrace();
			}
		}
	}
}
