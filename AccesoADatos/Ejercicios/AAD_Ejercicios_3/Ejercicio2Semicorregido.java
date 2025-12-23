package AAD_Ejercicios_3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

// Crédito a José María de Lucas Plata.
public class Ejercicio2Semicorregido {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios3/Productos2.dat");

		try {
			System.out.println("¿Cuánto productos deseas meter?");
			int cantidadProducto = entrada.nextInt();
			entrada.nextLine();

			RandomAccessFile escritor = new RandomAccessFile(fichero, "rw");

			for(int i = 0; i < cantidadProducto; i++) {
				System.out.printf("Introduce el ID del %dº producto:\n", i + 1);
				int id = entrada.nextInt();
				entrada.nextLine();
				escritor.writeInt(id);

				System.out.println("Introduce la cantidad de stock:");
				int cantidad = entrada.nextInt();
				entrada.nextLine();
				escritor.writeInt(cantidad);

				System.out.println("Introduzca el precio:");
				double precio = entrada.nextDouble();
				entrada.nextLine();
				escritor.writeDouble(precio);
			}

			escritor.seek(0);

			while (escritor.getFilePointer() < escritor.length()) {
				System.out.println(escritor.readInt());
				System.out.println(escritor.readInt());
				System.out.println(escritor.readDouble());
			}

			escritor.close();
			entrada.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
