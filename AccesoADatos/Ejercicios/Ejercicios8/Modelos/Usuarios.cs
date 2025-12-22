using System;
using System.Collections.Generic;
using System.Text;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Ejercicios8.Modelos
{
    public class Usuarios
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; }

        [BsonElement("nombre")]
        public string Nombre { get; set; }

        [BsonElement("edad")]
        public int Edad { get; set; }
    }
}
