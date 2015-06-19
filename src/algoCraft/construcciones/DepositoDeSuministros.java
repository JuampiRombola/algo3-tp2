package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;

public class DepositoDeSuministros extends Edificio {
	static boolean inicialmenteTerrestre = true;
	static int vida = 500;
	static int turnosQueTardaEnConstruirse = 6;
	static int capacidadDePoblacionASumar = 5;
	public static int cantidadMineral = 100;
	public static int cantidadGasVespeno = 0;

	public DepositoDeSuministros(int x, int y){
		super(vida, new Posicion(x, y, inicialmenteTerrestre), turnosQueTardaEnConstruirse);
	}
	
	@Override
	protected void continuarConstruccion(Jugador jugador) {
		if(this.contadorDeTurnos == this.turnosEnConstruirse){
			this.estaEnConstruccion = false;
			this.contadorDeTurnos = 0;
			jugador.sumarPoblacionMaxima(capacidadDePoblacionASumar);
		}
	}
}
