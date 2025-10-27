package Clase;

import java.io.*;
import java.util.*;

public class EjemploOutPutStream {
	public static void EscrituraDatos() {
		File ficheroDatos = new File("datos.bin");

		try {
			if(!ficheroDatos.exists()) {
				ficheroDatos.createNewFile();
			}

			DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroDatos));
			dos.writeInt(1);
			dos.writeDouble(1.2);
			dos.Boolean(false);
			dos.writeUTF("Hola, ¿Como vas?");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void LecturaDatos() {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("datos.bin"));
			int entero = dis.readInt();
			double numero = dis.readDouble();
			boolean booleano = dis.readBoolean();
			String frase = dis.readUTF();

			System.out.println("Entero:" + entero + ", Decimal: " + numero + ", Booleano: " + booleano + ", Frase: " + frase);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void EscrituraPersonas(ArrayList <Persona> personas) {
		String fichero = "persona.dat";
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

				oos.writeObject(personas);
				oos.close();
			} catch(IOException e) {
				System.out.println("El fichero es directorio y no se puede escribir");
			}
		}
	}

	public static void LecturaPersonas() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persona.dat"));
			ArrayList<Persona> personas = (ArrayList<Persona>) ois.readObject();

			System.out.println("Las personas almacenadas en el fichero son:");

			for(Persona p: personas) {
				System.out.println(p);
			}

		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String [] args) {
		ArrayList <Persona> personas = new ArrayList<>();

		persona.add(new Persona("Ana". 23));
		persona.add(new Persona("Luis", 21));
		persona.add(new persona("Mateo", 20));

		EscrituraPersonas(personas);
		LecturaPersonas();

		EscrituraDatos();
		LecturaDatos();
	}
}
