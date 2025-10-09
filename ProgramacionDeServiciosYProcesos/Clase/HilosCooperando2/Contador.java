package HilosCooperando2;

public class Contador implements Runnable {
	private int cuenta = 0;

	 synchronized public int getCuenta() {
		return cuenta;
	}

	 synchronized public int incrementa() {
		this.cuenta++;

		return cuenta;
	}
}
