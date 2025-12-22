package Tema2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Empleado {
	private static String [] columnasE = {"Nombre", "Cargo", "Fecha_Ingreso"}; 

	public static List<Object> nuevoE() {
		List<Object> datos = new ArrayList<>();
		Principal .sc.nextLine();
		
		IO.print("Introduzca el nombre: ");
		datos.add(Principal.sc.nextLine());

		IO.print("Introduzca el cargo (\'Jefe\' o \'Cajero\'): ");
		datos.add(Principal.sc.nextLine());

		IO.print("Introduzca la fecha: ");
		datos.add(Principal.sc.nextLine());
		
		return datos;
	}

	public static void nuevoEmpleado(Connection conexion) {
		List<Object> datos = nuevoE();

		try {
			String nuevoEmpleado = "insert into Empleado(Nombre, Cargo, Fecha_Ingreso) values(?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(nuevoEmpleado, Statement.RETURN_GENERATED_KEYS);

			ps.setObject(1, datos.get(0));
			ps.setObject(2, datos.get(1));
			ps.setObject(3, datos.get(2));

			int rs = ps.executeUpdate();

			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema a la hora de añadir el empleado, programa cerrado");
				System.exit(rs);
			} else {
				System.out.println("Se ha creado el Empleado correctamente.");
			}
		} catch (SQLException slqe) {
			slqe.printStackTrace();
		}
	}

	public static List<Object> modificacionE(int tipoCambio) {
		List<Object> cambios = new ArrayList<>();

		IO.print("Introduzca el identificador del empleado: ");
		cambios.add(Principal.sc.nextInt());

		IO.print("Introduzca el cambio: ");
		Principal.sc.nextLine();

		if(tipoCambio >= 0 || tipoCambio <= 2) {
			cambios.add(Principal.sc.nextLine());
		} else {
			System.err.println("No existe la opción que has escogido, ¡Hasta la próxima!");
			System.exit(tipoCambio);
		}

		return cambios;
	}

	public static void modificarEmpleado(Connection conexion) {
		int seleccion = Opciones.modificarEmpleado();
		List<Object> cambios = modificacionE(0);

		try {
			String modificarEmpleado = "update Empleado set " + columnasE[seleccion] + " = \"" + cambios.get(1) + "\" where ID_Empleado = ?";

			PreparedStatement ps = conexion.prepareStatement(modificarEmpleado);

			ps.setObject(1, cambios.get(0));

			int rs = ps.executeUpdate();

			if(rs <= 0) {
				System.err.println("Ha ocurrido un problema grave.");
				System.exit(rs);
			} else {
				System.out.println("Se ha modificado correctamente el empleado");
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void eliminarEmpleado(Connection conexion) {
		try {
			String eliminarEmpleado = "delete from Empleado where ID_Empleado = ?";
			PreparedStatement ps = conexion.prepareStatement(eliminarEmpleado);

			System.out.println("Introduzca el ID del Empleado a borrar: ");
			ps.setInt(1, Principal.sc.nextInt());

			int rs = ps.executeUpdate();

			if(rs == 0) {
				System.err.println("Ha ocurrido un problema grave.");
				System.exit(rs);
			} else {
				System.out.println("Se ha eliminado correctamente el empleado");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
