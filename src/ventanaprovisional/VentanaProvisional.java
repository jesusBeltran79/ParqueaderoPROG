package ventanaprovisional;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaProvisional extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelProvAgregarMoto ppam = new PanelProvAgregarMoto();
	private PanelPago pp = new PanelPago();
	private PanelInicial pl = new PanelInicial();
	private PanelMostrarMoto pm = new PanelMostrarMoto();

	public VentanaProvisional() {
		inicializarComponentes();

		setTitle("");
		setSize(1280, 720);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void cambiarPanel(JPanel panel) {

		getContentPane().setVisible(false);
		setContentPane(panel);
		panel.setVisible(true);
		revalidate();
		repaint();
	}

	public void inicializarComponentes() {
		pl = new PanelInicial();
		ppam = new PanelProvAgregarMoto();
		pp = new PanelPago();
		pm = new PanelMostrarMoto();
		setContentPane(pl);
	}

	public PanelPago getPp() {
		return pp;
	}

	public void setPp(PanelPago pp) {
		this.pp = pp;
	}

	public PanelProvAgregarMoto getPpam() {
		return ppam;
	}

	public void setPpam(PanelProvAgregarMoto ppam) {
		this.ppam = ppam;
	}

	public PanelInicial getPl() {
		return pl;
	}

	public void setPl(PanelInicial pl) {
		this.pl = pl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PanelMostrarMoto getPm() {
		return pm;
	}

	public void setPm(PanelMostrarMoto pm) {
		this.pm = pm;
	}

}
