package ventanaprovisional;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelAdmin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblGanancias, lblFondo;
	private JButton btnVolver, btnAceptar;

	private JTable jtblMotos;
	private Font fuente = new Font("Times new Roman", Font.BOLD, 28);
	private Font fuente2 = new Font("Times new Roman", Font.BOLD, 17);

	private JComboBox<String> jcomboPrecio;
	private String[] precio = { "Efectivo", "Nequi", "Daviplata" };

	public PanelAdmin() {
		inicializarComponentes();

		setLayout(null);
		setVisible(true);
	}

	public void inicializarComponentes() {
		ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/AdministradorMotosGanancias.png"));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(1280, 685, Image.SCALE_SMOOTH);
		lblFondo = new JLabel(new ImageIcon(imagenEscalada));
		lblFondo.setBounds(0, 0, 1280, 685);

		lblGanancias = new JLabel("Ganancia");
		btnVolver = new JButton("");
		btnAceptar = new JButton("");

		lblGanancias.setBounds(730, 171, 150, 50);
		btnVolver.setBounds(20, 573, 215, 100);
		btnAceptar.setBounds(740, 260, 160, 60);

		jcomboPrecio = new JComboBox<>(precio);
		jcomboPrecio.setBounds(557, 262, 170, 60);

		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setOpaque(false);

		btnAceptar.setContentAreaFilled(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setOpaque(false);

		lblGanancias.setFont(fuente);

		jcomboPrecio.setFont(fuente);

		String[] columnas = { "Placa", "Hora entrada", "Hora salida", "Tipo de cobro", "Precio" };
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
		scrollPane.setBounds(220, 350, 800, 210);
		// Opcional: forzar la visualización de la barra vertical solo cuando sea
		// necesario
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane);

		add(btnAceptar);
		add(jcomboPrecio);
		add(btnVolver);
		add(btnAceptar);

		add(lblGanancias);
		add(lblFondo);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JLabel getLblFondo() {
		return lblFondo;
	}

	public void setLblFondo(JLabel lblFondo) {
		this.lblFondo = lblFondo;
	}

	public JTable getJtblMotos() {
		return jtblMotos;
	}

	public void setJtblMotos(JTable jtblMotos) {
		this.jtblMotos = jtblMotos;
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

}
