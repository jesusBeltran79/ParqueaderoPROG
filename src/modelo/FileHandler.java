package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase utilitaria para manejar operaciones de entrada y salida con archivos.
 * Esta clase proporciona métodos para leer y escribir archivos serializados
 * (binarios) y de texto plano.
 */
public class FileHandler {

	private static Scanner sc; // Para leer archivos de texto plano
	private static PrintWriter pw; // Para escribir archivos de texto plano
	private static File archivo; // Referencia al archivo en el sistema
	private static final String FOLDER_NAME = "data"; // Nombre de la carpeta donde se almacenan los archivos

	private static FileOutputStream fos; // Flujo de salida para archivos serializados
	private static ObjectOutputStream oos; // Para escribir objetos serializados
	private static FileInputStream fis; // Flujo de entrada para archivos serializados
	private static ObjectInputStream ois; // Para leer objetos serializados

	/**
	 * Escribe un objeto serializado en un archivo.
	 * 
	 * @param url     La ruta del archivo dentro de la carpeta 'data'.
	 * @param content El objeto que será serializado y escrito en el archivo.
	 */
	public static void writeSerialized(String url, Object content) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(content); // Escribe el objeto serializado
			fos.close();
			oos.close();
		} catch (IOException e) {
			System.out.println("Ocurrió un error en la escritura del archivo serializado");
		}
	}

	/**
	 * Lee un objeto serializado desde un archivo.
	 * 
	 * @param url La ruta del archivo dentro de la carpeta 'data'.
	 * @return El objeto leído desde el archivo, o null si ocurre un error.
	 */
	public static Object readSerialized(String url) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			Object content = ois.readObject(); // Lee el objeto serializado
			fis.close();
			ois.close();
			return content;
		} catch (IOException e) {
			System.out.println("Ocurrió un error de lectura en el archivo serializado");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer los datos del archivo serializado (ClassNotFound)");
		}
		return null;
	}

	/**
	 * Verifica si la carpeta 'data' existe; si no, la crea.
	 */
	public static void checkFolder() {
		archivo = new File(FOLDER_NAME);
		if (archivo.exists() && archivo.isDirectory()) {
			return;
		} else {
			archivo.mkdir(); // Crea la carpeta
		}
	}

	/**
	 * Escribe un contenido en un archivo de texto plano.
	 * 
	 * @param url     La ruta del archivo dentro de la carpeta 'data'.
	 * @param content El contenido que será escrito en el archivo.
	 */
	public static void writeFile(String url, String content) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			pw = new PrintWriter(archivo);
			pw.write(content); // Escribe el contenido en el archivo
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error en la escritura del archivo (texto plano)");
		} catch (IOException e) {
			System.out.println("Error en la creación del archivo (texto plano)");
		}
	}

	/**
	 * Lee el contenido de un archivo de texto plano.
	 * 
	 * @param url La ruta del archivo dentro de la carpeta 'data'.
	 * @return El contenido del archivo en formato String, o null si ocurre un
	 *         error.
	 */
	public static String readFile(String url) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			sc = new Scanner(archivo);
			String content = "";
			while (sc.hasNext()) {
				content += sc.nextLine() + "\n"; // Lee el archivo línea por línea
			}
			return content;
		} catch (IOException e) {
			System.out.println("Error en la creación del archivo (texto plano)");
		}
		return null;
	}
}
