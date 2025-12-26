package Ventana_4_ContenidoWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class TraductorManual {
	static Scanner entrada = new Scanner(System.in);

	private static String obtenerHTML(URL direccion) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(direccion.openStream()));
		String inputLine, codigo = "";

		while ((inputLine = br.readLine()) != null) {
			codigo += inputLine;
		}

		br.close();

		return codigo;
	}

	private static String cortarHTML(String html) {
		int inicio, /* fin, */ fin2;

		inicio = html.indexOf("?langFrom=en\" class=\"tCur1iYh\">"); // 31 caracteres

		// String trozo = html.substring(inicio);

		// fin = trozo.indexOf("</a>");
		fin2 = html.indexOf("</a>", inicio);

		// String resultado1 = html.substring(inicio + 31, inicio + fin);
		String resultado2 = html.substring(inicio + 31, fin2);

		return resultado2;
	}

	public static void main(String [] args) throws IOException {
		@SuppressWarnings("deprecation")
		URL direccion = new URL("https://spanishdict.com/translate/Alfombra");
		String palabraTraducida = "";
		String html = obtenerHTML(direccion);

		palabraTraducida = cortarHTML(html);

		// System.out.println(html);
		System.out.println(palabraTraducida);
	}
}
