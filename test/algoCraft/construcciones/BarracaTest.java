package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class BarracaTest {

	private Barraca crearBarracaConstruida(){
		Barraca barraca = new Barraca(1, 1);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		return barraca;
	}
	
	@Test
	public void laBarracaEstaEnConstruccionCuandoRecienEsCreada(){
		Barraca barraca = new Barraca(1, 1);
		assertTrue(barraca.estaEnConstruccion());
	}
	
	@Test
	public void laBarracaDejaDeEstarEnConstruccionCuandoPasan12Turnos(){
		Barraca barraca = new Barraca(1, 1);
		int turno = 0;
		while(barraca.estaEnConstruccion()){
			barraca.avanzarTurno();
			turno++;
		}
		assertTrue(turno == 12);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaEstaNoCreoNingunaUnidad() {
		Barraca barraca = new Barraca(1, 1);
		assertFalse(barraca.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaYSeTerminaDeConstruirNoSeCreoNingunaUnidad(){
		Barraca barraca = crearBarracaConstruida();
		assertFalse(barraca.getSeCreoUnaUnidadNueva());
	}
	@Test
	public void laBarracaTarda3TurnosLuegoDeSuConstrucionEnCrearUnMarine() throws ElEdificioEstaEnConstruccion{
		Barraca barraca = crearBarracaConstruida();
		barraca.crearUnidad();
		int turno = 0;
		while(!barraca.getSeCreoUnaUnidadNueva()){
			turno++;
			barraca.avanzarTurno();
		}
		assertTrue(turno == 3);
	}
	
	@Test
	public void siSacoElMarineDespuesDeHaberSidoCreadoLaBarracaMuestraQueNoHayUnaUnidadNueva() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Barraca barraca = crearBarracaConstruida();
		barraca.crearUnidad();
		while(!barraca.getSeCreoUnaUnidadNueva()){
			barraca.avanzarTurno();
		}
		barraca.obtenerUltimaUnidadConstruida();
		assertFalse(barraca.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void crear2MarinesLleva6Turnos() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Barraca barraca = crearBarracaConstruida();
		barraca.crearUnidad();
		int turno = 0;
		int marinesCreados = 0;
		while (marinesCreados < 2){
			while(!barraca.getSeCreoUnaUnidadNueva()){
				turno++;
				barraca.avanzarTurno();
			}
			marinesCreados++;
			barraca.obtenerUltimaUnidadConstruida();
			barraca.crearUnidad();
		}
		assertTrue(turno == 6);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaEstaEstaEnTierra() {
		Barraca barraca = new Barraca(1, 1);
		assertEquals(true, barraca.esTerrestre());
	}
	@Test
	public void unaBarracaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Barraca barraca = new Barraca(1, 1);
		Posicion posicion = new Posicion(1,1, barraca.esTerrestre());
		assertEquals(barraca.getPosicion(), posicion);
	}
	
	@Test
	public void unaBarracaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Barraca barraca = new Barraca(2, 2);
		Posicion posicion = new Posicion(2,2, barraca.esTerrestre());
		assertEquals(barraca.getPosicion(), posicion);
	}
	
	@Test
	public void unaBarracaEnEl11movidaAl22DevuelveUnaPosicionEnEL22ConGetPosicion() {
		Barraca barraca = new Barraca(1, 1);
		barraca.setPosicion(2, 2);
		Posicion posicion = new Posicion(2,2, barraca.esTerrestre());
		assertEquals(barraca.getPosicion(), posicion);
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
			barraca.crearUnidad();
			for (int j = 0; j < 3; j++) {
				barraca.avanzarTurno();
			}
			try {
				Unidad marine = barraca.obtenerUltimaUnidadConstruida();
				assertEquals(marine.getVidaActual(), 40);
			} catch (NoSeCreoUnaNuevaUnidad e) {}
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alConstruirUnaBarracaNoSeCreoUnaUnidadNueva(){
		Barraca barraca = crearBarracaConstruida();
		barraca.getSeCreoUnaUnidadNueva();
	}

	
	@Test(expected = NoSeCreoUnaNuevaUnidad.class)
	public void siLaBarracaCreaUnaUnidadYNoPasaronLosTurnosNecesariosParaQueSeConstruyaNoSeLaPuedeObtener() throws NoSeCreoUnaNuevaUnidad{
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		try {
			barraca.crearUnidad();
			@SuppressWarnings("unused")
			Unidad marine = barraca.obtenerUltimaUnidadConstruida();
		} catch (ElEdificioEstaEnConstruccion e) { 
			fail();
		}
	}
	

	@Test
	public void cuandoLaBarracaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Barraca barraca = new Barraca(1, 1);
		
		try {
			barraca.crearUnidad();
			
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alCrearseLaBarracaYPasar12TurnosYaNoEstaEnConstruccion() {
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		assertFalse(barraca.estaEnConstruccion());
	}
	
	@Test
	public void alCrearseLaBarracaEstaEnConstruccion() {
		Barraca barraca = new Barraca(1, 1);

		assertTrue(barraca.estaEnConstruccion());
	}
	
	@Test
	public void alCrearseLaBarracaYPasar12TurnosEstaTieneTodaLaVida() {
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		
		assertEquals(1000, barraca.getVidaActual());
	}
	
	@Test
	public void alCrearseLaBarracaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Barraca barraca = new Barraca(1, 1);
		barraca.avanzarTurno();
		assertTrue(0 < barraca.getVidaActual());
	}
}
