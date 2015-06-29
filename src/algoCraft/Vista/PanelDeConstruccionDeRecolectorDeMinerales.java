package algoCraft.Vista;

import algoCraft.juego.Juego;
import Controlador.AccionConstruirRecolectorDeMinerales;

public class PanelDeConstruccionDeRecolectorDeMinerales extends PanelDeConstruccion {
private static String[] nombresEdificios = {"Recolector de minerales"};
private static final long serialVersionUID = 1L;

	PanelDeConstruccionDeRecolectorDeMinerales (Juego juego) {
		super(nombresEdificios, juego);
	}

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		button.setAction(new AccionConstruirRecolectorDeMinerales(juego, y, y));
	}
}
