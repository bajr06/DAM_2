package Ejercicios4.BibliotecaSemiCorregida;

abstract class Articulo {
	protected String codigo;
	protected String titulo;
	protected int anio_publicacion;
	
	public Articulo(String codigo, String titulo, int anio_publicacion) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.anio_publicacion = anio_publicacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public abstract void setCodigo(String codigo);

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio_publicacion() {
		return anio_publicacion;
	}

	public void setAnio_publicacion(int anio_publicacion) {
		this.anio_publicacion = anio_publicacion;
	}
}
