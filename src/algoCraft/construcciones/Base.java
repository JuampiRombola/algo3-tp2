package algoCraft.construcciones;

import algoCraft.mapa.Posicion;

public class Base extends Edificio {
	static int vidaMaxima = 2500;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 0;
	
	public Base(int x, int y) {
		super(null, vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse);
	}
}
