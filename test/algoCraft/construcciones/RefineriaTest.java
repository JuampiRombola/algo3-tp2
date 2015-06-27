package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;
import algoCraft.unidades.Goliath;

public class RefineriaTest {

	private Refineria crearRefineria(GasVespeno gasVespeno) {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		return new Refineria(jugador, gasVespeno);
	}
	
	@Test
	public void cuandoSeCreaUnRefineriaNoTieneGasVespenoesRecolectados() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = crearRefineria(gasVespeno);
		assertEquals(0, refineria.getRecursosRecolectados());
		}
	
	@Test
	public void cuandoSeCreaUnRefineriaEsteEstaEnTierra() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = crearRefineria(gasVespeno);
		assertEquals(true, refineria.esTerrestre());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadaTiene10Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = crearRefineria(gasVespeno);
		refineria.recolectar();
		assertEquals(10, refineria.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta2VecessDespuesDeHaberSidoCreadaTiene20Unidades() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = crearRefineria(gasVespeno);
		refineria.recolectar();
		refineria.recolectar();
		assertEquals(20, refineria.getRecursosRecolectados());
	}
	
	@Test
	public void siLaRefineriaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = crearRefineria(gasVespeno);
		int vidaInicial = refineria.getVidaActual();
		Goliath goliath = new Goliath(null, 2,2);
		goliath.atacar(refineria);
		assertTrue(refineria.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaRefineriaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(1, 1);
		Refineria refineria = crearRefineria(gasVespeno);
		Goliath goliath = new Goliath(null, 2,2);
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
		Refineria refineria = crearRefineria(gasVespeno);
		Posicion posicion = new Posicion(1,1, true);
		assertEquals(refineria.getPosicion(), posicion);
	}
	
	@Test
	public void unaRefineriaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		GasVespeno gasVespeno = new GasVespeno(2, 2);
		Refineria refineria = crearRefineria(gasVespeno);
		Posicion posicion = new Posicion(2,2, true);
		assertEquals(refineria.getPosicion(), posicion);
	}
	
	@Test
	public void siSeTerminaUnGasVespenoYSeDestruyeSuRefineriaLaPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		GasVespeno gasVespeno = (GasVespeno) mapa.getUnidad(new Posicion(2, 2, true));
		Refineria refineria = crearRefineria(gasVespeno);
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
		
		GasVespeno gasVespeno = (GasVespeno) mapa.getUnidad(new Posicion(2, 2, true));
		Refineria refineria = crearRefineria(gasVespeno);
		
		gasVespeno.extraer(990);
		refineria.recolectar();

		assertTrue(gasVespeno.estaDestruido());
		assertTrue(mapa.posicionEstaOcupada(gasVespeno.getPosicion()));
	}
}

