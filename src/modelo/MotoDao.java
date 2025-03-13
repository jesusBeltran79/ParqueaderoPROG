package modelo;

import java.util.ArrayList;

public class MotoDao {

	private ArrayList<Moto> listaMoto;
	private ArrayList<Moto> listaPagos;
	private final String FILE_NAME = "Moto.csv";
	private final String SERIAL_NAME = "Moto.dat";
	private final String FILE_GANANCIA = "MetodoPago.csv";

	public MotoDao() {
		FileHandler.checkFolder();
		readSerialized();

	}

	public String showAll() {
		String rta = "";
		if (listaMoto.isEmpty()) {
			return "No hay vuelos en la lista";
		} else {
			for (Moto Moto : listaMoto) {
				rta += Moto + "\n";
			}
			return rta;
		}
	}

	public ArrayList<Moto> getAll() {
		return listaMoto;
	}

	public int add(Moto newData) {
		Moto found = find(newData);
		if (find((newData)) == null) {
			listaMoto.add((newData));
			writeFile();
			writeSerialized();
			return 1;
		} else if (found.isEstaParqueado() == true) {
			return 2;
		} else {
			return 3;
		}
	}

	public double pago(Moto salida) {
		Moto found = find(salida);
		if (found != null) {
			listaPagos.add(found);
			double precio = 0;
			switch (found.getTipoDeCobro()) {
			case "Deportista":
				precio = found.precioDeportista();
				break;

			case "Evento":
				precio = found.precioEvento();
				break;

			case "Normal":
				precio = found.precio(false);
				break;
			default:
				precio = found.precio(false);
				break;
			}
			writeFilePago();
			return precio;
		}
		return -1;

	}

	public boolean delete(Moto toDelete) {
		Moto found = find((toDelete));
		if (found != null) {
			listaMoto.remove(found);
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	public Moto find(Moto toFind) {
		if (!listaMoto.isEmpty()) {
			for (Moto moto : listaMoto) {
				if (moto.getPlaca().equals(toFind.getPlaca())) {
					return moto;
				}
			}
		}
		return null;
	}

	public boolean update(Moto previous, Moto newData) {
		Moto found = find((previous));
		if (found != null) {
			listaMoto.remove(found);
			listaMoto.add((newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	public void writeFilePago() {
		String content = "";
		for (Moto moto : listaMoto) {
			content += moto.getPlaca() + ";";
			content += moto.getLlegada() + ";";
			content += moto.getSalida() + ";";
			content += moto.getTipoDeCobro() + ";";
			content += moto.getTipoDePago() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_GANANCIA, content);
	}

	public void writeFile() {
		String content = "";
		for (Moto m : listaMoto) {
			content += m.getPlaca() + ";";
			content += m.getNumeroTelefono() + ";";
			content += m.getUbicacion() + ";";
			content += m.getLlegada() + ";";
			content += m.getSalida() + ";";
			content += m.isEstaParqueado() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	public void writeSerialized() {
		FileHandler.writeSerialized(SERIAL_NAME, listaMoto);

	}

	@SuppressWarnings("unchecked")
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIAL_NAME);

		if (content == null) {
			System.out.println("ES NULO NORMAL");
			listaMoto = new ArrayList<>();
		} else {
			listaMoto = (ArrayList<Moto>) content;
		}

	}

	public ArrayList<Moto> getListaMoto() {
		return listaMoto;
	}

	public void setListaMoto(ArrayList<Moto> listaMoto) {
		this.listaMoto = listaMoto;
	}

	public ArrayList<Moto> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(ArrayList<Moto> listaPagos) {
		this.listaPagos = listaPagos;
	}

}
