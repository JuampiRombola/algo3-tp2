package algoCraft.recursos;

import static org.junit.Assert.*;

import org.junit.Test;

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
		GasVespeno gasVespeno = new GasVespeno(2, 2);
		Posicion posicion = new Posicion(2,2, true);
		assertEquals(gasVespeno.getPosicion(), posicion);
	}
	
	@Test
	public void siSeTerminaUnGasVespenoQuedaDestruido() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		gasVespeno.extraer(1000);
		
		assertTrue(gasVespeno.estaDestruido());
	}
}