package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class ContadorDiario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double ganancia;
	private LocalDate dia;

	public ContadorDiario() {

	}

	public ContadorDiario(double ganancia, LocalDate dia) {
		super();
		this.ganancia = ganancia;
		this.dia = dia;
	}

	public LocalDate getDia() {
		return dia;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "ContadorDiario [ganancia=" + ganancia + " dia=" + dia + "]";
	}

}
