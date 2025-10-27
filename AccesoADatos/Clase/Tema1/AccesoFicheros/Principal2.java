package AccesoFicheros;

import java.io.*;

public class Principal2 {
	public static void main(String [] args) {
		int numeroLista; // 4 bytes
		String nombre = "Pedro"; // Sabemos que un carácter son 2 bytes
		double nota; // 8 bytes
		
		try {
			// Creamos el fichero
			File fichero = new File("datos.dat");
			fichero.createNewFile();

			// El segundo parámetro indicamos si r - lectura, w - escritura o rw - lectura y escritura
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			
			// Alumnos a mano
			raf.writeInt(1); // 4 bytes
			raf.writeChars(nombre); // 10 bytes
			raf.writeDouble(5.12); // 8 bytes

			// Imprimimos la posición del puntero
			System.out.println(raf.getFilePointer());
			raf.seek(0);

			String cadena = "";
			while(raf.getFilePointer() < raf.length()) {
				System.out.println(raf.readInt());

				for(int i = 0; i < nombre.length(); i++) {
					cadena += raf.readChar();

					System.out.println(raf.cadena);
					System.out.println(raf.readDouble());
				}
			}

			raf.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} 
	}
}
