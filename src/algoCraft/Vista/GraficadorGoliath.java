package algoCraft.Vista;

public class GraficadorGoliath extends Graficador{
	
	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaGoliath(panelCentral, x, y);
	}
}
