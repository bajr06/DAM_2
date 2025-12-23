package Ejercicios1;

import java.util.Scanner;

public class Ejercicio4 {
	static Scanner entrada = new Scanner(System.in);
	
	public static void main(String [] args) {
		System.out.println("Bienvenido al primer creador de Matrices de DAM 2");
		
		System.out.println("Introduzca la cantidad de columnas:");
		int columnas = entrada.nextInt();
		
		System.out.println("Introduzca la cantidad de filas:");
		int filas = entrada.nextInt();
		
		int [][] matriz = new int [columnas][filas];
		int total = 0;
		
		for(int i = 0; i < columnas; i++) {
			for(int j = 0; j < filas; j++) {
				matriz[i][j] = (int) (Math.random() * 10);
				
				total += matriz[i][j];
			}
		}
		
		System.out.println("La sumatoria total de todos los números de la matriz generada es: " + total);
	}
}
