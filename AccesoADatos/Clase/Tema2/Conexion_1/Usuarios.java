package Conexion_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Usuarios {
	// Solo se puede ejecutar esto en Windows, no desde WSL.
	public static void main(String [] args) {
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "songoku"; // La clave es "cfgs" en clase.

		try {
			// 1. Carga el driver de la Base de Datos.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Crear la conexión.
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Se ha conectado a la Base de Datos.");

			// 3.1 Crear un Statement
			/*
			Statement sentencia0 = conexion.createStatement();
		 	// String consulta0 = "select * from Usuario";
			String consulta = "select * from Usuario where IdUsuario = 1 or 1 = 1";
			ResultSet resultado0 = sentencia0.executeQuery(consulta0);
			*/

			// 3.2 Crear PreparedStatement
			String consulta1 = "select * from Usuario where IdUsuario = ? or Nombre = ?";
			// String consulta1 = "update Usuario set Nombre = ? where IdUsuario = ?;";
			PreparedStatement sentencia1 = conexion.prepareStatement(consulta1);
			int numero = 1;
			
			sentencia1.setInt(1, numero);
			sentencia1.setString(2, "Leo");
			
			ResultSet resultado1 = sentencia1.executeQuery(); // Permite solo consultas.

			/* Si es la opción de UPDATE
			* int resultado = sentencia.executeUpdate();
			* System.out.println("Número de líneas afectadas: " + resultado);
			*/

			// 4. Mostrar resultados
			while(resultado1.next()) {
				int idUsuario = resultado1.getInt("idUsuario");
				String Nombre = resultado1.getString("Nombre");
				Date fecha = resultado1.getDate("FechaNacimiento");
				String Genero = resultado1.getString("Genero");

				System.out.println("IdUsuario: " + idUsuario + ", Nombre: " + Nombre + ", Fecha: " + fecha + ", Genero: " + Genero);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
