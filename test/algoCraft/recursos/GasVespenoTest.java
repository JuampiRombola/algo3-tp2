package algoCraft.recursos;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class GasVespenoTest {

	@Test
	public void cuandoSeCreaUnGasVespenoTiene1500Unidades() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		assertEquals(1000, gasVespeno.getUnidadesRestantes());
	}

	@Test
	public void cuandoSeCreaUnGasVespenoEsteEstaEnTierra() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		assertEquals(true, gasVespeno.esTerrestre());
	}
	
	@Test
	public void cuandoSeCreaUnGasVespenoSePuedeCambiarSuPosicion() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Posicion posicionInicial = gasVespeno.getPosicion();
		gasVespeno.setPosicion(1, 2);
		assertFalse(posicionInicial.equals(gasVespeno.getPosicion()));
	}
	
	@Test
	public void cuandoSeExtra10UnidadesDeUnGasVespenoTiene10UnidadesMenos() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		gasVespeno.extraer(10);
		assertEquals(990, gasVespeno.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoSeExtra20UnidadesDeUnGasVespenoTiene20UnidadesMenos() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		gasVespeno.extraer(10);
		gasVespeno.extraer(10);
		assertEquals(980, gasVespeno.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElGasVespenoSeQuedaSeQuedaSinUnidadesEstaDestruido() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		while (!gasVespeno.estaDestruido()) {
			gasVespeno.extraer(10);
		}
		assertEquals(0, gasVespeno.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElGasVespenoEstaDestruidoNoSePuedeSeguirSacandoUnidades() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		while (!gasVespeno.estaDestruido()) {
			gasVespeno.extraer(10);
		}
		gasVespeno.extraer(10);
		assertEquals(0, gasVespeno.getUnidadesRestantes());
	}
	
	@Test
	public void unGasVespenoEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(gasVespeno.getPosicion(), posicion);
	}
	
	@Test
	public void unCentroEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(gasVespeno.getPosicion(), posicion);
	}
	
	@Test
	public void siSeTerminaUnGasVespenoSuPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		GasVespeno gasVespeno = null;
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapa.hayGasVespenoEn(new Posicion(i, j, true))) continue;
				gasVespeno = (GasVespeno) mapa.getUnidad(new Posicion(i, j, true));
			}
		}
		
		assertTrue(mapa.posicionEstaOcupada(gasVespeno.getPosicion()));
		gasVespeno.extraer(1000);
		assertFalse(mapa.posicionEstaOcupada(gasVespeno.getPosicion()));
	}
	
	@Test
	public void siNoSeTerminaUnGasVespenoSuPosicionNoQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		GasVespeno gasVespeno = null;
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapa.hayGasVespenoEn(new Posicion(i, j, true))) continue;
				gasVespeno = (GasVespeno) mapa.getUnidad(new Posicion(i, j, true));
			}
		}
		
		assertTrue(mapa.posicionEstaOcupada(gasVespeno.getPosicion()));
		gasVespeno.extraer(999);
		assertTrue(mapa.posicionEstaOcupada(gasVespeno.getPosicion()));
	}
}