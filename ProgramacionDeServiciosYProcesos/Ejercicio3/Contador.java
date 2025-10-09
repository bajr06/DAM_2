package Ejercicio3;

public class Contador implements Runnable {
	private int votos = 0;

	synchronized public int getVotos() {
		return votos;
	}

	synchronized public int incrementa() {
		this.votos++;

		return votos;
	}
}
