package algoCraft.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoCraft.juego.Juego;
import algoCraft.juego.excepciones.NoSePuedenAgregarMasDe4Jugadores;
import algoCraft.juego.excepciones.NombreYaExistenteException;
import algoCraft.mapa.Mapa;

public class JuegoTest {
	
	static String jugador1 = "Jugador1";
	static String jugador2 = "Jugador2";
	static String jugador3 = "Jugador3";
	static String jugador4 = "Jugador4";
	static String jugador5 = "Jugador5";
	
	@Test
	public void cuandoSeCreaElJuegoEsteNoTieneJugadores() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		
		assertEquals(0, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeAgrgan3JugadoresYSeIniciaElJuegoTiene3Jugadores() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.agregarJugadorLlamado(jugador3);
		
		juego.iniciarPartida();
		
		assertEquals(3, juego.getCantidadDeJugadores());
	}
	
	@Test
	public void cuandoSeAgrgan2JugadoresYSeIniciaElJuegoTiene2Jugadores() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		
		juego.iniciarPartida();
		
		assertEquals(2, juego.getCantidadDeJugadores());
	}
	
	@Test(expected = NombreYaExistenteException.class)
	public void cuandoSeAgrgan2JugadoresConElMismoNombreSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador1);
	}
	
	@Test(expected = NoSePuedenAgregarMasDe4Jugadores.class)
	public void cuandoSeTrataDeAgregarUn5toJugadorSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.agregarJugadorLlamado(jugador3);
		juego.agregarJugadorLlamado(jugador4);
		juego.agregarJugadorLlamado(jugador5);
	}
	
	@Test
	public void cuandoSeAvanzaAlSiguienteJugadorElJugadorActualPasaASerElQueLeSigue() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.agregarJugadorLlamado(jugador3);
		juego.iniciarPartida();

		Jugador jugador1 = juego.getJugadorActual();
		juego.siguienteJugador();
		Jugador jugador2 = juego.getJugadorActual();
		
		assertTrue(jugador1 != jugador2);
	}
	
	@Test
	public void cuandoSePasaUnaRondaSeVuelveAlJugador1() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.agregarJugadorLlamado(jugador3);
		juego.iniciarPartida();

		Jugador jugadorInicial = juego.getJugadorActual();
		juego.siguienteJugador();
		juego.siguienteJugador();
		juego.siguienteJugador();
		Jugador jugadorFinal = juego.getJugadorActual();
		
		assertTrue(jugadorInicial == jugadorFinal);
	}
	
	@Test
	public void cuandoSeIniciaElJuegoCon3JugadoresNoTieneGanador() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.agregarJugadorLlamado(jugador3);

		juego.iniciarPartida();
		
		assertEquals(false, juego.hayGanador());
	}
	
	@Test
	public void cuandoSeIniciaElJuegoCon2JugadoresNoTieneGanador() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);

		juego.iniciarPartida();
		
		assertEquals(false, juego.hayGanador());
	}
	
	@Test
	public void cuandoSeCreaUnaPartidaConDosJugadoresYLaVidaDeLaBaseDeUnoDeEllosLlegaA0YEsSuTurnoSeDeterminaQueHayGanador() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.iniciarPartida();
		ArrayList<Jugador> jugadores = juego.getJugadores();
		Jugador jugador2 = jugadores.get(1);
		
		//Se le saca toda la vida a la base del jugador 2 para que aparezca como destruida
		jugador2.getBase().recibePuntosDeDanio(2500);
		juego.siguienteJugador();
		
		assertEquals(true, juego.hayGanador());
	}
	
	@Test
	public void cuandoSeCreaUnaPartidaConTresJugadoresYSeDeterminaQue2DeEllosHanPerdidoHayGanador() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.agregarJugadorLlamado(jugador1);
		juego.agregarJugadorLlamado(jugador2);
		juego.agregarJugadorLlamado(jugador3);
		juego.iniciarPartida();
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
	
	@Test
	public void cuandoSeLimpiaElJuegoSeBorranTodosLosDatos() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		juego.limpiarJuego();
		
		assertTrue(0 == juego.getCantidadDeJugadores());
		assertTrue(0 == juego.getJugadoresDerrotados());
	}
}
