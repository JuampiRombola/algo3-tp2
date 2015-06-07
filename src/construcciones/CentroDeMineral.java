package construcciones;

import recursos.Mineral;

public class CentroDeMineral extends EdificioRecolector{
	static boolean esTerrestre = true;
	
	public CentroDeMineral(Mineral mineral) {
		super(mineral);
	}
	
	public void recolectar(int cantidadRecolectada) {
		super.recolectar(cantidadRecolectada);
	}
}
