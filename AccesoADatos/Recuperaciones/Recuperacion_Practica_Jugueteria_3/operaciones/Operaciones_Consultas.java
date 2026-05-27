package operaciones;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operaciones_Consultas {

	// Producto más vendido (los cinco primeros)
	public void mostrarTop5JuguetesMasVendidos() {
		String sql = "SELECT j.Nombre, COUNT(v.ID_Venta) AS Total_Ventas " +
					 "FROM Venta v JOIN Juguete j ON v.ID_Juguete = j.ID_Juguete " +
					 "GROUP BY j.ID_Juguete ORDER BY Total_Ventas DESC LIMIT 5";
		System.out.println("\n--- TOP 5 JUGUETES MÁS VENDIDOS ---");
		ejecutarYMostrarConsultaSimple(sql);
	}

	// Los empleados que más venden
	public void mostrarEmpleadosMasVendedores() {
		String sql = "SELECT e.Nombre, COUNT(v.ID_Venta) AS Total_Ventas, SUM(v.Monto) AS Total_Facturado " +
					 "FROM Venta v JOIN Empleado e ON v.ID_Empleado = e.ID_Empleado " +
					 "GROUP BY e.ID_Empleado ORDER BY Total_Ventas DESC";
		System.out.println("\n--- CLASIFICACIÓN DE EMPLEADOS POR VENTAS ---");
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				System.out.println("Empleado: " + rs.getString("Nombre") + " | Ventas: " + rs.getInt("Total_Ventas") + " | Facturado: " + rs.getDouble("Total_Facturado") + "€");
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Obtener todos los juguetes que están disponibles en un stand específico
	public void mostrarJuguetesPorStand(int idStand, int idZona) {
		String sql = "SELECT j.Nombre, j.Categoria, s.Cantidad_disponible " +
					 "FROM Stock s JOIN Juguete j ON s.ID_Juguete = j.ID_Juguete " +
					 "WHERE s.ID_Stand = ? AND s.ID_Zona = ?";
		System.out.println("\n--- JUGUETES EN STAND " + idStand + " (ZONA " + idZona + ") ---");
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idStand);
			pstmt.setInt(2, idZona);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					System.out.println("- " + rs.getString("Nombre") + " [" + rs.getString("Categoria") + "] | Disponibles: " + rs.getInt("Cantidad_disponible"));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Obtener los datos de las ventas realizadas en un mes
	public void mostrarVentasPorMes(int mes, int anio) {
		String sql = "SELECT v.ID_Venta, j.Nombre AS Juguete, e.Nombre AS Empleado, v.Fecha, v.Monto " +
					 "FROM Venta v JOIN Juguete j ON v.ID_Juguete = j.ID_Juguete " +
					 "JOIN Empleado e ON v.ID_Empleado = e.ID_Empleado " +
					 "WHERE MONTH(v.Fecha) = ? AND YEAR(v.Fecha) = ?";
		System.out.println("\n--- VENTAS DEL MES " + mes + "/" + anio + " ---");
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, mes);
			pstmt.setInt(2, anio);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					System.out.println("ID: " + rs.getInt("ID_Venta") + " | " + rs.getString("Juguete") + " | Vendedor: " + rs.getString("Empleado") + " | Fecha: " + rs.getDate("Fecha") + " | Importe: " + rs.getDouble("Monto") + "€");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Obtener los datos de las ventas realizadas por un empleado en un mes
	public void mostrarVentasEmpleadoPorMes(int idEmpleado, int mes, int anio) {
		String sql = "SELECT v.ID_Venta, j.Nombre AS Juguete, v.Fecha, v.Monto " +
					 "FROM Venta v JOIN Juguete j ON v.ID_Juguete = j.ID_Juguete " +
					 "WHERE v.ID_Empleado = ? AND MONTH(v.Fecha) = ? AND YEAR(v.Fecha) = ?";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idEmpleado);
			pstmt.setInt(2, mes);
			pstmt.setInt(3, anio);
			try (ResultSet rs = pstmt.executeQuery()) {
				System.out.println("\n--- VENTAS DEL EMPLEADO ID " + idEmpleado + " EN " + mes + "/" + anio + " ---");
				while (rs.next()) {
					System.out.println("ID Venta: " + rs.getInt("ID_Venta") + " | Juguete: " + rs.getString("Juguete") + " | Fecha: " + rs.getDate("Fecha") + " | Monto: " + rs.getDouble("Monto") + "€");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Obtener los datos de los cambios de los empleados y el motivo del cambio
	public void mostrarHistorialCambios() {
		String sql = "SELECT c.ID_Cambio, e.Nombre AS Empleado, j1.Nombre AS Original, j2.Nombre AS Nuevo, c.Motivo, c.Fecha " +
					 "FROM Cambio c JOIN Empleado e ON c.ID_Empleado = e.ID_Empleado " +
					 "JOIN Juguete j1 ON c.ID_Juguete_Original = j1.ID_Juguete " +
					 "JOIN Juguete j2 ON c.ID_Juguete_Nuevo = j2.ID_Juguete";
		System.out.println("\n--- HISTORIAL DE CAMBIOS Y TRASPASOS ---");
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				System.out.println("Cambio #" + rs.getInt("ID_Cambio") + " | Responsable: " + rs.getString("Empleado") +
								   "\n  De: " + rs.getString("Original") + " -> A: " + rs.getString("Nuevo") +
								   "\n  Motivo: " + rs.getString("Motivo") + " | Fecha: " + rs.getDate("Fecha") + "\n");
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Lista de los productos ordenados por precio
	public void mostrarProductosOrdenadosPorPrecio() {
		String sql = "SELECT Nombre, Precio, Categoria, Cantidad_en_stock FROM Juguete ORDER BY Precio ASC";
		System.out.println("\n--- LISTADO DE PRODUCTOS (ORDENADOS POR PRECIO) ---");
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				System.out.println("- " + rs.getString("Nombre") + " (" + rs.getString("Categoria") + ") -> " + rs.getDouble("Precio") + "€ | Stock Total: " + rs.getInt("Cantidad_en_stock"));
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private void ejecutarYMostrarConsultaSimple(String sql) {
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				System.out.println("- " + rs.getString(1) + " | Cantidad/Operaciones: " + rs.getInt(2));
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar consulta: " + e.getMessage());
		}
	}
}
