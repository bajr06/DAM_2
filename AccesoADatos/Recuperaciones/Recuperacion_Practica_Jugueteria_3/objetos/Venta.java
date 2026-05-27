package objetos;

import java.sql.Date;

public class Venta {
	private int idVenta;
	private int idEmpleado;
	private int idJuguete;
	private int idStand;
	private int idZona;
	private Date fecha;
	private double monto;
	private String tipoPago;

	public Venta() {}

	public Venta(int idVenta, int idEmpleado, int idJuguete, int idStand, int idZona, Date fecha, double monto, String tipoPago) {
		this.idVenta = idVenta;
		this.idEmpleado = idEmpleado;
		this.idJuguete = idJuguete;
		this.idStand = idStand;
		this.idZona = idZona;
		this.fecha = fecha;
		this.monto = monto;
		this.tipoPago = tipoPago;
	}

	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public int getIdJuguete() {
		return idJuguete;
	}
	public void setIdJuguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}
	
	public int getIdStand() {
		return idStand;
	}
	public void setIdStand(int idStand) {
		this.idStand = idStand;
	}
	
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
}
