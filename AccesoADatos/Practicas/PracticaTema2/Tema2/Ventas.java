package Tema2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Ventas {
	private static List<Object> peticionVenta() {
		List<Object> datos = new ArrayList<>();
		
		IO.print("Introduzca el ID del empleado: ");
		datos.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID del juguete: ");
		datos.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID del stand: ");
		datos.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID de la zona: ");
		datos.add(Principal.sc.nextInt());

		IO.print("Introduzca el método de pago: ");
		datos.add(Principal.sc.nextLine());

		IO.print("Introduzca la cantidad a vender: ");
		datos.add(Principal.sc.nextInt());

		return datos;
	}

	private static void verficarStock(Connection conexion) {
		List<Object> datos = peticionVenta();

		try {
			String verificarStock = "select Cantidad from Stock where Stand_ID_Stand = ? and Stand_Zona_ID_Zona = ? and Juguete_ID_Juguete = ?;";
			
			PreparedStatement ps = conexion.prepareStatement(verificarStock);
			ps.setObject(1, datos.get(2));
			ps.setObject(2, datos.get(3));
			ps.setObject(1, datos.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if(rs.getInt("Cantidad") < (int) datos.get(5)) {
					System.err.println("No hay stock para realizar la venta");
					System.exit(0);;
				}
			} else {
				System.err.println("No existe ese juguete en el stand al indicado");
				System.exit(0);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void actualizarStock(Connection conexion, List<Object> datos) throws SQLException {
		String actualizarStock = "update Stock set Cantidad - ? where Stand_ID_Stand = ? and Stand_Zona_ID_Zona = ? and Juguete_ID_Juguete = ?;";

		PreparedStatement ps = conexion.prepareStatement(actualizarStock);
		ps.setObject(1, datos.get(5));
		ps.setObject(2, datos.get(2));
		ps.setObject(3, datos.get(3));
		ps.setObject(4, datos.get(1));
	}

    public static void realizarVenta(Connection conexion) {
		List<Object> datos = peticionVenta();
		verficarStock(conexion);

		try {
			String realizarVenta = "insert into Venta(Fecha, Monto, Tipo_Pago, Empleado_ID_Empleado, Stock_Stand_ID_Stand, Stock_Stand_Zona_ID_Zona, Stock_Juguete_ID_Juguete) values(curdate(), ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conexion.prepareStatement(realizarVenta, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, Juguete.obtenerPrecio(conexion, (int) datos.get(1)));
			ps.setObject(2, datos.get(4));
			ps.setObject(3, datos.get(0));
			ps.setObject(4, datos.get(2));
			ps.setObject(5, datos.get(3));
			ps.setObject(6, datos.get(1));

			int rs = ps.executeUpdate();

			if(rs <= 0) {
				System.err.println("Error en la venta. Cerrando programa");
				System.exit(rs);
			} else {
				System.out.println("Venta realizada correctamente.");
				actualizarStock(conexion, datos);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static List<Object> peticionDevolucion() {
		List<Object> datos1 = new ArrayList<>();

		IO.print("Introduzca el ID del Empleado: ");
		datos1.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID del juguete que había originalmente: ");
		datos1.add(Principal.sc.nextInt());
		
		IO.print("Introduzca el ID del Stand de origen: ");
		datos1.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID de la Zona de origen: ");
		datos1.add(Principal.sc.nextInt());

		return datos1;
	}

	private static List<Object> peticionCambio() {
		List<Object> datos2 = peticionDevolucion();

		IO.print("Introduzca el ID del nuevo juguete: ");
		datos2.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID del Stand de destino: ");
		datos2.add(Principal.sc.nextInt());

		IO.print("Introduzca el ID del la Zona de destino: ");
		datos2.add(Principal.sc.nextInt());

		return datos2;
	}

	private static void actualizarStockOrigen(Connection conexion, List<Object> datos) {
		try {
			String actualizarStock = "update Stock set Cantidad = Cantidad + 1 where Stand_ID_Stand = ? and Stand_Zona_ID_Zona = ? and Juguete_ID_Juguete = ?;";
			PreparedStatement ps = conexion.prepareStatement(actualizarStock);

			ps.setObject(1, datos.get(2));
			ps.setObject(2, datos.get(3));
			ps.setObject(3, datos.get(1));
			
			int rs = ps.executeUpdate();

			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema. Cerrando programa");
				System.exit(rs);
			} else {
				actualizarStock(conexion, datos);
				System.out.println("Actualización de Stock realizadas correctamente");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}


	private static void actualizarStockDestino(Connection conexion, List<Object> datos, int seleccion) {
		try {
			String actualizarStock = "update Stock set Cantidad = Cantidad - 1 where Stand_ID_Stand = ? and Stand_Zona_ID_Zona = ? and Juguete_ID_Juguete = ?;";
			PreparedStatement ps = conexion.prepareStatement(actualizarStock);

			ps.setObject(5, datos.get(5));
			ps.setObject(6, datos.get(6));
			ps.setObject(7, datos.get(4));

			int rs = ps.executeUpdate();
			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema. Cerrando programa");
				System.exit(rs);
			} else {
				System.out.println("Actualización de Stock realizadas correctamente");
			}
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
	}


	public static void realizarDevolucion(Connection conexion) {
		System.out.println("¿Qué deseas hacer?");
		IO.println("1. Cambio");
		IO.println("2. Devolucion");

		int seleccion = Principal.sc.nextInt();
		List<Object> datos = new ArrayList<>();

		if(seleccion == 1) {
			datos = peticionCambio();
		} else if(seleccion == 2) {
			datos = peticionDevolucion();
		} else {
			System.err.println("Error en la petición o selección errónea, cerrando programa");
			System.exit(seleccion);
		}

		IO.print("Indique el motivo de la devolución: ");
		String motivo = Principal.sc.nextLine();

		try {
			String realizarDevolucion = "insert into Cambio (Motivo, Fecha, Stock_Stand_ID_Stand_Original, Stock_Stand_Zona_ID_Zona_Original, Stock_Juguete_ID_Juguete_Original, Stock_Stand_ID_Stand_Nuevo, Stock_Stand_Zona_ID_Zona_Nuevo, Stock_Juguete_ID_Juguete_Nuevo, Empleado_ID_Empleado) values(?, curdate(), ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(realizarDevolucion, Statement.RETURN_GENERATED_KEYS);

			ps.setObject(1, motivo);
			ps.setObject(2, datos.get(2)); // Stock_Stand_ID_Stand_Original
			ps.setObject(3, datos.get(3)); // Stock_Stand_Zona_ID_Zona_Original
			ps.setObject(4, datos.get(1)); // Stock_Juguete_ID_Juguete_Original
			if(seleccion == 1) {
				ps.setObject(5, datos.get(5)); // Stock_Stand_ID_Stand_Nuevo
				ps.setObject(6, datos.get(6)); // Stock_Stand_Zona_ID_Zona_Nuevo
				ps.setObject(7, datos.get(4)); // Stock_Juguete_ID_Juguete_Nuevo
			} else if(seleccion == 2) {
				ps.setObject(5, datos.get(2)); // Stock_Stand_ID_Stand_Nuevo
				ps.setObject(6, datos.get(3)); // Stock_Stand_Zona_ID_Zona_Nuevo
				ps.setObject(7, datos.get(1)); // Stock_Juguete_ID_Juguete_Nuevo
			}
			ps.setObject(8, datos.get(0)); // Empleado_ID_Empleado

			int rs = ps.executeUpdate();

			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema. Cerrando programa");
				System.exit(rs);
			} else {
				System.out.println("Cambios/Devolución realizadas correctamente");
				actualizarStockOrigen(conexion, datos);
				actualizarStockDestino(conexion, datos, seleccion);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void masVendidos(Connection conexion) {
		String masVendidos = "select T1.Nombre, T2.total_vendido from (select ID_Juguete, Nombre from Juguete) T1 inner join (select Stock_Juguete_ID_Juguete as ID_J, count(*) as Total_Vendido from Venta group by Stock_Juguete_ID_Juguete) T2 on T1.ID_Juguete = T2.ID_J order by T2.Total_Vendido desc limit 5;";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(masVendidos);
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Top 5 productos más vendidos:");
			while (rs.next()) {
				System.out.println(rs.getString("Nombre") + " - Vendidos: " + rs.getInt("Total_Vendido"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}


	public static void masVentas(Connection conexion) {
		String masVentas = "select T1.Nombre, T2.ventas_realizadas from (SELECT ID_Empleado, Nombre from Empleado) T1 inner join (select Empleado_ID_Empleado as ID_E, count(*) as ventas_realizadas from Venta group by Empleado_ID_Empleado) T2 on T1.ID_Empleado = T2.ID_E order by T2.ventas_realizadas desc;";
        
		try {
            PreparedStatement ps = conexion.prepareStatement(masVentas);
            
			ResultSet rs = ps.executeQuery();
            
			System.out.println("Empleados que más venden:");
            
			while (rs.next()) {
                System.out.println(rs.getString("Nombre") + " - Ventas: " + rs.getInt("Ventas_Realizadas"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
