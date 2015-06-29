package algoCraft.Vista;

import Controlador.AccionConstruirEdificioDeProduccion;
import algoCraft.juego.Juego;

public class PanelDeConstruccionDeEdificiosDeProduccion extends PanelDeConstruccion {
	private static String[] nombresEdificios = {"Barraca", "Fabrica"};
	
	PanelDeConstruccionDeEdificiosDeProduccion(Juego juego) {
		super(nombresEdificios, juego);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		this.button.setAction(new AccionConstruirEdificioDeProduccion(juego, comboBox, x, y));
	}
}
