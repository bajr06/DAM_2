package Persona1

class Persona1 constructor (nombre: String, edad: Int){
	var this.nombre: String = nombre
	var this.edad: Int = edad

	override fun toString (): String {
		return "Persona[Nombre: " + nombre + ", Edad: " + edad + "]"
	}

	fun imprimirPersona () {
		println(toString())
	}
}
