package Conexion_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// Solo se puede ejecutar desde Windows
public class NBA {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "songoku"; // "cfgs" en clase.

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(url, usuario, password);

			System.out.println("Introduce la letra correspondiente:");
			String letra = entrada.nextLine() + '%';
			String consulta = "select * from Jugadores where Nombre like ?;";

			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(1, letra);

			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()) {
				int codigo = resultado.getInt("Codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo = resultado.getString("Nombre_Equipo");

				System.out.printf("%d.- %s: %s, %s, %d, %s, %s\n", codigo, nombre, procedencia, altura, peso, posicion, equipo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
