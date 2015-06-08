package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContadorDeTurnosTest {

	@Test
	public void elTurnoActualDeUnContadorRecienCreadoEsEl1(){
		ContadorDeTurnos contadorDeTurnos = new ContadorDeTurnos();
		assertTrue(contadorDeTurnos.obtenerTurnoActual() == 1);
	}
	
	@Test
	public void elTurnoActualDeUnContadorAlPasarUnTurnoEsEl2(){
		ContadorDeTurnos contadorDeTurnos = new ContadorDeTurnos();
		contadorDeTurnos.avanzarTurno();
		assertTrue(contadorDeTurnos.obtenerTurnoActual() == 2);
	}
}
