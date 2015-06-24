package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.CentroDeMineral;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;

public class CentroDeMineralTest {

	@Test
	public void cuandoSeCreaUnCentroNoTieneMineralesRecolectados() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		assertEquals(0, centro.getRecursosRecolectados());
		}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralEsteEstaEnTierra() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		assertEquals(true, centro.esTerrestre());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadoTiene10Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		centro.recolectar();
		assertEquals(10, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta2VecessDespuesDeHaberSidoCreadoTiene20Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		centro.recolectar();
		centro.recolectar();
		assertEquals(20, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siElCentroEsAtacadoPorUnGoliathSuVidaDisminuye() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		int vidaInicial = centro.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(centro);
		assertTrue(centro.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siElCentroEsAtacadoPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		Goliath goliath = new Goliath(2,2);
		while (!centro.estaDestruido()) {
			goliath.atacar(centro);
			goliath.avanzarTurno();
		}
		goliath.atacar(centro);
		assertEquals(0, centro.getVidaActual());
	}
	
	@Test
	public void unCentroEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(centro.getPosicion(), posicion);
	}
	
	@Test
	public void unCentroEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mineral mineral = new Mineral(2, 2);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		Posicion posicion = new Posicion(2,2, true);
		assertEquals(centro.getPosicion(), posicion);
	}
	
	@Test
	public void siSeTerminaUnMineralYSeDestruyeSuCentroLaPosicionQuedaVacia() {
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
		
		CentroDeMineral centro = new CentroDeMineral(mineral);
		mapa.ocuparRecurso(centro);
		
		mineral.extraer(1490);
		centro.recolectar();
		assertTrue(mineral.estaDestruido());
		centro.recibePuntosDeDanio(500);
		
		assertFalse(mapa.posicionEstaOcupada(centro.getPosicion()));
	}
	
	@Test
	public void siSeTerminaUnMineralYNoSeDestruyeSuCentroLaPosicionQuedaOcupada() {
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
		
		CentroDeMineral centro = new CentroDeMineral(mineral);
		mapa.ocuparRecurso(centro);
		
		mineral.extraer(1490);
		centro.recolectar();
		
		assertTrue(mineral.estaDestruido());
		assertTrue(mapa.posicionEstaOcupada(centro.getPosicion()));
	}
}
