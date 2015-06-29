package algoCraft.Vista;

public class GraficadorRecolectorMinerales extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera, int x, int y) {
		return new VistaRecolectorMineral(botonera, x, y);
	}

}
