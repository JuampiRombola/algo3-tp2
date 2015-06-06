package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class MarineTest {
	
	public Marine nuevoMarine(){
		int posicionEnX  = 1;
		int posicionEnY = 1;
		return new Marine(posicionEnX,posicionEnY);
	}
	
	@Test
	public void unMarineTiene40PuntosDeVidaMaximaInicialmente(){
		Marine marine = nuevoMarine();
		assertTrue(marine.getVidaMaxima() == 40);
	}
	
	@Test
	public void unMarineTiene40PuntosDeVidaActualInicialmente(){
		Marine marine = nuevoMarine();
		assertTrue(marine.getVidaActual() == 40);
	}
	
	@Test
	public void unMarineTiene6DeDanioInicialmente(){
		Marine marine = nuevoMarine();
		assertTrue(marine.getDanio() == 6);
	}
	
	@Test
	public void unMarineTiene4DeRangoInicialmente(){
		Marine marine = nuevoMarine();
		assertTrue(marine.getRango() == 4);
	}
}
