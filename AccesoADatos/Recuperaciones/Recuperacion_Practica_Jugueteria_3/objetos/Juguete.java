package objetos;

public class Juguete {
	private int idJuguete;
	private String nombre;
	private String descripcion;
	private double precio;
	private int cantidadEnStock;
	private String categoria;

	public Juguete() {
	}

	public Juguete(int idJuguete, String nombre, String descripcion, double precio, int cantidadEnStock, String categoria) {
		this.idJuguete = idJuguete;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidadEnStock = cantidadEnStock;
		this.categoria = categoria;
	}

	public int getIdJuguete() {
		return idJuguete;
	}
	public void setIdJuguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getCantidadEnStock() {
		return cantidadEnStock;
	}
	public void setCantidadEnStock(int cantidadEnStock) {
		this.cantidadEnStock = cantidadEnStock;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
