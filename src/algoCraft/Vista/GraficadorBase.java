package algoCraft.Vista;

public class GraficadorBase extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera, int x, int y) {
		return new VistaBase();
	}

}
