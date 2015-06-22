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
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		assertEquals(0, refineria.getRecursosRecolectados());
		}
	
	@Test
	public void cuandoSeCreaUnRefineriaEsteEstaEnTierra() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		assertEquals(true, refineria.esTerrestre());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadaTiene10Unidades() {
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		refineria.recolectar();
		assertEquals(10, refineria.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta2VecessDespuesDeHaberSidoCreadaTiene20Unidades() {
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
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = new Refineria(gasVespeno);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(refineria.getPosicion(), posicion);
	}
	
	@Test
	public void unaRefineriaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		GasVespeno gasVespeno = new GasVespeno(2, 2);
		Refineria refineria = new Refineria(gasVespeno);
		Posicion posicion = new Posicion(2,2, true);
		assertEquals(refineria.getPosicion(), posicion);
	}
}

