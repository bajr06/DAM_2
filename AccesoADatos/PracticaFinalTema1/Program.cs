using System;

namespace Program {
	class Program {
		static void Main(string [] args) {
			Console.WriteLine("Hola Mundo");

			int numero1 = 1;
			int numero2 = 5;

			Console.WriteLine("El resultado de la suma es: " + (numero1 + numero2));

			string frase = Console.ReadLine();

			Console.WriteLine("La frase que el usuario escribió es: \"" + frase +"\"");
		}
	}
}
