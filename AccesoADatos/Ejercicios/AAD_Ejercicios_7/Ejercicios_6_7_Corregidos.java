package AAD_Ejercicios_7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicios_6_7_Corregidos {
	public static Connection ConexionBD(String url, String usuario, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc,Driver");

		return DriverManager.getConnection(url, usuario, password);
	}

	public static void ejercicio1(Connection conexion) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Introduce la letra correspondiente: ");
			String letra = sc.nextLine() + '%';

			String consulta = "select * from Jugadores where Nombre like ?";
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

				System.out.println("- " + codigo + ": " + nombre + ", " + procedencia + ", " + altura + ", " + peso + ", " + posicion + ", " + equipo);
			}

			sc.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void ejercicio2(Connection conexion) {
		try {
			String consulta = "select avg(Peso) Peso_General from Jugadores";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()) {
				double peso = resultado.getDouble("Peso_General");

				System.out.println("Peso general: " + peso);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void ejercicio3(Connection conexion) {
		int codigo = 0;
		ArrayList<String> nombres = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		try {
			String consultaEquipos = "select Nombre from Equipos";
			PreparedStatement sentencia = conexion.prepareStatement(consultaEquipos);
			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()) {
				String nombreEquipo = resultado.getString("Nombre");

				System.out.println(codigo + ": " + nombreEquipo);
				
				nombres.add(nombreEquipo);
				codigo++;
			}

			System.out.println("Introduce el nombre del equipo: ");
			int codigoEquipo = sc.nextInt();

			String consultaJugadores = "select Nombre from Jugadores where Nombre_Equipo = ?";
			PreparedStatement sentencia2 = conexion.prepareStatement(consultaJugadores);
			
			sentencia2.setString(1, nombres.get(codigoEquipo));
			resultado = sentencia2.executeQuery();

			while(resultado.next()) {
				System.out.println(resultado.getString("Nombre"));
			}

			sc.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void ejercicio4(Connection conexion) {
		int codigo = 0;
		Scanner sc = new Scanner(System.in);

		try {
			/* Para obtener el código (que no es AI).
			*	select Codigo from Jugadores order by Codigo desc limit 1;
			*	select max(Codigo) from Jugadores;
			*/

			String consultaCodigo = "select max(Codigo) Codigo_Max from Jugadores";
			PreparedStatement sentencia = conexion.prepareStatement(consultaCodigo);
			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()) {
				codigo = resultado.getInt(1);

				System.out.println(codigo);
			}

			System.out.println("Introduce los datos del jugador (Nombre): ");
			String nombre = sc.nextLine();

			String consultaInserccion = "insert into Jugadores(Codigo, Nombre) values(?, ?))";
			PreparedStatement sentencia2 = conexion.prepareStatement(consultaInserccion);

			sentencia2.setInt(1, codigo + 1);
			sentencia2.setString(2, nombre);

			int lineas = sentencia2.executeUpdate();

			if(lineas > 0) {
				System.out.println("Se ha cambiado las lineas " + lineas);
			}
			sc.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void ejercicio5(Connection conexion) {
		int codigo = 0;
		int lineas = 0;
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Introduce el código del jugador que quieres borrar: ");
			codigo = sc.nextInt();

			// No hay borrado en cascada, por lo tanto, necesito borrar a mano.
			String consulta = "select * from Estadisticas where Jugador = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(consulta);
			sentencia1.setInt(1, codigo);

			if(sentencia1.execute()) {
				String consultaBorradoEstadistica = "delete from Estadisticas where Jugador = ?";
				PreparedStatement sentencia2 = conexion.prepareStatement(consultaBorradoEstadistica);
				
				sentencia2.setInt(1, codigo);
				lineas = sentencia2.executeUpdate();

				if(lineas >0) {
					System.out.println("Se han borrado las estadísticas del jugador " + codigo);
				}
			}

			// Borrado
			String consultaBorrado = "delete from Jugadores where Codigo = ?";
			PreparedStatement sentencia3 = conexion.prepareStatement(consultaBorrado);
			
			sentencia3.setInt(1, codigo);
			lineas = sentencia3.executeUpdate();

			if(lineas > 0) {
				System.out.println("Se han borrado las líneas: " + lineas);
			}
			
			sc.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "songoku"; // "cfgs" en la clase.

		System.out.println("Hola");

		try(Connection conexion = ConexionBD(url, usuario, password)) {
			int opcion = -1;
			Scanner sc = new Scanner(System.in);

			while(opcion != 0) {
				System.out.println("Escribe el número del ejercicio: ");
				opcion = sc.nextInt();

				switch (opcion) {
					case 0:
						System.out.println("Salir");
						break;
					case 1:
						ejercicio1(conexion);
						break;
					case 2:
						ejercicio2(conexion);
						break;
					case 3:
						ejercicio3(conexion);
						break;
					case 4:
						ejercicio4(conexion);
						break;
					case 5:
						ejercicio5(conexion);
						break;
					default:
						System.out.println("Opción no válida");
						break;
				}
			}

			sc.close();
		} catch(ClassNotFoundException | SQLException cnfsqle) {
			cnfsqle.printStackTrace();
		}
	}
}