package ventanaprovisional;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelAdmin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblGanancias;
	private JButton btnVolver, btnNequi, btnEfectivo, btnAceptar;
	private JTextField txfFecha;
	private JTable jtblMotos;
	private Font fuente = new Font("Times new Roman", Font.BOLD, 28);

	public PanelAdmin() {
		inicializarComponentes();

		setLayout(null);
		setVisible(true);
	}

	public void inicializarComponentes() {
		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/TablaDeMotos.png"));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1280, 685, Image.SCALE_SMOOTH);
		lblGanancias = new JLabel(new ImageIcon(imagenEscalada));
		lblGanancias.setBounds(0, 0, 1280, 685);

		lblGanancias = new JLabel("Ganancia");
		btnEfectivo = new JButton("Efectivo");
		btnNequi = new JButton("Nequi");
		btnVolver = new JButton("Volver");
		btnAceptar = new JButton("Aceptar");
		txfFecha = new JTextField("Fecha");

		lblGanancias.setBounds(100, 50, 150, 50);
		btnEfectivo.setBounds(300, 150, 150, 50);
		btnNequi.setBounds(400, 250, 150, 50);
		btnVolver.setBounds(200, 350, 150, 50);
		btnAceptar.setBounds(500, 450, 150, 50);
		txfFecha.setBounds(600, 550, 150, 50);

		add(lblGanancias);
		add(btnAceptar);
		add(btnEfectivo);
		add(btnNequi);
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

	public JButton getBtnNequi() {
		return btnNequi;
	}

	public void setBtnNequi(JButton btnNequi) {
		this.btnNequi = btnNequi;
	}

	public JButton getBtnEfectivo() {
		return btnEfectivo;
	}

	public void setBtnEfectivo(JButton btnEfectivo) {
		this.btnEfectivo = btnEfectivo;
	}

	public JTextField getTxfFecha() {
		return txfFecha;
	}

	public void setTxfFecha(JTextField txfFecha) {
		this.txfFecha = txfFecha;
	}

}
