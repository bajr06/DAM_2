package Ejercicios1;

public class Ejercicio3Corregido {
	/*
		Ejercicios 3:
		A partir de un array de enteros, recorrerlo y obtener
		por pantalla la cantidad de números partes
	*/
	public static void main(String[] args) {
		int contador = 0;
		int [] array = {9, 15, 21, 252, 20, 19, 22, 43, 44, 0, 1001, 78, 79, -5};

		for(int i = 0; i < array.length; i++) {
			if(array[i] % 2 == 0) {
				contador++;
			}
		}

		System.out.println("Hay " + contador + " pares.");
	}
}
