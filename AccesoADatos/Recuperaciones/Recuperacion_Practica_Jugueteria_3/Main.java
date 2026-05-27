import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import conexion.ConexionBD;
import conexion.InicializadorDB;
import objetos.Empleado;
import objetos.Juguete;
import objetos.Venta;
import operaciones.Operaciones_Consultas;
import operaciones.Operaciones_Empleados;
import operaciones.Operaciones_Juguetes;
import operaciones.Operaciones_Ventas;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Operaciones_Juguetes jugueteDAO = new Operaciones_Juguetes();
	private static final Operaciones_Empleados empleadoDAO = new Operaciones_Empleados();
	private static final Operaciones_Ventas ventaDAO = new Operaciones_Ventas();
	private static final Operaciones_Consultas consultasDAO = new Operaciones_Consultas();

	public static void main(String[] args) {
		System.out.println("Iniciando Sistema de Gestión de Juguetería...");
		
		// Verificación y carga de datos iniciales si no existen
		InicializadorDB.verificarYCargarDatos();

		boolean salir = false;
		while (!salir) {
			mostrarMenuPrincipal();

			try {
				int opcion = leerEntero("Selecciona una opción: ");

				switch (opcion) {
					case 1:
						menuJuguetes();
						break;
					case 2:
						menuEmpleados();
						break;
					case 3:
						menuVentas();
						break;
					case 4:
						menuConsultas();
						break;
					case 0:
						salir = true;
						System.out.println("Cerrando el sistema... ¡Hasta pronto!");
						ConexionBD.cerrarConexion();
						break;
					default:
						System.out.println("Opción no válida. Inténtalo de nuevo.");
				}
			} catch(NoSuchElementException nsee) {
				System.out.println("\nHa ocurrido un error inesperado.\nCerrando programa.");
				System.exit(1);
			}
		}
	}

	private static void mostrarMenuPrincipal() {
		System.out.println("\n========================================");
		System.out.println("        JUGUETERÍA - MENÚ PRINCIPAL       ");
		System.out.println("========================================");
		System.out.println("1. Gestión de Juguetes");
		System.out.println("2. Gestión de Empleados");
		System.out.println("3. Gestión de Ventas y Devoluciones");
		System.out.println("4. Obtener Datos de la Tienda (Consultas)");
		System.out.println("0. Salir");
		System.out.println("========================================");
	}

	// --- MENÚ JUGUETES ---
	private static void menuJuguetes() {
		System.out.println("\n--- GESTIÓN DE JUGUETES ---");
		System.out.println("1. Registrar un nuevo juguete");
		System.out.println("2. Modificar datos de un juguete");
		System.out.println("3. Eliminar juguete");
		int opc = leerEntero("Opción: ");

		if (opc == 1) {
			Juguete j = new Juguete();

			System.out.print("Nombre: "); j.setNombre(scanner.nextLine());
			System.out.print("Descripción: "); j.setDescripcion(scanner.nextLine());

			j.setPrecio(leerDouble("Precio: "));
			j.setCantidadEnStock(leerEntero("Cantidad en stock inicial: "));

			System.out.print("Categoría: "); j.setCategoria(scanner.nextLine());
			
			if(jugueteDAO.registrarJuguete(j)) System.out.println("Juguete registrado.");
		} else if (opc == 3) {
			int id = leerEntero("ID del juguete a eliminar: ");
			if(jugueteDAO.eliminarJuguete(id)) System.out.println("Juguete eliminado.");
		} else {
			System.out.println("Opción en desarrollo/modificación directa.");
			// Aquí puedes expandir la lógica para modificar instanciando un juguete con ID
		}
	}

	// --- MENÚ EMPLEADOS ---
	private static void menuEmpleados() {
		System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
		System.out.println("1. Registrar nuevo empleado");
		System.out.println("2. Eliminar empleado");
		int opc = leerEntero("Opción: ");

		if (opc == 1) {
			Empleado e = new Empleado();

			System.out.print("Nombre: "); e.setNombre(scanner.nextLine());
			System.out.print("Cargo: "); e.setCargo(scanner.nextLine());

			e.setFechaIngreso(new Date(System.currentTimeMillis())); // Fecha actual
			
			if(empleadoDAO.registrarEmpleado(e)) System.out.println("Empleado registrado.");
		} else if (opc == 2) {
			int id = leerEntero("ID del empleado a eliminar: ");
			if(empleadoDAO.eliminarEmpleado(id)) System.out.println("Empleado eliminado.");
		}
	}

	// --- MENÚ VENTAS ---
	private static void menuVentas() {
		System.out.println("\n--- VENTAS Y DEVOLUCIONES ---");
		System.out.println("1. Realizar una venta");
		System.out.println("2. Realizar una devolución");
		int opc = leerEntero("Opción: ");

		if (opc == 1) {
			Venta v = new Venta();

			v.setIdEmpleado(leerEntero("ID Empleado: "));
			v.setIdJuguete(leerEntero("ID Juguete: "));
			v.setIdStand(leerEntero("ID Stand: "));
			v.setIdZona(leerEntero("ID Zona: "));
			v.setMonto(leerDouble("Precio final (Monto): "));

			System.out.print("Tipo de pago (Efectivo, Tarjeta, etc.): ");

			v.setTipoPago(scanner.nextLine());
			v.setFecha(new Date(System.currentTimeMillis()));
			
			if(ventaDAO.realizarVenta(v)) System.out.println("Venta registrada con éxito.");
		} else if (opc == 2) {
			int idVenta = leerEntero("ID de la venta a devolver: ");
			if(ventaDAO.realizarDevolucion(idVenta)) System.out.println("Devolución procesada y stock restaurado.");
		}
	}

	// --- MENÚ CONSULTAS ---
	private static void menuConsultas() {
		System.out.println("\n--- OBTENER DATOS DE LA TIENDA ---");
		System.out.println("1. Producto más vendido (Top 5)");
		System.out.println("2. Empleados que más venden");
		System.out.println("3. Juguetes disponibles en un stand específico");
		System.out.println("4. Ventas realizadas en un mes");
		System.out.println("5. Ventas por empleado en un mes");
		System.out.println("6. Historial de cambios de los empleados");
		System.out.println("7. Lista de productos ordenados por precio");
		int opc = leerEntero("Opción: ");

		switch (opc) {
			case 1: consultasDAO.mostrarTop5JuguetesMasVendidos(); break;
			case 2: consultasDAO.mostrarEmpleadosMasVendedores(); break;
			case 3: 
				int stand = leerEntero("ID Stand: ");
				int zona = leerEntero("ID Zona: ");
				consultasDAO.mostrarJuguetesPorStand(stand, zona); 
				break;
			case 4:
				int mes = leerEntero("Mes (1-12): ");
				int anio = leerEntero("Año (ej. 2024): ");
				consultasDAO.mostrarVentasPorMes(mes, anio);
				break;
			case 5:
				int idEmp = leerEntero("ID Empleado: ");
				int m = leerEntero("Mes (1-12): ");
				int a = leerEntero("Año: ");
				consultasDAO.mostrarVentasEmpleadoPorMes(idEmp, m, a);
				break;
			case 6: consultasDAO.mostrarHistorialCambios(); break;
			case 7: consultasDAO.mostrarProductosOrdenadosPorPrecio(); break;
			default: System.out.println("Opción no válida.");
		}
	}

	// Métodos auxiliares para leer datos sin que el Scanner salte líneas incorrectamente
	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);

		while (!scanner.hasNextInt()) {
			System.out.print("Por favor, introduce un número válido: ");
			scanner.next();
		}
		int numero = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer
		
		return numero;
	}

	private static double leerDouble(String mensaje) {
		System.out.print(mensaje);

		while (!scanner.hasNextDouble()) {
			System.out.print("Por favor, introduce un número decimal válido (usa coma): ");
			scanner.next();
		}

		double numero = scanner.nextDouble();
		scanner.nextLine(); // Limpiar el buffer

		return numero;
	}
}
