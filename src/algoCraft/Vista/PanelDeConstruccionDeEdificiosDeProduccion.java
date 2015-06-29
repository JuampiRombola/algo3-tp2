package algoCraft.Vista;

import algoCraft.juego.Juego;

public class PanelDeConstruccionDeEdificiosDeProduccion extends PanelDeConstruccion {
	private static String[] nombresEdificios = {"Barraca", "Fabrica"};
	
	PanelDeConstruccionDeEdificiosDeProduccion(Juego juego) {
		super(nombresEdificios, juego);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	void prepararEventoDeProduccion(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
