package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;

public class DepositoDeSuministros extends Edificio {
	static boolean inicialmenteTerrestre = true;
	static int vida = 500;
	static int turnosQueTardaEnConstruirse = 6;
	public static int capacidadDePoblacionASumar = 5;
	public static int cantidadMineral = 100;
	public static int cantidadGasVespeno = 0;

	public DepositoDeSuministros(Jugador jugador, int x, int y){
		super(jugador, vida, new Posicion(x, y, inicialmenteTerrestre), turnosQueTardaEnConstruirse);
		jugador.pagarMineralGasVespeno(cantidadMineral, cantidadGasVespeno);
	}
	
	@Override
	protected void continuarConstruccion() {
		if(this.contadorDeTurnos == this.turnosEnConstruirse){
			this.estaEnConstruccion = false;
			this.contadorDeTurnos = 0;
			this.jugador.sumarPoblacionMaxima(capacidadDePoblacionASumar);
		}
	}
	
	@Override
	public void recibePuntosDeDanio(int danio) {
		super.recibePuntosDeDanio(danio);
		if (this.estaDestruido())
			this.jugador.sumarPoblacionMaxima(-capacidadDePoblacionASumar);
	}
}
