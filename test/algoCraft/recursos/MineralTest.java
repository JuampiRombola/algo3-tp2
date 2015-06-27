package algoCraft.recursos;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.mapa.Posicion;
import algoCraft.recursos.Mineral;


public class MineralTest {

	@Test
	public void cuandoSeCreaUnMineralTiene1500Unidades() {
		Mineral mineral = new Mineral(1, 1);
		assertEquals(1500, mineral.getUnidadesRestantes());
	}

	@Test
	public void cuandoSeCreaUnMineralEsteEstaEnTierra() {
		Mineral mineral = new Mineral(1, 1);
		assertEquals(true, mineral.esTerrestre());
	}
	
	@Test
	public void cuandoSeCreaUnMineralSePuedeCambiarSuPosicion() {
		Mineral mineral = new Mineral(1, 1);
		Posicion posicionInicial = mineral.getPosicion();
		mineral.setPosicion(1, 2);
		assertFalse(posicionInicial.equals(mineral.getPosicion()));
	}
	
	@Test
	public void cuandoSeExtra10UnidadesDeUnMineralTiene10UnidadesMenos() {
		Mineral mineral = new Mineral(1, 1);
		mineral.extraer(10);
		assertEquals(1490, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoSeExtra20UnidadesDeUnMineralTiene20UnidadesMenos() {
		Mineral mineral = new Mineral(1, 1);
		mineral.extraer(10);
		mineral.extraer(10);
		assertEquals(1480, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralSeQuedaSeQuedaSinUnidadesEstaDestruido() {
		Mineral mineral = new Mineral(1, 1);
		while (!mineral.estaDestruido()) {
			mineral.extraer(10);
		}
		assertEquals(0, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralEstaDestruidoNoSePuedeSeguirSacandoUnidades() {
		Mineral mineral = new Mineral(1, 1);
		while (!mineral.estaDestruido()) {
			mineral.extraer(10);
		}
		mineral.extraer(10);
		assertEquals(0, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void unMineralEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mineral mineral = new Mineral(1, 1);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(mineral.getPosicion(), posicion);
	}
	
	@Test
	public void unCentroEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mineral mineral = new Mineral(1, 1);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(mineral.getPosicion(), posicion);
	}
	@Test
	public void siSeTerminaUnMineralQuedaDestruido() {
		Mineral mineral = new Mineral(1, 1);
		mineral.extraer(1500);
		
		assertTrue(mineral.estaDestruido());
	}
}
