package algoCraft.Vista;

public class GraficadorBase extends Graficador {

	@Override
	PosicionVista getPosicionVista() {
		return new VistaBase();
	}

}
