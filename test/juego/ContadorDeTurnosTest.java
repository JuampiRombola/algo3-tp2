package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContadorDeTurnosTest {

	@Test
	public void elTurnoActualDeUnContadorRecienCreadoEsEl1(){
		ContadorDeTurnos.iniciarContador();
		ContadorDeTurnos contadorDeTurnos = ContadorDeTurnos.getInstancia();
		assertTrue(contadorDeTurnos.obtenerTurnoActual() == 1);
	}
	
	@Test
	public void elTurnoActualDeUnContadorAlPasarUnTurnoEsEl2(){
		ContadorDeTurnos.iniciarContador();
		ContadorDeTurnos contadorDeTurnos = ContadorDeTurnos.getInstancia();
		contadorDeTurnos.avanzarTurno();
		assertTrue(contadorDeTurnos.obtenerTurnoActual() == 2);
	}
	
	@Test
	public void elTurnoActualDeUnContadorAlPasarDosTurnosEsEl3(){
		ContadorDeTurnos.iniciarContador();
		ContadorDeTurnos contadorDeTurnos = ContadorDeTurnos.getInstancia();
		contadorDeTurnos.avanzarTurno();
		contadorDeTurnos.avanzarTurno();
		assertTrue(contadorDeTurnos.obtenerTurnoActual() == 3);
	}
}
