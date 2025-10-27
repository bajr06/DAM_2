package AccesoFicheros;

import java.io.*;

public class Principal {
	public static void main(String [] args) {
		try {
			// Creamos el fichero
			File fichero = new File("datos.dat");
			fichero.createNewFile();

			// El segundo parámetro indicamos si r - lectura, w - escritura o rw - lectura y escritura
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			raf.writeInt(0); // 4 bytes
			raf.writeInt(1);
			raf.writeInt(2);

			// Imprimimos la posición del puntero
			System.out.println(raf.getFilePointer());
			raf.seek(4);

			// Lee los siguientes 4 bytes
			System.out.println(raf.readInt());

			raf.seek(0);

			while(raf.getFilePointer() < raf.length()) {
				System.out.println(raf.readInt());
			}

			raf.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} 
	}
}
