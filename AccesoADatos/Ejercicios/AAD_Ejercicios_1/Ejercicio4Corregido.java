package AAD_Ejercicios_1;

import java.io.File;
import java.util.Scanner;

public class Ejercicio4Corregido {
	public static void listar(File directorio) {
		if(directorio.exists() && directorio.isDirectory()) {
			String [] listaArchivos = directorio.list();
			
			for(String archivo: listaArchivos) {
				// Caso base sería un archivo
				// Caso recursivo un directorio
				File a = new File(directorio, archivo);

				if(a.isDirectory()) {
					listar(a);
				}

				System.out.println(a.getAbsolutePath());
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Introduce nombre del directorio: ");
		String nombreDirectorio = sc.nextLine();
		File directorio = new File("AccesoADatos/Ejercicios1/" + nombreDirectorio);
		
		if(directorio.exists() && directorio.isDirectory()) {
			listar(directorio);
		} else {
			System.out.println("No es directorio");
		}

		sc.close();
	}
}
