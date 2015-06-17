package algoCraft.unidades;

import algoCraft.mapa.Posicion;

public class Goliath extends Unidad {
	static int vidaMaxima = 125;
	static int danioBasico = 12;
	static int rangoBasico = 5;
	static Arma arma = new Arma(danioBasico,rangoBasico);
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 6;
	static int cantidadDePoblacionGoliath = 2;
	
	public Goliath(int posicionEnX, int posicionEnY) {
		super(vidaMaxima, arma, new Posicion(posicionEnX,posicionEnY, inicialmenteTerrestre), turnosEnConstruirse, cantidadDePoblacionGoliath);
	}
}
