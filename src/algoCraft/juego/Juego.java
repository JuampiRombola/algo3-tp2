package algoCraft.juego;

import java.util.ArrayList;

import algoCraft.juego.Jugador;

public class Juego {
	public ArrayList<Jugador> jugadores;
	
	public Juego() {
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public int getCantidadDeJugadores() {
		return this.jugadores.size();
	}
}
