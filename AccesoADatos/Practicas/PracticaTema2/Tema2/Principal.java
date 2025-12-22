package Tema2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
	static Scanner sc = new Scanner(System.in);

	protected static Connection conexionBD() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/Jugueteria";
		String usuario = "root";
		String contrasenia = "songoku";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

		return conexion;
	}

	public static void main(String[] args) {
		try {
			Connection conexion = conexionBD();
			
			if (Comprobacion.comprobarTablas(conexion)) {
				System.out.println("Todas las tablas tienen datos.");
			} else {
				System.err.println("No todas las tablas de la base de datos tienen datos, tenga cuidado.");
			}

			Opciones.opciones(conexion);
		} catch (ClassNotFoundException | SQLException cnfsqle) {
			cnfsqle.printStackTrace();
		}
	}
}
