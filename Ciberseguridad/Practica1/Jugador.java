package Ciberseguridad.Practica1;

import java.io.IOException;
import java.util.Random;

class Jugador extends Thread {
    /** Tipo de jugador: "cifras" o "letras". */
    private final String tipo;

    /** Límite de caracteres a escribir por cada jugador. */
    private static final int LIMITE = 20;

    /** Generador de aleatorios. */
    private static final Random rnd = new Random();

    public Jugador(String tipo) {
        this.tipo = tipo;
        setName(tipo); // nombre del hilo para identificación
    }

    @Override
    public void run() {
        try {
            // Aleatorizar quién empieza
            Thread.sleep(rnd.nextInt(1000));

            for (int i = 0; i < LIMITE; i++) {
                char c = (tipo.equals("cifras")) ? cifraRandom() : letraRandom();

                // Escribir en el recurso compartido (fosSalida) de forma atómica
                synchronized (CifrasLetras.fosSalidaLock) {
                    CifrasLetras.fosSalida.write(c);
                    CifrasLetras.fosSalida.flush();
                }
            }

            // Avisar al hilo principal si aún no hay ganador
            synchronized (CifrasLetras.getSincronizador()) {
                if (CifrasLetras.ganador == null) {
                    CifrasLetras.ganador = tipo;
                    CifrasLetras.getSincronizador().notify(); // notificar al main
                }
            }

        } catch (InterruptedException e) {
            // Interrupciones controladas
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.err.println("Error de E/S escribiendo en salida.txt: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en el hilo " + tipo + ": " + e.getMessage());
        }
    }

    /** Devuelve una letra aleatoria mayúscula entre 'A' y 'Z'. */
    private char letraRandom() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] arrayLetras = letras.toCharArray(); // convertir String a array de chars
        return arrayLetras[rnd.nextInt(arrayLetras.length)];
    }

    /** Devuelve una cifra aleatoria entre '0' y '9'. */
    private char cifraRandom() {
        return (char) ('0' + rnd.nextInt(10));
    }
}
