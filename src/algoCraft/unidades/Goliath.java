package algoCraft.unidades;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;

public class Goliath extends Unidad {
	static int vidaMaxima = 125;
	static int danioBasico = 36;
	static int rangoBasico = 5;
	static Arma arma = new Arma(danioBasico,rangoBasico);
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 6;
	public static int cantidadMineral = 100;
	public static int cantidadGasVespeno = 50;
	public static int cantidadDePoblacion = 2;
	
	public Goliath(Jugador jugador, int posicionEnX, int posicionEnY) {
		super(jugador, vidaMaxima, arma, new Posicion(posicionEnX,posicionEnY, inicialmenteTerrestre), cantidadDePoblacion);
	}
	
	public static int getCantidadDeMineralQueCUesta() {
		return cantidadMineral;
	}
	
	public static int getCantidadDeGasQueCUesta() {
		return cantidadGasVespeno;
	}
	
	public static int getCantidadDePoblacionQueOcupa() {
		return cantidadDePoblacion;
	}
}
