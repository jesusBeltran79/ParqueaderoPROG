package ventanaprovisional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAdmin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblGanancias;
	private JButton btnVolver, btnAceptar;
	private JTextField txfFecha;
	private JComboBox<String> jcomboPrecio;
	private String[] precio = { "Efectivo", "Nequi" };

	public PanelAdmin() {
		inicializarComponentes();

		setLayout(null);
		setVisible(true);
	}

	public void inicializarComponentes() {

		lblGanancias = new JLabel("Ganancia");
		btnVolver = new JButton("Volver");
		btnAceptar = new JButton("Aceptar");
		txfFecha = new JTextField("Fecha");

		lblGanancias.setBounds(100, 50, 150, 50);
		btnVolver.setBounds(200, 350, 150, 50);
		btnAceptar.setBounds(500, 450, 150, 50);
		txfFecha.setBounds(600, 550, 150, 50);

		jcomboPrecio = new JComboBox<>(precio);
		jcomboPrecio.setBounds(595, 513, 215, 70);

		add(lblGanancias);
		add(btnAceptar);
		add(jcomboPrecio);
		add(btnVolver);
		add(btnAceptar);
		add(txfFecha);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JLabel getLblGanancias() {
		return lblGanancias;
	}

	public void setLblGanancias(JLabel lblGanancias) {
		this.lblGanancias = lblGanancias;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JComboBox<String> getJcomboPrecio() {
		return jcomboPrecio;
	}

	public void setJcomboPrecio(JComboBox<String> jcomboPrecio) {
		this.jcomboPrecio = jcomboPrecio;
	}

	public String[] getPrecio() {
		return precio;
	}

	public void setPrecio(String[] precio) {
		this.precio = precio;
	}

	public JTextField getTxfFecha() {
		return txfFecha;
	}

	public void setTxfFecha(JTextField txfFecha) {
		this.txfFecha = txfFecha;
	}

}
