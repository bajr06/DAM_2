package BBDD;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Gestion.GeneradorDatos;
import Gestion.GestorJugueteria;

public class Principal {
	static Scanner s = new Scanner(System.in);
	
	private static void seleccionOpcion() {
		IO.println("Seleccione la opción que desee ejecutar: ");
		IO.println("1. Gestionar Juguetes");
		IO.println("2. Gestionar Empleados");
		IO.println("3. Realizar Venta");
		IO.println("4. Realizar Cambio de Stand");
		IO.println("5. Reportes Top Ventas");
		IO.println("6. Realizar Devolución");
		IO.println("7. Reporte Ventas por Mes");
		IO.println("8. Reporte Ventas Empleado/Mes");
		IO.println("9. Ranking Mejores Empleados");
		IO.println("10. Ver Datos de la Tienda");
		IO.println("11. Ver Historial de Cambios");
		IO.println("11. Ver Historial de Cambios");
		IO.println("12. Ver Stock de un Stand Específico");
		IO.println("13. Lista de Juguetes por Precio");
		IO.println("0. Salir");
	}

	private static void seleccionEmpleado() {
		IO.println("Seleccione la accion a realizar: ");
		IO.println("1. Registrar Empleado");
		IO.println("2. Modificar Empleado");
		IO.println("3. Eliminar Empleado");
	}

	public static void main(String[] args) {
		GestorJugueteria gestor = new GestorJugueteria();

		try {
			GeneradorDatos.inicializarDatos(Conexion.getConnection());
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("No se pudo conectar a la base de datos.\nCerrando programa.");
			System.exit(1);
		}

		int opcion = -1;
		
		IO.println("Bienvenido a la juguetería C+#");
		while (opcion != 0) {
			try {
				seleccionOpcion();
				opcion = s.nextInt();

				switch (opcion) {
					case 1:
						IO.print("Inserte el nombre del jueguete: ");
						String nombreJ = s.next();

						IO.print("Inserte Precio su precio: ");
						double precio = s.nextDouble();

						gestor.gestionarJuguete("INSERTAR", nombreJ, precio, 0);
						break;
					case 2:
						seleccionEmpleado();
						int opcion1 = s.nextInt();
						s.nextLine();

						if (opcion1 == 1) {
							IO.print("Inserte el Nombre del empleado: ");
							String nombreE = s.nextLine();

							IO.print("Idique suargo (Jefe/Cajero): ");
							String cargo = s.next();

							IO.print("Fecha Ingreso (YYYY-MM-DD): ");
							String fecha = s.next();
							
							gestor.gestionarEmpleado("INSERTAR", 0, nombreE, cargo, fecha);
						} 
						else if (opcion1 == 2) {
							IO.print("Inserte el ID del empleado a modificar: ");
							int id = s.nextInt();
							s.nextLine();

							IO.print("Ponga su Nuevo Nombre: ");
							String nombreNuevo = s.nextLine();

							IO.print("Ponga su Nuevo Cargo (Jefe/Cajero): ");
							String cargoNuevo = s.next();

							IO.print("La Nueva Fecha: (YYYY-MM-DD)");
							String fechaNueva = s.next();

							gestor.gestionarEmpleado("MODIFICAR", id, nombreNuevo, cargoNuevo, fechaNueva);
						} 
						else if (opcion1 == 3) {
							IO.print("Inserte el ID del empleado a eliminar: ");
							int id = s.nextInt();
							
							gestor.gestionarEmpleado("ELIMINAR", id, null, null, null);
						}
						break;
					case 3:
						IO.print("Inserte el ID Empleado: ");
						int e = s.nextInt();

						IO.print("Inserte el ID Juguete: ");
						int j = s.nextInt();

						gestor.registrarVenta(e, j, 1, 1, 20.0, "Efectivo");
						break;
					case 4:
						IO.print("Inserte el identificador del Empleado responsable: ");
						int idEmpC = s.nextInt();

						IO.print("ID del Juguete a cambiar: ");
						int idCambioJ = s.nextInt();

						IO.print("ID del Stand de Origen: ");
						int idStandOrigen = s.nextInt();

						IO.print("ID de la Zona de Origen: ");
						int idZonaOrigen = s.nextInt();

						IO.print("ID del Stand de Destino: ");
						int idStandDestino = s.nextInt();

						IO.print("ID de la Zona de Destino: ");
						int idZonaDestino = s.nextInt();
						s.nextLine();

						IO.print("Motivo del cambio: ");
						String motivo = s.nextLine();

						gestor.realizarCambio(idEmpC, idCambioJ, idStandOrigen, idZonaOrigen, idStandDestino, idZonaDestino, motivo);
						break;
					case 5:
						gestor.reporteTopVentas();
						break;
					case 6:
						IO.print("inserte el ID de la venta a devolver: ");
						int idDevolucion = s.nextInt();

						gestor.realizarDevolucion(idDevolucion);
						break;
					case 7:
						System.out.print("Introduzca el número del mes (1-12): ");
						int numeroMes = s.nextInt();

						gestor.reporteVentasMes(numeroMes);
						break;
					case 8:
						System.out.print("ID Empleado: ");
						int idER = s.nextInt();

						System.out.print("Mes: ");
						int mesER = s.nextInt();

						gestor.reporteVentasEmpleadoMes(idER, mesER);
						break;
					case 9:
						gestor.reporteMejoresEmpleados();
						break;
					case 10:
						gestor.obtenerDatosTienda();
						break;
					case 11:
						gestor.reporteHistorialCambios();
						break;
					case 12:
						IO.print("Inserte el ID del Stand: ");
						int idS = s.nextInt();

						IO.print("Inserte el ID de la Zona: ");
						int idZ = s.nextInt();
						
						gestor.mostrarStockPorStand(idS, idZ);
						break;  
					case 13:
						gestor.listarJuguetesPorPrecio();
						break;
					default:
						System.out.println("No existe la opción que has escrito.\nInserte el número de las opciones presentadas\n");
				}
			} catch(NoSuchElementException nsee) {
				System.err.println("No has introducido un elemento como se debería.\n Vuelve a intentarlo.");
			}
		}
		System.out.println("Cerrando...");
	}
}
