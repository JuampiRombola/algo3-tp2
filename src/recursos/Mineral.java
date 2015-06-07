package recursos;

import mapa.Posicion;

public class Mineral extends Recurso {
	static boolean esTerrestre = true;
	static int unidadesMaximasDeMineral = 1500;
	
	public Mineral(int posicionEnX, int posicionEnY) {
		super(unidadesMaximasDeMineral, new Posicion(posicionEnX, posicionEnY, esTerrestre));
	}
}
