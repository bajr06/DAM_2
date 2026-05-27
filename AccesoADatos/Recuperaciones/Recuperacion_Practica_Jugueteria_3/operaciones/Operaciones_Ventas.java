package operaciones;

import conexion.ConexionBD;
import objetos.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operaciones_Ventas {
	// Realiza una venta controlando estrictamente el stock.
	public boolean realizarVenta(Venta venta) {
		Connection conn = ConexionBD.getConexion();
		if (conn == null) return false;

		String sqlCheckStock = "SELECT Cantidad_disponible FROM Stock WHERE ID_Stand = ? AND ID_Zona = ? AND ID_Juguete = ?";
		String sqlUpdateStockStand = "UPDATE Stock SET Cantidad_disponible = Cantidad_disponible - 1 WHERE ID_Stand = ? AND ID_Zona = ? AND ID_Juguete = ?";
		String sqlUpdateStockGlobal = "UPDATE Juguete SET Cantidad_en_stock = Cantidad_en_stock - 1 WHERE ID_Juguete = ?";
		String sqlInsertVenta = "INSERT INTO Venta (ID_Empleado, ID_Juguete, ID_Stand, ID_Zona, Fecha, Monto, Tipo_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			// Desactivar el auto-commit para gestionar la transacción manualmente
			conn.setAutoCommit(false);

			// 1. Verificar Stock disponible en el stand
			try (PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheckStock)) {
				pstmtCheck.setInt(1, venta.getIdStand());
				pstmtCheck.setInt(2, venta.getIdZona());
				pstmtCheck.setInt(3, venta.getIdJuguete());
				
				try (ResultSet rs = pstmtCheck.executeQuery()) {
					if (!rs.next() || rs.getInt("Cantidad_disponible") <= 0) {
						System.out.println("Error: No hay stock disponible de este juguete en el stand seleccionado.");
						conn.rollback();
						return false;
					}
				}
			}

			// 2. Descontar del stock del Stand
			try (PreparedStatement pstmtUpdStand = conn.prepareStatement(sqlUpdateStockStand)) {
				pstmtUpdStand.setInt(1, venta.getIdStand());
				pstmtUpdStand.setInt(2, venta.getIdZona());
				pstmtUpdStand.setInt(3, venta.getIdJuguete());
				pstmtUpdStand.executeUpdate();
			}

			// 3. Descontar del stock global del Juguete
			try (PreparedStatement pstmtUpdGlobal = conn.prepareStatement(sqlUpdateStockGlobal)) {
				pstmtUpdGlobal.setInt(1, venta.getIdJuguete());
				pstmtUpdGlobal.executeUpdate();
			}

			// 4. Registrar la Venta
			try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertVenta)) {
				pstmtInsert.setInt(1, venta.getIdEmpleado());
				pstmtInsert.setInt(2, venta.getIdJuguete());
				pstmtInsert.setInt(3, venta.getIdStand());
				pstmtInsert.setInt(4, venta.getIdZona());
				pstmtInsert.setDate(5, venta.getFecha());
				pstmtInsert.setDouble(6, venta.getMonto());
				pstmtInsert.setString(7, venta.getTipoPago());
				pstmtInsert.executeUpdate();
			}

			// Confirmar transacción si todo ha ido bien
			conn.commit();
			return true;

		} catch (SQLException e) {
			try {
				conn.rollback(); // Deshacer cambios ante cualquier fallo técnico
				System.err.println("Transacción revertida debido a un error: " + e.getMessage());
			} catch (SQLException ex) {
				System.err.println("Error haciendo rollback: " + ex.getMessage());
			}
			return false;
		} finally {
			try {
				conn.setAutoCommit(true); // Restaurar el comportamiento por defecto
			} catch (SQLException e) {
				System.err.println("Error al restaurar autoCommit: " + e.getMessage());
			}
		}
	}

	// Realiza la devolución de un juguete, sumando de nuevo las existencias.
	public boolean realizarDevolucion(int idVenta) {
		Connection conn = ConexionBD.getConexion();
		if (conn == null) return false;

		String sqlSelectVenta = "SELECT ID_Juguete, ID_Stand, ID_Zona FROM Venta WHERE ID_Venta = ?";
		String sqlDeleteVenta = "DELETE FROM Venta WHERE ID_Venta = ?";
		String sqlAddStockStand = "UPDATE Stock SET Cantidad_disponible = Cantidad_disponible + 1 WHERE ID_Stand = ? AND ID_Zona = ? AND ID_Juguete = ?";
		String sqlAddStockGlobal = "UPDATE Juguete SET Cantidad_en_stock = Cantidad_en_stock + 1 WHERE ID_Juguete = ?";

		try {
			conn.setAutoCommit(false);

			int idJuguete = -1, idStand = -1, idZona = -1;

			// 1. Obtener los detalles de la venta que se va a devolver
			try (PreparedStatement pstmtSel = conn.prepareStatement(sqlSelectVenta)) {
				pstmtSel.setInt(1, idVenta);
				try (ResultSet rs = pstmtSel.executeQuery()) {
					if (rs.next()) {
						idJuguete = rs.getInt("ID_Juguete");
						idStand = rs.getInt("ID_Stand");
						idZona = rs.getInt("ID_Zona");
					} else {
						System.out.println("Error: No se encontró ninguna venta con el ID especificado.");
						conn.rollback();
						return false;
					}
				}
			}

			// 2. Devolver stock al Stand
			try (PreparedStatement pstmtAddStand = conn.prepareStatement(sqlAddStockStand)) {
				pstmtAddStand.setInt(1, idStand);
				pstmtAddStand.setInt(2, idZona);
				pstmtAddStand.setInt(3, idJuguete);
				pstmtAddStand.executeUpdate();
			}

			// 3. Devolver stock al Juguete global
			try (PreparedStatement pstmtAddGlobal = conn.prepareStatement(sqlAddStockGlobal)) {
				pstmtAddGlobal.setInt(1, idJuguete);
				pstmtAddGlobal.executeUpdate();
			}

			// 4. Eliminar el registro de la venta (o marcar como devuelto si el diseño lo contemplara)
			try (PreparedStatement pstmtDel = conn.prepareStatement(sqlDeleteVenta)) {
				pstmtDel.setInt(1, idVenta);
				pstmtDel.executeUpdate();
			}

			conn.commit();
			return true;

		} catch (SQLException e) {
			try { conn.rollback(); } catch (SQLException ex) { System.err.println(ex.getMessage()); }
			System.err.println("Error en la devolución: " + e.getMessage());
			return false;
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
