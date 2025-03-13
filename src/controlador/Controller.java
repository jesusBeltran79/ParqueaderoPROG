package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import modelo.Moto;
import modelo.MotoDao;
import ventanaprovisional.VentanaProvisional;

public class Controller implements ActionListener {
	private VentanaProvisional vp;
	private MotoDao m;

	public Controller() {
		vp = new VentanaProvisional();
		m = new MotoDao();
		inicializarComponentes();

	}

	public void inicializarComponentes() {
		vp.getPpam().getbtnBuscar().addActionListener(this);
		vp.getPpam().getbtnBuscar().setActionCommand("buscarMoto");
		vp.getPpam().getBtnVolver().addActionListener(this);
		vp.getPpam().getBtnVolver().setActionCommand("volverPrincipal");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "buscarMoto":
			buscarMoto();
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
			Moto nueva = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), agregar.getUbicacion(),
					agregar.getLlegada(), salida, false, agregar.getTipoDeCobro(), "Nequi");
			vp.getPpam().getTxfNumero().setText(nueva.getNumeroTelefono());
			vp.getPpam().getTxfPlaca().setText(nueva.getPlaca());
			m.update(agregar, nueva);

			JOptionPane.showMessageDialog(null, "El precio es " + m.pago(agregar), "CREAR", 0);
		} else {
			LocalDateTime llegadaNueva = LocalDateTime.now();
			agregar = m.find(agregar);
			ubicacion = (String) vp.getPpam().getJcomboUbicacion().getSelectedItem();
			tipoPago = (String) vp.getPpam().getJcomboPago().getSelectedItem();
			Moto nueva = new Moto(agregar.getPlaca(), agregar.getNumeroTelefono(), ubicacion, llegadaNueva, null, true,
					tipoPago, "");
			vp.getPpam().getTxfNumero().setText(nueva.getNumeroTelefono());
			vp.getPpam().getTxfPlaca().setText(nueva.getPlaca());
			m.update(agregar, nueva);
			JOptionPane.showMessageDialog(null, "Moto ya existe ", "CREAR", 0);
		}

	}
}
