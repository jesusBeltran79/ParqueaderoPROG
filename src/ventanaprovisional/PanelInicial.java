package ventanaprovisional;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInicial extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblFondo;
	private JButton btnIngresarAdmin, btnIngresarMostrar;

	public PanelInicial() {
		// TODO Auto-generated constructor stub
		inicializarComponentes();

		setLayout(null);
		setVisible(true);
	}

	public void inicializarComponentes() {
		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Inicio.png"));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1280, 720);

		btnIngresarAdmin = new JButton("");
		btnIngresarMostrar = new JButton("");

		btnIngresarAdmin.setBounds(250, 435, 300, 100);
		btnIngresarMostrar.setBounds(755, 435, 300, 100);

		btnIngresarAdmin.setContentAreaFilled(false);
		btnIngresarAdmin.setBorderPainted(false);
		btnIngresarAdmin.setOpaque(false);

		btnIngresarMostrar.setContentAreaFilled(false);
		btnIngresarMostrar.setBorderPainted(false);
		btnIngresarMostrar.setOpaque(false);

		add(btnIngresarAdmin);
		add(btnIngresarMostrar);

		add(lblFondo);
	}

	public JLabel getLblFondo() {
		return lblFondo;
	}

	public void setLblFondo(JLabel lblFondo) {
		this.lblFondo = lblFondo;
	}

	public JButton getBtnIngresarAdmin() {
		return btnIngresarAdmin;
	}

	public void setBtnIngresarAdmin(JButton btnIngresarAdmin) {
		this.btnIngresarAdmin = btnIngresarAdmin;
	}

	public JButton getBtnIngresarMostrar() {
		return btnIngresarMostrar;
	}

	public void setBtnIngresarMostrar(JButton btnIngresarMostrar) {
		this.btnIngresarMostrar = btnIngresarMostrar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
