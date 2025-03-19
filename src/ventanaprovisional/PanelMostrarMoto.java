package ventanaprovisional;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelMostrarMoto extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblFondo;
	private JButton btnVolver;
	private JTable jtblMotos;
	private Font fuente = new Font("Times new Roman", Font.BOLD, 20);
	private Font fuente2 = new Font("Times new Roman", Font.BOLD, 17);

	public PanelMostrarMoto() {
		// TODO Auto-generated constructor stub
		inicializarComponentes();

		setLayout(null);
		setVisible(true);

	}

	public void inicializarComponentes() {
		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/TablaDeMotos.png"));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1280, 685, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1280, 685);

		btnVolver = new JButton("");
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setOpaque(false);
		btnVolver.setBounds(20, 573, 215, 100);

		String[] columnas = { "Placa", "Telefono", "Ubicacion", "Fecha de entrada", "Hora" };
		jtblMotos = new JTable(new DefaultTableModel(null, columnas));
		jtblMotos.setBackground(Color.WHITE);
		jtblMotos.setForeground(new Color(0, 40, 105));
		jtblMotos.setFont(fuente2);

		// Configuración del encabezado de la tabla
		jtblMotos.getTableHeader().setBackground(new Color(0, 15, 40));
		jtblMotos.getTableHeader().setForeground(Color.WHITE);
		jtblMotos.getTableHeader().setFont(fuente);

		// Envolver la tabla en un JScrollPane
		JScrollPane scrollPane = new JScrollPane(jtblMotos);
		scrollPane.setBounds(251, 272, 800, 262);
		// Opcional: forzar la visualización de la barra vertical solo cuando sea
		// necesario
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane);
		add(btnVolver);
		add(lblFondo);
	}

	public JLabel getLblFondo() {
		return lblFondo;
	}

	public void setLblFondo(JLabel lblFondo) {
		this.lblFondo = lblFondo;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getJtblMotos() {
		return jtblMotos;
	}

	public void setJtblMotos(JTable jtblMotos) {
		this.jtblMotos = jtblMotos;
	}

	public Font getFuente() {
		return fuente;
	}

	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}

}
