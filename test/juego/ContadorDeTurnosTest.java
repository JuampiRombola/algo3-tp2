package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContadorDeTurnosTest {

	@Test
	public void elTurnoActualDeUnContadorRecienCreadoEsEl1(){
		ContadorDeTurnos contadorDeTurnos = new ContadorDeTurnos();
		assertTrue(contadorDeTurnos.obtenerTurnoActual() == 1);
	}
}
