package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class FabricaTest {
	
	private Barraca nuevaBarracaConstruida(){
		Barraca barraca = new Barraca(1,1);
		for(int i = 0; i < 12; i++)
			barraca.avanzarTurno();
		return barraca;
	}
	
	private Fabrica crearFabricaConstruida(){
		Fabrica fabrica;
		try {
			fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
			//La excepcion esta controlada por la nueva barraca construida. Si hay una falla proviene de la barraca
		} catch (LaBarracaNoEsValida e) { fabrica = null;}
		for(int i = 0; i < 12; i++)
			fabrica.avanzarTurno();
		return fabrica;
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() throws LaBarracaNoEsValida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		Posicion posicion = new Posicion(1,1, fabrica.esTerrestre());
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() throws LaBarracaNoEsValida {
		Fabrica fabrica = new Fabrica(2, 2, nuevaBarracaConstruida());
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL22ConSetPosicion22() throws LaBarracaNoEsValida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		fabrica.setPosicion(2, 2);
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() throws LaBarracaNoEsValida{
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() throws LaBarracaNoEsValida{
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
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() throws LaBarracaNoEsValida{
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
	public void laFabricaEstaEnConstruccionCuandoRecienEsCreada() throws LaBarracaNoEsValida{
		Barraca barraca = nuevaBarracaConstruida();;
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		assertTrue(fabrica.estaEnConstruccion());
	}
	
	@Test
	public void laFabricaDejaDeEstarEnConstruccionCuandoPasan12Turnos() throws LaBarracaNoEsValida{
		Barraca barraca = nuevaBarracaConstruida();
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		int turno = 0;
		while(fabrica.estaEnConstruccion()){
			fabrica.avanzarTurno();
			turno++;
		}
		assertTrue(turno == 12);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaYSeTerminaDeConstruirNoSeCreoNingunaUnidad(){
		Fabrica fabrica = crearFabricaConstruida();
		assertFalse(fabrica.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void laFabricaTarda6TurnosLuegoDeSuConstrucionEnCrearUnMarine() throws ElEdificioEstaEnConstruccion{
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearUnidad();
		int turno = 0;
		while(!fabrica.getSeCreoUnaUnidadNueva()){
			turno++;
			fabrica.avanzarTurno();
		}
		assertTrue(turno == 6);
	}

	@Test
	public void siSacoElGoliathDespuesDeHaberSidoCreadoLaBarracaMuestraQueNoHayUnaUnidadNueva() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearUnidad();
		while(!fabrica.getSeCreoUnaUnidadNueva()){
			fabrica.avanzarTurno();
		}
		fabrica.obtenerUltimaUnidadConstruida();
		assertFalse(fabrica.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void crear2GoliathsLleva12Turnos() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearUnidad();
		int turno = 0;
		int marinesCreados = 0;
		while (marinesCreados < 2){
			while(!fabrica.getSeCreoUnaUnidadNueva()){
				turno++;
				fabrica.avanzarTurno();
			}
			marinesCreados++;
			fabrica.obtenerUltimaUnidadConstruida();
			fabrica.crearUnidad();
		}
		assertTrue(turno == 12);
	}
	
	@Test
	public void cuandoLaFabricaCreaUnMarineEsteTieneTodaSuVida() throws LaBarracaNoEsValida {
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
	public void siLaFabricaCreaUnaUnidadYNoPasaronLosTurnosNecesariosParaQueSeConstruyaNoSeLaPuedeObtener() throws NoSeCreoUnaNuevaUnidad, LaBarracaNoEsValida{
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
	public void cuandoSeCreaUnaFabricaEstaNoCreoNingunaUnidad() throws LaBarracaNoEsValida{
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		assertEquals(false, fabrica.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void cuandoLaFabricaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() throws LaBarracaNoEsValida{
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		try {
			fabrica.crearUnidad();
			
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alCrearseLaFabricaYPasar12TurnosEstaTieneTodaLaVida() throws LaBarracaNoEsValida {
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno();
		}
		
		assertEquals(1250, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaYPasarUnTurnoTieneMasDe0PuntosDeVida() throws LaBarracaNoEsValida{
		Fabrica fabrica = new Fabrica(1, 1, nuevaBarracaConstruida());
		fabrica.avanzarTurno();
		assertTrue(0 < fabrica.getVidaActual());
	}
		
	@Test(expected=LaBarracaNoEsValida.class)
	public void siTratoDeCrearLaFabricaConUnaBarracaDestruidaObtengoUnaExcepcion() throws LaBarracaNoEsValida{
		Barraca barraca = nuevaBarracaConstruida();
		barraca.recibePuntosDeDanio(barraca.getVidaActual());
		@SuppressWarnings("unused")
		Fabrica fabrica = new Fabrica(1, 1, barraca);
	}
	
	@Test(expected=LaBarracaNoEsValida.class)
	public void siTratoDeCrearLaFabricaConUnaBarracaEnConstruccionObtengoUnaExcepcion() throws LaBarracaNoEsValida{
		Barraca barraca = new Barraca(1,1);
		@SuppressWarnings("unused")
		Fabrica fabrica = new Fabrica(1, 1, barraca);
	}
}
