package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneradorDatos {
	public static void inicializarDatos(Connection conexion) throws SQLException {
		if (estaVacia(conexion, "Empleado")) {
			System.out.println("Base de datos vacía. Cargando datos iniciales...");

			ejecutarInserts(conexion);
		}
	}

	private static boolean estaVacia(Connection conexion, String tabla) throws SQLException {
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + tabla);
		
		return rs.next() && rs.getInt(1) == 0;
	}

	private static void ejecutarInserts(Connection conn) throws SQLException {
		Statement st = conn.createStatement();

		st.executeUpdate("INSERT INTO Zona (Nombre, Descripcion) VALUES ('Norte', 'Juguetes Madera'), ('Sur', 'Electrónicos')");
		st.executeUpdate("INSERT INTO Stand (Nombre, Descripcion, Zona_ID_Zona) VALUES ('Stand A1', 'Principal', 1), ('Stand B1', 'Secundario', 2)");
		st.executeUpdate("INSERT INTO Empleado (Nombre, Cargo, Fecha_Ingreso) VALUES ('Admin', 'Jefe', '2023-01-01'), ('Vendedor1', 'Cajero', '2023-05-15')");
		st.executeUpdate("INSERT INTO Juguete (Nombre, Descripcion, Precio, Cantidad_Stock) VALUES ('LEGO Star Wars', 'Set construcción', 99.99, 0), ('Pelota', 'Futbol', 15.00, 0)");
		st.executeUpdate("INSERT INTO Stock (Stand_ID_Stand, Stand_Zona_ID_Zona, Juguete_ID_Juguete, Cantidad) VALUES (1, 1, 1, 10), (1, 1, 2, 10)");

		System.out.println("Carga aleatoria completada.");
	}
}
