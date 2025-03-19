package modelo;

import java.util.ArrayList;

public class ContadorDiarioDao {

	private ArrayList<ContadorDiario> listaContadorDiario;

	private final String FILE_NAME = "ContadorDiario.csv";
	private final String SERIAL_NAME = "ContadorDiario.dat";

	public ContadorDiarioDao() {
		FileHandler.checkFolder();
		readSerialized();

	}

	public String showAll() {
		String rta = "";
		if (listaContadorDiario.isEmpty()) {
			return "No hay vuelos en la lista";
		} else {
			for (ContadorDiario ContadorDiario : listaContadorDiario) {
				rta += ContadorDiario + "\n";
			}
			return rta;
		}
	}

	public ArrayList<ContadorDiario> getAll() {
		return listaContadorDiario;
	}

	public boolean add(ContadorDiario newData) {

		if (find((newData)) == null) {
			listaContadorDiario.add((newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}

	}

	public boolean delete(ContadorDiario toDelete) {
		ContadorDiario found = find((toDelete));
		if (found != null) {
			listaContadorDiario.remove(found);
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	public ContadorDiario find(ContadorDiario toFind) {
		if (!listaContadorDiario.isEmpty()) {
			for (ContadorDiario ContadorDiario : listaContadorDiario) {
				if (ContadorDiario.getDia().equals(toFind.getDia())) {
					return ContadorDiario;
				}
			}
		}
		return null;
	}

	public boolean update(ContadorDiario previous, ContadorDiario newData) {
		ContadorDiario found = find((previous));
		if (found != null) {
			listaContadorDiario.remove(found);
			listaContadorDiario.add((newData));
			writeFile();
			writeSerialized();
			return true;
		} else {
			return false;
		}
	}

	public void writeFile() {
		String content = "";
		for (ContadorDiario m : listaContadorDiario) {
			content += m.getGanancia() + ";";
			content += m.getDia() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	public void writeSerialized() {
		FileHandler.writeSerialized(SERIAL_NAME, listaContadorDiario);

	}

	@SuppressWarnings("unchecked")
	public void readSerialized() {
		Object content = FileHandler.readSerialized(SERIAL_NAME);

		if (content == null) {
			System.out.println("ES NULO NORMAL");
			listaContadorDiario = new ArrayList<>();
		} else {
			listaContadorDiario = (ArrayList<ContadorDiario>) content;
		}

	}

	public ArrayList<ContadorDiario> getListaContadorDiario() {
		return listaContadorDiario;
	}

	public void setListaContadorDiario(ArrayList<ContadorDiario> listaContadorDiario) {
		this.listaContadorDiario = listaContadorDiario;
	}

}
