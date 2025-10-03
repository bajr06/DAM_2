/*
class Persona (nombre : String, primerApellido : String, edad : Int) {
	
	var nombre = nombre
	var primerApellido = primerApellido
	var edad = edad
	*/

class Persona(nombre : String, primerApellido : String, edad : Int) {}
fun main() {
	val persona1 = Persona("Pepito", "Grillo", 26)
	print(persona1.nombre)
}

