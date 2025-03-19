package ventanaprovisional;

import java.awt.Font;
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
	private JLabel lblFondo, lblHora, lblMinutos, lblPlaca, lblCelular, lblPrecio, lblTipoDeCobro;
	private JComboBox<String> jcomboPrecio;
	private String[] precio = { "Efectivo", "Nequi" };

	private Font fuente = new Font("Verdana", Font.BOLD, 28);

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
		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/CobroDeMotos.png"));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1280, 685, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1280, 685);

		btnAceptar = new JButton("");
		btnVolver = new JButton("");
		lblCelular = new JLabel("Celular");
		lblHora = new JLabel("Hora");
		lblMinutos = new JLabel("Minutos");
		lblPlaca = new JLabel("Placa");
		lblPrecio = new JLabel("Precio");
		lblTipoDeCobro = new JLabel("Cobro");

		btnAceptar.setBounds(20, 435, 215, 100);
		btnVolver.setBounds(20, 580, 215, 100);

		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setOpaque(false);

		btnAceptar.setContentAreaFilled(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setOpaque(false);

		lblPrecio.setBounds(595, 350, 215, 80);
		lblHora.setBounds(595, 580, 120, 80);
		lblMinutos.setBounds(720, 580, 120, 80);
		lblCelular.setBounds(595, 273, 215, 80);
		lblPlaca.setBounds(595, 205, 215, 80);
		lblTipoDeCobro.setBounds(595, 430, 215, 80);

		jcomboPrecio = new JComboBox<>(precio);
		jcomboPrecio.setBounds(595, 513, 215, 70);

		lblPrecio.setFont(fuente);
		lblHora.setFont(fuente);
		lblMinutos.setFont(fuente);
		lblCelular.setFont(fuente);
		lblPlaca.setFont(fuente);
		lblTipoDeCobro.setFont(fuente);

		jcomboPrecio.setFont(fuente);

		btnAceptar.setFont(fuente);
		btnVolver.setFont(fuente);

		add(lblTipoDeCobro);
		add(lblPrecio);
		add(lblCelular);
		add(lblPlaca);
		add(lblMinutos);
		add(jcomboPrecio);
		add(lblHora);
		add(btnAceptar);
		add(btnVolver);

		add(lblFondo);

	}

	public JLabel getLblTipoDeCobro() {
		return lblTipoDeCobro;
	}

	public void setLblTipoDeCobro(JLabel lblTipoDeCobro) {
		this.lblTipoDeCobro = lblTipoDeCobro;
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
