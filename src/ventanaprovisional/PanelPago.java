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
	private JButton btnAceptar, btnVolver;
	private JLabel lblFondo, lblHora, lblMinutos, lblPlaca, lblCelular, lblPrecio;
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
		ImageIcon icono = new ImageIcon("parqueadero/src/imagenes/ProvDatos.png");
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1000, 700);

		btnAceptar = new JButton("Buscar");
		btnVolver = new JButton("Volver");
		lblCelular = new JLabel("Celular");
		lblHora = new JLabel("Hora");
		lblMinutos = new JLabel("Minutos");
		lblPlaca = new JLabel("Placa");
		lblPrecio = new JLabel("Precio");

		btnAceptar.setBounds(108, 419, 196, 45);
		btnVolver.setBounds(258, 419, 196, 45);

		lblHora.setBounds(300, 50, 150, 50);
		lblMinutos.setBounds(400, 50, 150, 50);
		lblCelular.setBounds(200, 50, 150, 50);
		lblPlaca.setBounds(500, 50, 150, 50);
		lblPlaca.setBounds(600, 50, 150, 50);
		btnAceptar.setOpaque(true);

		jcomboPrecio = new JComboBox<>(precio);
		jcomboPrecio.setBounds(296, 339, 150, 26);

		add(lblFondo);

		add(lblPrecio);
		add(lblCelular);
		add(lblPlaca);
		add(lblMinutos);
		add(jcomboPrecio);
		add(lblHora);
		add(btnAceptar);
		add(btnVolver);

	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	public JLabel getLblMinutos() {
		return lblMinutos;
	}

	public void setLblMinutos(JLabel lblMinutos) {
		this.lblMinutos = lblMinutos;
	}

	public JLabel getLblFondo() {
		return lblFondo;
	}

	public void setLblFondo(JLabel lblFondo) {
		this.lblFondo = lblFondo;
	}

	public JComboBox<String> getjcomboPrecio() {
		return jcomboPrecio;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
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

	public JLabel getLblPlaca() {
		return lblPlaca;
	}

	public void setLblPlaca(JLabel lblPlaca) {
		this.lblPlaca = lblPlaca;
	}

	public JLabel getLblCelular() {
		return lblCelular;
	}

	public void setLblCelular(JLabel lblCelular) {
		this.lblCelular = lblCelular;
	}

}
