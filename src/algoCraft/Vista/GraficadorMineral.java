package algoCraft.Vista;

public class GraficadorMineral extends Graficador{
	@Override
	PosicionVista getPosicionVista() {
		return new VistaMineral();
	}
}
