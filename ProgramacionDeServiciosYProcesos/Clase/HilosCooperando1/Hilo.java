package HilosCooperando1:

public class Hilo implements Runnable {
	int numHilo, miParte, MiCuenta = 0;
	private final Contador cont;

	Hilo(int numHilo, int miParte, Contador C) {
		this.numHilo = numHilo;
		this.miParte = miParte;
		this.cont = c;
	}
}
