package Ventana_4_ContenidoWeb;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TraductorAutomatico {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		String web = "https://spanishdict.com/translate/Alfombra";
		Document documento = Jsoup.connect(web).get();
		Element palabra = documento.select("div#quickdef1-es a.tCur1iYh").get(0);

		System.out.println(palabra.html().toUpperCase());
	}
}
