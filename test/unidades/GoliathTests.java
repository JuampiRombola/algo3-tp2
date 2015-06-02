package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoliathTests {
	@Test
	public void unMarineTiene125PuntosDeVidaMaximaInicialmente(){
		Goliath goliath = new Goliath();
		assertTrue(goliath.getVidaMaxima() == 125);
	}
	
	@Test
	public void unMarineTiene125PuntosDeVidaActualInicialmente(){
		Goliath goliath = new Goliath();
		assertTrue(goliath.getVidaActual() == 125);
	}
	
	@Test
	public void unGoliathTiene12DeDanioInicialmente(){
		Goliath goliath = new Goliath();
		assertTrue(goliath.getDanio() == 12);
	}
	
	@Test
	public void unMarineTiene5DeRangoInicialmente(){
		Goliath goliath = new Goliath();
		assertTrue(goliath.getRango() == 5);
	}
}
