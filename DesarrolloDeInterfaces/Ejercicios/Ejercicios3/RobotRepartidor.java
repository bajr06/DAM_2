package Ejercicios3;

public class RobotRepartidor extends VehiculoInteligente {
	String tipoCarga;
	double capacidadLitros;
	String zonaOperacion;
	boolean resistenteAgua;
	int numeroRuedas;

	public RobotRepartidor() {
	}

	public RobotRepartidor(String tipoCarga, double capacidadLitros, String zonaOperacion, boolean resistenteAgua, int numeroRuedas) {
		this.tipoCarga = tipoCarga;
		this.capacidadLitros = capacidadLitros;
		this.zonaOperacion = zonaOperacion;
		this.resistenteAgua = resistenteAgua;
		this.numeroRuedas = numeroRuedas;
	}


	public String getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public double getCapacidadLitros() {
		return capacidadLitros;
	}
	public void setCapacidadLitros(double capacidadLitros) {
		this.capacidadLitros = capacidadLitros;
	}

	public String getZonaOperacion() {
		return zonaOperacion;
	}
	public void setZonaOperacion(String zonaOperacion) {
		this.zonaOperacion = zonaOperacion;
	}

	public boolean isResistenteAgua() {
		return resistenteAgua;
	}
	public void setResistenteAgua(boolean resistenteAgua) {
		this.resistenteAgua = resistenteAgua;
	}

	public int getNumeroRuedas() {
		return numeroRuedas;
	}
	public void setNumeroRuedas(int numeroRuedas) {
		this.numeroRuedas = numeroRuedas;
	}

	public void mostrarInfo() {
		IO.println("Tipo de Carga: " + tipoCarga +
		"\nCapacidadLitros: " + capacidadLitros +
		"\nZona de Operación: " + zonaOperacion +
		"\nResistente al Agua: " + resistenteAgua +
		"\nNúmero de ruedas: " + numeroRuedas);
	}

	public void iniciarReparto() {
		IO.println("Iniciar el reparto.");
	}
}
