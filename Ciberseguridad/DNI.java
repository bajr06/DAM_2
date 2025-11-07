package Códigos_CRC;

import java.util.Scanner;

public class ValidarDNI {
	
	static Scanner read = new Scanner(System.in);
	
	public static void main(String[] args) {
		String nDNI = pedir_numero();

		while (!comprobar_num(nDNI)) {
			nDNI = pedir_numero();
		}

		char letra = calcula_letra(nDNI);
		if(letra!='0') {
			System.out.println("Tu DNI es "+nDNI+letra);
		}
	}
	
	private static char calcula_letra(String dni) {
		char letra;
		long nDNI = toInt(dni);

		if(nDNI==-1) {
			return '0';
		}
		
		long resto =nDNI%23;
		char[] letras_posibles = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
		
		letra = letras_posibles[(int)resto];
		
		return letra;
	}
	
	private static long toInt(String num) {
		char[] num_separados = new char[8];
		num_separados = num.toCharArray();
		long num_largo=0;
		for (int i = num_separados.length-1, j=0; i >= 0; i--, j++) {
			num_largo += (num_separados[j]-48)*Math.pow(10, i);
			if(num_separados[j]-48>9) {
				System.out.println("Error.");
				return -1;
			}
			//System.out.println(num_largo);
		}
		return num_largo;
	}

	private static boolean comprobar_num(String nDNI) {
		int vueltas = nDNI.length();
		//System.out.println(vueltas);
		if (vueltas==8)
			return true;
		return false;
	}
	
	private static String pedir_numero() {
		System.out.print("Escribe el número de tu DNI: ");
		String num = read.next();
		read.nextLine();
		return num;
	}

}
