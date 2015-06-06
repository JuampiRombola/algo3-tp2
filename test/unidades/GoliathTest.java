package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoliathTest {
	
	public Goliath nuevoGoliath(){
		return new Goliath();
	}
	
	@Test
	public void unGoliathTiene125PuntosDeVidaMaximaInicialmente(){
		Goliath goliath = nuevoGoliath();
		assertTrue(goliath.getVidaMaxima() == 125);
	}
	
	@Test
	public void unGoliathTiene125PuntosDeVidaActualInicialmente(){
		Goliath goliath = nuevoGoliath();
		assertTrue(goliath.getVidaActual() == 125);
	}
	
	@Test
	public void unGoliathTiene12DeDanioInicialmente(){
		Goliath goliath = nuevoGoliath();
		assertTrue(goliath.getDanio() == 12);
	}
	
	@Test
	public void unGoliathTiene5DeRangoInicialmente(){
		Goliath goliath = nuevoGoliath();
		assertTrue(goliath.getRango() == 5);
	}
}
