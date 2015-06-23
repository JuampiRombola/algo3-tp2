package algoCraft.juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class JuegoTest {

	@Test
	public void cuandoSeCreaElJuegoEsteNoTieneJugadores() {
		Juego juego = new Juego();
		
		assertEquals(0, juego.getCantidadDeJugadores());
	}
}
