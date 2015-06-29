package algoCraft.Vista;

public class GraficadorGasVespeno extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaGasVespeno(panelCentral, x, y);
	}

}
