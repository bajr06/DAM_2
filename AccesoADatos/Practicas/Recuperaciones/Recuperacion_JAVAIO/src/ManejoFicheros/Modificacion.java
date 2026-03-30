package ManejoFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import Objetos.Empleado;
import Objetos.Planta;

public class Modificacion {
	public static void modificarPlantasXML(File [] ficheros,ArrayList<Planta> plantas) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(ficheros[0]));

		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();

		bw.write("<plantas>");
		bw.newLine();

		for(int i = 0; i < plantas.size(); i++) {
			if(plantas.get(i).getCantidad() > 0) {
				bw.write("\t<planta>");
				bw.newLine();
	
				bw.write("\t\t<codigo>" + plantas.get(i).getCodigo() + "</codigo>");
				bw.newLine();
	
				bw.write("\t\t<nombre>" + plantas.get(i).getNombre() + "</nombre>");
				bw.newLine();
	
				bw.write("\t\t<foto>" + plantas.get(i).getFoto() + "</foto>");
				bw.newLine();
	
				bw.write("\t\t<descripcion>" + plantas.get(i).getDescripcion() + "</descripcion>");
				bw.newLine();
	
				bw.write("\t</planta>");
				bw.newLine();
			} else if(plantas.get(i).getCantidad() <= 0) {
				modificarPlantasBajaXML(ficheros[2], plantas.get(i));
			}
		}
			
		bw.write("</plantas>");

		bw.close();
	}

	public static void modificarPlantasDAT(File [] ficheros, ArrayList<Planta> plantas) throws IOException {
		ficheros[1].delete();
		
		RandomAccessFile raf = new RandomAccessFile(ficheros[1], "rw");
		
		for(int i = 0; i < plantas.size(); i++) {
			if(plantas.get(i).getCantidad() > 0) {
				raf.writeInt(plantas.get(i).getCodigo());
				raf.writeFloat(plantas.get(i).getPrecio());
				raf.writeInt(plantas.get(i).getCantidad());
			} else if(plantas.get(i).getCantidad() <= 0) {
				modificarPlantasBajaDAT(ficheros[3], plantas.get(i));
			}
		}

		raf.close();
	}

	public static void modificarEmpleadoDAT(File fichero, ArrayList<Empleado> empleados) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
		
		oos.writeObject(empleados);

		oos.close();
	}
	
	public static void modificarPlantasBajaXML(File fichero, Planta planta) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		ArrayList<String> datosBaja = new ArrayList<>();
		String linea;
		
		while((linea = br.readLine()) != null) {
			datosBaja.add(linea);
		}
		
		br.close();

		BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
		
		if(datosBaja.size() <= 1) {
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();

			bw.write("<plantas>");
			bw.newLine();
		}
		
		for(int i = 0; i < datosBaja.size() - 1; i++) {
			bw.write(datosBaja.get(i));
			bw.newLine();
		}

		bw.write("\t<planta>");
		bw.newLine();

		bw.write("\t\t<codigo>" + planta.getCodigo() + "</codigo>");
		bw.newLine();

		bw.write("\t\t<nombre>" + planta.getNombre() + "</nombre>");
		bw.newLine();
			
		bw.write("\t\t<foto>" + planta.getFoto() + "</foto>");
		bw.newLine();

		bw.write("\t\t<descripcion>" + planta.getDescripcion() + "</descripcion>");
		bw.newLine();
			
		bw.write("\t</planta>");
		bw.newLine();
			
		bw.write("</plantas>");
		bw.newLine();

		bw.close();
	}
	
	public static void modificarPlantasBajaDAT(File ficheroDAT, Planta planta) {
		try(RandomAccessFile raf = new RandomAccessFile(ficheroDAT, "rw")) {
			long longitudTotal = raf.length();
			
			raf.seek(longitudTotal);
			
			raf.writeInt(planta.getCodigo());
			raf.writeFloat(planta.getPrecio());
			raf.writeInt(planta.getCantidad());
		} catch(IOException ioe) {
			System.err.println("Fichero " + ioe.getMessage() +", cerrando programa.");
			System.exit(1);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void modificarEmpleadosBajaDAT(File ficheroDAT, Empleado empleado) throws IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(ficheroDAT));
		String linea = br.readLine();
		ArrayList<Empleado> empleadosBaja = new ArrayList<>();

		br.close();

		if(linea != null) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroDAT));
			
			empleadosBaja = (ArrayList<Empleado>) ois.readObject();

			ois.close();
		}

		empleadosBaja.add(empleado);

		modificarEmpleadoDAT(ficheroDAT, empleadosBaja);
	}
}
