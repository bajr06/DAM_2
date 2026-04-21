package ManejoFicheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Objetos.Empleado;
import Objetos.Planta;

public class Carga {
	public static ArrayList<Planta> cargaPlantasXML(File ficheroXML) {
		ArrayList<Planta> plantas = new ArrayList<>();

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
			Document doc = docB.parse(ficheroXML);

			doc.getDocumentElement().normalize();

			NodeList lista = doc.getElementsByTagName("planta");
			int cantidad = lista.getLength();

			for(int i = 0; i < cantidad; i++) {
				Node nodo = lista.item(i);

				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element planta = (Element) nodo;
					int codigo = Integer.parseInt(planta.getElementsByTagName("codigo").item(0).getTextContent());
					String nombre = planta.getElementsByTagName("nombre").item(0).getTextContent();
					String foto = planta.getElementsByTagName("foto").item(0).getTextContent();
					String descripcion = planta.getElementsByTagName("descripcion").item(0).getTextContent();

					plantas.add(new Planta(codigo, nombre, foto, descripcion));
				}
			}
		} catch(ParserConfigurationException | SAXException | IOException pcsaxioe) {
			System.err.println("Fichero sin contenido: \"" + pcsaxioe.getMessage() +"\".\nCerrando programa.");
			System.exit(1);
		}

		return plantas;
	}

	public static void cargaPlantasDAT(ArrayList<Planta> plantas, File ficheroDAT) {
		try(RandomAccessFile raf = new RandomAccessFile(ficheroDAT, "r")) {
			int posicion = 0;
			
			while(raf.getFilePointer() < raf.length()) {
				if(raf.readInt() == plantas.get(posicion).getCodigo()) {
					plantas.get(posicion).setPrecio(raf.readFloat());
					plantas.get(posicion).setCantidad(raf.readInt());
				}
				posicion++;
			}
		} catch(IOException ioe) {
			System.err.println("Fichero " + ioe.getMessage() +".\nCerrando programa.");
			System.exit(1);
		} catch(IndexOutOfBoundsException ioofe) {
			System.err.println("Fichero corrupto \"" + ioofe.getMessage() +"\".\nCerrando programa.");
			System.exit(1);
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Empleado> cargaEmpleadosDAT(File ficheroDAT) {
		ArrayList<Empleado> empleados = new ArrayList<>();

		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroDAT))) {
			empleados = (ArrayList<Empleado>) ois.readObject();

		} catch (IOException | ClassNotFoundException iocnfe) {
			System.err.println("Error en fichero de empleados (" + iocnfe.getMessage() +"), cerrando programa.");
			System.exit(1);
		}

		return empleados;
	}
}
