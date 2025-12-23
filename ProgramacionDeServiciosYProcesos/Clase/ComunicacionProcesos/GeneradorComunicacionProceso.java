package ComunicacionProcesos;

import java.io.*;

public class GeneradorComunicacionProceso {
	@SuppressWarnings("deprecation")
	public void ejecutar(String ruta) {
		Runtime rt = Runtime.getRuntime(); // Lo mismo que el ProcessBuilder, pero este último tiene más opciones de utilidad.
		Process proceso = null;
		String line;

		try {
			proceso = rt.exec(ruta);
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		if(proceso != null) {
			proceso.destroy();
		}

		try {
			proceso.waitFor();
		} catch(InterruptedException e) {
			System.exit(-2);
		}

		System.exit(0);
	}

    public void ejecutar(String ruta, String nombre) {
        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }
}
