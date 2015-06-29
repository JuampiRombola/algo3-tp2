package algoCraft.Vista;

public class GraficadorMineral extends Graficador{
	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera) {
		return new VistaMineral(botonera);
	}
}
