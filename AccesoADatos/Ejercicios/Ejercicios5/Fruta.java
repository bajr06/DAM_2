package Ejercicios5;

import java.util.ArrayList;

public class Fruta {
	String nombre;
	String tipo;
	String color;
	String origen;
	double precio;
	String temporada;
	ArrayList<String> nutrientes;


	public Fruta(String nombre, String tipo, String color, String origen, double precio, String temporada) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.color = color;
		this.origen = origen;
		this.precio = precio;
		this.temporada = temporada;
		this.nutrientes = null;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}


	public ArrayList<String> getNutrientes() {
		return nutrientes;
	}

	public void setNutrientes(ArrayList<String> nutrientes) {
		this.nutrientes = nutrientes;
	}


	@Override
	public String toString() {
		return "Fruta [Nombre = " + nombre + ", Tipo = " + tipo + ", Color = " + color + ", Origen = " + origen + ", Precio = " + precio + ", Temporada = " + temporada + "]";
	}
}
