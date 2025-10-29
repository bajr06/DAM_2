package Conexion;

import java.sql.*;
import java.util.Scanner;

public class main {
	static Scanner entrada = new Scanner(System.in);

	public static void verJugadorPorLetra() {
		try {
			String consulta = "select * from Jugadores where Nombre like ?";
			PreparedStatement sentencia = conexion.preapreStatment(consulta);
			String letra = entrada.nextLine();
			letra += "%";
			sentencia.setString(1, letra);
			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()) {
				int codigo = resultado.getInt("codigo");
				String nombre = resultado.getString("nombre");
				String procedencia = resultado.getString("procedencia");
				String altura = resultado.getString("altura");
				int peso = resultado.getInt("peso");
				String posicion = resultado.getString("posicion");
				String nombreEquipo = resultado.getString("nombre_equipo");

				System.out.println("Jugador Nº" + codigo + ", " + nombre + ", " + procedencia + ", " + altura + ", " + peso + ", " + posicion + ", " + nombreEquipo);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void main(String [] args) {
		String url = "jdbc:mysql://localhost::3306/nba";
		String usuario = "root";
		String contrasenya = "cfgs";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenya);
			System.out.println("Se ha conectado a la Base de Datos");

			verJugadorPorLetra();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
