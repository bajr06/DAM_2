package Gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BBDD.Conexion;

public class GestorJugueteria {
	public void gestionarJuguete(String accion, String nombre, double precio, int id) {
		String sql = switch (accion) {
			case "INSERTAR" -> "insert into Juguete (Nombre, Precio, Cantidad_Stock) values (?, ?, 0)";
			case "MODIFICAR" -> "update Juguete set Nombre = ?, Precio = ? where ID_Juguete = ?";
			case "ELIMINAR" -> "delete from Juguete where ID_Juguete = ?";
			default -> "";
		};

		try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
			if (accion.equals("ELIMINAR")) {
				ps.setInt(1, id);
			} else {
				ps.setString(1, nombre);
				ps.setDouble(2, precio);
				
				if (accion.equals("MODIFICAR")) {
					ps.setInt(3, id);
				}
			}
			
			ps.executeUpdate();

			System.out.println("Operación " + accion + " realizada.");
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error en la consulta: " + sqlcnfe.getMessage() + "\n.Cerrando programa.");
			System.exit(0);
		}
	}

	public void registrarVenta(int idEmpleado, int idJuguete, int idStand, int idZona, double monto, String pago) {
		String consulta = "select Cantidad from Stock where Juguete_ID_Juguete=? and Stand_ID_Stand=? and Stand_Zona_ID_Zona=?";
		String actualizacion = "update Stock set Cantidad = Cantidad - 1 where Juguete_ID_Juguete=? and Stand_ID_Stand=?";
		String inserccion = "insert into Venta (Fecha, Monto, Tipo_Pago, Empleado_ID_Empleado, Stock_Stand_ID_Stand, Stock_Stand_Zona_ID_Zona, Stock_Juguete_ID_Juguete) values (curdate(), ?, ?, ?, ?, ?, ?)";

		try {
			Connection conexion = Conexion.getConnection();
			conexion.setAutoCommit(false);

			PreparedStatement ps1 = conexion.prepareStatement(consulta);
			ps1.setInt(1, idJuguete);
			ps1.setInt(2, idStand);
			ps1.setInt(3, idZona);
			
			ResultSet rs = ps1.executeQuery();
			
			if (rs.next() && rs.getInt("Cantidad") > 0) {
				PreparedStatement ps2 = conexion.prepareStatement(actualizacion);
				ps2.setInt(1, idJuguete);
				ps2.setInt(2, idStand);
				ps2.executeUpdate();

				PreparedStatement ps3 = conexion.prepareStatement(inserccion);
				ps3.setDouble(1, monto);
				ps3.setString(2, pago);
				ps3.setInt(3, idEmpleado);
				ps3.setInt(4, idStand);
				ps3.setInt(5, idZona);
				ps3.setInt(6, idJuguete);
				ps3.executeUpdate();
					
				conexion.commit();
				
				System.out.println("Venta exitosa.");
			} else {
				System.out.println("Sin stock suficiente.");
				
				conexion.rollback();
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error en la realización de la venta: " + sqlcnfe.getMessage() + "\n.Cerrando programa.");
		}
	}

	public void realizarCambio(int idEmpleado, int idJuguete, int idStandOriginal, int idZonaOriginal, int idStandNuevo, int idZonaNueva, String motivo) {
		String sql1 = "update Stock set Cantidad = Cantidad - 1 " +
						"where Juguete_ID_Juguete=? and Stand_ID_Stand=? and Stand_Zona_ID_Zona=? and Cantidad > 0";
		
		String sql2 = "insert into Stock (Stand_ID_Stand, Stand_Zona_ID_Zona, Juguete_ID_Juguete, Cantidad) " +
						"values (?, ?, ?, 1) on duplicate key update Cantidad = Cantidad + 1";
		
		String sql3 = "insert into Cambio (Motivo, Fecha, Stock_Stand_ID_Stand_Original, Stock_Stand_Zona_ID_Zona_Original, " +
						"Stock_Juguete_ID_Juguete_Original, Stock_Stand_ID_Stand_Nuevo, Stock_Stand_Zona_ID_Zona_Nuevo, " +
						"Stock_Juguete_ID_Juguete_Nuevo, Empleado_ID_Empleado) values (?, curdate(), ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conexion = Conexion.getConnection();
			conexion.setAutoCommit(false);

			PreparedStatement ps1 = conexion.prepareStatement(sql1);
			ps1.setInt(1, idJuguete);
			ps1.setInt(2, idStandOriginal);
			ps1.setInt(3, idZonaOriginal);
			
			int filas = ps1.executeUpdate();
			if (filas == 0) {
				System.out.println("No hay stock disponible en el stand de origen.");
				return;
			}

			PreparedStatement ps2 = conexion.prepareStatement(sql2);
			ps2.setInt(1, idStandNuevo);
			ps2.setInt(2, idZonaNueva);
			ps2.setInt(3, idJuguete);
			ps2.executeUpdate();

			PreparedStatement ps3 = conexion.prepareStatement(sql3);
			ps3.setString(1, motivo);
			ps3.setInt(2, idStandOriginal);
			ps3.setInt(3, idZonaOriginal);
			ps3.setInt(4, idJuguete);
			ps3.setInt(5, idStandNuevo);
			ps3.setInt(6, idZonaNueva);
			ps3.setInt(7, idJuguete);
			ps3.setInt(8, idEmpleado);
			ps3.executeUpdate();

			conexion.commit();
			
			System.out.println("Cambio de stand registrado correctamente.");
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error de conexión o durante la transacción: " + sqlcnfe.getMessage() + "\nCerrando programa");
			System.exit(1);
		}
	}

	public void reporteTopVentas() {
		String sql = "select j.Nombre, count(v.ID_Venta) as Total from Venta v join Juguete j " +
						"on v.Stock_Juguete_ID_Juguete = j.ID_Juguete group by j.ID_Juguete order by Total desc limit 5";
		
		imprimirQuery(sql, "TOP 5 PRODUCTOS");
	}

	private void imprimirQuery(String sql, String titulo) {
		try {
			Connection conexion = Conexion.getConnection();
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			System.out.println("\n--- " + titulo + " ---");

			while (rs.next()) {
				System.out.println(rs.getString(1) + " | " + rs.getString(2));
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error a la hora de imprimir la consulta: " + sqlcnfe.getMessage() + "\n.Cerrando programa.");
			System.exit(1);
		}
	}

	public void gestionarEmpleado(String accion, int id, String nombre, String cargo, String fechaIngreso) {
		String sql = switch (accion.toUpperCase()) {
			case "INSERTAR" -> "INSERT INTO Empleado (Nombre, Cargo, Fecha_Ingreso) VALUES (?, ?, ?)";
			case "MODIFICAR" -> "UPDATE Empleado SET Nombre = ?, Cargo = ?, Fecha_Ingreso = ? WHERE ID_Empleado = ?";
			case "ELIMINAR" -> "DELETE from Empleado WHERE ID_Empleado = ?";
			default -> "";
		};

		try {
			Connection conexion = Conexion.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);
			
			if (accion.equalsIgnoreCase("ELIMINAR")) {
				ps.setInt(1, id);
			} else {
				ps.setString(1, nombre);
				ps.setString(2, cargo);
				ps.setString(3, fechaIngreso);
				
				if (accion.equalsIgnoreCase("MODIFICAR")) {
					ps.setInt(4, id);
				}
			}
			
			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("Operación " + accion + " en Empleado realizada con éxito.");
			} else {
				System.out.println("No se encontró el empleado con ID: " + id);
			}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error en la gestión de empleados: " + sqlcnfe.getMessage() + ".\nCerrando programa.");
			System.exit(0);
		}
	}

	public void realizarDevolucion(int idVenta) {
		String seleccion = "select Stock_Juguete_ID_Juguete, Stock_Stand_ID_Stand, Stock_Stand_Zona_ID_Zona from Venta where ID_Venta = ?";
		String actualizacionStock = "update Stock set Cantidad = Cantidad + 1 where Juguete_ID_Juguete = ? and Stand_ID_Stand = ? and Stand_Zona_ID_Zona = ?";
		String borrado = "delete from Venta where ID_Venta = ?";

		try {
			Connection conexion = Conexion.getConnection();
			conexion.setAutoCommit(false);
			
			PreparedStatement psSel = conexion.prepareStatement(seleccion);
			psSel.setInt(1, idVenta);
			
			ResultSet rs = psSel.executeQuery();
			if (rs.next()) {
				int idJug = rs.getInt(1);
				int idSta = rs.getInt(2);
				int idZon = rs.getInt(3);

				PreparedStatement psUpd = conexion.prepareStatement(actualizacionStock);
				psUpd.setInt(1, idJug);
				psUpd.setInt(2, idSta);
				psUpd.setInt(3, idZon);

				psUpd.executeUpdate();

				PreparedStatement psDel = conexion.prepareStatement(borrado);
				psDel.setInt(1, idVenta);
				
				psDel.executeUpdate();
				conexion.commit();
				
				System.out.println("Devolución procesada.\nStock actualizado.");
				} else {
					System.out.println("Venta no encontrada.");
				}

		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error en devolución: " + sqlcnfe.getMessage() + ".\nCerrando programa.");
			System.exit(1);
		}
	}

	public void reporteVentasMes(int mes) {
		String sql = "select ID_Venta, Fecha, Monto, Tipo_Pago from Venta where month(Fecha) = ?";
		imprimirQueryConParametro(sql, "Ventas del mes " + mes, mes);
	}

	public void reporteVentasEmpleadoMes(int idEmpleado, int mes) {
		String sql = "select ID_Venta, Fecha, Monto from Venta where Empleado_ID_Empleado = ? and month(Fecha) = ?";
		try {
			Connection conexion = Conexion.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idEmpleado);
			ps.setInt(2, mes);
			
			ResultSet rs = ps.executeQuery();

			System.out.println("\n--- VENTAS EMPLEADO " + idEmpleado + " EN MES " + mes + " ---");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + " | Fecha: " + rs.getDate(2) + " | Monto: " + rs.getFloat(3));
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error en reporte: " + sqlcnfe.getMessage() + ".\nCerrando programa.");
		}
	}

	private void imprimirQueryConParametro(String sql, String titulo, int parametro) {
		try {
			Connection conexion = Conexion.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, parametro);

			ResultSet rs = ps.executeQuery();

			System.out.println("\n--- " + titulo + " ---");

			while (rs.next()) {
				System.out.println("Venta ID: " + rs.getInt(1) + " | Monto: " + rs.getFloat(3));
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error: " + sqlcnfe.getMessage());
		}
	}

	public void reporteMejoresEmpleados() {
		String sql = "select e.Nombre, count(v.ID_Venta) as Total_Ventas " +
					"from Venta v join Empleado e on v.Empleado_ID_Empleado = e.ID_Empleado " +
					"GROUP BY e.ID_Empleado order by Total_Ventas desc";
		
		imprimirQuery(sql, "RANKING DE VENDEDORES");
	}

	public void obtenerDatosTienda() {
		String sql = "select " +
					"(select count(*) from Juguete) as Tipos_Juguetes, " +
					"(select SUM(Cantidad) from Stock) as Stock_Total, " +
					"(select SUM(Monto) from Venta) as Facturacion_Total";
		
		try {
			Connection conexion = Conexion.getConnection();
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				System.out.println("\n--- RESUMEN GENERAL DE LA TIENDA ---");
				System.out.println("Variedad de productos: " + rs.getInt(1));
				System.out.println("Unidades totales en stands: " + rs.getInt(2));
				System.out.println("Ingresos totales: " + rs.getFloat(3) + "€");
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error al obtener datos: " + sqlcnfe.getMessage() + ".\nCerrando programa.");
			System.exit(1);
		}
	}

	public void reporteHistorialCambios() {
		String sql = "select c.Fecha, e.Nombre, j.Nombre, c.Motivo " +
					"from Cambio c " +
					"join Empleado e ON c.Empleado_ID_Empleado = e.ID_Empleado " +
					"join Juguete j ON c.Stock_Juguete_ID_Juguete_Original = j.ID_Juguete";
		
		try  {
			Connection conexion = Conexion.getConnection();
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			System.out.println("\n--- HISTORIAL DE MOVIMIENTOS Y CAMBIOS ---");
			System.out.printf("%-12s | %-15s | %-15s | %-20s\n", "FECHA", "EMPLEADO", "JUGUETE", "MOTIVO");

			while (rs.next()) {
				System.out.printf("%-12s | %-15s | %-15s | %-20s\n", 
					rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error en reporte de cambios: " + sqlcnfe.getMessage() + ".\nCerrando programa.");
			System.exit(1);
		}
	}

	public void mostrarStockPorStand(int idStand, int idZona) {
		String sql = "select j.Nombre, s.Cantidad from Stock s " +
					 "join Juguete j on s.Juguete_ID_Juguete = j.ID_Juguete " +
					 "where s.Stand_ID_Stand = ? and s.Stand_Zona_ID_Zona = ?";
		try {
			Connection conexion = Conexion.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idStand);
			ps.setInt(2, idZona);

			ResultSet rs = ps.executeQuery();

			System.out.println("\n--- STOCK DEL STAND " + idStand + " (ZONA " + idZona + ") ---");

			boolean hayDatos = false;
			while (rs.next()) {
				System.out.println("Producto: " + rs.getString(1) + " | Unidades: " + rs.getInt(2));
				hayDatos = true;
			}

			if (!hayDatos) {
				System.out.println("No hay productos registrados en este stand.");
			}
		} catch (SQLException | ClassNotFoundException sqlcnfe) {
			System.err.println("Error al consultar stock por stand: " + sqlcnfe.getMessage() + ".\nCerrando programa.");
			System.exit(1);
		}
	}

	public void listarJuguetesPorPrecio() {
		String sql = "select Nombre, Precio, Cantidad_Stock from Juguete order by Precio desc";
		imprimirQuery(sql, "PRODUCTOS POR PRECIO (MAYOR A MENOR)");
	}

	public void actualizarTotalJuguete(int idJuguete) {
		String sql = "update Juguete set Cantidad_Stock = (select sum(Cantidad) from Stock where Juguete_ID_Juguete = ?) where ID_Juguete = ?";
		try {
			Connection conexion = Conexion.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idJuguete);
			ps.setInt(2, idJuguete);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Error al sincronizar stock global." + e.getMessage() + ".\nCerrando programa.");
			System.exit(1);
		}
	}
}