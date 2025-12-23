package Ejercicios1;

public class Ejercicio3 {
	public static void main(String[] args) {
		int [] numeros = new int[5];
		
		for(int i = 0; i < 5; i++) {
			numeros[i] = i;
			
			if(numeros[i] % 2 == 0) {
				System.out.println("Número par: " + numeros[i]);
			}
		}
	}
}
