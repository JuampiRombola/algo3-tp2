package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class MarineTest {
	@Test
	public void unMarineTiene40PuntosDeVidaMaximaInicialmente(){
		Marine marine = new Marine();
		assertTrue(marine.getVidaMaxima() == 40);
	}
	
	@Test
	public void unMarineTiene40PuntosDeVidaActualInicialmente(){
		Marine marine = new Marine();
		assertTrue(marine.getVidaActual() == 40);
	}
	
	@Test
	public void unMarineTiene6DeDanioInicialmente(){
		Marine marine = new Marine();
		assertTrue(marine.getDanio() == 6);
	}
	
	@Test
	public void unMarineTiene4DeRangoInicialmente(){
		Marine marine = new Marine();
		assertTrue(marine.getRango() == 4);
	}
}
