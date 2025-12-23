package Ejercicio3;

public class Hilo implements Runnable {
	private String zona;
	private int habitantes, total;
	private String nombrePartido;
	private final Contador votos;
	
	Hilo(String zona, int habitantes, String nombrePartido, Contador votos) {
		this.zona = zona;
		this.habitantes = habitantes;
		this.nombrePartido = nombrePartido;
		this.votos = votos;
	}

	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}

	public int getHabitantes() {
		return habitantes;
	}
	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public String getNombrePartido() {
		return nombrePartido;
	}
	public void setNombrePartido(String nombrePartido) {
		this.nombrePartido = nombrePartido;
	}

	public Contador getVotos() {
		return votos;
	}



	@Override
	public void run() {
		for(int i = 0; i < habitantes; i++) {
			this.votos.incrementa();
			total++;
		}
	}
}
