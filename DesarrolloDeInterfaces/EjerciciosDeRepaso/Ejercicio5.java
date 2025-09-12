// package Ejercicios_Repaso;

import java.util.Scanner;

public class Ejercicio5 {
	static Scanner entrada = new Scanner(System.in);
	
	public static void main(String [] args) {
		System.out.println("Calculadora de factorial");
		
		System.out.println("Introduzca un número positivo: ");
		int numero = entrada.nextInt();
		
		int total = factorial(numero, 1);
		
		System.out.println("El total del factorial de " + numero + " es: " + total);
	}
	
	public static int factorial(int numero, int total) {
		if(numero == 0) {
			return total;
		}
		
		total *= numero;
		numero--;
		
		return factorial(numero, total);
	}
}
