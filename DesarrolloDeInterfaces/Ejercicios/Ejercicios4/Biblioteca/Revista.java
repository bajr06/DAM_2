package Ejercicios4.Biblioteca;

public class Revista extends Articulo {
	protected int numero;

	public Revista(String codigo, String titulo, int anyo_publicacion, int numero) {
		super(codigo, titulo, anyo_publicacion);
		this.numero = numero;
	}

	@Override
	public void setCodigo(String codigo) {
		this.codigo = "ISSN" + codigo;
	}

	@Override
	public String toString() {
		return "Revista [Código = " + codigo + ", Título = " + titulo + ", Año de Publicación = " + anyo_publicacion + ", Número = " + numero + "]";
	}
}
