package HilosCooperando1;

public class Principal {
	private static final int NUM_HILOS = 10;
	private static final int CUENTA_TOTAL = 100000;

	public static void main(String [] args) {
		Thread [] hilos = new Thread[NUM_HILOS];
		Contador contadoContador = new Contador();

		for(int i = 0; i < NUM_HILOS; i++) {
			Thread th = new Thread(new Hilo(i, CUENTA_TOTAL/NUM_HILOS, contadorCompartido));
			th.start();
			hilos[i] = th;
		}

		for(Thread h : hilo)  
			try {
				h.join();
			} cacth(IntterupedEception i2) {
				ptintf("Error.");
			}
	}
}
