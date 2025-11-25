package EjercicioUML;

import java.time.LocalDate;

public class VehiculoInteligente {
	int id;
	String modelo;
	boolean sensoresActivos;
	double bateriaNivel;
	LocalDate fechaActivacion;

	
	public VehiculoInteligente() {
	}

	public VehiculoInteligente(int id, String modelo, boolean sensoresActivos, double bateriaNivel, LocalDate fechaActivacion) {
		this.id = id;
		this.modelo = modelo;
		this.sensoresActivos = sensoresActivos;
		this.bateriaNivel = bateriaNivel;
		this.fechaActivacion = fechaActivacion;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


}
