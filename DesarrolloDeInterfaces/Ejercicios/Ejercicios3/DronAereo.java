package Ejercicios3;

public class DronAereo extends VehiculoInteligente{
	double altitudMaxima;
	int numeroHelices;
	double pesoCargaMax;
	boolean gpsIntegrado;
	String fabricante;
	
	public DronAereo() {
	}

	public DronAereo(double altitudMaxima, int numeroHelices, double pesoCargaMax, boolean gpsIntegrado, String fabricante) {
		this.altitudMaxima = altitudMaxima;
		this.numeroHelices = numeroHelices;
		this.pesoCargaMax = pesoCargaMax;
		this.gpsIntegrado = gpsIntegrado;
		this.fabricante = fabricante;
	}


	public double getAltitudMaxima() {
		return altitudMaxima;
	}
	public void setAltitudMaxima(double altitudMaxima) {
		this.altitudMaxima = altitudMaxima;
	}

	public int getNumeroHelices() {
		return numeroHelices;
	}
	public void setNumeroHelices(int numeroHelices) {
		this.numeroHelices = numeroHelices;
	}

	public double getPesoCargaMax() {
		return pesoCargaMax;
	}
	public void setPesoCargaMax(double pesoCargaMax) {
		this.pesoCargaMax = pesoCargaMax;
	}

	public boolean isGpsIntegrado() {
		return gpsIntegrado;
	}
	public void setGpsIntegrado(boolean gpsIntegrado) {
		this.gpsIntegrado = gpsIntegrado;
	}

	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void mostrarInfo() {
		IO.println("Altitud Máxima: " + altitudMaxima +
		"\nNúmero de Helices: " + numeroHelices +
		"\nPeso de Carga Máximo: " + pesoCargaMax +
		"\nGPS Integrado: " + gpsIntegrado +
		"\nFabricante: " + fabricante);
	}

	public void despegar() {
		IO.println("Despegando.");
	}
}
