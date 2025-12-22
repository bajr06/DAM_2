package Ejercicios3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Créditos a Jose María de Lucas Plata.
public class Ejercicio1Semicorregido {
	public static List<Integer> fibo(int n) {
		List<Integer> fibonacci = new ArrayList<>();

		int a = 0;
		int temp = 1;
		fibonacci.add(0);
		fibonacci.add(1);

		for(int i = 0; i < n; i++) {
			int fibo = a + temp;
			a = temp;
			temp = fibo;
			fibonacci.add(fibo);
		}

		return fibonacci;
	}

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		File fichero = new File("AccesoADatos/Ejercicios/Ejercicios3/Fibonacci2.dat");

		try {
			RandomAccessFile escritor = new RandomAccessFile(fichero, "rw");

			System.out.println("Dime de cuantos números quieres hacer la sucesión de Fibonacci.");

			int cantidadFibo = entrada.nextInt();
			List<Integer> fibonacci = fibo(cantidadFibo);

			for(int numeroFi: fibonacci) {
				escritor.writeInt(numeroFi);
			}

			System.out.println("Sucesión de números de Fibonacci actualizada correctamente.");

			for(int numeroImprimir: fibonacci) {
				System.out.println(numeroImprimir);
			}

			System.out.println("¿Qué posicion quieres buscar?");
			int posicion = entrada.nextInt();

			posicion *= 4;

			entrada.nextLine();
			escritor.seek(posicion);

			System.out.println(escritor.readInt());

			escritor.close();
			entrada.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
