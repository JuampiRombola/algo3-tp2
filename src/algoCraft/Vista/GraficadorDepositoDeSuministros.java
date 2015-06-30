package algoCraft.Vista;

public class GraficadorDepositoDeSuministros extends Graficador{

	@Override
	public PosicionVista getPosicionVista(PanelCentral panelCentral, int x, int y) {
		return new VistaDepositoDeSuministros(panelCentral, x, y);
	}
}
