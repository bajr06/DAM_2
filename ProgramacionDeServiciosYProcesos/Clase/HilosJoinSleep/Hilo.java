package HilosJoinSleep;

public class Hilo implements Runnable {
	private final String nombre;


	Hilo(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public void run() {
		System.out.printf("Hola soy el hilo %s\n", nombre);

		for(int i = 0; i < 5; i++) {
			Random r = new Random();

			int pausa = 20 + r.nextInt(500 - 20);

			System.out.printf("Hilo %s hace pausa %d milisegundos\n", this.nombre, pausa);
			try {
				Thread.sleep(pausa);
			} catch(InterrupedException ie) {
				System.out.printf("Hilo %s interrupido\n", nombre);
			}
		}
	}
}
