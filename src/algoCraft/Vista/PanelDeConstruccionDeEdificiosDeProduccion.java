package algoCraft.Vista;

import Controlador.AccionConstruirBarraca;
import Controlador.AccionConstruirFabrica;
import algoCraft.juego.Juego;

public class PanelDeConstruccionDeEdificiosDeProduccion extends PanelDeConstruccion {
	private static String[] nombresEdificios = {"Barraca", "Fabrica"};
	
	PanelDeConstruccionDeEdificiosDeProduccion(Juego juego) {
		super(nombresEdificios, juego);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		if (edificios.getSelectedItem() == "Barraca")
			button.setAction(new AccionConstruirBarraca(juego, x, y));
		if(edificios.getSelectedItem() == "Fabrica")
			button.setAction(new AccionConstruirFabrica(juego, x, y));
	}
}
