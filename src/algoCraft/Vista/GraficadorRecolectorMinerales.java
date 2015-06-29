package algoCraft.Vista;

public class GraficadorRecolectorMinerales extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaRecolectorMineral(panelCentral, x, y);
	}

}
