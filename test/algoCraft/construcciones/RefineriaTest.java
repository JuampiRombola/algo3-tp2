package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;
import algoCraft.unidades.Goliath;

public class RefineriaTest {

	@Test
	public void cuandoSeCreaUnRefineriaNoTieneGasVespenoesRecolectados() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		assertEquals(0, refineria.getRecursosRecolectados());
		}
	
	@Test
	public void cuandoSeCreaUnRefineriaEsteEstaEnTierra() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		assertEquals(true, refineria.esTerrestre());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadaTiene10Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		refineria.recolectar();
		assertEquals(10, refineria.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta2VecessDespuesDeHaberSidoCreadaTiene20Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		refineria.recolectar();
		refineria.recolectar();
		assertEquals(20, refineria.getRecursosRecolectados());
	}
	
	@Test
	public void siLaRefineriaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		int vidaInicial = refineria.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(refineria);
		assertTrue(refineria.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaRefineriaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno aasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(aasVespeno);
		Goliath goliath = new Goliath(2,2);
		while (!refineria.estaDestruido()) {
			goliath.atacar(refineria);
			goliath.avanzarTurno();
		}
		goliath.atacar(refineria);
		assertEquals(0, refineria.getVidaActual());
	}
	
	@Test
	public void unaRefineriaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(refineria.getPosicion(), posicion);
	}
	
	@Test
	public void unaRefineriaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(2, 2);
		Refineria refineria = new Refineria(gasVespeno);
		Posicion posicion = new Posicion(2,2, true);
		assertEquals(refineria.getPosicion(), posicion);
	}
	
	@Test
	public void siSeTerminaUnGasVespenoYSeDestruyeSuRefineriaLaPosicionQuedaVacia() {
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
		
		Refineria refineria = new Refineria(gasVespeno);
		mapa.ocuparRecurso(refineria);
		
		gasVespeno.extraer(990);
		refineria.recolectar();
		assertTrue(gasVespeno.estaDestruido());
		refineria.recibePuntosDeDanio(750);
		
		assertFalse(mapa.posicionEstaOcupada(refineria.getPosicion()));
	}
	
	@Test
	public void siSeTerminaUnGasVespenoYNoSeDestruyeSuRefineriaLaPosicionQuedaOcupada() {
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
		
		Refineria refineria = new Refineria(gasVespeno);
		mapa.ocuparRecurso(refineria);
		
		gasVespeno.extraer(990);
		refineria.recolectar();

		assertTrue(gasVespeno.estaDestruido());
		assertTrue(mapa.posicionEstaOcupada(gasVespeno.getPosicion()));
	}
}

