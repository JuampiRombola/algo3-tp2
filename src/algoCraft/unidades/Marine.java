package algoCraft.unidades;

import algoCraft.mapa.Posicion;

public class Marine extends Unidad{
	static int vidaMaxima = 40;
	static int danioBasico = 6;
	static int rangoBasico = 4;
	static Arma arma = new Arma(danioBasico,rangoBasico);
	static boolean inicialmenteTerrestre = true;
	
	public Marine(int posicionEnX, int posicionEnY) {
		super(vidaMaxima, arma, new Posicion(posicionEnX,posicionEnY, inicialmenteTerrestre));
	}
}
