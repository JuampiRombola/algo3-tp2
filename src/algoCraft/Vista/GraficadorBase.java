package algoCraft.Vista;

public class GraficadorBase extends Graficador {

	@Override
	public PosicionVista getPosicionVista(PanelBotonera botonera) {
		return new VistaBase();
	}

}
