package algoCraft.Vista;

public class GraficadorGasVespeno extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera) {
		return new VistaGasVespeno(botonera);
	}

}
