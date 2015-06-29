package algoCraft.Vista;

public class GraficadorRefineria extends Graficador{

	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera, int x, int y) {
		return new VistaRefineria(botonera);
	}

}
