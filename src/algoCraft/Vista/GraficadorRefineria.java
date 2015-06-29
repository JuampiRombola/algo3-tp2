package algoCraft.Vista;

public class GraficadorRefineria extends Graficador{

	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaRefineria(panelCentral);
	}

}
