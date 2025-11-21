package Ejercicios2;

// import java.util.ArrayList;

public class Main {
	/*
	public static int posicion(double precio, ArrayList<Fruta> frutas) {
		int posicion = -1;
		int valorAnterior = 0;

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
			File ficheroXML = new File("frutas.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstat();
			DocumentBuilder docB = dbf.newDocumentBuilder();

			Document documento = docB.parse(ficheroXML);
			documento.getDocumentElement().normalize();

			NodeList listaFrutas = document.getElementByTagName("fruta");
			int longitud = listaFrutas.getLength();

			for(int i = 0; i < longitud; i++) {
				Node nodo = listaFrutas.item(i);

				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element fruta = (Element)nodo;
					ArrayList<Nutrientes> nutrientes = new ArrayList<>()
					Fruta piezaFruta = new Fruta(fruta.getElementByTagName("nombre").item(0).getTextContent(),
							fruta.getElementByTagName("tipo").item(0).getTextContent(),
							fruta.getElementByTagName("color").item(0).getTextContent(),
							fruta.getElementByTagName("origen").item(0).getTextContent(),
							Double.valueOf(fruta.getElementByTagName("precio").item(0).getTextContent()),
							fruta.getElementByTagName("temporada").item(0).getTextContent(),
							);

					NodeList listaNutrientes = fruta.getElementByTagName("nutriente");

					for(int j= 0; j < listaNutrientes.getLength(); j++) {
						nutrientes.add(listaNutrientes.item(i).getTextContent());
					}

					piezaFruta.setNutrientes(nutrientes);
					frutas.add(piezaFruta);
				}

				Frutas.sort(Comparator.comparing(Fruta::getPrecio()));
			}

			for(Fruta elemento : frutas) {
				System.out.println(elemento.toString());
				ArrayList<String> nutrientes = elemento.getNutrientes();

				for(String nutriente : nutrientes) {
					System.out.println(nutrientes);
				}
			}

		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	*/
}
