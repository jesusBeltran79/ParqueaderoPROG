package ventanaprovisional;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaProvisional extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelProvAgregarMoto ppam;
	private PanelPago pp;

	public VentanaProvisional() {
		inicializarComponentes();

		setTitle("");
		setSize(1366, 805);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void cambiarPanel(JPanel panel) {

		getContentPane().setVisible(false);
		setContentPane(panel);
		panel.setVisible(true);
	}

	public void inicializarComponentes() {
		ppam = new PanelProvAgregarMoto();
		pp = new PanelPago();
		setContentPane(ppam);
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

}
