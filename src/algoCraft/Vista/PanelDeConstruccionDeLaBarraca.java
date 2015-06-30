package algoCraft.Vista;

import Controlador.AccionConstruirMarine;
import algoCraft.juego.Juego;

public class PanelDeConstruccionDeLaBarraca extends PanelDeConstruccion {
	
	private static final long serialVersionUID = 1L;
	private static String[] nombresUnidades = {"Marine"};
	
	PanelDeConstruccionDeLaBarraca(Juego juego) {
		super(nombresUnidades, juego);
	}

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		this.button.setAction(new AccionConstruirMarine(juego, x, y));
	}

}
