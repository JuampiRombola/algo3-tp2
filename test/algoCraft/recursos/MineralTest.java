package algoCraft.recursos;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.mapa.Mapa;
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
	public void siSeTerminaUnMineralSuPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		Mineral mineral = null;
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapa.hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) mapa.getUnidad(new Posicion(i, j, true));
			}
		}
		
		assertTrue(mapa.posicionEstaOcupada(mineral.getPosicion()));
		mineral.extraer(1500);
		assertFalse(mapa.posicionEstaOcupada(mineral.getPosicion()));
	}
	
	@Test
	public void siNoSeTerminaUnMineralSuPosicionNoQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		Mineral mineral = null;
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapa.hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) mapa.getUnidad(new Posicion(i, j, true));
			}
		}
		
		assertTrue(mapa.posicionEstaOcupada(mineral.getPosicion()));
		mineral.extraer(1499);
		assertTrue(mapa.posicionEstaOcupada(mineral.getPosicion()));
	}
}
