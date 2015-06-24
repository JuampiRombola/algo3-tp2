package algoCraft.juego;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import java.util.Scanner;
import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;

public class Juego {
	private ArrayList<Jugador> jugadores;
	private Iterator<Jugador> iteradorJugadores;
	private Jugador jugadorActual;
	private int jugadoresDerrotados;
	
	public Juego() {
		this.jugadores = new ArrayList<Jugador>();
		this.jugadoresDerrotados = 0;
	}
	
	public int getCantidadDeJugadores() {
		return this.jugadores.size();
	}
	
	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}
	
	public int getJugadoresDerrotados() {
		return this.jugadoresDerrotados;
	}
	
	public void anadirJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}
	
	private List<String> pedirNombresDeJugadores(int cantidadDeJugadores) {
		ArrayList<String> nombres = new ArrayList<String>();
		nombres.add("Jugador1");
		nombres.add("Jugador2");
		nombres.add("Jugador3");
		nombres.add("Jugador4");
		return nombres.subList(0, cantidadDeJugadores);
	}
	
	private void anadirJugadores(List<String> nombres, List<Base> bases, int cantidadDeJugadores) {
		for (int i = 0; i < cantidadDeJugadores; i++) {
			Base base = bases.get(i);
			String nombre = nombres.get(i);
			Jugador jugador = new Jugador(nombre, base);
			this.jugadores.add(jugador);
		}
	}
	
	public void iniciarPartida(int cantidadDeJugadores) {
		Mapa.reiniciarInstanciaParaTest();
		int cantidad = (cantidadDeJugadores > 4) ? 4 : cantidadDeJugadores;
		List<Base> bases = (List<Base>)Mapa.getMapa().cargarBases(cantidad);
		List<String> nombres = this.pedirNombresDeJugadores(cantidad);
		this.anadirJugadores(nombres, bases, cantidad);
		this.iteradorJugadores = this.jugadores.iterator();
		this.jugadorActual = this.iteradorJugadores.next();
	}
	
	public void siguienteJugador() {
		this.jugadorActual.avanzarTurno();
		this.jugadorActual.desactivar();
		this.jugadorActual = this.iteradorJugadores.next();
		this.jugadorActual.actualizarEstado();
		if (!this.iteradorJugadores.hasNext()) {
			this.iteradorJugadores = this.jugadores.iterator();
		}
		if (this.jugadorActual.perdioLaPartida()) {
			this.jugadoresDerrotados++;
			this.siguienteJugador();
		}
		this.jugadorActual.activar();
	}
	
	public Jugador getJugadorActual() {
		return this.jugadorActual;
	}
	
	public boolean hayGanador() {
		return (this.jugadoresDerrotados + 1 == this.getCantidadDeJugadores());
	}
	
	/*
	public String validarNombre(ArrayList<String> nombres) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String nombre = "";
		while ((!nombres.contains(nombre)) && (nombre != "")) {
			System.out.print("Ingrese el nombre del Jugador: ");
			nombre = input.next();
		}
		return nombre;
	}
	*/
}
