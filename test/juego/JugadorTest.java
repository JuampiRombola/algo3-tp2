package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class JugadorTest {

	@Test
	public void cuandoSeCreaUnJugadorTiene200UnidadesDeMineral() {
		Jugador jugador = new Jugador();
		assertEquals(200, jugador.getMinerales());
	}

	@Test
	public void siAgrego10UnidadesDeMineralAUnJugadorRecienCreadoPasaATener210Unidades() {
		Jugador jugador = new Jugador();
		jugador.sumarMinerales(10);
		assertEquals(210, jugador.getMinerales());
	}
}
