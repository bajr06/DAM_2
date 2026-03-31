package Objetos;

public class Planta {
	private int codigo;
	private String nombre;
	private String foto;
	private String descripcion;
	private float precio;
	private int cantidad;


	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	public Planta(int codigo, String nombre, String foto, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.foto = foto;
		this.descripcion = descripcion;
	}

	public Planta(int codigo, String nombre, String foto, String descripcion, float precio, int cantidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.foto = foto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	
	@Override
	public String toString() {
		return "Planta [Código = " + codigo + ", Nombre = " + nombre + ", Foto = " + foto + ", Descripción = " + descripcion + ", Precio = " + precio + ", Cantidad = " + cantidad + "]";
	}
}
