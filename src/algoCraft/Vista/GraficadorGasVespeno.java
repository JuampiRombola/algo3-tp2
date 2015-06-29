package algoCraft.Vista;

public class GraficadorGasVespeno extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera, int x, int y) {
		return new VistaGasVespeno(botonera, x, y);
	}

}
