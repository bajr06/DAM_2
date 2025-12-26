package Ejercicios4.BibliotecaSemiCorregida;

import java.util.ArrayList;

// Créditos a Jose María de Lucas Plata
public class PrincipalSemicorregido {
	public void prestar(ArrayList<Libro> listaLibros, String codigoABuscar) {
		String titulo = "";

		for(Libro libroAPrestar : listaLibros) {
			if(libroAPrestar.getCodigo().equalsIgnoreCase(codigoABuscar) && libroAPrestar.isPrestado() == false) {
				titulo = libroAPrestar.getTitulo();
				libroAPrestar.prestar();
			} else {
				System.out.println("Ya está prestado el libro que estás solicitando");
			}
		}

		System.out.println("El libro " + titulo + " con código " + codigoABuscar + " ha sido prestado correctamente.");
	}

	public void devolver(ArrayList<Libro> listaLibros, String codigoABuscar) {
		String titulo = "";

		for(Libro libroADevolver : listaLibros) {
			if(libroADevolver.getCodigo().equalsIgnoreCase(codigoABuscar)) {
				titulo = libroADevolver.getTitulo();

				if(libroADevolver.isPrestado()) {
					libroADevolver.devolver();

					System.out.println("Libro " + titulo + " devuelto correctamente.");
				} else {
					System.out.println("Este libro no está prestado, no lo puedes devolver.");
				}
			}
		}

		System.out.println("El libro " + titulo + " con código " + codigoABuscar + "ha sido devuelto correctamente.");
	}

	public void esPrestado(ArrayList<Libro> listaLibros, String codigoABuscar) {
		String titulo = "";

		for(Libro libroAComprobar : listaLibros) {
			if(libroAComprobar.getCodigo().equalsIgnoreCase(codigoABuscar)) {
				titulo = libroAComprobar.getTitulo();

				if(libroAComprobar.esPrestado() == true) {
					System.out.println("Libro " + titulo + " está actualmente prestado.");
				} else {
					System.out.println("Este libro no esta prestado.");
				}
			}
		}

		System.out.println("El libro " + titulo + " con código " + codigoABuscar + " ha sido prestado correctamente.");
	}

	public void menu() {
		System.out.println("1. Mostrar libros.");
		System.out.println("2. Prestar libros.");
		System.out.println("3. Devolver libro");
		System.out.println("4. Comprobar libro");
	}

	public static void main(String[] args) {
		ArrayList<Libro> listaLibros = new ArrayList<>();

		Libro libro1 = new Libro("32594085", "Las historias de Leo", 1937, false);
		Libro libro2 = new Libro("32594345", "Las historiestas de Andreu", 2013, false);

		listaLibros.add(libro1);
		listaLibros.add(libro2);
	}
}
