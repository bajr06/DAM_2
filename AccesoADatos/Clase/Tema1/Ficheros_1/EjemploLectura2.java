package Ficheros_1;

import java.io.*;

public class EjemploLectura2 {
	public static void main(String[] args) {
		// Dentro de 'new File' se pone la ruta del archivo o fichero.
		File fichero = new File("AccesoADatos/Clase/Tema1/Ficheros_1/FicheroEjemplo2_0.txt");
		
		if(fichero.exists()) {
			System.out.println("Nombre de fichero: " + fichero.getName());
			System.out.println("Ruta: " + fichero.getPath());
			System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
			System.out.println("Permiso lectura: " + fichero.canRead());
			System.out.println("Tamaño: " + fichero.length());
			
			try {
				// Devuelve caracter a caracter.
				FileReader lector = new FileReader(fichero);
				
				// Llena el buffer de caracteres y lee líneas.
				BufferedReader buffer = new BufferedReader(lector);
				
				String linea;
				while((linea = buffer.readLine()) != null) {
					System.out.println(linea);
				}

				lector.close();
				
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
