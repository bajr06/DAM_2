package Conexion;

import java.sql.*;

public class Main {
	public static void main(String [] args) {
		String url = "jdbc:mysql://localhost::3306/mydb";
		String usuario = "root";
		String password = "cfgs";

		try {
			// 1. Carga el driver de la Base de Datos
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Crear la conexión.
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Se ha conectado a la Base de Datos.");

			// 3. Crear un Statement
			Statement sentencia0 = conexion.createStatement();
		 	String consulta0 = "select * from Usuario";
			// String consulta = "select * from Usuario where IdUsuario = 1 or 1 = 1";
			ResultSet resultado0 = sentencia0.executeQuery(consulta0);

			// 3.1 Crear PreparedStatement
			/* String consulta1 = "select * from Usuario where IdUsuario = ? or Nombre = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(consulta1);
			int numero = 1;
			sentencia1.setInt(1, numero);
			sentencia1.setString(2, "Leo");
			ResultSet resultado1 = sentencia1.executeQuery(); // Permite solo consultas. */

			// 4. Mostrar resultados
			while(resultado0.next()) {
				int idUsuario = resultado0.getInt("idUsuario");
				String Nombre = resultado0.getString("Nombre");
				Date fecha = resultado0.getDate("FechaNacimiento");
				String Genero = resultado0.getString("Genero");

				System.out.println("IdUsuario: " + idUsuario + ", Nombre: " + Nombre + ", Fecha: " + fecha + ", Genero: " + Genero);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
