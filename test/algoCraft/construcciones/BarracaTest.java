package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class BarracaTest {

	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Barraca barraca = new Barraca(1, 1);
		assertEquals(true, barraca.esTerrestre());
	}

	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Barraca barraca = new Barraca(1, 1);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		int vidaInicial = barraca.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		
		goliath.atacar(barraca);
		
		assertTrue(barraca.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Barraca barraca = new Barraca(1, 1);
		Goliath goliath = new Goliath(2,2);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		
		while (!barraca.estaDestruido()) {
			goliath.atacar(barraca);
			goliath.avanzarTurno();
		}
		goliath.atacar(barraca);
		
		assertEquals(0, barraca.getVidaActual());
	}
	
	@Test
	public void cuandoLaBarracaCreaUnMarineEsteTieneTodaSuVida() {
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		try {
			Unidad marine = barraca.crearUnidad();
			for (int j = 0; j < 3; j++) {
				barraca.avanzarTurno();
			}
			
			assertEquals(marine.getVidaActual(), 40);
		} catch (EdificioNoHabilitadoException e) {}
	}
	
	@Test
	public void cuandoLaBarracaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Barraca barraca = new Barraca(1, 1);
		
		try {
			barraca.crearUnidad();
			
			fail();
		} catch (EdificioNoHabilitadoException e) {}
	}
	
	@Test
	public void elMetodoGetNivelDevuelveQueLaBarracaEsUnEdificioDeNivel1() {
		Barraca barraca = new Barraca(1, 1);
		
		assertTrue(barraca.getNivel() == 1);
	}
	
	@Test
	public void alCrearseLaBarracaYPasar10TurnosEstaHabilitada() {
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		
		assertTrue(barraca.estaHabilitado());
	}
	
	@Test
	public void alCrearseLaBarracaEstaDeshabilitada() {
		Barraca barraca = new Barraca(1, 1);

		assertFalse(barraca.estaHabilitado());
	}
	
	@Test
	public void alDeshabiltarseLaBarracaEstaDeshabilitada() {
		Barraca barraca = new Barraca(1, 1);
		
		barraca.deshabilitarProduccion();
		
		assertFalse(barraca.estaHabilitado());
	}
	
	@Test
	public void alDeshabiltarseYHabilitarseLaBarracaEstaHabilitada() {
		Barraca barraca = new Barraca(1, 1);
		
		barraca.deshabilitarProduccion();
		barraca.habilitarProduccion();
		
		assertTrue(barraca.estaHabilitado());
	}
}
