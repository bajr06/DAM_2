// package Ejercicios1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3Corregido {
    // Introducir la ruta
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del directorio: ");
        String ruta = sc.nextLine();

        System.out.print("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        // 1. A = Concatenar new File(ruta + nombreFichero);
        // 2. Saber new File(ruta, nombreFichero);
        // 3. Comprobar antes de que crear que ruta es un directorio.

        File directorio = new File(ruta);

        if(directorio.exists() && directorio.isDirectory()) {
            File fichero = new File(ruta, nombreFichero);

            // Ahora realizaría la creación del fichero
            try {
                if(fichero.createNewFile()) {
                    // Se crea
                    System.out.println("Fichero se crea");
                } else {
                    // Fichero existe
                    System.out.println("Fichero existe.");
                }
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println("La ruta introducida no es un directorio.");
        }

        sc.close();
    }
}
