package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class FabricaTest {
	
	private Barraca nuevaBarracaConstruida(){
		Barraca barraca = new Barraca(1,1);
		for(int i = 0; i < 12; i++)
			barraca.avanzarTurno();
		return barraca;
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		
		goliath.atacar(fabrica);
		
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
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
	public void cuandoLaFabricaCreaUnMarineEsteTieneTodaSuVida() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
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
	public void siLaFabricaCreaUnaUnidadYNoPasaronLosTurnosNecesariosParaQueSeConstruyaNoSeLaPuedeObtener() throws NoSeCreoUnaNuevaUnidad, LaBarracaEstaDestruida{
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
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
	public void cuandoSeCreaUnaFabricaEstaNoCreoNingunaUnidad() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		assertEquals(false, fabrica.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void cuandoLaFabricaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		try {
			fabrica.crearUnidad();
			
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alCrearseLaFabricaYPasar12TurnosEstaTieneTodaLaVida() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		
		assertEquals(1250, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaTiene0PuntosDeVida() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		assertEquals(0, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaYPasarUnTurnoTieneMasDe0PuntosDeVida() throws LaBarracaEstaDestruida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		fabrica.avanzarTurno();
		assertTrue(0 < fabrica.getVidaActual());
	}
		
	@Test(expected=LaBarracaEstaDestruida.class)
	public void siTratoDeCrearLaFabricaConUnaBarracaDestruidaObtengoUnaExcepcion() throws LaBarracaEstaDestruida{
		Barraca barraca = new Barraca(1,1);
		barraca.avanzarTurno();
		barraca.recibePuntosDeDanio(barraca.getVidaActual());
		@SuppressWarnings("unused")
		Fabrica fabrica = new Fabrica(1, 1, barraca);
	}
}
