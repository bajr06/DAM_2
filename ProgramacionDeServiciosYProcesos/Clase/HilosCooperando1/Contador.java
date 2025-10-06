package HilosCooperando1;

public class Contador implements Runnable {
	private  int cuenta = 0;

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}


	Contador(int cantidad) {
		this.cantidad = cantidad;
	}
}
