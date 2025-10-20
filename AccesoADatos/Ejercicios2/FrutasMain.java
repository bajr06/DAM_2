package Ejercicio2;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;

public class FrutasMain {
	public static void main(String[] args) {
		try {
			File archivo = new File("frutas.txt");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(archivo);
			
			doc.getDocumentElement().normalize();

			NodeList listaFrutas = doc.getElementsByTagName("fruta");
			Scanner scanner = new Scanner(System.in);

			System.out.println("Todas las frutas:");
			
			for (int i = 0; i < listaFrutas.getLength(); i++) {
				Element fruta = (Element) listaFrutas.item(i);
				String nombre = fruta.getElementsByTagName("nombre").item(0).getTextContent();
				String tipo = fruta.getElementsByTagName("tipo").item(0).getTextContent();
				String color = fruta.getElementsByTagName("color").item(0).getTextContent();
				String origen = fruta.getElementsByTagName("origen").item(0).getTextContent();
				double precio = Double.parseDouble(fruta.getElementsByTagName("precio").item(0).getTextContent());
				String temporada = fruta.getElementsByTagName("temporada").item(0).getTextContent();
				NodeList nutrientes = fruta.getElementsByTagName("nutriente");
                List<String> listaNutrientes = new ArrayList<>();
                for (int j = 0; j < nutrientes.getLength(); j++) {
                    listaNutrientes.add(nutrientes.item(j).getTextContent());
                }

                System.out.println(nombre + " (" + tipo + ") - " + color + ", " + origen + ", €" + precio + ", " + temporada + ", Nutrientes: " + listaNutrientes);
            }

            System.out.println("\nFiltrar por precio:");
            System.out.print("Operador (mayor/menor/igual): ");
            String operador = scanner.nextLine();
            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());

            System.out.println("\nFrutas filtradas por precio:");
            for (int i = 0; i < listaFrutas.getLength(); i++) {
                Element fruta = (Element) listaFrutas.item(i);
                double precio = Double.parseDouble(fruta.getElementsByTagName("precio").item(0).getTextContent());
                boolean cumple = operador.equals("mayor") && precio > valor ||
                                 operador.equals("menor") && precio < valor ||
                                 operador.equals("igual") && precio == valor;
                if (cumple) {
                    String nombre = fruta.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println("- " + nombre + " (€" + precio + ")");
                }
            }

            System.out.println("\nFiltrar por nutriente:");
            System.out.print("Nutriente a buscar: ");
            String nutrienteBuscado = scanner.nextLine();

            System.out.println("\nFrutas con " + nutrienteBuscado + ":");
            for (int i = 0; i < listaFrutas.getLength(); i++) {
                Element fruta = (Element) listaFrutas.item(i);
                NodeList nutrientes = fruta.getElementsByTagName("nutriente");
                for (int j = 0; j < nutrientes.getLength(); j++) {
                    if (nutrientes.item(j).getTextContent().equalsIgnoreCase(nutrienteBuscado)) {
                        String nombre = fruta.getElementsByTagName("nombre").item(0).getTextContent();
                        System.out.println("- " + nombre);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
