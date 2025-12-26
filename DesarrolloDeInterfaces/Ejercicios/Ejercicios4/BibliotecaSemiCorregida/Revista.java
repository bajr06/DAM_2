package Ejercicios4.BibliotecaSemiCorregida;

public class Revista extends Articulo {
	int numeroEdicion;

	public Revista(String codigo, String titulo, int anio_publicacion, int numeroEdicion) {
		super(codigo, titulo, anio_publicacion);
		this.numeroEdicion = numeroEdicion;
	}

	public int getNumeroEdicion() {
		return numeroEdicion;
	}
	public void setNumeroEdicion(int numeroEdicion) {
		this.numeroEdicion = numeroEdicion;
	}

	@Override
	public String toString() {
		return "Revista [Coódigo = " + codigo + ", Numero de Edicion=" + numeroEdicion + ", Titulo = " + titulo + ", Año de publicacion = " + anio_publicacion + "]";
	}

	@Override
	public void setCodigo(String codigoParam) {
		this.codigo = "ISSN" + codigoParam;
	}
}
