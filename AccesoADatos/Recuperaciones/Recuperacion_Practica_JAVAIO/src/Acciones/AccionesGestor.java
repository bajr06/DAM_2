package Acciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ManejoFicheros.Carga;
import ManejoFicheros.Modificacion;
import Menus.MenuGestor;
import Objetos.Empleado;
import Objetos.Planta;

public class AccionesGestor {
	static Scanner s = new Scanner(System.in);

	private static ArrayList<Planta> lecturaPlantasBaja(File fichero) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		String linea = br.readLine();
		br.close();
		
		if(!linea.isBlank()) {
			ArrayList<Planta> plantasBaja = Carga.cargaPlantasXML(fichero);
			
			return plantasBaja;
		} else {
			return null;
		}
	}
	
	private static boolean comprobacionExistenciaAltasPlantas(int codigoBuscar, ArrayList<Planta> plantasAlta) {
		if(!(plantasAlta == null)) {
			for(Planta p: plantasAlta) {
				if(p.getCodigo() == codigoBuscar) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean comprobacionExistenciaBajasPlantas(int codigoBuscar, ArrayList<Planta> plantasBaja) {
		if(!(plantasBaja == null)) {
			for(Planta p: plantasBaja) {
				if(p.getCodigo() == codigoBuscar) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static Planta plantaSeleccionada(int seleccion, ArrayList<Planta> plantasBaja, File fichero) {
		Planta plantaAlta = null;
		
		for(Planta p: plantasBaja) {
			if(p.getCodigo() == seleccion) {
				plantaAlta = p;
			}
		}
		
		Carga.cargaPlantasDAT(plantasBaja, fichero);
				
		return plantaAlta;
	}
	
	public static void darAltaPlanta(ArrayList<Planta> plantasAlta, File [] ficheros) {
		try {
			int seleccion = MenuGestor.preguntaAltaPlantas();
			boolean comprobacion;
			ArrayList<Planta> plantasBaja = lecturaPlantasBaja(ficheros[2]);

			if(seleccion == 1) {
				Object [] datos = MenuGestor.peticionDatosPlanta();
				
				comprobacion = comprobacionExistenciaAltasPlantas((int)datos[0], plantasAlta);
				comprobacion = comprobacionExistenciaBajasPlantas((int)datos[0], plantasBaja);
				
				if(!comprobacion) {
					plantasAlta.add(new Planta((int)datos[0], (String) datos[1], (String) datos[2], (String) datos[3], (float) datos[4], (int) datos[5]));
	
					System.out.println("Nueva planta dada de alta correctamente\n");
				} else {
					System.out.println("Ya existe una planta con la numeración que le quiere dar.\nIntentelo de nuevo\n");
				}
			} else if(seleccion == 2) {
				int seleccionPlantaBaja = MenuGestor.peticionAltaPlanta();
				
				comprobacion = comprobacionExistenciaBajasPlantas(seleccionPlantaBaja, plantasBaja);
				
				if(comprobacion) {
					Planta plantaBaja = plantaSeleccionada(seleccionPlantaBaja, plantasBaja, ficheros[3]);
					
					if(plantaBaja.getCantidad() <= 0) {
						plantaBaja.setCantidad(MenuGestor.peticionCantidadNueva());
					}
					
					plantasAlta.add(plantaBaja);
					plantasAlta.sort((p1, p2) -> Integer.compare(p1.getCodigo(), p2.getCodigo()));
					
					System.out.println("Planta dada de alta correctamente.\n");
				} else {
					System.out.println("No existe la planta.\n");
				}
			} else {
				System.out.println("Opción no existente, intentelo de nuevo\n");
			}
			
			IO.println();
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta: " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		} catch(IOException ioe) {
			System.err.println("Error de lectura: \"" + ioe.getMessage() + "\".");
		}
	}

	
	public static void darBajaPlanta(ArrayList<Planta> plantas, File [] ficheros) {	
		try {
			int identificador = MenuGestor.peticionBajaPlanta();
			for(int i = 0; i < plantas.size(); i++) {
				if(plantas.get(i).getCodigo() == identificador) {
					Modificacion.modificarPlantasBajaXML(ficheros[2], plantas.get(i));
					Modificacion.modificarPlantasBajaDAT(ficheros[3], plantas.get(i));	
					
					plantas.remove(i);
					
					System.out.println("Planta dada de baja correctamente.\n");
				}
			}
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta: " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		} catch(IOException ioe) {
			System.err.println("Error en la lectura/escritura del fichero: \"" + ioe.getMessage() + "\"\n Cerrando programa.");
			System.exit(1);
		}
	}
	
	private static void modificarPlanta(int seleccion, Planta planta) {
		switch(seleccion) {
			case 1:
				planta.setCodigo(MenuGestor.modificarCodigo());
				break;
			case 2:
				planta.setNombre(MenuGestor.modificarNombre());
				break;
			case 3:
				planta.setFoto(MenuGestor.modificarFoto());
				break;
			case 4:
				planta.setDescripcion(MenuGestor.modificarDescripcion());
				break;
			case 5:
				planta.setPrecio(MenuGestor.modificarPrecio());
				break;
			case 6:
				planta.setCantidad(MenuGestor.modificarCantidad());
				break;
			default:
				System.out.println("No existe la opción que usted ha seleccionado.\n");
		}
	} 
	
	public static void modificarDatosPlanta(ArrayList<Planta> plantas) {
		try {
			int seleccionPlanta = MenuGestor.peticionPlantaMoficar();
			
			for(Planta p: plantas) {
				if(p.getCodigo() == seleccionPlanta) {
					int seleccionParteModificar = MenuGestor.preguntaParteModificarPlanta();
					
					modificarPlanta(seleccionParteModificar, p);
				}
			}
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta: " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		}
	}

	public static boolean comprobarContrasenya(String contrasenya) {
		String regex = "^(?=.*[a-z]{3})(?=.*[A-Z]{3})(?=.*\\d{3}).+$";

		if(contrasenya.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	private static ArrayList<Empleado> lecturaEmpleadosBaja(File fichero) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		String linea = br.readLine();
		br.close();
		
		if(linea != null) {
			ArrayList<Empleado> empleadosBaja = Carga.cargaEmpleadosDAT(fichero);
			
			return empleadosBaja;
		} else {
			return null;
		}
	}
	
	private static boolean comprobacionExistenciaAltasEmpleados(int codigoBuscar, ArrayList<Empleado> empleadosAlta) {
		if(!(empleadosAlta == null)) {
			for(Empleado e: empleadosAlta) {
				if(e.getIdentificacion() == codigoBuscar) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static boolean comprobacionExistenciaBajasEmpleados(int codigoBuscar, ArrayList<Empleado> empleadosBaja) {
		if(!(empleadosBaja == null)) {
			for(Empleado e: empleadosBaja) {
				if(e.getIdentificacion() == codigoBuscar) {
					return false;
				}
			}
		}
		
		return true;
	}

	private static Empleado empleadoSeleccionada(int seleccion, ArrayList<Empleado> empleadosBaja, File fichero) {
		Empleado empleadoAlta = null;
		
		for(Empleado e: empleadosBaja) {
			if(e.getIdentificacion() == seleccion) {
				empleadoAlta = e;
			}
		}
						
		return empleadoAlta;
	}

	public static void darAltaEmpleado(ArrayList<Empleado> empleadosAlta, File [] ficheros) {
		try {
			int seleccion = MenuGestor.preguntaAltaEmpleados();
			boolean comprobacion;
			ArrayList<Empleado> empleadosBaja = lecturaEmpleadosBaja(ficheros[5]);
			
			if(seleccion == 1) {
				Object [] datos = MenuGestor.peticionDatosEmpleado();

				comprobacion = comprobacionExistenciaAltasEmpleados((int)datos[0], empleadosAlta);
				comprobacion = comprobacionExistenciaBajasEmpleados((int)datos[0], empleadosBaja);

				if(!comprobacion) {
					empleadosAlta.add(new Empleado((int)datos[0], String.valueOf(datos[1]), String.valueOf(datos[2]), String.valueOf(datos[3])));

					System.out.println("Nuevo empleado dado de alta correctamente\n");
				} else {
					System.out.println("Ya existe ese empleado");
				}
			} else if(seleccion == 2) {
				int seleccionEmpleadoBaja = MenuGestor.peticionAltaEmpleado();
				
				comprobacion = comprobacionExistenciaBajasEmpleados(seleccionEmpleadoBaja, empleadosBaja);
				
				if(comprobacion) {
					Empleado empleadoBaja = empleadoSeleccionada(seleccionEmpleadoBaja, empleadosBaja, ficheros[3]);
					
					empleadosAlta.add(empleadoBaja);
					
					System.out.println("Empleado dado de alta correctamente.\n");
				} else {
					System.out.println("No existe el empleado, o ya existia de antes.\n");
				}
			} else {
				System.out.println("Opción no existente, intentelo de nuevo\n");
			}
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta: " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		} catch(IOException ioe) {
			System.err.println("Error en la lectura/escritura del fichero: \"" + ioe.getMessage() + "\"\n Cerrando programa.");
			System.exit(1);
		}
	}

	public static void darBajaEmpleado(ArrayList<Empleado> empleados, File [] ficheros) {
		try {
			int identificador = MenuGestor.peticionBajaEmpleado();
			for(int i = 0; i < empleados.size(); i++) {
				if(empleados.get(i).getIdentificacion() == identificador) {
					Modificacion.modificarEmpleadosBajaDAT(ficheros[5], empleados.get(i));
					
					empleados.remove(i);
					
					System.out.println("Empleado dado de baja correctamente.\n");
				}
			}
		} catch(NoSuchElementException nsee) {
			System.err.println("Entrada incorrecta: " + nsee.getMessage() + "\nInserte los datos según se le pida.\n");
		} catch(IOException | ClassNotFoundException iocnfe) {
			System.err.println("Error en la lectura/escritura del fichero: \"" + iocnfe.getMessage() + "\"\n Cerrando programa.");
			System.exit(1);
		}
		
	}

	@SuppressWarnings("unchecked")
	public static void mostrarEstadisticas(File[] directorios) {
		double ingresosTotales = 0;
		ArrayList<String> [] lineas = new ArrayList[2];
		String linea;
		String [] separacionLineas = new String[3];
		ArrayList<String []> tickets = new ArrayList<>();
		tickets.add(directorios[2].list());
		tickets.add(directorios[3].list());

		try {
			for(int i = 0; i < tickets.size(); i++) {
				for(int j = 0; j < tickets.get(i).length; j++) {
					if(i == 0) {
						lineas = AccionesGenerales.buscarCodigoFichero(new File(directorios[2].getName() + "/" + tickets.get(i)[j]));
					} else if(i == 1) {
						lineas = AccionesGenerales.buscarCodigoFichero(new File(directorios[3].getName() + "/" + tickets.get(i)[j]));
					}

					linea = lineas[0].get(lineas[0].size() - 1);

					separacionLineas = linea.split(" ");

					ingresosTotales += Double.parseDouble(separacionLineas[1]);
				}
			}

			System.out.println("Los ingresos totales son " + String.format("%.2f", ingresosTotales) + " \u20AC");
		} catch(IOException ioe) {
			System.err.println("Error en la lectura de los ficheros: \"" + ioe.getMessage() + "\"\nCerrando Programa");
			System.exit(1);
		}
	}
}
