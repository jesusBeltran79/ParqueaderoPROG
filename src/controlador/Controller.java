package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.ContadorDiario;
import modelo.ContadorDiarioDao;
import modelo.Moto;
import modelo.MotoDao;
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "buscarMoto":
			buscarMoto();
			break;
		case "aceptarPago":
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
					agregar.getLlegada(), salida, false, agregar.getTipoDeCobro(), "Nequi");
			vp.getPpam().getTxfNumero().setText(actual.getNumeroTelefono());
			vp.getPpam().getTxfPlaca().setText(actual.getPlaca());
			m.update(agregar, actual);
			JOptionPane.showMessageDialog(null, "Precio" + m.pago(actual), "TRATAMIENTO", 1);

		} else {
			LocalDateTime llegadaNueva = LocalDateTime.now();
			agregar = m.find(agregar);
			ubicacion = (String) vp.getPpam().getJcomboUbicacion().getSelectedItem();
			tipoPago = (String) vp.getPpam().getJcomboPago().getSelectedItem();
			actual = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), ubicacion, llegadaNueva, null, true,
					tipoPago, "");
			vp.getPpam().getTxfNumero().setText(actual.getNumeroTelefono());
			vp.getPpam().getTxfPlaca().setText(actual.getPlaca());
			m.update(agregar, actual);
			JOptionPane.showMessageDialog(null, "Moto ya existe ", "CREAR", 0);
		}

	}

	public void ganancia() {

	}

}
