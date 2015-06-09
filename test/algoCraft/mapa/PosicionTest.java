package algoCraft.mapa;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class PosicionTest {

	@Test
	public void posicionDevuelveCoordenadaXCorrectamente() {
		Posicion posicion = new Posicion(1, 2, true);
		
		Assert.assertEquals(1, posicion.getX());
	}

	@Test
	public void posicionDevuelveCoordenadaYCorrectamente() {
		Posicion posicion = new Posicion(1, 2, true);
		
		Assert.assertEquals(2, posicion.getY());
	}
	
	@Test
	public void dosPosicionesSonIgualesSiTienenLasMimasCoordenadasYMismoTerreno() {
		Posicion posicion1 = new Posicion(1, 2, true);
		Posicion posicion2 = new Posicion(1, 2, true);
		Assert.assertEquals(posicion1, posicion2);
	}
	
	@Test
	public void dosPosicionesSonDistintasSiTienenDistintasCoordenadas() {
		Posicion posicion1 = new Posicion(5, 1, true);
		Posicion posicion2 = new Posicion(1, 2, true);
		Assert.assertFalse(posicion1.equals(posicion2));
	}
	
	@Test
	public void dosPosicionesSonDistintasSiTienenDistintasCoordenadaX() {
		Posicion posicion1 = new Posicion(1, 1, true);
		Posicion posicion2 = new Posicion(2, 1, true);
		Assert.assertFalse(posicion1.equals(posicion2));
	}
	
	@Test
	public void dosPosicionesSonDistintasSiTienenDistintasCoordenadaY() {
		Posicion posicion1 = new Posicion(1, 1, true);
		Posicion posicion2 = new Posicion(1, 2, true);
		Assert.assertFalse(posicion1.equals(posicion2));
	}
	
	@Test
	public void dosPosicionesSonDistintasSiTienenDistintoTerreno() {
		Posicion posicion1 = new Posicion(1, 1, true);
		Posicion posicion2 = new Posicion(1, 1, false);
		Assert.assertFalse(posicion1.equals(posicion2));
	}
	
	@Test
	public void alCompararUnaPosicionConOtroObjetoSeDevuelveFalse() {
		Posicion posicion1 = new Posicion(1, 1, true);
		Object otroObjeto = new Mapa(1, 1);
		Assert.assertFalse(posicion1.equals(otroObjeto));
	}
	
	@Test
	public void siLasCoordenadasdeUnCasilleroSon2y2YLaDelOtroSon3y2LaDistanciaEs1() {
		Posicion posicion1 = new Posicion(2, 2, true);
		Posicion posicion2 = new Posicion(3, 2, true);
		Assert.assertTrue(1.0 == posicion1.calcularDistancia(posicion2));
	}
}
