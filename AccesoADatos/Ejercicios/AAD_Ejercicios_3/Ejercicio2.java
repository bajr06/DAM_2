package AAD_Ejercicios_3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
	static Scanner entrada = new Scanner(System.in);

	private static int menu() {
		System.out.println("1. Almacenar productos.");
		System.out.println("2. Visualizar productos (la lista entera).");
		System.out.println("3. Visualizar los datos de un producto en concreto, basado en su ID.");
		System.out.println("4. Borrar productos dado un ID.");
		System.out.println("5. Modificar los campos de un producto.");
		int seleccion = entrada.nextInt();

		return seleccion;
	}

	private static int pedirId() {
		System.out.println("Introduzca el ID del producto: ");
		int id = entrada.nextInt();

		return id;
	}

	private static int pedirStock() {
		System.out.println("Introduzca el Stock del producto: ");
		int stock = entrada.nextInt();

		return stock;
	}

	private static double pedirPrecio() {
		System.out.println("Introduzca el Precio del producto: ");
		double precio = entrada.nextDouble();

		return precio;
	}

	public static void main(String[] args) {
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios3/Productos1.dat");

		try {
			if(!fichero.exists()) {
				fichero.createNewFile();
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

			System.out.println("Seleccione la opción que quieres realizar: ");
			int opcion = menu();

			do {
				switch(opcion) {
					case 1:
						raf.writeInt(pedirId());
						raf.writeInt(pedirStock());
						raf.writeDouble(pedirPrecio());
						break;
					case 2:
						raf.seek(0);

						while(raf.getFilePointer() < raf.length()) {
							System.out.println("ID: " + raf.readInt());
							System.out.println("Stock: " + raf.readInt());
							System.out.println("Precio " + raf.readDouble());
						}

						break;
					case 3:
						int busqueda = pedirId();
						raf.seek(0);

						while (raf.getFilePointer() < raf.length()) {
							if(raf.readInt() == busqueda) {
								raf.seek(raf.getFilePointer() - 4);

								System.out.println("ID: " + raf.readInt());
								System.out.println("Stock: " + raf.readInt());
								System.out.println("Precio " + raf.readDouble());
							}
						}

						break;
					case 4:
						int borrado = pedirId();
						raf.seek(0);

						while (raf.getFilePointer() < raf.length()) {
							if(raf.readInt() == borrado) {
								raf.seek(raf.getFilePointer() - 4);

								raf.writeInt(0);
								raf.writeInt(0);
								raf.writeDouble(0);
							}
						}

						break;
					case 5:
						int cambio = pedirId();
						raf.seek(0);

						while (raf.getFilePointer() < raf.length()) {
							if(raf.readInt() == cambio) {
								raf.writeInt(pedirStock());
								raf.writeDouble(pedirPrecio());
							}
						}
						break;
					default:
						IO.println("Hay 2 opciones... O has salido, o has escogido una opción errónea");
						break;
				}

				System.out.println("Seleccione la opción que quieres realizar: ");
				opcion = menu();
			} while(opcion != 6);

			IO.println("Pues si, has salido. ¡Hasta la próxima!");

			raf.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
