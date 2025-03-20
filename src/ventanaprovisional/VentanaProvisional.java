package ventanaprovisional;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaProvisional extends JFrame {

	private static final long serialVersionUID = 1L;

	// Paneles existentes
	private PanelProvAgregarMoto ppam = new PanelProvAgregarMoto();
	private PanelPago pp = new PanelPago();
	private PanelInicial pl = new PanelInicial();
	private PanelMostrarMoto pm = new PanelMostrarMoto();
	private PanelAdmin pa = new PanelAdmin();

	// Campo de texto para capturar el input del escáner (puede estar oculto)
	private JTextField qrInputField;

	// Lista de listeners para el evento de QR
	private List<QRScanListener> qrScanListeners = new ArrayList<>();

	public VentanaProvisional() {
		inicializarComponentes();

		setTitle("Sistema de Parqueadero");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Método para cambiar el panel actual.
	 */
	public void cambiarPanel(JPanel panel) {
		getContentPane().setVisible(false);
		setContentPane(panel);
		panel.setVisible(true);
		revalidate();
		repaint();
	}

	/**
	 * Inicializa los componentes de la ventana, incluyendo los paneles y el campo
	 * para capturar el input del escáner.
	 */
	public void inicializarComponentes() {
		// Inicializa los paneles
		pl = new PanelInicial();
		ppam = new PanelProvAgregarMoto();
		pp = new PanelPago();
		pm = new PanelMostrarMoto();
		pa = new PanelAdmin();

		// Configura el panel inicial como contenido principal
		setContentPane(pl);

		// Crea el campo de texto para el QR
		// Este campo puede estar oculto (por ejemplo, 0 de alto) o fuera de la vista,
		// ya que el lector actúa como teclado.
		qrInputField = new JTextField();
		qrInputField.setColumns(20);
		// Puedes hacerlo no visible en la interfaz si lo deseas
		qrInputField.setVisible(true);

		// Agrega un KeyListener para detectar cuando se termina de "teclear" el código
		qrInputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String qrData = qrInputField.getText().trim();
					if (!qrData.isEmpty()) {
						// Notifica a los listeners registrados
						notificarQRScan(qrData);
						// Limpia el campo para la siguiente lectura
						qrInputField.setText("");
					}
				}
			}
		});

		// Agregamos el campo al frame, por ejemplo en el sur, aunque esté oculto
		add(qrInputField, BorderLayout.SOUTH);
		// Solicita el foco en este campo para que siempre reciba el input
		qrInputField.requestFocusInWindow();
	}

	/**
	 * Permite registrar un listener para recibir notificaciones cuando se escanee
	 * un QR.
	 * 
	 * @param listener El listener a registrar.
	 */
	public void addQRScanListener(QRScanListener listener) {
		qrScanListeners.add(listener);
	}

	/**
	 * Notifica a todos los listeners que se ha detectado un QR.
	 * 
	 * @param qrData El contenido leído del QR.
	 */
	private void notificarQRScan(String qrData) {
		for (QRScanListener listener : qrScanListeners) {
			listener.onQRScanned(qrData);
		}
	}

	// --- Getters y setters para los paneles ---

	public PanelAdmin getPa() {
		return pa;
	}

	public void setPa(PanelAdmin pa) {
		this.pa = pa;
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

	public PanelMostrarMoto getPm() {
		return pm;
	}

	public void setPm(PanelMostrarMoto pm) {
		this.pm = pm;
	}

	// --- Interfaz para los listeners de QR ---

	public interface QRScanListener {
		/**
		 * Se invoca cuando se detecta y se procesa la información de un QR.
		 * 
		 * @param qrData El contenido obtenido del QR.
		 */
		void onQRScanned(String qrData);
	}
}
