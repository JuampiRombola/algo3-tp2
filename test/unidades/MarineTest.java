package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class MarineTest {
	@Test
	void unMarineTiene40PuntosDeVidaMaximaInicialmente(){
		Marine marine = new Marine();
		assertTrue(marine.getVidaMaxima() == 40);
	}
	
	@Test
	void unMarineTiene40PuntosDeVidaActualInicialmente(){
		Marine marine = new Marine();
		assertTrue(marine.getVidaActual() == 40);
	}
}
