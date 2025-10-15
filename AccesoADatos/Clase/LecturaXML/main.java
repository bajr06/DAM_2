package LecturaXML;

import java.io.*;

public class main {
	public static void main(String [] args) {
		try {
			File ficheroXML = new File("persona.xml");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstant();
			Document docB = dbf.newDocumentBuilder();

			Document doc = docB.parse(ficheroXML);

			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("persona");

			int cantidad = lista.length();

			for(int i = 0; i < cantidad; i++) {
				Node nodo = lista.item(i);

				if(nodo.getNodeType == Node.ELEMENT_NODE) {
					Element persona = (Element)nodo;
					String nombre = persona.getElementByTagName("nombre").item(0).getTextContent();
					String edad = persona.getElementByTagName("edad").item(0).getTextContent();
					String ciudad = persona.getElementByTagName("ciudad").item(0).getTextContent();

					System.out.println("La persona es: " + nombre + ", con edad " + edad + " años , en la ciudad " + ciudad);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
