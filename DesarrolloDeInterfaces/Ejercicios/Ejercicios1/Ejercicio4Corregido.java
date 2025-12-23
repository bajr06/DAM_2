package Ejercicios1;

public class Ejercicio4Corregido {
	/*
		Ejercicio 4:
		Dada una matriz de enteros de n x n, recorrerla
		y dar la suma de todos sus números
	*/
	public static void main(String[] args) {
		int contador = 0;

		int [][] matriz = {{2, 5, 8, 10},
						  {1, 6, 10, 25},
						  {9, 6, 12, 21},
						  {22, 16, 4, -7}};

		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[i].length; j++) {
				// contador = contador + matriz[i][j];
				contador += matriz[i][j];
			}
		}

		System.out.println("La suma de la matriz es: " + contador);
	}
}
