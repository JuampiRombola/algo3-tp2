package algoCraft.juego;

import java.util.LinkedList;

import algoCraft.juego.Jugador;

public class Juego {
	public LinkedList<Jugador> jugadores;
	
	public Juego() {
		this.jugadores = new LinkedList<Jugador>();
	}
	
	public int getCantidadDeJugadores() {
		return this.jugadores.size();
	}
	
	public void anadirJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
}
