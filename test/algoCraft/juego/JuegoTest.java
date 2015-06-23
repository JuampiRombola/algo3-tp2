package algoCraft.juego;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Base;

public class JuegoTest {

	@Test
	public void cuandoSeCreaElJuegoEsteNoTieneJugadores() {
		Juego juego = new Juego();
		
		assertEquals(0, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeAgregaUnJugadorElJuegoTieneUnJugadorMas() {
		Juego juego = new Juego();
		int cantidadInicial = juego.getCantidadDeJugadores();
		
		juego.anadirJugador(new Jugador("Jugador", new Base(3,3)));
		
		assertEquals(cantidadInicial + 1, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeAgreganDosJugadoresElJuegoTiene2JugadoresMas() {
		Juego juego = new Juego();
		int cantidadInicial = juego.getCantidadDeJugadores();
		
		juego.anadirJugador(new Jugador("Jugador1", new Base(3,3)));
		juego.anadirJugador(new Jugador("Jugador2", new Base(2,2)));
		
		assertEquals(cantidadInicial + 2, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeIniciaElJuegoCon3JugadoresTiene3Jugadores() {
		Juego juego = new Juego();

		juego.iniciarPartida(3);
		
		assertEquals(3, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeIniciaElJuegoCon2JugadoresTiene2Jugadores() {
		Juego juego = new Juego();

		juego.iniciarPartida(2);
		
		assertEquals(2, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeIniciaElJuegoConMasDe4JugadoresSoloSeTiene4Jugadores() {
		Juego juego = new Juego();

		juego.iniciarPartida(5);
		
		assertEquals(4, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeAvanzaAlSiguienteJugadorElJugadorActualPasaASerElQueLeSigue() {
		Juego juego = new Juego();
		juego.iniciarPartida(3);

		Jugador jugador1 = juego.getJugadorActual();
		juego.siguienteJugador();
		Jugador jugador2 = juego.getJugadorActual();
		
		assertTrue(jugador1 != jugador2);
	}
	
	@Test
	public void cuandoSePasaUnaRondaSeVuelveAlJugador1() {
		Juego juego = new Juego();
		juego.iniciarPartida(3);

		Jugador jugadorInicial = juego.getJugadorActual();
		juego.siguienteJugador();
		juego.siguienteJugador();
		juego.siguienteJugador();
		Jugador jugadorFinal = juego.getJugadorActual();
		
		assertTrue(jugadorInicial == jugadorFinal);
	}
}
