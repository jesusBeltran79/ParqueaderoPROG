package controlador;

import java.time.LocalDateTime;

import modelo.Moto;
import modelo.MotoDao;

public class Main {

	public static void main(String[] args) {
		LocalDateTime x = LocalDateTime.now();
		LocalDateTime salida = x.plusHours(3).plusMinutes(1);
		Moto motico = new Moto("YPG96C", "3102130121", "C", x, salida, true);

		MotoDao m = new MotoDao();
		m.add(motico);

		System.out.println(motico.precioDeportista());

	}

}
