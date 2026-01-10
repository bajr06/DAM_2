package Ficheros_2_Binarios;

import java.io.*;
import java.util.*;

public class EjemploOutPutStream {
	public static void EscrituraDatos() {
		File ficheroDatos = new File("AccesoADatos/Clase/AAD_Tema_1/Ficheros_2_Binarios/datos1.bin");

		try {
			if(!ficheroDatos.exists()) {
				ficheroDatos.createNewFile();
			}

			DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroDatos));
			dos.writeInt(2);
			dos.writeDouble(1.2);
			dos.writeBoolean(false);
			dos.writeUTF("Hola, ¿Cómo vas?");

			dos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void LecturaDatos() {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("AccesoADatos/Clase/AAD_Tema_1/Ficheros_2_Binarios/datos1.bin"));
			int entero = dis.readInt();
			double numero = dis.readDouble();
			boolean booleano = dis.readBoolean();
			String frase = dis.readUTF();

			System.out.println("Entero:" + entero + ", Decimal: " + numero + ", Booleano: " + booleano + ", Frase: " + frase);

			dis.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void EscrituraPersonas(ArrayList <Persona> personas) {
		String fichero = "AccesoADatos/Clase/AAD_Tema_1/Ficheros_2_Binarios/persona2.dat";
		File ficheroEscritura = new File(fichero);

		if(!ficheroEscritura.exists()) {
			try {
				ficheroEscritura.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				// La abro para escritura y escribo un flujo de datos
				FileOutputStream fos = new FileOutputStream(ficheroEscritura);

				// El tipo de dato que tiene que transformar es un objeto en byte.
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				// Escribe el array entero de personas
				oos.writeObject(personas);
				
				oos.close();
			} catch(IOException e) {
				System.err.println("El fichero es directorio y no se puede escribir");
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void LecturaPersonas() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("AccesoADatos/Clase/AAD_Tema_1/Ficheros_2_Binarios/persona2.dat"));
			ArrayList<Persona> personas = (ArrayList<Persona>) ois.readObject();

			System.out.println("Las personas almacenadas en el fichero son:");
			for(Persona p: personas) {
				System.out.println(p);
			}

			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String [] args) {
		ArrayList <Persona> personas = new ArrayList<>();

		personas.add(new Persona("Ana", 23));
		personas.add(new Persona("Luis", 21));
		personas.add(new Persona("Mateo", 20));

		EscrituraDatos();
		LecturaDatos();

		EscrituraPersonas(personas);
		LecturaPersonas();
	}
}
