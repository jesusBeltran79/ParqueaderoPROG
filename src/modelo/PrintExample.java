package modelo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.OrientationRequested;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class PrintExample {
	public static void main(String[] args) {

		String codigo = "KUJ21P"; // Código de barras que seguimos el formato "LETRALETRALETRANUMERONUMEROLETRA"

		// Configuración de la codificación
		Map<EncodeHintType, Object> opciones = new HashMap<>();
		opciones.put(EncodeHintType.MARGIN, 2); // Margen del código de barras

		try {
			MultiFormatWriter escritor = new MultiFormatWriter();
			BitMatrix matriz = escritor.encode(codigo, BarcodeFormat.CODE_128, 300, 100, opciones);

			BufferedImage imagen = new BufferedImage(matriz.getWidth(), matriz.getHeight(), BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < matriz.getWidth(); x++) {
				for (int y = 0; y < matriz.getHeight(); y++) {
					imagen.setRGB(x, y, matriz.get(x, y) ? 0x000000 : 0xFFFFFF); // Color negro o blanco
				}
			}

			// Guardar la imagen como archivo PNG
			File archivo = new File("codigoDeBarras.png");
			ImageIO.write(imagen, "PNG", archivo);

			System.out.println("Código de barras generado exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Aquí puedes agregar la lógica para obtener los datos del ticket
	String nombreCliente = "Juan Pérez";
	String placaVehiculo = "ABC123";
	String horaEntrada = "10:00";
	String horaSalida = "12:00";
	double costoPorHora = 2000.0;
	int horasParqueo = 2; // Cambia esto según la lógica que implementes
	double total = horasParqueo * costoPorHora;

	// Imprimir el ticket
	// imprimirTicket(nombreCliente, placaVehiculo, horaEntrada, horaSalida,
	// horasParqueo, costoPorHora, total);

	private static void imprimirTicket(String nombreCliente, String placaVehiculo, String horaEntrada,
			String horaSalida, int horasParqueo, double costoPorHora, double total) {
		String ticket = "===============================\n" + "          TICKET PARQUEADERO   \n"
				+ "===============================\n" + "Cliente: " + nombreCliente + "\n" + "Placa: " + placaVehiculo
				+ "\n" + "Hora de Entrada: " + horaEntrada + "\n" + "Hora de Salida: " + horaSalida + "\n"
				+ "Horas de Parqueo: " + horasParqueo + "\n" + String.format("Costo por Hora: $%.2f\n", costoPorHora)
				+ String.format("Total a Pagar: $%.2f\n", total) + "===============================\n"
				+ "   ¡Gracias por su visita!    \n" + "===============================\n";

		// Imprimir el ticket
		try {
			// Convertir el ticket a un arreglo de bytes
			byte[] bytes = ticket.getBytes("UTF-8");

			// Crear un objeto de impresión
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(new Copies(1));
			pras.add(OrientationRequested.PORTRAIT);
			PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

			if (printService != null) {
				DocPrintJob job = printService.createPrintJob();
				job.print(new SimpleDoc(bytes, flavor, null), pras);
				System.out.println("Ticket enviado a la impresora.");
			} else {
				System.out.println("No se encontró una impresora predeterminada.");
			}
		} catch (PrintException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}