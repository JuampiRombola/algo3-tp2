package algoCraft.recursos;

import algoCraft.mapa.Posicion;

public class GasVespeno extends Recurso {
	static boolean esTerrestre = true;
	static int unidadesMaximasDeMineral = 1000;
	
	public GasVespeno(int posicionEnX, int posicionEnY) {
		super(unidadesMaximasDeMineral, new Posicion(posicionEnX, posicionEnY, esTerrestre));
	}
}
