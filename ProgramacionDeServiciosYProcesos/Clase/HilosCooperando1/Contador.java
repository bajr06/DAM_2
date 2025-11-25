package HilosCooperando1;

public class Contador implements Runnable {
	private  int cuenta = 0;

	public int getCuenta() {
		return cuenta;
	}

	public int incrementa() {
		this.cuenta++;

		return cuenta;
	}

	@Override
	public void run() {
			throw new UnsupportedOperationException("Unimplemented method 'run'");
	}
}
