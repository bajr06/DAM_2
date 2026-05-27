package objetos;

import java.sql.Date;

public class Cambio {
	private int idCambio;
	private int idEmpleado;
	private int idJugueteOriginal;
	private int idJugueteNuevo;
	private String motivo;
	private Date fecha;
	private int standOrigen;
	private int idZonaOrigen;
	private int standDestino;
	private int idZonaDestino;

	public Cambio() {
	}

	public Cambio(int idCambio, int idEmpleado, int idJugueteOriginal, int idJugueteNuevo, String motivo, Date fecha, int standOrigen, int idZonaOrigen, int standDestino, int idZonaDestino) {
		this.idCambio = idCambio;
		this.idEmpleado = idEmpleado;
		this.idJugueteOriginal = idJugueteOriginal;
		this.idJugueteNuevo = idJugueteNuevo;
		this.motivo = motivo;
		this.fecha = fecha;
		this.standOrigen = standOrigen;
		this.idZonaOrigen = idZonaOrigen;
		this.standDestino = standDestino;
		this.idZonaDestino = idZonaDestino;
	}

	public int getIdCambio() {
		return idCambio;
	}
	public void setIdCambio(int idCambio) {
		this.idCambio = idCambio;
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdJugueteOriginal() {
		return idJugueteOriginal;
	}
	public void setIdJugueteOriginal(int idJugueteOriginal) {
		this.idJugueteOriginal = idJugueteOriginal;
	}
	
	public int getIdJugueteNuevo() {
		return idJugueteNuevo;
	}
	public void setIdJugueteNuevo(int idJugueteNuevo) {
		this.idJugueteNuevo = idJugueteNuevo;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
  
	public int getStandOrigen() {
		return standOrigen;
	}
	public void setStandOrigen(int standOrigen) {
		this.standOrigen = standOrigen;
	}
	
	public int getIdZonaOrigen() {
		return idZonaOrigen;
	}
	public void setIdZonaOrigen(int idZonaOrigen) {
		this.idZonaOrigen = idZonaOrigen;
	}
	
	public int getStandDestino() {
		return standDestino;
	}
	public void setStandDestino(int standDestino) {
		this.standDestino = standDestino;
	}
	
	public int getIdZonaDestino() {
		return idZonaDestino;
	}
	public void setIdZonaDestino(int idZonaDestino) {
		this.idZonaDestino = idZonaDestino;
	}
}
