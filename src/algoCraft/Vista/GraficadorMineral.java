package algoCraft.Vista;

public class GraficadorMineral extends Graficador{
	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaMineral(panelCentral, x, y);
	}

}
