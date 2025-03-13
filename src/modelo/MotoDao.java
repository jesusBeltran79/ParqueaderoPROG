package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MotoDao {

	private ArrayList<Moto> listaMoto;
	private final String FILE_NAME = "Moto.csv";
	private final String SERIAL_NAME = "Moto.dat";

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

	public boolean add(Moto newData) {
		if (find((newData)) == null) {
			listaMoto.add((newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
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

	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);

		if (content == null || content.equals("")) {
			listaMoto = new ArrayList<>();
		} else {
			listaMoto = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] cols = row.split(";");
				Moto temporal = new Moto();
				temporal.setPlaca(cols[0]);
				temporal.setNumeroTelefono(cols[1]);
				temporal.setUbicacion(cols[2]);
				temporal.setLlegada(LocalDateTime.parse(cols[3]));
				temporal.setSalida(LocalDateTime.parse(cols[4]));
				temporal.setEstaParqueado(Boolean.parseBoolean(cols[5]));
				listaMoto.add(temporal);
			}
		}
	}

	public void writeSerialized() {
		FileHandler.writeSerialized(SERIAL_NAME, listaMoto);
	}

	@SuppressWarnings("unchecked")
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIAL_NAME);

		if (content == null) {
			System.out.println("ES NULO");
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
}
