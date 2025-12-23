package AAD_Ejercicios_3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {
	static Scanner entrada = new Scanner(System.in);

	private static int cantidadNumeros() {
		System.out.println("¿Qué cantidad de números quieres que tenga la serie de Fibonacci?");
		int cantidadNumeros = entrada.nextInt();

		return cantidadNumeros;
	}

	private static void fibonacci(RandomAccessFile raf, int cantidad) {
		try {
			raf.writeInt(0);
			raf.writeInt(1);

			int fibonacci = 0, num1, num2;

			for(int i = 0; i < cantidad * 4; i += 4) {
				raf.seek(i);
				num1 = raf.readInt();

				raf.seek(i + 4);
				num2 = raf.readInt();

				fibonacci = num1 + num2;

				raf.seek(i + 8);
				raf.writeInt(fibonacci);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static int peticion() {
		System.out.println("¿Hasta que posición quieres ver la serie de Fibonacci?");
		int posicion = entrada.nextInt();

		return posicion;
	}

	public static void main(String[] args) {
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios3/Fibonacci1.dat");

		if(!fichero.exists()) {
			try{
				fichero.createNewFile();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}

		int cantidad = cantidadNumeros();

		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

			fibonacci(raf, cantidad);

			raf.seek(0);
			System.out.println("Fichero actualizado como Dios manda");

			int posicion = peticion();

			raf.seek(posicion * 4);
			System.out.println(raf.readInt());

			raf.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
