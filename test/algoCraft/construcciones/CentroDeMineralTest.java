package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.CentroDeMineral;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;

public class CentroDeMineralTest {

	@Test
	public void laVidaMaximaDelCentroEs500() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		assertTrue(500 == centro.getVidaMaxima());
	}
	
	@Test
	public void cuandoSeCreaUnCentroNoTieneMineralesRecolectados() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		assertEquals(0, centro.getRecursosRecolectados());
		}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralEsteEstaEnTierra() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		assertEquals(true, centro.esTerrestre());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadoTiene10Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		centro.recolectar();
		assertEquals(10, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta2VecessDespuesDeHaberSidoCreadoTiene20Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		centro.recolectar();
		centro.recolectar();
		assertEquals(20, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siElCentroEsAtacadoPorUnGoliathSuVidaDisminuye() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Jugador jugador2 = new Jugador("Jugador", new Base(4, 4));
		jugador2.activar();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		int vidaInicial = centro.getVidaActual();
		Goliath goliath = new Goliath(jugador2, 2, 2);
		goliath.atacar(centro);
		assertTrue(centro.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siElCentroEsAtacadoPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Jugador jugador2 = new Jugador("Jugador", new Base(4, 4));
		jugador2.activar();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		Goliath goliath = new Goliath(jugador2, 2, 2);
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(centro.getPosicion(), posicion);
	}
	
	@Test
	public void unCentroEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(2, 2);
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		Posicion posicion = new Posicion(2,2, true);
		assertEquals(centro.getPosicion(), posicion);
	}
	
	@Test
	public void siSeTerminaUnMineralYSeDestruyeSuCentroLaPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		Mineral mineral = (Mineral) mapa.getUnidad(new Posicion(4, 4, true));
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		mapa.cargarBases(1);
		
		Mineral mineral = (Mineral) mapa.getUnidad(new Posicion(4, 4, true));
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		
		mineral.extraer(1490);
		centro.recolectar();
		
		assertTrue(mineral.estaDestruido());
		assertTrue(mapa.posicionEstaOcupada(centro.getPosicion()));
	}
	
	@Test
	public void siSeTerminaUnMineralSuCentroNoSigueSumandoRecursos() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		mapa.cargarBases(1);
		
		Mineral mineral = (Mineral) mapa.getUnidad(new Posicion(4, 4, true));
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		
		mineral.extraer(1490);
		centro.recolectar();
		
		assertTrue(10 == centro.getRecursosRecolectados());
	}
	
	@Test
	public void elCentroDeMineralCuesta50UnidadesDeMineral() {
		
		assertEquals(50, CentroDeMineral.getCostoMineral());
	}
	
	@Test
	public void elCentroDeMineralCuesta0UnidadesDeGasVespeno() {
		
		assertEquals(0, CentroDeMineral.getCostoGas());
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElRecursoQueTieneAsociadoTieneTodosSusRecursos() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Mineral mineral = new Mineral(1, 1);
		int unidadesIniciales = mineral.getUnidadesRestantes();
		CentroDeMineral centro = new CentroDeMineral(jugador, mineral);
		assertEquals(unidadesIniciales, centro.getRecursosRestantes());
	}
}
