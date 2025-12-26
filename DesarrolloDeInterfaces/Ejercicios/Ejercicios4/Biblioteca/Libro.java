package Ejercicios4.Biblioteca;

public class Libro extends Articulo implements Prestable {
	protected boolean prestado;

	@Override
	public void setCodigo(String codigo) {
		this.codigo = "ISBN" + codigo;
	}

	public boolean isPrestado() {
		return prestado;
	}
	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	@Override
	public void prestar() {
		prestado = true;
	}
	@Override
	public void devolver() {
		prestado = false;
	}
	@Override
	public boolean prestado() {
		return prestado;
	}


	public Libro(String codigo, String titulo, int anyo_publicacion) {
		super(codigo, titulo, anyo_publicacion);
		this.prestado = false;
	}

	@Override
	public String toString() {
		return "Libro [Código = " + codigo + ", Título = " + titulo + ", Año de Publicación = " + anyo_publicacion + ", Estado = " + prestado + "]";
	}

	
}
