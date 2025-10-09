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

	public int getHabitantes() {
		return habitantes;
	}

	@Override
	public void run() {
		for(int i = 0; i < habitantes; i++) {
			this.votos.incrementa();
			total;
		}
	}
}
