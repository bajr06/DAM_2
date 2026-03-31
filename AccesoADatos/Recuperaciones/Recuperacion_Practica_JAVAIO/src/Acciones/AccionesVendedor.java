package Acciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import Menus.MenuVendedor;
import Objetos.Empleado;
import Objetos.Planta;

public class AccionesVendedor {
	public static void mostrarPlantas(ArrayList<Planta> plantas) {
		for (Planta planta : plantas) {
			System.out.println(planta.toString());
		}

		IO.println();
	}

	private static void crearTicket(Empleado empleado, Planta [] plantas, int [] cantidades, File [] ficheros, File [] directorios) {
		int cantidadFicheros = AccionesGenerales.contarFicherosVD(directorios[1], directorios[2]);
		File nuevoTicket = new File("Tickets/" + cantidadFicheros + ".txt");
		float precioTotal = 0;

		try {
			if(!nuevoTicket.createNewFile()) {
				System.out.println("¡Cuidado! Parece ser que hay tickets que han sido eliminados...");
				System.out.println("Cerrando programa.");
				System.exit(0);
			}

			PrintWriter pw = new PrintWriter(nuevoTicket);

			pw.printf("Número Ticket: %d\n", cantidadFicheros);
			pw.printf("------------------------------//------------------------------\n");
			pw.printf("Identificador del Empleado que ha atendido: %s\n", empleado.getIdentificacion());
			pw.printf("Nombre del empleado: %s\n", empleado.getNombre());
			pw.printf("Codigo_Producto			Cantidad			Precio_Unitario\n");
			for(int i = 0; i < plantas.length; i++) {
				pw.println(plantas[i].getCodigo() + "\t\t\t\t\t\t" + cantidades[i] + "\t\t\t\t\t" + (cantidades[i] * plantas[i].getPrecio()));

				precioTotal += cantidades[i] * plantas[i].getPrecio();
				plantas[i].setCantidad(plantas[i].getCantidad() - cantidades[i]);
			}
			pw.printf("------------------------------//------------------------------\n");
			pw.println("Total: " + precioTotal + " €");

			pw.close();
		} catch(IOException ioe) {
			System.err.println("Error: " + ioe + ": Directorio o fichero no existente.");
		}

		IO.println("Venta realizada correctamente.\n");
	}

	public static void realizarVenta(Empleado empleado, ArrayList<Planta> plantas, File [] ficheros, File [] directorios) {
		int cantidadPlantasVendidas = MenuVendedor.cantidadPlantasVenta();
		int [] cantidades = new int[cantidadPlantasVendidas];
		Planta [] plantasAVender = new Planta[cantidadPlantasVendidas];
		int seleccionPlanta;

		try {
			for(int i = 0; i < cantidadPlantasVendidas; i++) {
				seleccionPlanta = MenuVendedor.seleccionPlantaV();
				
				for(Planta planta: plantas) {
					if(planta.getCodigo() == seleccionPlanta) {
						plantasAVender[i] = planta;
					}
				}

				if(plantasAVender[i] == null) {
					System.out.println("No existe esta planta");
					System.out.println("Revise primero si el código de la planta existe e intente realizar una venta nuevamente.\n");
					return;
				}
				
				cantidades[i] = MenuVendedor.seleccionCantidadV();

				if(cantidades[i] > plantasAVender[i].getCantidad() || plantasAVender[i].getCantidad() <= 0) {
					System.out.println("Planta sin disponibilidad");
					System.out.println("Revise primero si hay disponibilidad de la planta a vender e intenta realizar una venta nuevamente.\n");
					return;
				}

				IO.println();
			}

			if(MenuVendedor.confirmar()) {
				crearTicket(empleado, plantasAVender, cantidades, ficheros, directorios);
			} else {
				System.out.println("Operación cancelada\n");
			}
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta ha devuelto " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		}
	}

	private static void crearDevolucion(ArrayList<Planta> plantas, ArrayList<String> lineasAntiguoTicket, Object [][] cantidades, File [] ficheros, File antiguoTicket) {
		File nuevaDevolucion = new File("Devoluciones/" + antiguoTicket.getName());
		float precioTotal = 0;

		try {
			if(!nuevaDevolucion.createNewFile()) {
				System.out.println("¡Cuidado! Parece ser que hay tickets que han sido eliminados...");
				System.out.println("Cerrando programa.");
				System.exit(0);
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(nuevaDevolucion));

			for(int i = 0; i < lineasAntiguoTicket.size() - 1; i++) {
				bw.write(lineasAntiguoTicket.get(i) + "\n");
				if(i == lineasAntiguoTicket.size() - 3) {
					for(int j = 0; j < cantidades.length; j++) {
						bw.write(cantidades[j][0] + "\t\t\t\t\t\t" + cantidades[j][1] + "\t\t\t\t\t -" + cantidades[j][2] + "\n");

						for(Planta p: plantas) {
							if((int) cantidades[j][0] == p.getCodigo()) {
								p.setCantidad((int)cantidades[j][1] + p.getCantidad());
							}
						}

						precioTotal -= (float) cantidades[j][2];
					}
				}
			}

			bw.write("Total: " + precioTotal + " €\n");
			bw.close();

			antiguoTicket.delete();
			
			IO.println("Devolución realizada correctamente.\n");
		} catch(IOException ioe) {
			System.err.println("Error: " + ioe + ": Directorio o fichero no existente.");
		}
	}

	public static void realizarDevolucion(ArrayList<Planta> plantas, File [] ficheros, File [] directorio) {
		try {
			int seleccion = MenuVendedor.seleccionTicket();
			File fichero = new File("Tickets/"+ seleccion + ".txt");

			if(fichero.exists()) {
				ArrayList<String>[] ticket = AccionesGenerales.buscarCodigoFichero(fichero);

				int contador = 0;
				Object[][] sublineas = new Object[ticket[1].size()][3];
				String[] sublineasTemporal1;
				String[] sublineasTemporal2;

				for(String linea: ticket[1]) {
					sublineasTemporal1 = linea.split("\t\t\t\t\t\t");
					sublineasTemporal2 = sublineasTemporal1[1].split("\t\t\t\t\t");
					
					sublineas[contador][0] = Integer.parseInt(sublineasTemporal1[0]);
					sublineas[contador][1] = Integer.parseInt(sublineasTemporal2[0]);
					sublineas[contador][2] = Float.parseFloat(sublineasTemporal2[1]);

					contador++;
				}

				if(MenuVendedor.confirmar()) {
					crearDevolucion(plantas, ticket[0], sublineas, directorio, fichero);
				} else {
					System.out.println("Operación cancelada\n");
				}	
			} else {
				System.out.println("El ticket con la numeración que usted busca, no existe.");
			}

			IO.println();

		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta ha devuelto " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		} catch(IOException ioe) {
			System.err.println("Error en la lectura del fichero: " + ioe.getMessage() + "\n");
		}
	}

	public static void buscarTicket(File [] ficheros) {
		try {
			int busqueda = MenuVendedor.seleccionTicket();
			
			File ficheroBuscadoV = new File("Tickets/" + busqueda + ".txt");
			File ficheroBuscadoD = new File("Devoluciones/" + busqueda + ".txt");

			if(ficheroBuscadoV.exists()) {
				AccionesGenerales.leerFichero(ficheroBuscadoV);
			} else if(ficheroBuscadoD.exists()) {
				AccionesGenerales.leerFichero(ficheroBuscadoD);
			} else if(!ficheroBuscadoV.exists() && !ficheroBuscadoD.exists()) {
				System.out.println("El ticket con la numeración que usted busca, no existe.");
			}

			IO.println();
			
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta ha devuelto " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		} catch(IOException ioe) {
			System.err.println("Error en la lectura del fichero: " + ioe.getMessage() + "\n");
		}
	}
}

