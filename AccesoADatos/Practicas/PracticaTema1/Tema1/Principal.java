package Tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.ArrayList;

public class Principal {
	static Scanner entrada = new Scanner(System.in);

	private static void verificarDirectorios(String [] carpetas) {
		for (String carpeta : carpetas) { // Recorremos la lista de directorios a comprobar.
			File dir = new File("src\\" + carpeta); // Creamos el fichero, basandonos en que su ubicación está en src.
			if (!dir.exists()) { // Si NO existe.
				if (dir.mkdirs()) { // Y creamos el directorio, y lo notificamos.
					System.out.println("Carpeta creada: " + carpeta);
				} else { // Si falla, lo noficiamos.
					System.err.println("Error al crear la carpeta: " + carpeta);
				}
			} else { // Y si ya existe, le indicamos al vendedor/gesto que ya existe.
				System.out.println("La carpeta ya existe: " + carpeta);
			}
		}
	}

	public static ArrayList<Planta> cargarPlantasXML(String ruta) {
		ArrayList<Planta> lista = new ArrayList<>(); // Creamos una lista vacia del tipo Planta.

		try {
			File archivo = new File(ruta); // Accedemos a la dirección del fichero a leer (plantas.xml)
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			
			NodeList nodos = doc.getElementsByTagName("planta"); // Separamos cada parte del XML dependiendo de que empiece con "Planta"
			
			for (int i = 0; i < nodos.getLength(); i++) { // Vamos recorriendo cada uno de las "Plantas".
				Node nodo = nodos.item(i); // Hacemos que cada uno sea un item (el cual empieza en 0).
				
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;

					// Recopilamos los datos de cada uno de las plantas.
					int codigo = Integer.parseInt(elemento.getElementsByTagName("codigo").item(0).getTextContent());
					String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
					String foto = elemento.getElementsByTagName("foto").item(0).getTextContent();
					String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();

					lista.add(new Planta(codigo, nombre, foto, descripcion)); // Agregamos los datos al ArrayList.
				}
			}
		} catch (Exception e) { // Error en caso de que haya un caso de error en alguna parte de la recopilación de la información del XML,
			System.err.println("Error al cargar plantas.xml: " + e.getMessage());
		}
		
		return lista;
	}

	private static ArrayList<DatosPlanta> cargarPlantasDAT(String ruta) {
		ArrayList<DatosPlanta> lista = new ArrayList<>(); // Creamos una lista vacia de "DatosPlanta".
		
		try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) { // Accedemos al fichero binario en modo lectura.
			while (raf.getFilePointer() < raf.length()) { // Siempre que el puntero apunte a una posición que no este fuera del rango del fichero binario.
				int codigo = raf.readInt();
				float precio = raf.readFloat();
				int stock = raf.readInt();
				
				lista.add(new DatosPlanta(codigo, precio, stock)); // Leera toda la información y la añadira a la lista dinámica.
			}
		} catch (Exception e) { // Si hay algún error a la hora de leer el fichero binario, lo imprimimos por terminal.
			System.err.println("Error al cargar plantas.dat: " + e.getMessage());
		}

		return lista;
	}
	
	private static ArrayList<Empleado> cargarEmpleado(String ruta){
		ArrayList<Empleado> ListaEmpleados = new ArrayList <>();

		try (FileOutputStream fos = new FileOutputStream(ruta); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			Empleado empleado1 = new Empleado(1452,"Teresa","asb123","vendedor");
			Empleado empleado2 = new Empleado(0234,"Miguel Angel","123qwe","vendedor");
			Empleado empleado3 = new Empleado(7532,"Natalia","xs21qw4","gestor");

			ListaEmpleados.add(empleado1);
			ListaEmpleados.add(empleado2);
			ListaEmpleados.add(empleado3);
			
			oos.writeObject(ListaEmpleados); // Escribimos el objeto dentro de la lista empleados.
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return ListaEmpleados;
	}

	private static Empleado registro(ArrayList<Empleado> empleados) {
		Empleado usuario = null; // Creamos un objeto vacio de Empleado
		int intentos = 3; 
		
		System.out.println("INICIO DE SESIÓN");

		while (intentos > 0 && usuario == null) {
			try {
				System.out.print("Identificación: ");
				String idInput = entrada.nextLine(); // Elimina los espacios en blanco, en caso de que se haya introducido antes o después de la cadena.
				if (idInput.isEmpty()) { // Pilla un error en caso de que no se introduzca nada y lo pasa al catch que contenga la misma excepción.
					throw new IllegalArgumentException("La identificación no puede estar vacía.");
				}
				int id = Integer.parseInt(idInput); // Traspaso del Array de Carácteres a un Entero (La única manera para control de errores en caso de que no se introduzca un dato).

				System.out.print("Contraseña: ");
				String pass = entrada.nextLine().trim();
				if (pass.isEmpty()) {
					throw new IllegalArgumentException("La contraseña no puede estar vacía.");
				}
				
				for (Empleado e : empleados) { // Bucle que va pasando por cada uno de los empleados.
					if (e.getIdentificacion() == id && e.getContrasena().equals(pass)) { // Si la identificación y la contraseña son correctas.
						usuario = e; // Seleccionamos el usuario y empezamos a trabajar con el.
						break; // Se sale abruptamente del bucle, para evitar recorrerlo más de la cuenta.
					}
				}
				
				if (usuario == null) { // Si no encontramos al usuario en la lista de empleados, notificamos al usuario de que no e ha encontrado, y le restamos un intento
					intentos--;
					System.out.println("Credenciales incorrectas. Intentos restantes: " + intentos);
				}
			} catch (NumberFormatException nfe) { // Pillar error en caso de que el valor introducido no sea un número entero.
				System.out.println("La identificación debe ser un número entero.");
			} catch (IllegalArgumentException iae) { // Los 2 errores anteriores en caso de que no se haya introducido valores en los escaneos por pantalla.
				System.out.println(iae.getMessage());
			} catch (Exception e) {
				System.out.println("Error inesperado: " + e.getMessage());
			}
		}

		if (usuario != null) { // Si hemos encontrado al usuario, le damos la bienvenida.
			System.out.println("\nBienvenido, " + usuario.getNombre() + " (" + usuario.getCargo() + ")");
		} else { // Si gemos agotado todos los intentos, cerramos el programa directamente, y devolvemos error.
			System.out.println("\nSe han agotado los intentos. Acceso denegado.");
		}
		
		return usuario;
	}

	private static void mostrarGestor(Empleado gestor) { // Si al iniciar sesión, el empleado es un gestor.
		ArrayList<DatosPlanta> datos = cargarPlantasDAT("src\\Plantas\\plantas.dat"); // Cargamos los datos de la ruta que le damos.
		int opcion;
		
		do {
			System.out.println("\nMENÚ GESTOR");
			System.out.println("1. Ver listado de plantas");
			System.out.println("2. Modificar stock y precio");
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");
			
			try {
				opcion = Integer.parseInt(entrada.nextLine()); // Lo realizamos asi para el control de errores.

				switch (opcion) {
					case 1 -> mostrarPlantas(datos);
					case 2 -> modificarPlanta(datos);
					case 0 -> System.out.println("Cerrando sesión.");
					default -> System.out.println("Opción inválida.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				opcion = -1;
			}
		} while (opcion != 0);
	}

	private static void mostrarPlantas(ArrayList<DatosPlanta> datos) {
		System.out.println("\nLISTADO DE PLANTAS");
		
		for (DatosPlanta d : datos) { // Después de haber cargado todos los datos de las plantas, los imprimimos.
			System.out.printf("Código: %d | Precio: %.2f € | Stock: %d\n", d.getCodigo(), d.getPrecio(), d.getStock());
		}
	}

	private static void guardarPlantasDAT(String ruta, ArrayList<DatosPlanta> datos) {
		try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) { // Accedemos al fichero en modo de lectura y escritura.
			raf.setLength(0); // En caso de cambios que haya, borramos y lo volvemos a escribir.

			for (DatosPlanta d : datos) {
				raf.writeInt(d.getCodigo());
				raf.writeFloat(d.getPrecio());
				raf.writeInt(d.getStock());
			}
			
			System.out.println("Datos de plantas guardados correctamente en " + ruta);
		} catch (Exception e) {
			System.err.println("Error al guardar plantas.dat: " + e.getMessage());
		}
	}

	private static void modificarPlanta(ArrayList<DatosPlanta> datos) {
		System.out.print("Código de planta a modificar: ");
		int codigo = Integer.parseInt(entrada.nextLine()); // Siempre que escaneemos por terminal de esta manera, es para el tratado de errores.
		DatosPlanta planta = null;
		
		for (DatosPlanta d : datos) { // Vamos pasando por todos los datos del ArrayList de datos
			if (d.getCodigo() == codigo) {
				planta = d;
				break;
			}
		}
		
		if (planta == null) {
			System.out.println("Planta no encontrada.");
			return; // Salida abrupta de la función
		}
		
		System.out.println("Planta actual → Precio: " + planta.getPrecio() + " € | Stock: " + planta.getStock());
		
		try {
			System.out.print("Nuevo precio: ");
			float nuevoPrecio = Float.parseFloat(entrada.nextLine());

			System.out.print("Nuevo stock: ");
			int nuevoStock = Integer.parseInt(entrada.nextLine());

			planta.setPrecio(nuevoPrecio); // Introducimos los nuevos valores cambiados.
			planta.setStock(nuevoStock);

			guardarPlantasDAT("src\\Plantas\\plantas.dat", datos);
			System.out.println("Planta actualizada correctamente.");
		} catch (Exception e) {
			System.err.println("Error al modificar: " + e.getMessage());
		}
	}

	private static void mostrarVendedor(Empleado vendedor) {
		ArrayList<DatosPlanta> datos = cargarPlantasDAT("src\\Plantas\\plantas.dat"); // Cargamos los datos de las plantas.
		int opcion;
		
		do {
			System.out.println("\nMENÚ VENDEDOR");
			System.out.println("1. Ver plantas disponibles");
			System.out.println("2. Realizar venta");
			System.out.println("3. Realizar devolución");
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");
			try {
				opcion = Integer.parseInt(entrada.nextLine());
				
				switch (opcion) {
					case 1 -> mostrarDisponibles(datos);
					case 2 -> realizarVenta(datos, vendedor);
					case 3 -> realizarDevolucion(datos);
					case 0 -> System.out.println("Cerrando sesión...");
					default -> System.out.println("Opción inválida.");
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				opcion = -1;
			}
		} while (opcion != 0);
	}

	private static void mostrarDisponibles(ArrayList<DatosPlanta> datos) {
		System.out.println("\nPLANTAS DISPONIBLES");
		
		for (DatosPlanta d : datos) {
			if (d.getStock() > 0) {
				System.out.printf("Código: %d | Precio: %.2f € | Stock: %d\n", d.getCodigo(), d.getPrecio(), d.getStock());
			}
		}
	}

	private static void realizarVenta(ArrayList<DatosPlanta> datos, Empleado vendedor) {
		System.out.print("Código de planta a vender: ");
		int codigo = Integer.parseInt(entrada.nextLine());
		DatosPlanta planta = null;
		
		for (DatosPlanta d : datos) {
			if (d.getCodigo() == codigo) {
				planta = d;
				break;
			}
		}

		if (planta == null || planta.getStock() == 0) {
			System.out.println("Planta no disponible.");
			return;
		}

		System.out.print("Cantidad a vender: ");
		int cantidad = Integer.parseInt(entrada.nextLine());

		if (cantidad <= 0 || cantidad > planta.getStock()) {
			System.out.println("Stock insuficiente o cantidad inválida.");
			return;
		}
		
		planta.setStock(planta.getStock() - cantidad);
		guardarPlantasDAT("src\\Plantas\\plantas.dat", datos);
		
		generarTicket(vendedor, planta, cantidad);
		System.out.println("Venta realizada correctamente.");
	}

	private static int contarTickets(String carpeta) {
		File dir = new File(carpeta);
		if (!dir.exists()) {
			return 0;
		}

		File[] archivos = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String nombre) {
				return nombre.startsWith("ticket_") && nombre.endsWith(".txt");
			}
		});

		if (archivos != null) {
			return archivos.length;
		} else {
			return 0;
		}
	}

	private static void generarTicket(Empleado vendedor, DatosPlanta planta, int cantidad) {
		try {
			int numero = contarTickets("src\\Tickets") + 1; // Después de haber contado los tickets.
			String nombreArchivo = "src\\Tickets\\ticket_" + numero + ".txt"; // Usamos el número que devolvió la función anterior y lo implementamos en el nombre del nuevo ticket.
			
			try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
				LocalDateTime ahora = LocalDateTime.now(); // Le colocamos la fecha
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Con el formato que nos pide.
				
				// Y esta es la estructura del ticket que hemos creado,
				pw.println("TICKET DE VENTA");
				pw.println("Fecha: " + ahora.format(formato));
				pw.println("Vendedor: " + vendedor.getNombre());
				pw.println("Planta: Código " + planta.getCodigo());
				pw.println("Cantidad: " + cantidad);
				pw.printf("Precio unitario: %.2f€\n", planta.getPrecio());
				pw.printf("Total: %.2f€\n", planta.getPrecio() * cantidad);
				pw.println("------------------------------");
			}

			System.out.println("Ticket generado: " + nombreArchivo);
		} catch (Exception e) {
			System.err.println("Error al generar ticket: " + e.getMessage());
		}
	}

	private static void realizarDevolucion(ArrayList<DatosPlanta> datos) {
		System.out.print("Nombre del ticket a devolver (ej. ticket_3.txt): ");
		String nombreTicket = entrada.nextLine().trim();

		File original = new File("src/Tickets/" + nombreTicket);
		if (!original.exists()) {
			System.out.println("Ticket no encontrado.");
			return;
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(original))) {
			String linea;
			int codigo = -1;
			int cantidad = 0;
			float precio = 0;
			ArrayList<String> contenido = new ArrayList<>();
			
			while ((linea = br.readLine()) != null) {
				contenido.add(linea);

				if (linea.startsWith("Planta: Código")) {
					codigo = Integer.parseInt(linea.split(" ")[2]);
				} else if (linea.startsWith("Cantidad:")) {
					cantidad = Integer.parseInt(linea.split(":")[1].trim());
				} else if (linea.startsWith("Precio unitario:")) {
					precio = Float.parseFloat(linea.split(":")[1].replace("€", "").trim());
				}
			}
			
			if (codigo == -1 || cantidad == 0 || precio == 0) {
				System.out.println("No se pudo extraer información del ticket.");
				return;
			}

			for (DatosPlanta d : datos) { // Actualizamos el stock
				if (d.getCodigo() == codigo) {
					d.setStock(d.getStock() + cantidad);
					break;
				}
			}

			guardarPlantasDAT("PLANTAS/plantas.dat", datos); // Guardamos los cambios en plantas.dat
			
			File devuelto = new File("src/Devoluciones/" + nombreTicket); // Creamos el ticket modificado
			try (PrintWriter pw = new PrintWriter(new FileWriter(devuelto))) {
				for (String l : contenido) {
					if (l.startsWith("Total:")) {
						pw.printf("Total: -%.2f€\n", precio * cantidad);
					} else {
						pw.println(l);
					}
				}
				
				pw.println("DEVOLUCIÓN REGISTRADA");
			}
			
			System.out.println("Ticket devuelto: " + devuelto.getName());
		} catch (Exception e) {
			System.out.println("Error al procesar devolución: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		String[] carpetas = {"Plantas", "Empleados", "Tickets", "Devoluciones", "Baja"};
		// 1. Verificar directorios
        System.out.println("Verificando directorios...");
        verificarDirectorios(carpetas);

        // 2. Cargar empleados
        System.out.println("👥 Cargando empleados...");
        ArrayList<Empleado> empleados = cargarEmpleado("src\\Empleados\\empleado.dat");

        // 3. Iniciar sesión
        Empleado usuario = registro(empleados);

        // 4. Mostrar menú según cargo
        if (usuario != null) {
            switch (usuario.getCargo().toLowerCase()) {
                case "gestor" -> mostrarGestor(usuario);
                case "vendedor" -> mostrarVendedor(usuario);
                default -> System.out.println("Cargo no reconocido: " + usuario.getCargo());
            }
        }

        System.out.println("\nPrograma finalizado.");

	}
}
