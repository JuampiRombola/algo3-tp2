package algoCraft.construcciones;

import algoCraft.mapa.Posicion;

public class Base extends Edificio {
	static int vidaMaxima = 2500;
	static boolean inicialmenteTerrestre = true;
	
	public Base(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
	}
}
