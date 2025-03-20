package modelo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class PrintExample {

	/**
	 * Genera la imagen del código de barras a partir del código dado.
	 */
	public static BufferedImage generateBarcodeImage(String codigo) throws Exception {
		Map<EncodeHintType, Object> opciones = new HashMap<>();
		opciones.put(EncodeHintType.MARGIN, 2);
		MultiFormatWriter escritor = new MultiFormatWriter();
		BitMatrix matriz = escritor.encode(codigo, BarcodeFormat.CODE_128, 300, 100, opciones);
		BufferedImage imagen = new BufferedImage(matriz.getWidth(), matriz.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < matriz.getWidth(); x++) {
			for (int y = 0; y < matriz.getHeight(); y++) {
				imagen.setRGB(x, y, matriz.get(x, y) ? 0x000000 : 0xFFFFFF);
			}
		}
		return imagen;
	}

	/**
	 * Genera un BufferedImage con el contenido del ticket (logo, datos y código de
	 * barras). Ajusta dimensiones, fuentes y posiciones según lo requiera tu recibo
	 * térmico.
	 */
	public static BufferedImage generateTicketImage(String tipoPago, String placaVehiculo, String horaEntrada,
			String horaSalida, long horasParqueo, long minutosParqueo, double total) throws Exception {
		// Dimensiones del ticket (ajusta según el ancho de tu papel térmico)
		int width = 384; // Por ejemplo, 384px (ajusta según resolución de la impresora)
		int height = 600; // Altura aproximada, puede ser variable
		BufferedImage ticketImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = ticketImg.createGraphics();

		// Fondo blanco
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		// Configurar fuente para los textos
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial", Font.BOLD, 12));

		int yPos = 20;
		// Dibujar el logo
		try {
			BufferedImage logo = ImageIO.read(new File("src/imagenes/LogoOlima.jpg"));
			int logoWidth = 210;
			int logoHeight = 210;
			g2d.drawImage(logo, 0, yPos, logoWidth, logoHeight, null);
		} catch (Exception e) {
			System.out.println("No se pudo cargar el logo: " + e.getMessage());
		}
		yPos += 220;

		// Agregar datos del ticket
		g2d.drawString("NIT: 41387939", 10, yPos);
		yPos += 15;
		g2d.drawString("Celular: 3152132435", 10, yPos);
		yPos += 15;
		g2d.drawString("Direccion: ", 10, yPos);
		yPos += 20;
		g2d.drawString("===============================", 10, yPos);
		yPos += 15;
		g2d.drawString("          OLIMA PARQUEADERO   ", 10, yPos);
		yPos += 15;
		g2d.drawString("===============================", 10, yPos);
		yPos += 15;
		g2d.drawString("Placa: " + placaVehiculo, 10, yPos);
		yPos += 15;
		g2d.drawString("Hora de Entrada: " + horaEntrada, 10, yPos);
		yPos += 15;
		g2d.drawString("Hora de Salida: " + horaSalida, 10, yPos);
		yPos += 15;
		g2d.drawString("Horas de Parqueo: " + horasParqueo, 10, yPos);
		yPos += 15;
		g2d.drawString("Minutos de Parqueo: " + minutosParqueo, 10, yPos);
		yPos += 15;
		g2d.drawString("Total a Pagar: " + total, 10, yPos);
		yPos += 15;
		g2d.drawString("Tipo de Pago: " + tipoPago, 10, yPos);
		yPos += 15;
		g2d.drawString("===============================", 10, yPos);
		yPos += 15;
		g2d.drawString("   ¡Gracias por su visita!    ", 10, yPos);
		yPos += 15;
		g2d.drawString("===============================", 10, yPos);
		yPos += 10;

		g2d.dispose();
		return ticketImg;
	}

	public static BufferedImage generateTicketImageIngreso(String placaVehiculo, String horaEntrada, String numero)
			throws Exception {

		int width = 384;
		int height = 600;
		BufferedImage ticketImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = ticketImg.createGraphics();

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial", Font.BOLD, 12));

		int yPos = 20;

		try {
			BufferedImage logo = ImageIO.read(new File("src/imagenes/LogoOlima.jpg"));
			int logoWidth = 210;
			int logoHeight = 210;
			g2d.drawImage(logo, 0, yPos, logoWidth, logoHeight, null);
		} catch (Exception e) {
			System.out.println("No se pudo cargar el logo: " + e.getMessage());
		}
		yPos += 220;

		g2d.drawString("NIT: 41387939", 10, yPos);
		yPos += 15;
		g2d.drawString("Celular: 3152132435", 10, yPos);
		yPos += 15;
		g2d.drawString("Direccion: ", 10, yPos);
		yPos += 20;
		g2d.drawString("===============================", 10, yPos);
		yPos += 15;
		g2d.drawString("          OLIMA PARQUEADERO   ", 10, yPos);
		yPos += 15;
		g2d.drawString("===============================", 10, yPos);
		yPos += 15;
		g2d.drawString("Placa: " + placaVehiculo, 10, yPos);
		yPos += 15;
		g2d.drawString("Hora de Entrada: " + horaEntrada, 10, yPos);
		yPos += 15;
		g2d.drawString("Tipo de Pago: " + numero, 10, yPos);
		yPos += 15;
		g2d.drawString("===============================", 10, yPos);
		yPos += 15;
		g2d.drawString("   ¡Gracias por su visita!    ", 10, yPos);
		yPos += 15;
		g2d.drawString("===============================", 10, yPos);
		yPos += 10;

		BufferedImage barcodeImg = generateBarcodeImage(placaVehiculo);
		g2d.drawImage(barcodeImg, -20, yPos, barcodeImg.getWidth(), barcodeImg.getHeight(), null);

		g2d.dispose();
		return ticketImg;
	}

	/**
	 * Envía la imagen del ticket a la impresora predeterminada.
	 */
	public static void printTicketImage(BufferedImage ticketImage) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setPrintable(new Printable() {
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if (pageIndex != 0) {
					return NO_SUCH_PAGE;
				}
				Graphics2D g2d = (Graphics2D) graphics;
				// Mover la imagen al área imprimible
				g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
				// Opcional: escalar la imagen para que se ajuste al área imprimible
				double scaleX = pageFormat.getImageableWidth() / ticketImage.getWidth();
				double scaleY = pageFormat.getImageableHeight() / ticketImage.getHeight();
				double scale = Math.min(scaleX, scaleY);
				g2d.scale(scale, scale);
				g2d.drawImage(ticketImage, 0, 0, null);
				return PAGE_EXISTS;
			}
		});
		// Muestra el diálogo de impresión (puedes omitirlo si deseas impresión
		// silenciosa)
		if (printerJob.printDialog()) {
			try {
				printerJob.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método para probar la generación e impresión del ticket como imagen.
	 */
	public static void main(String[] args) {
		try {
			// Datos de ejemplo para el ticket
			BufferedImage ticketImg = generateTicketImage("Deportista", "ABC123", "10:00", "12:00", 2, 5, 2000.0);
			// Envía la imagen a la impresora
			printTicketImage(ticketImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
