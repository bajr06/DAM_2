package HilosJoinSleep;

public class Principal {
	public static void main(String [] args) {
		Thread h1 = new Thread(new Hilo("H1"));
		Thread h2 = new Thread(new Hilo("H2"));

		System.out.println("Desde el hilo principal, antes de lanzar...");

		h1.start();
		h2.start();

		try {
			h1.join();
			h2.join();
		} catch(InterrupedException ie) {
			System.out.println("Se ha interrumpido");
		}

		System.out.println("Hilo principal terminado");
	}
}
