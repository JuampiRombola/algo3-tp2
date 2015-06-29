package algoCraft.Vista;

public class GraficadorFabrica extends Graficador {
	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaFabrica();
	}
}
