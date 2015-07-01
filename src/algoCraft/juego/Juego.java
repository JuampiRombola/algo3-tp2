package algoCraft.juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



import java.util.Observable;

//import java.util.Scanner;
import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedenAgregarMasDe4Jugadores;
import algoCraft.juego.excepciones.NombreYaExistenteException;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicionable;

public class Juego extends Observable{
	private ArrayList<String> nombresJugadores;
	private ArrayList<Jugador> jugadores;
	private Iterator<Jugador> iteradorJugadores;
	private Jugador jugadorActual;
	
	public Juego() {
		this.jugadores = new ArrayList<Jugador>();
		this.nombresJugadores = new ArrayList<String>();
	}
	
	public void limpiarJuego(){
		jugadores.clear();
		nombresJugadores.clear();
	}
	
	public int getCantidadDeJugadores() {
		return this.jugadores.size();
	}
	
	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}
	
	public int getJugadoresDerrotados() {
		int jugadoresDerrotados = 0;
		for (Jugador jugador : jugadores){
			if (jugador.perdioLaPartida())
				jugadoresDerrotados++;
		}
		return jugadoresDerrotados;
	}
	
	private void agregarJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
	
	public void agregarJugadorLlamado(String nombre) {
		if (this.nombresJugadores.size() >= 4)
			throw new NoSePuedenAgregarMasDe4Jugadores();
		if (this.nombresJugadores.contains(nombre))
			throw new NombreYaExistenteException();
		this.nombresJugadores.add(nombre);
	}
	
	private void agregarJugadores(ArrayList<String> nombres, List<Base> bases, int cantidadDeJugadores) {
		for (int i = 0; i < cantidadDeJugadores; i++) {
			Base base = bases.get(i);
			String nombre = nombres.get(i);
			Jugador jugador = new Jugador(nombre, base);
			base.setJugador(jugador);
			this.agregarJugador(jugador);
		}
	}
	
	public void iniciarPartida() {
		int cantidad = this.nombresJugadores.size();
		Mapa mapa = Mapa.getMapa();
		mapa.limpiarMapa();
		List<Base> bases = (List<Base>)mapa.cargarBases(cantidad);
		this.agregarJugadores(this.nombresJugadores, bases, cantidad);
		this.iteradorJugadores = this.jugadores.iterator();
		this.jugadorActual = this.iteradorJugadores.next();
		this.jugadorActual.activar();
		setChanged();
		notifyObservers();
	}
	
	public void siguienteJugador() {
		if(!hayGanador()){
			this.jugadorActual.desactivar();
			if (!this.iteradorJugadores.hasNext()) {
				this.avanzarTurnoTodasLasUnidades();
				this.iteradorJugadores = this.jugadores.iterator();
			}
			this.jugadorActual = this.iteradorJugadores.next();
			if (this.jugadorActual.perdioLaPartida())
				this.siguienteJugador();
			this.jugadorActual.activar();
		}
		setChanged();
		notifyObservers();
	}
	
	private void avanzarTurnoTodasLasUnidades() {
		Collection<Posicionable> posicionables = Mapa.getMapa().getElementos();
		for (Posicionable posicionable : posicionables)
			posicionable.avanzarTurno();
	}

	public Jugador getJugadorActual() {
		return this.jugadorActual;
	}
	
	public boolean hayGanador() {
		return (this.getJugadoresDerrotados() + 1 == this.getCantidadDeJugadores());
	}
}
