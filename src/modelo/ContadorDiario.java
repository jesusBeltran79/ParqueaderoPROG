package modelo;

import java.io.Serializable;
import java.sql.Date;

public class ContadorDiario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double ganancia;
	private double idFactura;
	private Date dia;

	public ContadorDiario() {

	}

	public ContadorDiario(double ganancia, double idFactura, Date dia) {
		super();
		this.ganancia = ganancia;
		this.idFactura = idFactura;
		this.dia = dia;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public double getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(double idFactura) {
		this.idFactura = idFactura;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "ContadorDiario [ganancia=" + ganancia + ", idFactura=" + idFactura + ", dia=" + dia + "]";
	}

}
