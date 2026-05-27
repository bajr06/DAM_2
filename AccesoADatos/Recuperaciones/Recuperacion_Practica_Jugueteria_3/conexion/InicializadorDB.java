package conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorDB {
	// Verifica si la tabla Juguete tiene registros. Si no los tiene, inicia la carga desde ficheros.
	public static void verificarYCargarDatos() {
		Connection conn = ConexionBD.getConexion();

		if (conn == null) return;
		String queryVerificacion = "SELECT COUNT(*) AS total FROM Juguete";

		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(queryVerificacion)) {

			if (rs.next() && rs.getInt("total") == 0) {
				System.out.println("La base de datos está vacía. Iniciando carga de datos de prueba...");
				cargarDatosDesdeFicheros(conn);
			} else {
				System.out.println("La base de datos ya contiene información. Omitiendo carga inicial.");
			}

		} catch (SQLException e) {
			System.err.println("Error al verificar el estado de la base de datos: " + e.getMessage());
		}
	}

	// Lee ficheros (ej. .sql o .csv) y ejecuta las inserciones.
	private static void cargarDatosDesdeFicheros(Connection conn) {
		String rutaFichero = "src/conexion/datos_iniciales.sql"; 

		try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
			 Statement stmt = conn.createStatement()) {

			String linea;
			StringBuilder queryBuilder = new StringBuilder();

			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty() || linea.startsWith("--")) continue;
				
				queryBuilder.append(linea);
				
				// Si la línea termina en punto y coma, se ejecuta la sentencia
				if (linea.trim().endsWith(";")) {
					stmt.execute(queryBuilder.toString());
					queryBuilder.setLength(0); // Limpiar para la siguiente sentencia
				}
			}
			System.out.println("Datos aleatorios cargados con éxito desde el fichero.");

		} catch (IOException e) {
			System.err.println("Error al leer el fichero de datos: " + e.getMessage() + "\n(Asegúrate de crear un archivo en la raíz de tu proyecto con algunos INSERT INTO).");
		} catch (SQLException e) {
			System.err.println("Error al ejecutar el SQL del fichero: " + e.getMessage());
		}
	}
}
