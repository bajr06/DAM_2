package Ejercicios1;

public class Ejercicio5Corregido {
	/*
		Ejercicio 5:
		Calcula el factorial de un número entero positivo
		a través de la recursividad.
	*/
	public static void main(String[] args) {
		System.out.println(factorial(3));
	}

	public static int factorial(int positivo) {
		// Caso base o condición de parada.
		if(positivo == 1) {
			return 1;
		} else {
			return positivo * factorial(positivo - 1);
		}
	}
}
