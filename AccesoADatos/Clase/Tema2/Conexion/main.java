package Conexion;

import java.sql.*;

public class main {
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
			Statement sentencia = conexion.createStatement();
			// String consulta = "select * from Usuario";
			String consulta = "select * from Usuario where IdUsuario = 1 or 1 = 1";
			ResultSet resultado = sentencia.exceptQuery(consulta);

			// 4. Mostrar resultados
			while(resultado.next()) {
				int idUsuario = resultado.getInt("idUsuario");
				String Nombre = resultado.getString("Nombre");
				Date fecha = resultado.getDate("FechaNacimiento");
				String Genero = resultado.getString("Genero");

				System.out.println("IdUsuario: " + idUsuario + ", Nombre: " + Nombre + ", Genero: " + Genero);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
