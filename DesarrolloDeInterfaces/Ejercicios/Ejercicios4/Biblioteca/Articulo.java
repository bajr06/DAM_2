package Ejercicios4.Biblioteca;

abstract class Articulo {
	protected String codigo;
	protected String titulo;
	protected int anyo_publicacion;

	public String getCodigo() {
		return codigo;
	}
	public abstract void setCodigo(String codigo);

	public int getAnyo_Publicacion() {
		return anyo_publicacion;
	}
	public void setAnyo_Publicacion(int anyo_publicacion) {
		this.anyo_publicacion = anyo_publicacion;
	}


	public Articulo(String codigo, String titulo, int anyo_publicacion) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.anyo_publicacion = anyo_publicacion;
	}

	public abstract String toString();
}
