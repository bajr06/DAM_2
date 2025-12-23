package AAD_Ejercicios_5;

import java.io.File;

import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Principal2Corregido {

	public static int posicion(double precio, ArrayList<Fruta> frutas) {
		int posicion = -1;
		double valorAnterior = 0;

		for(int i = 0; i < frutas.size(); i++) {
			if(frutas.get(i).getPrecio() == precio) {
				posicion = i;
			} else {
				if(valorAnterior > frutas.get(i).getPrecio()) {
					posicion = i - 1;
				}
			}

			valorAnterior = frutas.get(i).getPrecio();
		}

		return posicion;
	}


	public static void main(String [] args) {
		ArrayList<Fruta> frutas = new ArrayList<>();

		try {
			File ficheroXML = new File("AccesoADatos/Ejercicios/Ejercicios2/frutas.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();

			Document documento = docB.parse(ficheroXML);
			documento.getDocumentElement().normalize();

			NodeList listaFrutas = documento.getElementsByTagName("fruta");
			int longitud = listaFrutas.getLength();

			for(int i = 0; i < longitud; i++) {
				Node nodo = listaFrutas.item(i);

				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element fruta = (Element)nodo;
					ArrayList<String> nutrientes = new ArrayList<>();

					Fruta piezaFruta = new Fruta(
						fruta.getElementsByTagName("nombre").item(0).getTextContent(),
						fruta.getElementsByTagName("tipo").item(0).getTextContent(),
						fruta.getElementsByTagName("color").item(0).getTextContent(),
						fruta.getElementsByTagName("origen").item(0).getTextContent(),
						Double.valueOf(fruta.getElementsByTagName("precio").item(0).getTextContent()),
						fruta.getElementsByTagName("temporada").item(0).getTextContent()
					);

					NodeList listaNutrientes = fruta.getElementsByTagName("nutriente");

					for(int j= 0; j < listaNutrientes.getLength(); j++) {
						nutrientes.add(listaNutrientes.item(j).getTextContent());
					}

					piezaFruta.setNutrientes(nutrientes);
					frutas.add(piezaFruta);
				}

				// Indicar el campo de la clase.
				frutas.sort(Comparator.comparing(Fruta::getPrecio));
			}

			for(Fruta elemento : frutas) {
				System.out.println(elemento.toString());

				ArrayList<String> nutrientes = elemento.getNutrientes();

				for(String nutriente : nutrientes) {
					System.out.println(nutriente);
				}

				IO.println();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
