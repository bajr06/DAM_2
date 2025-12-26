package Ejercicios2.CentroEducativoCorregido;

import java.util.Date;

public class Ejercicios6Corregido {
	public static void main(String[] args) {
		// Creando profesores.
		Profesor profe1 = new Profesor("5678904T", "Txema", "González", 1000.0, 8, true);
		Profesor profe2 = new Profesor("5934122B", "Jose L.", "Álvarez", 1000.0, 0, true);

		// Creado personal de Admin.
		Administracion admin1 = new Administracion("6459912L", "Enrique", "Donoso", 1552.22, "Ingeniero", 3);
		Administracion admin2 = new Administracion("7889594K", "Eva", "Huertas", 1466.31, "Diplomada", 1);

		// Creando directivos.
		Directivo dir1 = new Directivo("5147331M", "Manuel", "de Castro", 1675.15, true, 'T');
		Directivo dir2 = new Directivo("5698443F", "Javier", "Pozo", 1623.04, false, 'M');

		System.out.println(profe1.toString());
		System.out.println(profe2.getSalario());
		System.out.println(admin1.toString());
		System.out.println(admin2.toString());
		System.out.println(dir1.isSalesiano());
		System.out.println(dir2.toString());

		// Creando módulos.
		Modulo uno = new Modulo("SMR", 1000, profe1, false);
		Modulo dos = new Modulo("Teleco", 2000, new Profesor("4767896A", "Jesús", "Martínez", 20000, 7, true), true);

		System.out.println(uno.toString());
		System.out.println(dos.toString());

		// Creando usuarios
		@SuppressWarnings("deprecation")
		Alumno Juan = new Alumno("147A", "Juan", "Perez", new Date(99, 2, 12), 'V', true, uno);
		@SuppressWarnings("deprecation")
		Alumno Ana = new Alumno("963147A", "Ana", "Díaz", new Date(110, 2, 12),'H', false, dos);

		System.out.println(Juan.toString());
		System.out.println(Ana.toString());
	}
}
