package Ejercicios3;

public class CocheAutonomo extends VehiculoInteligente{
	int nivelAutonomia;
	double velocidadMaxima;
	String matricula;
	int numeroPasajeros;
	String softwareVersion;

	public CocheAutonomo() {
	}

	public CocheAutonomo(int nivelAutonomia, double velocidadMaxima, String matricula, int numeroPasajeros, String softwareVersion) {
		this.nivelAutonomia = nivelAutonomia;
		this.velocidadMaxima = velocidadMaxima;
		this.matricula = matricula;
		this.numeroPasajeros = numeroPasajeros;
		this.softwareVersion = softwareVersion;
	}

	public int getNivelAutonomia() {
		return nivelAutonomia;
	}
	public void setNivelAutonomia(int nivelAutonomia) {
		this.nivelAutonomia = nivelAutonomia;
	}

	public double getVelocidadMaxima() {
		return velocidadMaxima;
	}
	public void setVelocidadMaxima(double velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getNumeroPasajeros() {
		return numeroPasajeros;
	}
	public void setNumeroPasajeros(int numeroPasajeros) {
		this.numeroPasajeros = numeroPasajeros;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public void mostrarInfo() {
		IO.println("Nivel de Autonomia: " + nivelAutonomia +
		"\nVelocidad Máxima: " + velocidadMaxima +
		"\nMatricula: " + matricula +
		"\nNúmero de Pasajeros: " + numeroPasajeros +
		"\nVersion de Software: " + softwareVersion);
	}

	public void activarModoAutonomo() {
		IO.println("Modo autónomo activado");
	}
}
