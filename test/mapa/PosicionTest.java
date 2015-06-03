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
	
	@Test
	public void dosPosicionesSonIgualesSiTienenLasMimasCoordenadas() {
		Posicion posicion1 = new Posicion(1, 2);
		Posicion posicion2 = new Posicion(1, 2);
		Assert.assertEquals(posicion1, posicion2);
	}
	
	@Test
	public void dosPosicionesSonDistintasSiTienenDistintasCoordenadas() {
		Posicion posicion1 = new Posicion(1, 1);
		Posicion posicion2 = new Posicion(1, 2);
		Assert.assertFalse(posicion1.equals(posicion2));
	}
	
	@Test
	public void siLasCoordenadasdeUnCasilleroSon2y2YLaDelOtroSon3y2LaDistanciaEsUno() {
		Posicion posicion1 = new Posicion(2, 2);
		Posicion posicion2 = new Posicion(3, 2);
		Assert.assertTrue(1.0 == posicion1.calcularDistancia(posicion2));
	}
}
