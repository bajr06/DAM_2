package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://localhost:3306/jugueteria";
	private static final String USER = "root"; 
	private static final String PASSWORD = "songoku"; 
	private static Connection conexion = null;

	private ConexionBD() {
	}

	// Devuelve la instancia única de la conexión a la base de datos.
	public static Connection getConexion() {
		if (conexion == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Conexión a la base de datos 'jugueteria' establecida.");
			} catch (ClassNotFoundException e) {
				System.err.println("Error: No se encontró el driver JDBC de MySQL. Asegúrate de tener el .jar en tu Build Path.");
			} catch (SQLException e) {
				System.err.println("Error de conexión a la BD: " + e.getMessage());
			}
		}
		return conexion;
	}


	// Cierra la conexión cuando el programa termina.
	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
				conexion = null;
				System.out.println("Conexión cerrada correctamente.");
			} catch (SQLException e) {
				System.err.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}
}
