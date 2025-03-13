package ventanaprovisional;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPago extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAceptar;
	private JLabel lblFondo, lblGanancia, lblHora;
	private JComboBox<String> jcomboPrecio;
	private String[] precio = { "Efectivo", "Nequi" };

	/**
	 * Constructor
	 */
	public PanelPago() {
		inicializarComponentes();

		setLayout(null);
		setVisible(true);
	}

	/**
	 * Metodo para inicializar los componentes
	 */
	public void inicializarComponentes() {
		ImageIcon icono = new ImageIcon("Parqueadero/src/imagenes/ProvDatos.png");
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1000, 700);

		btnAceptar = new JButton("Buscar");
		lblGanancia = new JLabel("Ganancia");
		lblHora = new JLabel("Hora");

		btnAceptar.setBounds(108, 419, 196, 45);

		lblGanancia.setBounds(699, 419, 196, 45);

		lblHora.setBounds(300, 50, 150, 50);

		btnAceptar.setOpaque(true);
		lblGanancia.setOpaque(true);

		jcomboPrecio = new JComboBox<>(precio);
		jcomboPrecio.setBounds(296, 339, 150, 26);

		add(lblFondo);

		add(jcomboPrecio);
		add(lblHora);
		add(btnAceptar);
		add(lblGanancia);
	}

	public JLabel getLblFondo() {
		return lblFondo;
	}

	public void setLblFondo(JLabel lblFondo) {
		this.lblFondo = lblFondo;
	}

	public JLabel getLblGanancia() {
		return lblGanancia;
	}

	public void setLblGanancia(JLabel lblGanancia) {
		this.lblGanancia = lblGanancia;
	}

	public JComboBox<String> getjcomboPrecio() {
		return jcomboPrecio;
	}

	public void setjcomboPrecio(JComboBox<String> jcomboPrecio) {
		this.jcomboPrecio = jcomboPrecio;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JLabel getLblHora() {
		return lblHora;
	}

	public void setLblHora(JLabel lblHora) {
		this.lblHora = lblHora;
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

	public JButton getbtnAceptar() {
		return btnAceptar;
	}

	public void setbtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

}
