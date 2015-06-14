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
	public void cuandoLaFabricaCreaUnMarineEsteTieneTodaSuVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		try {
			fabrica.crearUnidad();
			for (int j = 0; j < 6; j++) {
				fabrica.avanzarTurno();
			}
			try {
				Unidad goliath = fabrica.obtenerUltimaUnidadConstruida();
				
				assertEquals(goliath.getVidaActual(), 125);
			} catch (NoSeCreoUnaNuevaUnidad e) {}
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test(expected = NoSeCreoUnaNuevaUnidad.class)
	public void siLaFabricaCreaUnaUnidadYNoPasaronLosTurnosNecesariosParaQueSeConstruyaNoSeLaPuedeObtener() throws NoSeCreoUnaNuevaUnidad{
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		try {
			fabrica.crearUnidad();
			@SuppressWarnings("unused")
			Unidad goliath = fabrica.obtenerUltimaUnidadConstruida();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaEstaNoCreoNingunaUnidad() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		assertEquals(false, fabrica.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void cuandoLaFabricaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		try {
			fabrica.crearUnidad();
			
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
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
	public void alCrearseLaFabricaYPasar12TurnosEstaTieneTodaLaVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		
		assertEquals(1250, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaTiene0PuntosDeVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		assertEquals(0, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		
		fabrica.avanzarTurno();
		
		assertTrue(0 < fabrica.getVidaActual());
	}
}
