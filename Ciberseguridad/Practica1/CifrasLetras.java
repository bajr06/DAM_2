package Practica1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CifrasLetras {
	/** Recurso compartido: flujo de salida al fichero. */
    public static FileOutputStream fosSalida;

    /** Candado para proteger operaciones sobre fosSalida. */
    public static final Object fosSalidaLock = new Object();

    /** Objeto sincronizador para wait/notify entre hilos y main. */
    private static final Object sincronizador = new Object();

    /** Ganador del juego: "cifras" o "letras" cuando alguno complete primero. */
    public static volatile String ganador = null;

    /** Acceso controlado al sincronizador (permite mantenerlo private). */
    public static Object getSincronizador() {
        return sincronizador;
    }

    public static void main(String[] args) {
        try {
            // Crear/limpiar fichero de salida
            File f = new File("salida.txt");
            if (f.exists()) f.delete();
            fosSalida = new FileOutputStream(f, true); // append

            // Instanciar los dos jugadores
            Jugador cifras = new Jugador("cifras");
            Jugador letras = new Jugador("letras");

            // Lanzar los hilos
            cifras.start();
            letras.start();

            // Hilo principal espera a que alguno termine sus 20 y notifique
            synchronized (getSincronizador()) {
                while (ganador == null) {
                    getSincronizador().wait();
                }
            }

            System.out.println("¡Tenemos un ganador!: " + ganador.toUpperCase());

            // Opcional: esperar a que ambos finalicen antes de cerrar el recurso
            cifras.join();
            letras.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo principal fue interrumpido.");
        } catch (IOException e) {
            System.err.println("Error inicializando el fichero salida.txt: " + e.getMessage());
        } finally {
            // Cerrar el recurso compartido
            if (fosSalida != null) {
                try {
                    fosSalida.close();
                } catch (IOException e) {
                    System.err.println("Error cerrando salida.txt: " + e.getMessage());
                }
            }
        }
    }

}

/* Preguntas teóricas
- Espera ocupada: bucle que revisa continuamente una condición sin bloquear; consume CPU de forma innecesaria.
- Espera semi-ocupada: alterna comprobación con pausas (sleep) reduciendo consumo, pero sigue siendo menos eficiente que bloquear.
- Ventaja de wait(): el hilo se bloquea sin consumir CPU hasta recibir notify; mejora rendimiento y latencia del sistema bajo carga.
- Procesos vs hilos: procesos tienen espacios de memoria aislados; hilos comparten memoria dentro del mismo proceso. Beneficios de hilos: menor coste de creación, comunicación más rápida y fácil compartición de estado.
*/
