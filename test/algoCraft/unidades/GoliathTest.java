package algoCraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.unidades.Goliath;

public class GoliathTest {
	
	public Goliath nuevoGoliath(){
		//la posicion del goliath no es relevante en las pruebas que llaman a este metodo
		int posicionEnX  = 1;
		int posicionEnY = 1;
		return new Goliath(posicionEnX, posicionEnY);
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