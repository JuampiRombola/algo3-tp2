package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class FabricaTest {

	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Fabrica fabrica = new Fabrica(1, 1);
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		
		goliath.atacar(fabrica);
		
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Fabrica fabrica = new Fabrica(1, 1);
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		Goliath goliath = new Goliath(2,2);
		
		while (!fabrica.estaDestruido()) {
			goliath.atacar(fabrica);
			goliath.avanzarTurno();
		}
		goliath.atacar(fabrica);
		
		assertEquals(0, fabrica.getVidaActual());
	}
	
	@Test
	public void cuandoLaFabricaCreaUnGoliathEsteTieneTodaSuVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		try {
			Unidad goliath = fabrica.crearUnidad();
			for (int j = 0; j < 6; j++) {
				fabrica.avanzarTurno();
			}
			assertEquals(goliath.getVidaActual(), 125);
		} catch (EdificioNoHabilitadoException e) {}
	}
	
	@Test
	public void cuandoLaFabricaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		try {
			fabrica.crearUnidad();
			
			fail();
		} catch (EdificioNoHabilitadoException e) {}
	}
	
	@Test
	public void elMetodoGetNivelDevuelveQueLaFabricaEsUnEdificioDeNivel2(){
		Fabrica fabrica = new Fabrica(1, 1);
		assertTrue(fabrica.getNivel() == 2);
	}
	
	@Test
	public void alCrearseLaBarracaYPasar10TurnosEstaHabilitada() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		
		assertTrue(fabrica.estaHabilitado());
	}
	
	@Test
	public void alCrearseLaBarracaEstaDeshabilitada() {
		Fabrica fabrica = new Fabrica(1, 1);

		assertFalse(fabrica.estaHabilitado());
	}
	
	@Test
	public void alDeshabiltarseLaFabricaEstaDeshabilitada(){
		Fabrica fabrica = new Fabrica(1, 1);
		
		fabrica.deshabilitarProduccion();
		
		assertFalse(fabrica.estaHabilitado());
	}
	
	@Test
	public void alDeshabiltarseYHabilitarseLaFabricaEstaHabilitada(){
		Fabrica fabrica = new Fabrica(1, 1);
		
		fabrica.deshabilitarProduccion();
		fabrica.habilitarProduccion();
		
		assertTrue(fabrica.estaHabilitado());
	}
}
