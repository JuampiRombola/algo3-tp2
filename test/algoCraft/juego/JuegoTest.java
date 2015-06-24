package algoCraft.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoCraft.construcciones.Base;
import algoCraft.juego.Juego;

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
	
	@Test
	public void cuandoSeIniciaElJuegoCon3JugadoresNoTieneGanador() {
		Juego juego = new Juego();

		juego.iniciarPartida(3);
		
		assertEquals(false, juego.hayGanador());
	}
	
	@Test
	public void cuandoSeIniciaElJuegoCon2JugadoresNoTieneGanador() {
		Juego juego = new Juego();

		juego.iniciarPartida(2);
		
		assertEquals(false, juego.hayGanador());
	}
	
	@Test
	public void cuandoSeCreaUnaPartidaConDosJugadoresYLaVidaDeLaBaseDeUnoDeEllosLlegaA0YEsSuTurnoSeDeterminaQueHayGanador() {
		Juego juego = new Juego();
		juego.iniciarPartida(2);
		ArrayList<Jugador> jugadores = juego.getJugadores();
		Jugador jugador2 = jugadores.get(1);
		
		//Se le saca toda la vida a la base del jugador 2 para que aparezca como destruida
		jugador2.getBase().recibePuntosDeDanio(2500);
		juego.siguienteJugador();
		
		assertEquals(true, juego.hayGanador());
	}
	
	@Test
	public void cuandoSeCreaUnaPartidaConTresJugadoresYSeDeterminaQue2DeEllosHanPerdidoHayGanador() {
		Juego juego = new Juego();
		juego.iniciarPartida(3);
		ArrayList<Jugador> jugadores = juego.getJugadores();
		Jugador jugador1 = jugadores.get(0);
		Jugador jugador2 = jugadores.get(1);
		Jugador jugador3 = jugadores.get(2);
		
		//Se le saca toda la vida a la base del jugador 2 para que aparezca como destruida
		//La base del jugador 3 pierde un poco de vida
		jugador2.getBase().recibePuntosDeDanio(2500);
		jugador3.getBase().recibePuntosDeDanio(25);
		juego.siguienteJugador();
		assertEquals(false, jugador3.perdioLaPartida());
		assertEquals(1, juego.getJugadoresDerrotados());
		
		//Se le saca vida a la base del jugador 1 en el turno del jugador 3
		jugador1.getBase().recibePuntosDeDanio(250);
		assertEquals(false, jugador1.perdioLaPartida());
		juego.siguienteJugador();
		assertEquals(1, juego.getJugadoresDerrotados());
		
		//Se deja destruida la base del jugador 1 en el turno del jugador 3, por lo que al avanzar el turno
		//y se decreta que hay un ganador
		juego.siguienteJugador();
		jugador1.getBase().recibePuntosDeDanio(2500);
		assertEquals(true, juego.hayGanador());
	}
}
