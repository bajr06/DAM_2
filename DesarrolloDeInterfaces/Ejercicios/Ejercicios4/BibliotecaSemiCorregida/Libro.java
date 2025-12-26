package Ejercicios4.BibliotecaSemiCorregida;

public class Libro extends Articulo implements Prestable {
	boolean prestado = false;

	public Libro(String codigo, String titulo, int anio_publicacion, boolean prestado) {
		super(codigo, titulo, anio_publicacion);
		this.prestado = prestado;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	@Override
	public void setCodigo(String codigoParam) {
		this.codigo = "ISBN" + codigoParam;
	}

	@Override
	public String toString() {
		return "Libro [Codigo = " + codigo + ", Estado = " + prestado + ", Titulo = " + titulo + ", Año de publicacion = " + anio_publicacion + "]";
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
	public boolean esPrestado() {
		return prestado;
	}
}
