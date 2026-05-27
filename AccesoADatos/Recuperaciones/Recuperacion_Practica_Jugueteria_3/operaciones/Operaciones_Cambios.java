package operaciones;

import conexion.ConexionBD;
import objetos.Cambio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operaciones_Cambios {

	// Registra un cambio o traspaso de producto actualizando los stocks correspondientes.
	public boolean registrarCambio(Cambio cambio) {
		Connection conn = ConexionBD.getConexion();
		if (conn == null) return false;

		String sqlCheckStock = "SELECT Cantidad_disponible FROM Stock WHERE ID_Stand = ? AND ID_Zona = ? AND ID_Juguete = ?";
		String sqlDescontarOrigen = "UPDATE Stock SET Cantidad_disponible = Cantidad_disponible - 1 WHERE ID_Stand = ? AND ID_Zona = ? AND ID_Juguete = ?";
		String sqlIncrementarDestino = "UPDATE Stock SET Cantidad_disponible = Cantidad_disponible + 1 WHERE ID_Stand = ? AND ID_Zona = ? AND ID_Juguete = ?";
		String sqlInsertCambio = "INSERT INTO Cambio (ID_Empleado, ID_Juguete_Original, ID_Juguete_Nuevo, Motivo, Fecha, Stand_origen, ID_zona_origen, Stand_destino, ID_zona_destino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn.setAutoCommit(false); // Iniciamos transacción

			// 1. Verificar si hay stock disponible en el origen
			try (PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheckStock)) {
				pstmtCheck.setInt(1, cambio.getStandOrigen());
				pstmtCheck.setInt(2, cambio.getIdZonaOrigen());
				pstmtCheck.setInt(3, cambio.getIdJugueteOriginal());

				try (ResultSet rs = pstmtCheck.executeQuery()) {
					if (!rs.next() || rs.getInt("Cantidad_disponible") <= 0) {
						System.out.println("Error: No hay existencias del juguete en el stand de origen para realizar el cambio.");
						conn.rollback();
						return false;
					}
				}
			}

			// 2. Descontar una unidad del stand de origen
			try (PreparedStatement pstmtDesc = conn.prepareStatement(sqlDescontarOrigen)) {
				pstmtDesc.setInt(1, cambio.getStandOrigen());
				pstmtDesc.setInt(2, cambio.getIdZonaOrigen());
				pstmtDesc.setInt(3, cambio.getIdJugueteOriginal());
				pstmtDesc.executeUpdate();
			}

			// 3. Incrementar una unidad en el stand de destino
			// Nota: Se asume que el registro de stock ya existe en destino; si no, se sumaría tras crearlo.
			try (PreparedStatement pstmtInc = conn.prepareStatement(sqlIncrementarDestino)) {
				pstmtInc.setInt(1, cambio.getStandDestino());
				pstmtInc.setInt(2, cambio.getIdZonaDestino());
				pstmtInc.setInt(3, cambio.getIdJugueteNuevo());
				int filasAfectadas = pstmtInc.executeUpdate();
				
				// Si el juguete no existía previamente en ese stand, creamos la fila de stock
				if (filasAfectadas == 0) {
					String sqlInsertStock = "INSERT INTO Stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (?, ?, ?, 1)";
					try (PreparedStatement pstmtInsStock = conn.prepareStatement(sqlInsertStock)) {
						pstmtInsStock.setInt(1, cambio.getStandDestino());
						pstmtInsStock.setInt(2, cambio.getIdZonaDestino());
						pstmtInsStock.setInt(3, cambio.getIdJugueteNuevo());
						pstmtInsStock.executeUpdate();
					}
				}
			}

			// 4. Insertar el registro histórico del cambio
			try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertCambio)) {
				pstmtInsert.setInt(1, cambio.getIdEmpleado());
				pstmtInsert.setInt(2, cambio.getIdJugueteOriginal());
				pstmtInsert.setInt(3, cambio.getIdJugueteNuevo());
				pstmtInsert.setString(4, cambio.getMotivo());
				pstmtInsert.setDate(5, cambio.getFecha());
				pstmtInsert.setInt(6, cambio.getStandOrigen());
				pstmtInsert.setInt(7, cambio.getIdZonaOrigen());
				pstmtInsert.setInt(8, cambio.getStandDestino());
				pstmtInsert.setInt(9, cambio.getIdZonaDestino());
				pstmtInsert.executeUpdate();
			}

			conn.commit(); // Confirmamos todo si no hubo errores
			return true;

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
			System.err.println("Error al procesar el cambio de producto: " + e.getMessage());
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
