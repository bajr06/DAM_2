package AccesoADatos.EjerciciosTemporal;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
	public static void main(String [] args) {
		try {
			File ficheroXML = new File("frutas.xml");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();

			Document doc = docB.parse(ficheroXML);
			doc.getDocumentElement().normalize();

			NodeList lista = doc.getElementsByTagName("fruta");

			int cantidad = lista.getLength();

			for(int i = 0; i < cantidad; i++) {
				Node nodo = lista.item(i);

				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element fruta = (Element)nodo;

					String nombre = fruta.getElementsByTagName("nombre").item(0).getTextContent();
					String tipo = fruta.getElementsByTagName("tipo").item(0).getTextContent();
					String color = fruta.getElementsByTagName("color").item(0).getTextContent();
					String origen = fruta.getElementsByTagName("origen").item(0).getTextContent();
					String precio = fruta.getElementsByTagName("precio").item(0).getTextContent();
					String temporada = fruta.getElementsByTagName("temporada").item(0).getTextContent();
					String nutrientes = fruta.getElementsByTagName("nutrientes").item(0).getTextContent();

					System.out.println("Fruta " + i + " = Nombre: " + nombre + ", Tipo: " + tipo + ", Color: " + color + ", Origen: " + origen + ", Precio: " + precio + ", Temporada: " + temporada + ", Nutrientes: " + nutrientes);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
