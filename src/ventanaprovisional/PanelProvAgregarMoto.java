package ventanaprovisional;

import java.awt.Font;
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

	private Font fuente = new Font("Verdana", Font.BOLD, 24);

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
		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/AdministracionDeMotos.png"));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1366, 768);

		btnBuscar = new JButton("");
		btnVolver = new JButton("");
		lblGanancia = new JLabel("Ganancia");
		txfNumero = new JTextField("Numero");
		txfPlaca = new JTextField("Placa");

		btnBuscar.setBounds(20, 500, 215, 100);
		btnVolver.setBounds(20, 660, 215, 100);
		lblGanancia.setBounds(699, 419, 196, 45);
		txfNumero.setBounds(610, 390, 215, 80);
		txfPlaca.setBounds(610, 270, 215, 80);

		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setOpaque(false);

		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setOpaque(false);

		lblGanancia.setOpaque(true);

		jcomboUbicacion = new JComboBox<>(ubicaciones);
		jcomboUbicacion.setBounds(610, 510, 215, 80);

		jcomboPago = new JComboBox<>(tipo);
		jcomboPago.setBounds(610, 630, 215, 80);

		btnBuscar.setFont(fuente);
		btnVolver.setFont(fuente);
		lblGanancia.setFont(fuente);
		txfNumero.setFont(fuente);
		txfPlaca.setFont(fuente);
		jcomboUbicacion.setFont(fuente);
		jcomboPago.setFont(fuente);

		// Se añaden los componentes antes del fondo
		add(jcomboUbicacion);
		add(jcomboPago);
		add(txfNumero);
		add(txfPlaca);
		add(btnBuscar);
		add(btnVolver);
		add(lblGanancia);

		// Ahora agregamos el fondo AL FINAL para que quede atrás
		add(lblFondo);
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
