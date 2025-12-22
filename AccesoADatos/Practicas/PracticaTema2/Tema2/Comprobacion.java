package Tema2;

// import java.io.BufferedReader;
// import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;

public class Comprobacion {
	private static boolean comprobarTabla(Connection conexion, String tabla, String id) throws SQLException {
		String sql = "select count(*) as Total from " + tabla;

		PreparedStatement ps = conexion.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			int total = rs.getInt("Total");
			return total > 0; // true si hay datos, false si está vacía
		}
		
		return false;
	}

	/* public static void cargarDatos(Connection conexion) {
		try (BufferedReader br = new BufferedReader(new FileReader("PracticaFinalTema2/src/Tema2/Carga.txt"))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				linea = linea.trim();
				
				if (!linea.isEmpty()) {
					try {
						PreparedStatement ps = conexion.prepareStatement(linea, Statement.RETURN_GENERATED_KEYS);
						ps.executeUpdate(linea);
						System.out.println("Ejecutado: " + linea);
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	public static boolean comprobarTablas(Connection conexion) throws SQLException {
		String[] tablas = {"Cambio", "Empleado", "Juguete", "Stand", "Venta", "Zona"};
		boolean comprobacion;
		int contador = 0;

		for(int i = 0; i < tablas.length; i++) {
			comprobacion = comprobarTabla(conexion, tablas[i], "ID_" + tablas[i]);
			System.out.println("¿La tabla " + tablas[i] + " presenta datos? " + comprobacion);
			
			if(comprobacion) {
				contador++;
			} else if(!comprobacion){
				contador--;
			}
		}

		if(contador == 6) {
			return true;
		} else {
			return false;
		}
	}
}
