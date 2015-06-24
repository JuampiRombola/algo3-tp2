package algoCraft.Vista;

public class GraficadorBase extends Graficador {

	@Override
	public PosicionVista getPosicionVista() {
		return new VistaBase();
	}

}
