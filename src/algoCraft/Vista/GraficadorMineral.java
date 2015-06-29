package algoCraft.Vista;

public class GraficadorMineral extends Graficador{
	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera, int x, int y) {
		return new VistaMineral(botonera, x, y);
	}

}
