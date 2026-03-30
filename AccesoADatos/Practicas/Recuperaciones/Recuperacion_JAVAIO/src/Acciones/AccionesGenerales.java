package Acciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AccionesGenerales {
	public static int contarFicherosVD(File directorioVentas, File directorioDevoluciones) {
		String [] listaVentas = directorioVentas.list();
		String [] listaDevoluciones = directorioDevoluciones.list();
		int cont1 = 0, cont2 = 0;
		
		for(String ventas: listaVentas) {
			ventas.getBytes();
			cont1++;
		}

		for(String devoluciones: listaDevoluciones) {
			devoluciones.getBytes();
			cont2++;
		}

		return cont1 + cont2;
	}

	public static void leerFichero(File ficheroALeer) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(ficheroALeer));
		String linea;

		IO.println();
		while((linea = br.readLine()) != null) {
			System.out.println(linea);
		}

		br.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String>[] buscarCodigoFichero(File fichero) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		ArrayList<String> lineasTicket = new ArrayList<>(), lineasProductos = new ArrayList<>();
		String linea, productos;
		while((linea = br.readLine()) != null) {
			lineasTicket.add(linea);

			if(linea.equals("Codigo_Producto			Cantidad			Precio_Unitario")) {
				while((productos = br.readLine()) != null) {					
					if(productos.equals("------------------------------//------------------------------")) {
						lineasTicket.add(productos);
						break;
					}
					lineasProductos.add(productos);
				}
			}
		}

		ArrayList<String>[] ticket = new ArrayList[2];

		ticket[0] = lineasTicket;
		ticket[1] = lineasProductos;

		br.close();
		return ticket;
	}
}
