package objetos;

public class Zona {
	private int idZona;
	private String nombre;
	private String descripcion;

	public Zona() {
	}

	public Zona(int idZona, String nombre, String descripcion) {
		this.idZona = idZona;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
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
}
