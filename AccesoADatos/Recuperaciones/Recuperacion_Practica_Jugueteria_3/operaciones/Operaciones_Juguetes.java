package operaciones;

import conexion.ConexionBD;
import objetos.Juguete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Operaciones_Juguetes {

	// Registrar un nuevo juguete
	public boolean registrarJuguete(Juguete juguete) {
		String sql = "INSERT INTO Juguete (Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, juguete.getNombre());
			pstmt.setString(2, juguete.getDescripcion());
			pstmt.setDouble(3, juguete.getPrecio());
			pstmt.setInt(4, juguete.getCantidadEnStock());
			pstmt.setString(5, juguete.getCategoria());
			
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al registrar juguete: " + e.getMessage());
			return false;
		}
	}

	// Modificar los datos de un juguete existente
	public boolean modificarJuguete(Juguete juguete) {
		String sql = "UPDATE Juguete SET Nombre = ?, Descripcion = ?, Precio = ?, Cantidad_en_stock = ?, Categoria = ? WHERE ID_Juguete = ?";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, juguete.getNombre());
			pstmt.setString(2, juguete.getDescripcion());
			pstmt.setDouble(3, juguete.getPrecio());
			pstmt.setInt(4, juguete.getCantidadEnStock());
			pstmt.setString(5, juguete.getCategoria());
			pstmt.setInt(6, juguete.getIdJuguete());
			
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al modificar juguete: " + e.getMessage());
			return false;
		}
	}

	// Eliminar un juguete por su ID
	public boolean eliminarJuguete(int idJuguete) {
		String sql = "DELETE FROM Juguete WHERE ID_Juguete = ?";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, idJuguete);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar juguete: " + e.getMessage());
			return false;
		}
	}

	// Método extra útil para listar y comprobar que funciona
	public List<Juguete> obtenerTodos() {
		List<Juguete> lista = new ArrayList<>();
		String sql = "SELECT * FROM Juguete";
		try (Connection conn = ConexionBD.getConexion();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				lista.add(new Juguete(
						rs.getInt("ID_Juguete"),
						rs.getString("Nombre"),
						rs.getString("Descripcion"),
						rs.getDouble("Precio"),
						rs.getInt("Cantidad_en_stock"),
						rs.getString("Categoria")
				));
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener juguetes: " + e.getMessage());
		}
		return lista;
	}
}
