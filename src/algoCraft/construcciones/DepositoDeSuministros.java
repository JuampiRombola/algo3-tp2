package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class DepositoDeSuministros extends Edificio {
	static boolean inicialmenteTerrestre = true;
	static int vida = 500;
	static int turnosQueTardaEnConstruirse = 6;
	public static int capacidadDePoblacionASumar = 5;
	public static int cantidadMineral = 100;
	public static int cantidadGasVespeno = 0;

	
	public static int getCostoMineral(){
		return cantidadMineral;
	}
	public static int getCostoGas(){
		return cantidadGasVespeno;
	}
	
	public DepositoDeSuministros(Jugador jugador, int x, int y){
		super(jugador, vida, new Posicion(x, y, inicialmenteTerrestre), turnosQueTardaEnConstruirse);
		if (!Mapa.getMapa().posicionEstaOcupada(new Posicion(x, y, inicialmenteTerrestre)))
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
	public void destruir() {
		this.jugador.sumarPoblacionMaxima(-capacidadDePoblacionASumar);
		Mapa.getMapa().removerPosicionable(this);
	}
}
