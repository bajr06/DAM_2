package Tema2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Juguete {
	private static String [] columnasJ = {"Nombre", "Descripcion", "Precio", "Cantidad_Stock"};

	public static List<Object> nuevoJ() {
		List<Object> datos = new ArrayList<>();
		Principal .sc.nextLine();
		
		IO.print("Introduzca el nombre: ");
		datos.add(Principal.sc.nextLine());

		IO.print("Introduzca una descripción: ");
		datos.add(Principal.sc.nextLine());

		IO.print("Introduzca el precio: ");
		datos.add(Principal.sc.nextFloat());

		IO.print("Introduzca la cantidad: ");
		datos.add(Principal.sc.nextInt());

		return datos;
	}

	public static void nuevoJuguete(Connection conexion) {
		List<Object> datos = nuevoJ();

		try {
			String nuevoJuguete = "insert into Juguete (Nombre, Descripcion, Precio, Cantidad_Stock) values (?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(nuevoJuguete, Statement.RETURN_GENERATED_KEYS);

			ps.setObject(1, datos.get(0));
			ps.setObject(2, datos.get(1));
			ps.setObject(3, datos.get(2));
			ps.setObject(4, datos.get(3));

			int rs = ps.executeUpdate();

			if(rs == 0) {
				System.err.println("Ha ocurrido un problema grave.");
				System.exit(rs);
			} else {
				System.out.println("Se ha añadido correctamente el jueguete");
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static List<Object> modificacionJ(int tipoCambio) {
		List<Object> respuesta = new ArrayList<>();

		System.out.println("Introduzca el ID del juguete: ");
		respuesta.add(Principal.sc.nextInt());

		System.out.println("Introduce el nuevo dato: ");
		Principal.sc.nextLine();

		if(tipoCambio == 0 || tipoCambio == 1) {
			respuesta.add(Principal.sc.nextLine());
		} else if(tipoCambio == 2) {
			respuesta.add(Principal.sc.nextFloat());
		} else if(tipoCambio == 3) {
			respuesta.add(Principal.sc.nextInt());
		} else {
			System.err.println("No existe la opción que has escogido, ¡Hasta la próxima!");
			System.exit(tipoCambio);
		}

		return respuesta;
	}

	public static void modificarJuguete(Connection conexion) {
		int seleccion = Opciones.modificarJuguete();
		List<Object> cambios = modificacionJ(seleccion);

		String modificarJuguete = "update Juguete set " + columnasJ[seleccion] + " = \"" + cambios.get(1) + "\" where ID_Juguete = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(modificarJuguete);

			ps.setObject(1, cambios.get(0));

			int rs = ps.executeUpdate();
			
			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema grave.");
				System.exit(rs);
			} else {
				System.out.println("Se ha modificado correctamente el juguete");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void eliminarJuguete(Connection conexion) {
		String eliminarJuguete = "delete from Juguete where ID_Juguete = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(eliminarJuguete);

			System.out.println("Introduzca el ID del juguete a borrar: ");
			ps.setInt(1, Principal.sc.nextInt());

			int rs = ps.executeUpdate();

			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema grave.");
				System.exit(rs);
			} else {
				System.out.println("Se ha eliminado correctamente el juguete");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static double obtenerPrecio(Connection conexion,  int id_Juguete) throws SQLException{
		String obtenerPrecio = "select Precio from Juguete where ID_Juguete = ?";
		
		PreparedStatement ps = conexion.prepareStatement(obtenerPrecio);
		ps.setInt(1, id_Juguete);

		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			return rs.getDouble("Precio");
		} else {
			return 0.0;
		}
	}
}
