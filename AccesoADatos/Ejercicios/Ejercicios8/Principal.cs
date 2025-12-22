using MongoDB.Driver;
using System;
using Ejercicios8.Modelos;

namespace Ejercicios8
{
    class Principal
    {
        static void Main(string[] args)
        {
            // Conexión a la base de datos MongoDB.
            var Cliente = new MongoClient("mongodb://localhost:27017");
            var database = Cliente.GetDatabase("PruebasDAM2");
            var UsuariosDB = database.GetCollection<Usuarios>("Usuarios");

            // Insertar un nuevo usuario en la base de datos.
            /*var nuevoUsuario = new Usuarios
            {
                Id = "694292c8b34fb2107d99f821",
                Nombre = "Bryan Jiménez",
                Edad = 19
            };
            UsuariosDB.InsertOne(nuevoUsuario);*/

            // Recuperar e imprimir todos los usuarios de la base de datos.
            /* List<Usuarios> listaUsuarios = UsuariosDB.Find(d=> true).ToList();*/

            // Actualizar un usuario existente en la base de datos.
            /* UsuariosDB.ReplaceOne(d => d.Id == "694292c8b34fb2107d99f821", nuevoUsuario);*/

            // Eliminar un usuario de la base de datos.
            UsuariosDB.DeleteOne(d => d.Id == "694292c8b34fb2107d99f821");
        }
    }
}
