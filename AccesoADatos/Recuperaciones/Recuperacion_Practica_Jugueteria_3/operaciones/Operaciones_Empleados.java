package operaciones;

import conexion.ConexionBD;
import objetos.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Operaciones_Empleados {

	// Registrar un nuevo empleado
	public boolean registrarEmpleado(Empleado empleado) {
		String sql = "INSERT INTO Empleado (Nombre, Cargo, Fecha_ingreso) VALUES (?, ?, ?)";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, empleado.getNombre());
			pstmt.setString(2, empleado.getCargo());
			pstmt.setDate(3, empleado.getFechaIngreso());
			
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al registrar empleado: " + e.getMessage());
			return false;
		}
	}

	// Modificar los datos de un empleado
	public boolean modificarEmpleado(Empleado empleado) {
		String sql = "UPDATE Empleado SET Nombre = ?, Cargo = ?, Fecha_ingreso = ? WHERE ID_Empleado = ?";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, empleado.getNombre());
			pstmt.setString(2, empleado.getCargo());
			pstmt.setDate(3, empleado.getFechaIngreso());
			pstmt.setInt(4, empleado.getIdEmpleado());
			
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al modificar empleado: " + e.getMessage());
			return false;
		}
	}

	// Eliminar un empleado
	public boolean eliminarEmpleado(int idEmpleado) {
		String sql = "DELETE FROM Empleado WHERE ID_Empleado = ?";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, idEmpleado);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar empleado: " + e.getMessage());
			return false;
		}
	}

	// Listar todos los empleados
	public List<Empleado> obtenerTodos() {
		List<Empleado> lista = new ArrayList<>();
		String sql = "SELECT * FROM Empleado";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				lista.add(new Empleado(
						rs.getInt("ID_Empleado"),
						rs.getString("Nombre"),
						rs.getString("Cargo"),
						rs.getDate("Fecha_ingreso")
				));
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener empleados: " + e.getMessage());
		}
		return lista;
	}
}
