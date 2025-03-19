package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import modelo.ContadorDiario;
import modelo.ContadorDiarioDao;
import modelo.Moto;
import modelo.MotoDao;
import ventanaprovisional.PanelInicial;
import ventanaprovisional.PanelProvAgregarMoto;
import ventanaprovisional.VentanaProvisional;

public class Controller implements ActionListener {
	private VentanaProvisional vp;
	private MotoDao m;
	private Moto actual;
	private ContadorDiarioDao cd;
	private ContadorDiario contador;
	private LocalDate hoy;

	public Controller() {

		hoy = LocalDate.now();
		vp = new VentanaProvisional();
		m = new MotoDao();
		actual = new Moto();
		cd = new ContadorDiarioDao();
		if (cd.add(new ContadorDiario(0, hoy))) {
			cd.add(new ContadorDiario(0, hoy));
		}
		inicializarComponentes();

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

		vp.getPm().getBtnVolver().addActionListener(this);
		vp.getPm().getBtnVolver().setActionCommand("VolverAPrincipalDesdeMostrar");

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
		default:
			break;
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
			System.out.println(m.showAll());
			return;
		} else if (m.add(agregar) == 2) {
			LocalDateTime salida = LocalDateTime.now();
			agregar = m.find(agregar);
			actual = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), agregar.getUbicacion(),
					agregar.getLlegada(), salida, true, agregar.getTipoDeCobro(), null);
			vp.getPpam().getTxfNumero().setText(actual.getNumeroTelefono());
			vp.getPpam().getTxfPlaca().setText(actual.getPlaca());
			m.update(agregar, actual);

			vp.getPp().getLblPlaca().setText(actual.getPlaca());
			vp.getPp().getLblCelular().setText(actual.getNumeroTelefono());
			Duration duracion = Duration.between(actual.getLlegada(), actual.getSalida());
			vp.getPp().getLblHora().setText(duracion.toHoursPart() + "");
			vp.getPp().getLblMinutos().setText(duracion.toMinutesPart() + "");
			vp.getPp().getLblPrecio().setText(m.pago(actual) + "");

			vp.cambiarPanel(vp.getPp());

		} else {
			LocalDateTime llegadaNueva = LocalDateTime.now();
			agregar = m.find(agregar);
			ubicacion = (String) vp.getPpam().getJcomboUbicacion().getSelectedItem();
			tipoPago = (String) vp.getPpam().getJcomboPago().getSelectedItem();
			actual = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), ubicacion, llegadaNueva, null, true,
					tipoPago, "");

			m.update(agregar, actual);

		}

	}

	public void pago() {
		String tipoCobro = (String) vp.getPp().getJcomboPrecio().getSelectedItem();
		actual = new Moto(actual.getPlaca(), actual.getNumeroTelefono(), actual.getUbicacion(), actual.getLlegada(),
				actual.getSalida(), false, actual.getTipoDeCobro(), tipoCobro);
		m.update(actual, actual);

		vp.cambiarPanel(vp.getPpam());
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

			matriz[0] = moto.getPlaca();
			matriz[1] = moto.getNumeroTelefono();
			matriz[2] = moto.getUbicacion();
			matriz[3] = fechaStr;
			matriz[4] = horaStr;

			modeloTabla.addRow(matriz);
		}
	}

}
