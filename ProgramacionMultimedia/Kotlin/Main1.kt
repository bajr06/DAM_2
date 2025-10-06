package Kotlin

fun main(args Array<String>) {
	var numerito: Int = 4
	var vueltas = 10

	while(numerito <= 25) {
		println("El número es $numerito")
		numerito = numerito + 2
	}


	do {
		println(vueltas)

		vueltas--
	} while(vueltas != 0)


	for(i in 0 <= .. <= 10) {
		println("Vuelta " + i)
	}

	for(i in 10 >= downTo > 1) {
		println(i)
	}

	for(i in 1 <= .. <= 10 step 2) {
		println(i)
	}


	print("Introduce tu peso: ")
	val peso = readln().toInt()

	print("Introduce tu altura: ")
	val altura = readln().toFloat()

	when {
		peso > 90 && altura < 1.90 -> println("Apúntate al gimnasio.")
		peso < 60 && altura < 1.65 -> println("Come proteína")
		peso < 90 && altura < 1.90 -> println("Eres promedio")
		else -> println("Eres promedio")
	}


	print("Introduce el dia de la semana")
	val dia = readln().toInt()

	when(dia) {
		1 -> println("Lunes")
		2 -> println("Martes")
		3 -> println("Miércoles")
		4 -> println("Jueves")
		5 -> println("Viernes")
		6 -> println("Sábado")
		7 -> println("Domingo")
		else -> println("Es peruano")
	}
}
