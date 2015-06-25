package algoCraft.unidades;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;

public class Marine extends Unidad{
	static int vidaMaxima = 40;
	static int danioBasico = 6;
	static int rangoBasico = 4;
	static Arma arma = new Arma(danioBasico,rangoBasico);
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 3;
	public static int cantidadMineral= 50;
	public static int cantidadGasVespeno = 0;
	public static int cantidadDePoblacion = 1;
	
	public Marine(Jugador jugador, int posicionEnX, int posicionEnY) {
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