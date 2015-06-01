package test.mapa;

import modelo.mapa.Casillero;

import org.junit.Assert;
import org.junit.Test;

public class testCasillero {

	@Test
	public void casilleroVacioCuandoSeCrea() {
		Casillero casillero = new Casillero();
		Assert.assertTrue(casillero.estaVacio());
	}

}
