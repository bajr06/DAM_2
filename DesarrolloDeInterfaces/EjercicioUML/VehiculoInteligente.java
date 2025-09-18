package EjercicioUML;

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


	public getId() {
		return id;
	}
	public setId(int id) {
		this.id = id;
	}

	public getModelo() {
		return modelo;
	}
	public setModelo(String modelo) {
		this.modelo = modelo;
	}


}
