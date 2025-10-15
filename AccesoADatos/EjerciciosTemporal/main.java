package EjercicioTemporal;

import java.io.*;

public class main {
	public static void main(String [] args) {
		try {
			File ficheroXML = new File("frutas.xml");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstant();
			Document docB = dbf.newDocumentBuilder();

			Document doc = docB.parse(ficheroXML);
			doc.getDocumentElement().normalize();

			NodeList lista = doc.getElementByTagName("fruta");

			int cantidad = lista.length();

			for(int i = 0; i < cantidad; i++) {
				Node nodo = lista.item(i);

				if(nodo.getNoteType() == Node.Element_NODE) {
					Element fruta = (Element)nodo;

					String nombre = fruta.getElementByTagName("nombre").item(0).getContent();
					String tipo = fruta.getElementByTagName("tipo").item(0).getContent();
					String color = fruta.getElementByTagName("color").item(0).getContent();
					String origen = fruta.getElementByTagName("origen").item(0).getContent();
					String precio = fruta.getElementByTagName("precio").item(0).getContent();
					String temporada = fruta.getElementByTagName("temporada").item(0).getContent();
					String nutrientes = fruta.getElementByTagName("nutrientes").item(0).getContent();

					System.out.println("Fruta " + i + " = Nombre: " + nombre + ", Tipo: " + tipo + ", Color: " + color + ", Origen: " + origen + ", Precio: " + precio + ", Temporada: " + temporada + ", Nutrientes: " + nutrientes);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
