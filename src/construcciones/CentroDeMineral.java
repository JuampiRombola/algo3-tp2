package construcciones;

import recursos.Mineral;

public class CentroDeMineral extends EdificioRecolector{
	static boolean esTerrestre = true;
	static int cantidadARecolectar = 10;
	static int vida = 1000;
	
	public CentroDeMineral(Mineral mineral) {
		super(mineral, vida);
	}
	
	public void recolectar() {
		super.recolectar(cantidadARecolectar);
	}
}
