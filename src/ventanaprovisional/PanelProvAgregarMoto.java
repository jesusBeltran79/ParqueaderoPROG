package ventanaprovisional;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelProvAgregarMoto extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnBuscar, btnVolver;
	private JLabel lblFondo, lblGanancia;
	private JTextField txfPlaca, txfNumero;
	private JComboBox<String> jcomboUbicacion;
	private String[] ubicaciones = { "A", "B", "C" };
	private JComboBox<String> jcomboPago;
	private String[] tipo = { "Deportista", "Evento", "Normal" };

	/**
	 * Constructor
	 */
	public PanelProvAgregarMoto() {
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

		btnBuscar = new JButton("Buscar");
		btnVolver = new JButton("Volver");
		lblGanancia = new JLabel("Ganancia");
		txfNumero = new JTextField("Numero");
		txfPlaca = new JTextField("Placa");

		btnBuscar.setBounds(108, 419, 196, 45);
		btnVolver.setBounds(401, 419, 196, 45);
		lblGanancia.setBounds(699, 419, 196, 45);
		txfNumero.setBounds(50, 50, 150, 50);
		txfPlaca.setBounds(300, 50, 150, 50);

		btnBuscar.setOpaque(true);
		btnVolver.setOpaque(true);
		lblGanancia.setOpaque(true);

		jcomboUbicacion = new JComboBox<>(ubicaciones);
		jcomboUbicacion.setBounds(296, 339, 150, 26);

		jcomboPago = new JComboBox<>(tipo);
		jcomboPago.setBounds(500, 339, 150, 26);

		add(lblFondo);

		add(jcomboUbicacion);
		add(jcomboPago);
		add(txfNumero);
		add(txfPlaca);
		add(btnBuscar);
		add(btnVolver);
		add(lblGanancia);
	}

	public JButton getbtnBuscar() {
		return btnBuscar;
	}

	public void setbtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
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

	public JTextField getTxfPlaca() {
		return txfPlaca;
	}

	public void setTxfPlaca(JTextField txfPlaca) {
		this.txfPlaca = txfPlaca;
	}

	public JTextField getTxfNumero() {
		return txfNumero;
	}

	public void setTxfNumero(JTextField txfNumero) {
		this.txfNumero = txfNumero;
	}

	public JComboBox<String> getJcomboUbicacion() {
		return jcomboUbicacion;
	}

	public void setJcomboUbicacion(JComboBox<String> jcomboUbicacion) {
		this.jcomboUbicacion = jcomboUbicacion;
	}

	public String[] getubicaciones() {
		return ubicaciones;
	}

	public void setubicaciones(String[] ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public String[] getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(String[] ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public JComboBox<String> getJcomboPago() {
		return jcomboPago;
	}

	public void setJcomboPago(JComboBox<String> jcomboPago) {
		this.jcomboPago = jcomboPago;
	}

	public String[] getTipo() {
		return tipo;
	}

	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}

}
