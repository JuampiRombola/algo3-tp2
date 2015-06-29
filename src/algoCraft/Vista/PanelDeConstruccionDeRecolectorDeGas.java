package algoCraft.Vista;

import Controlador.AccionConstruirRecolectorDeGas;
import algoCraft.juego.Juego;


public class PanelDeConstruccionDeRecolectorDeGas extends PanelDeConstruccion {

	private static String[] nombresEdificios = {"Recolector de gas"};
	
	PanelDeConstruccionDeRecolectorDeGas(Juego juego) {
		super(nombresEdificios, juego);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		button.setAction(new AccionConstruirRecolectorDeGas(juego, y, y));
	}
}
