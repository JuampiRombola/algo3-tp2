package mapa;

import org.junit.Assert;
import org.junit.Test;

public class PosicionTest {

	@Test
	public void posicionDevuelveCoordenadaXCorrectamente() {
		Posicion posicion = new Posicion(1, 2);
		
		Assert.assertEquals(1, posicion.getX());
	}

	@Test
	public void posicionDevuelveCoordenadaYCorrectamente() {
		Posicion posicion = new Posicion(1, 2);
		
		Assert.assertEquals(2, posicion.getY());
	}
}
