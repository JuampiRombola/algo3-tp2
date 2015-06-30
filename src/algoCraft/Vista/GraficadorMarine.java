package algoCraft.Vista;

public class GraficadorMarine extends Graficador{
	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaMarine(panelCentral, x, y);
	}
}
