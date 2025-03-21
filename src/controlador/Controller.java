package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.ContadorDiario;
import modelo.ContadorDiarioDao;
import modelo.Moto;
import modelo.MotoDao;
import modelo.PrintExample;
import ventanaprovisional.VentanaProvisional;

public class Controller implements ActionListener {
	private LocalDate hoy;
	private VentanaProvisional vp;
	private MotoDao m;
	private Moto actual;
	private ContadorDiarioDao cd;
	private ContadorDiario contador;

	public Controller() {

		cd = new ContadorDiarioDao();
		hoy = LocalDate.now();
		vp = new VentanaProvisional();
		m = new MotoDao();
		actual = new Moto();
		contador = cd.find(new ContadorDiario(0, hoy));
		if (contador == null) {
			cd.add(new ContadorDiario(0, hoy));
			contador = new ContadorDiario(0, hoy);
		}
		inicializarComponentes();
		// Suscribirse al evento de QR para que, al escanear, se ejecute la lógica
		vp.addQRScanListener(new VentanaProvisional.QRScanListener() {
			@Override
			public void onQRScanned(String qrData) {
				processQRScan(qrData);
			}
		});
	}

	public void inicializarComponentes() {
		vp.getPpam().getbtnBuscar().addActionListener(this);
		vp.getPpam().getbtnBuscar().setActionCommand("buscarMoto");
		vp.getPpam().getBtnVolver().addActionListener(this);
		vp.getPpam().getBtnVolver().setActionCommand("volverPrincipal");

		vp.getPp().getbtnAceptar().addActionListener(this);
		vp.getPp().getbtnAceptar().setActionCommand("aceptarPago");
		vp.getPp().getBtnVolver().addActionListener(this);
		vp.getPp().getBtnVolver().setActionCommand("volverPago");

		vp.getPl().getBtnIngresarAdmin().addActionListener(this);
		vp.getPl().getBtnIngresarAdmin().setActionCommand("ingresarAdmin");
		vp.getPl().getBtnIngresarMostrar().addActionListener(this);
		vp.getPl().getBtnIngresarMostrar().setActionCommand("ingresarMostrar");
		vp.getPl().getBtnAdmin().addActionListener(this);
		vp.getPl().getBtnAdmin().setActionCommand("ocultoAdmin");

		vp.getPm().getBtnVolver().addActionListener(this);
		vp.getPm().getBtnVolver().setActionCommand("VolverAPrincipalDesdeMostrar");

		vp.getPa().getBtnAceptar().addActionListener(this);
		vp.getPa().getBtnAceptar().setActionCommand("aceptarTipoAdmin");
		vp.getPa().getBtnVolver().addActionListener(this);
		vp.getPa().getBtnVolver().setActionCommand("volverAdmin");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "buscarMoto":
			buscarMoto();
			break;
		case "aceptarPago":
			pago();
			break;
		case "volverPago":
			vp.cambiarPanel(vp.getPpam());
			break;
		case "ingresarAdmin":
			cambioAAdmin();
			break;
		case "ingresarMostrar":
			cambioAMostrar();
			agregarMotos(m.getListaMoto());
			break;
		case "volverPrincipal":
			cambioInicialDesdeAdmin();
			break;
		case "VolverAPrincipalDesdeMostrar":
			cambioInicialDesdeMostrar();
			break;
		case "ocultoAdmin":
			acciones();
			break;
		case "volverAdmin":
			vp.cambiarPanel(vp.getPl());
			break;
		case "aceptarTipoAdmin":
			String combo = (String) vp.getPa().getJcomboPrecio().getSelectedItem();
			agregarMotosAdmin(m.getListaPagos(), combo);
			break;
		default:
			break;
		}
	}

	public void acciones() {
		vp.getPa().getLblGanancias().setText(contador.getGanancia() + "");
		String contraseñaCorrecta = "mamita";
		String contraseñaIngresada = JOptionPane.showInputDialog("Ingresa la contraseña:");
		if (contraseñaIngresada != null && contraseñaIngresada.equals(contraseñaCorrecta)) {
			vp.cambiarPanel(vp.getPa());
		} else {
			JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
		}
	}

	public void buscarMoto() {
		String placa = vp.getPpam().getTxfPlaca().getText();
		String numero = vp.getPpam().getTxfNumero().getText();
		String ubicacion = (String) vp.getPpam().getJcomboUbicacion().getSelectedItem();
		String tipoPago = (String) vp.getPpam().getJcomboPago().getSelectedItem();
		LocalDateTime llegada = LocalDateTime.now();

		Moto agregar = new Moto(placa, numero, ubicacion, llegada, null, true, tipoPago, "");

		if (m.add(agregar) == 1) {
			m.add(agregar);
			actual = agregar;
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			String horaEntrada = actual.getLlegada().format(timeFormatter);
			try {
				BufferedImage ticketImage = PrintExample.generateTicketImageIngreso(actual.getPlaca(), horaEntrada,
						actual.getNumeroTelefono());
				PrintExample.printTicketImage(ticketImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Moto creada con exito");

			return;
		} else if (m.add(agregar) == 2) {
			LocalDateTime salida = LocalDateTime.now();
			agregar = m.find(agregar);
			actual = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), agregar.getUbicacion(),
					agregar.getLlegada(), salida, true, agregar.getTipoDeCobro(), null);
			vp.getPpam().getTxfNumero().setText(actual.getNumeroTelefono());
			vp.getPpam().getTxfPlaca().setText(actual.getPlaca());
			m.update(agregar, actual);

			// Actualizar datos en PanelPago
			vp.getPp().getLblPlaca().setText(actual.getPlaca());
			vp.getPp().getLblCelular().setText(actual.getNumeroTelefono());
			Duration duracion = Duration.between(actual.getLlegada(), actual.getSalida());
			vp.getPp().getLblHora().setText(duracion.toHoursPart() + "");
			vp.getPp().getLblMinutos().setText(duracion.toMinutesPart() + "");
			vp.getPp().getLblPrecio().setText(m.pago(actual) + "");
			vp.getPp().getLblTipoDeCobro().setText(actual.getTipoDeCobro());

			vp.cambiarPanel(vp.getPp());
		} else {
			LocalDateTime llegadaNueva = LocalDateTime.now();
			agregar = m.find(agregar);
			ubicacion = (String) vp.getPpam().getJcomboUbicacion().getSelectedItem();
			tipoPago = (String) vp.getPpam().getJcomboPago().getSelectedItem();
			actual = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), ubicacion, llegadaNueva, null, true,
					tipoPago, "");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			String horaEntrada = actual.getLlegada().format(timeFormatter);
			try {
				// Generar la imagen del ticket
				BufferedImage ticketImage = PrintExample.generateTicketImageIngreso(actual.getPlaca(), horaEntrada,
						actual.getNumeroTelefono());
				// Enviar la imagen a la impresora
				PrintExample.printTicketImage(ticketImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Moto guardada con exito");
			m.update(agregar, actual);
		}
	}

	public void pago() {
		String tipoCobro = (String) vp.getPp().getJcomboPrecio().getSelectedItem();
		Moto motoAgregara = new Moto(actual.getPlaca(), actual.getNumeroTelefono(), actual.getUbicacion(),
				actual.getLlegada(), actual.getSalida(), false, actual.getTipoDeCobro(), tipoCobro);
		m.agregarPago(motoAgregara);
		m.update(actual, motoAgregara);
		actual = motoAgregara;
		JOptionPane.showMessageDialog(null, "Pago realizado con exito");
		vp.cambiarPanel(vp.getPpam());

		contador = cd.find(new ContadorDiario(0, hoy));
		double ganancia = contador.getGanancia() + m.pago(actual);
		cd.update(contador, new ContadorDiario(ganancia, hoy));
		contador = new ContadorDiario(ganancia, hoy);
		vp.getPa().getLblGanancias().setText(contador.getGanancia() + "");

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String horaEntrada = actual.getLlegada().format(timeFormatter);
		String horaSalida = actual.getSalida().format(timeFormatter);
		Duration duracion = Duration.between(actual.getLlegada(), actual.getSalida());
		long horas = duracion.toHoursPart();
		long minutos = duracion.toMinutesPart();
		double totalPagar = m.pago(actual);

		try {
			// Generar la imagen del ticket
			BufferedImage ticketImage = PrintExample.generateTicketImage(actual.getTipoDeCobro(), actual.getPlaca(),
					horaEntrada, horaSalida, horas, minutos, totalPagar);
			// Enviar la imagen a la impresora
			PrintExample.printTicketImage(ticketImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cambioAAdmin() {
		vp.cambiarPanel(vp.getPpam());
	}

	public void cambioAMostrar() {
		vp.cambiarPanel(vp.getPm());
	}

	public void cambioInicialDesdeAdmin() {
		vp.cambiarPanel(vp.getPl());
	}

	public void cambioInicialDesdeMostrar() {
		vp.cambiarPanel(vp.getPl());
	}

	public void agregarMotos(ArrayList<Moto> listaDeMotos) {
		String[] matriz = new String[5];
		DefaultTableModel modeloTabla = (DefaultTableModel) vp.getPm().getJtblMotos().getModel();
		modeloTabla.setRowCount(0);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		for (int i = 0; i < listaDeMotos.size(); i++) {
			Moto moto = listaDeMotos.get(i);
			LocalDateTime fecha = moto.getLlegada();
			String fechaStr = fecha.format(dateFormatter);
			String horaStr = fecha.format(timeFormatter);
			if (moto.isEstaParqueado()) {
				matriz[0] = moto.getPlaca();
				matriz[1] = moto.getNumeroTelefono();
				matriz[2] = moto.getUbicacion();
				matriz[3] = fechaStr;
				matriz[4] = horaStr;
				modeloTabla.addRow(matriz);
			}
		}
	}

	public void agregarMotosAdmin(ArrayList<Moto> listaDeMotos, String pago) {
		String[] matriz = new String[5];
		DefaultTableModel modeloTabla = (DefaultTableModel) vp.getPa().getJtblMotos().getModel();
		modeloTabla.setRowCount(0);
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		for (int i = 0; i < listaDeMotos.size(); i++) {
			Moto moto = listaDeMotos.get(i);
			LocalDateTime fecha = moto.getLlegada();
			LocalDateTime fechaSalida = moto.getSalida();
			String horaEntradaStr = fecha.format(timeFormatter);
			String horaSalidaStr = fechaSalida.format(timeFormatter);
			if (hoy.equals(moto.getSalida().toLocalDate())) {
				if (pago.toUpperCase().equals("NEQUI")) {
					if (moto.getTipoDePago().toUpperCase().equals("NEQUI")) {
						matriz[0] = moto.getPlaca();
						matriz[1] = horaEntradaStr;
						matriz[2] = horaSalidaStr;
						matriz[3] = moto.getTipoDeCobro();
						matriz[4] = m.pagoTabla(new Moto("", "", "", moto.getLlegada(), moto.getSalida(), false,
								moto.getTipoDeCobro(), "")) + "";
						modeloTabla.addRow(matriz);
					}
				}
			}
			if (hoy.equals(moto.getSalida().toLocalDate())) {
				if (pago.toUpperCase().equals("EFECTIVO")) {
					if (moto.getTipoDePago().toUpperCase().equals("EFECTIVO")) {
						matriz[0] = moto.getPlaca();
						matriz[1] = horaEntradaStr;
						matriz[2] = horaSalidaStr;
						matriz[3] = moto.getTipoDeCobro();
						matriz[4] = m.pagoTabla(new Moto("", "", "", moto.getLlegada(), moto.getSalida(), false,
								moto.getTipoDeCobro(), "")) + "";
						modeloTabla.addRow(matriz);
					}
				}
			}

			if (hoy.equals(moto.getSalida().toLocalDate())) {
				if (pago.toUpperCase().equals("DAVIPLATA")) {
					if (moto.getTipoDePago().toUpperCase().equals("DAVIPLATA")) {
						matriz[0] = moto.getPlaca();
						matriz[1] = horaEntradaStr;
						matriz[2] = horaSalidaStr;
						matriz[3] = moto.getTipoDeCobro();
						matriz[4] = m.pagoTabla(new Moto("", "", "", moto.getLlegada(), moto.getSalida(), false,
								moto.getTipoDeCobro(), "")) + "";
						modeloTabla.addRow(matriz);
					}
				}
			}
		}
	}

	/**
	 * Método para procesar el QR escaneado. Se asume que el dato recibido es la
	 * placa.
	 */
	public void processQRScan(String qrData) {
		// Buscamos la moto usando la placa proveniente del QR
		Moto motoEncontrada = m.find(new Moto(qrData, null, null, null, null, true, null, null));
		if (motoEncontrada == null) {
			JOptionPane.showMessageDialog(null, "No se encontró moto para la placa: " + qrData);
			return;
		}
		// Actualizamos la hora de salida
		LocalDateTime salida = LocalDateTime.now();
		actual = new Moto(motoEncontrada.getPlaca(), motoEncontrada.getNumeroTelefono(), motoEncontrada.getUbicacion(),
				motoEncontrada.getLlegada(), salida, true, motoEncontrada.getTipoDeCobro(),
				motoEncontrada.getTipoDePago());
		m.update(motoEncontrada, actual);

		// Actualizamos los labels del PanelPago
		vp.getPp().getLblPlaca().setText(actual.getPlaca());
		vp.getPp().getLblCelular().setText(actual.getNumeroTelefono());
		Duration duracion = Duration.between(actual.getLlegada(), actual.getSalida());
		vp.getPp().getLblHora().setText(duracion.toHoursPart() + "");
		vp.getPp().getLblMinutos().setText(duracion.toMinutesPart() + "");
		vp.getPp().getLblPrecio().setText(m.pago(actual) + "");
		vp.getPp().getLblTipoDeCobro().setText(actual.getTipoDeCobro());

		// Cambiamos al panel de pago
		vp.cambiarPanel(vp.getPp());
	}
}
