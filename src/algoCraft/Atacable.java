package algoCraft;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicionable;

public interface Atacable extends Posicionable{
	boolean estaDestruido();
	void recibePuntosDeDanio(int danio);
	int getVidaActual();
	Jugador getJugador();
}
