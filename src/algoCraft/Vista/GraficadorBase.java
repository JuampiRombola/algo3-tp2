package algoCraft.Vista;

public class GraficadorBase extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaBase();
	}
}
