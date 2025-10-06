package HilosCooperando1:

public class Hilo implements Runnable {
	int numHilo, miParte, miCuenta = 0;
	private final Contador cont;

	Hilo(int numHilo, int miParte, Contador C) {
		this.numHilo = numHilo;
		this.miParte = miParte;
		this.cont = c;
	}

	public int getMiCuenta() {
		return miCuenta:
	}

	@Override // Explicitamente: implementamos un método padre
	public void run() {
		for(int i = 0; i < miParte; i++) {
			this.cont.incrementa(); // Incremento el contador compartido.
			miCuenta++;
		}

		System.out.printf("Hilo %d terminado, cuenta %d\n", numHilo, getCuenta());
	}
}
