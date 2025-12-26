/*
using MongoDB.Driver;
using Ejercicios8.Modelos;

namespace Ejercicios8
{
	public class Principal
	{
		static void Main(string [] args)
		{
			// Conexión a la base de datos MongoDB.
			MongoClient Cliente = new MongoClient("mongodb://localhost:27017");
			IMongoDatabase database = Cliente.GetDatabase("PruebasDAM2");
			IMongoCollection<Usuario> UsuariosDB = database.GetCollection<Usuario>("Usuarios");

			// Insertar un nuevo usuario en la base de datos.
			Usuario nuevoUsuario = new Usuario
			{
				Id = "694292c8b34fb2107d99f821",
				Nombre = "Jose María de Lucas Plata",
				Edad = 27
			};	
			UsuariosDB.InsertOne(nuevoUsuario);
			
			
			// Recuperar e imprimir todos los usuarios de la base de datos.
			List<Usuario> listaUsuarios = UsuariosDB.Find(d => true).ToList();

			foreach(Usuario leerUsuario in listaUsuarios)
			{
				Console.WriteLine("Identificador = {0}, Nombre = {1}, Edad = {2}", leerUsuario.Id, leerUsuario.Nombre, leerUsuario.Edad);
			}

			
			// Actualizar un usuario existente en la base de datos.
			Usuario nuevoUsuario2 = new Usuario
			{
				Id = "694292c8b34fb2107d99f821",
				Nombre = "Bryan Andreu Jiménez Rojas",
				Edad = 19
			};
			UsuariosDB.ReplaceOne(d => d.Id == "694292c8b34fb2107d99f821", nuevoUsuario2);
			
			// Eliminar un usuario de la base de datos.
			UsuariosDB.DeleteOne(d => d.Id == "694292c8b34fb2107d99f821");
		}
	}
}
*/
