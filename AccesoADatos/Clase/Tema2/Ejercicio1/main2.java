package Ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PracticaBBDD {
	public static void BuscarJugadoresPorPrimeraLetra(Connection conexion, Scanner entrada) {
		String letra = "";
		try {
			String consulta = "Select * from jugadores where Nombre like ?";
			
			System.out.println("Introduce la letra por la que quieres buscar a los jugadores:");
			letra = entrada.nextLine();
			letra += "%";
			
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			sentencia.setString(1, letra);
			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				int codigo = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo = resultado.getString("Nombre_Equipo");

				System.out.println("Jugador con Código: " + codigo + " Nombre: " + nombre + " Procedencia: " + procedencia + " Altura: " + altura + " Peso" + peso + " Posicion" + posicion + " Equipo de procedencia" + equipo);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void pesoMedio(Connection conexion, Scanner entrada) {
		try {
			String consulta = "Select avg(peso) as MediaPeso from jugadores";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				double avgPeso = resultado.getDouble("MediaPeso");
				System.out.println("La media del peso de los jugadores es: " + avgPeso);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void mostrarDatosEquipo(Connection conexion, Scanner entrada) {
		String equipo = "";
		try {
			String ListadoEquipos = "Select Nombre from equipos";
			PreparedStatement sentencia = conexion.prepareStatement(ListadoEquipos);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				String nombre = resultado.getString("Nombre");
				System.out.printf("Equipo %s \n", nombre);
			}
			
			System.out.println("Escribe el nombre del equipo que quieras conocer sus datos: ");
			equipo = entrada.nextLine();
			
			String seleccionEquipo = "Select * from equipos where Nombre = ?";
			PreparedStatement sentencia2 = conexion.prepareStatement(seleccionEquipo);
			sentencia2.setString(1, equipo);
			ResultSet resultado2 = sentencia2.executeQuery();
			while(resultado2.next()) {
				String nombre = resultado2.getString("Nombre");
				String ciudad = resultado2.getString("Ciudad");
				String conferencia = resultado2.getString("Conferencia");
				String division = resultado2.getString("Division");
				System.out.println("Nombre de equipo: " + nombre + ", Ciudad: " + ciudad + ", Conferencia: " + conferencia + ", División: " + division);
			}
			
			String ListarJugadoresEquipos = "Select * from jugadores where Nombre_equipo = ?";
			PreparedStatement sentencia3 = conexion.prepareStatement(ListarJugadoresEquipos);
			sentencia3.setString(1, equipo);
			ResultSet resultado3 = sentencia3.executeQuery();
			
			while(resultado.next()) {
				int codigo = resultado3.getInt("codigo");
				String nombre = resultado3.getString("Nombre");
				String procedencia = resultado3.getString("Procedencia");
				String altura = resultado3.getString("Altura");
				int peso = resultado3.getInt("Peso");
				String posicion = resultado3.getString("Posicion");

				System.out.println("Jugador con Código: " + codigo + " Nombre: " + nombre + " Procedencia: " + procedencia + " Altura: " + altura + " Peso" + peso + " Posicion" + posicion);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Nos hemos conectado a la base de datos de la NBA.");
			BuscarJugadoresPorPrimeraLetra(conexion, entrada);
			pesoMedio(conexion, entrada);
			mostrarDatosEquipo(conexion, entrada);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
