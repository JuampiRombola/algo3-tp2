package algoCraft.Vista;

public class GraficadorBarraca extends Graficador{
	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaBarraca();
	}
}
