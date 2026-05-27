package objetos;

public class Stock {
	private int idStand;
	private int idZona;
	private int idJuguete;
	private int cantidadDisponible;

	public Stock() {
	}

	public Stock(int idStand, int idZona, int idJuguete, int cantidadDisponible) {
		this.idStand = idStand;
		this.idZona = idZona;
		this.idJuguete = idJuguete;
		this.cantidadDisponible = cantidadDisponible;
	}

	public int getIdStand() {
		return idStand;
	}
	public void setIdStand(int idStand) {
		this.idStand = idStand;
	}
	
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	
	public int getIdJuguete() {
		return idJuguete;
	}
	public void setIdJuguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}
	
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
}
