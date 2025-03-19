package modelo;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Moto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String placa;
	private String numeroTelefono;
	private String ubicacion;
	private LocalDateTime llegada;
	private LocalDateTime salida;
	private boolean estaParqueado;
	private String tipoDeCobro;
	private String tipoDePago;

	public Moto() {
	}

	public Moto(String placa, String numeroTelefono, String ubicacion, LocalDateTime llegada, LocalDateTime salida,
			boolean estaParqueado, String tipoDeCobro, String tipoDePago) {
		super();
		this.placa = placa;
		this.numeroTelefono = numeroTelefono;
		this.ubicacion = ubicacion;
		this.llegada = llegada;
		this.salida = salida;
		this.estaParqueado = estaParqueado;
		this.tipoDeCobro = tipoDeCobro;
		this.tipoDePago = tipoDePago;
	}

	public Moto(String placa, LocalDateTime llegada, LocalDateTime salida, String tipoDeCobro, String tipoDePago) {
		super();
		this.placa = placa;
		this.llegada = llegada;
		this.salida = salida;
		this.tipoDeCobro = tipoDeCobro;
		this.tipoDePago = tipoDePago;
	}

	public String getTipoDeCobro() {
		return tipoDeCobro;
	}

	public void setTipoDeCobro(String tipoDeCobro) {
		this.tipoDeCobro = tipoDeCobro;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public LocalDateTime getLlegada() {
		return llegada;
	}

	public void setLlegada(LocalDateTime llegada) {
		this.llegada = llegada;
	}

	public LocalDateTime getSalida() {
		return salida;
	}

	public void setSalida(LocalDateTime salida) {
		this.salida = salida;
	}

	public boolean isEstaParqueado() {
		return estaParqueado;
	}

	public void setEstaParqueado(boolean estaParqueado) {
		this.estaParqueado = estaParqueado;
	}

	public double precioEvento() {
		Duration duracion = Duration.between(llegada, salida);
		long minutos = duracion.toMinutes();
		double precio = 0;

		if (minutos < 60) {
			if (minutos < 15) {
				return 600;
			} else if (minutos < 30) {
				return 1200;
			} else if (minutos < 45) {
				return 1800;
			} else {
				return 2400;
			}
		}

		if (minutos < 720) {
			if (minutos == 60) {
				return 3000;
			}
			precio = 3000;
			minutos = minutos - 60;
			while (minutos > 0) {
				minutos = minutos - 15;
				if (minutos < 0) {
					break;
				}
				precio += 750;
				if (precio >= 15000) {
					return 15000;
				}
			}

			if (precio % 100 != 0) {
				return precio + 50;
			}
			return precio;
		}
		precio = 15000;
		minutos = duracion.toMinutes();
		minutos = minutos - 720;
		while (minutos > 0) {
			precio += 3000;
			minutos = minutos - 60;
			if (minutos < 0) {
				return precio;
			}

		}
		return precio;
	}

	public double precioDeportista() {
		Duration duracion = Duration.between(llegada, salida);
		long minutos = duracion.toMinutes();
		if (minutos < 180) {
			return 5500;
		} else {
			double precio = precio(true);
			precio = precio + 5500;

			if (precio >= 10000) {
				return 10000;
			}
			return precio;
		}
	}

	public double precio(boolean esDeportista) {
		Duration duracion = Duration.between(llegada, salida);
		long minutos = duracion.toMinutes();

		if (esDeportista == true) {
			minutos = minutos - 180;
		}

		if (minutos < 60) {
			if (minutos < 15) {
				return 500;
			} else if (minutos < 30) {
				return 1000;
			} else if (minutos < 45) {
				return 1500;
			} else {
				return 2000;
			}
		} else if (minutos < 120) {
			if (minutos < 75) {
				return 2500;
			} else if (minutos < 90) {
				return 3100;
			} else if (minutos < 105) {
				return 3700;
			} else {
				return 4300;
			}
		} else if (minutos < 180) {
			if (minutos < 135) {
				return 5000;
			} else if (minutos < 150) {
				return 5300;
			} else if (minutos < 165) {
				return 5500;
			} else {
				return 5800;
			}
		} else if (minutos < 300) {
			if (minutos < 195) {
				return 6000;
			} else if (minutos < 210) {
				return 6500;
			} else if (minutos < 225) {
				return 7000;
			} else if (minutos < 240) {
				return 7500;
			} else if (minutos < 255) {
				return 8000;
			} else if (minutos < 270) {
				return 8500;
			} else if (minutos < 285) {
				return 9000;
			} else {
				return 9500;
			}
		} else if (minutos > 300 && minutos < 720) {
			return 10000;
		} else {
			double precio = 10000;
			minutos = minutos - 720;
			while (minutos > 0) {
				precio += 2000;
				minutos = minutos - 60;
				if (minutos < 0) {
					return precio;
				}

			}
			return precio;
		}

	}

	@Override
	public String toString() {
		return "Moto [placa=" + placa + ", numeroTelefono=" + numeroTelefono + ", ubicacion=" + ubicacion + ", llegada="
				+ llegada + ", salida=" + salida + ", estaParqueado=" + estaParqueado + ", tipoDeCobro=" + tipoDeCobro
				+ ", tipoDePago=" + tipoDePago + "]";
	}

}
