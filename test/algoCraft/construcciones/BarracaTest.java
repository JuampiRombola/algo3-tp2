package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.construcciones.excepciones.ElEdificioNoPuedeCrearLaUnidad;
import algoCraft.construcciones.excepciones.NoSeCreoUnaNuevaUnidad;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class BarracaTest {

	private Barraca crearBarracaConstruida(){
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
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
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		int turno = 0;
		while(barraca.estaEnConstruccion()){
			barraca.avanzarTurno(jugador);
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
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = crearBarracaConstruida();
		barraca.crearMarine();
		int turno = 0;
		while(!barraca.getSeCreoUnaUnidadNueva()){
			turno++;
			barraca.avanzarTurno(jugador);
		}
		assertTrue(turno == 3);
	}
	
	@Test
	public void siSacoElMarineDespuesDeHaberSidoCreadoLaBarracaMuestraQueNoHayUnaUnidadNueva() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = crearBarracaConstruida();
		barraca.crearMarine();
		while(!barraca.getSeCreoUnaUnidadNueva()){
			barraca.avanzarTurno(jugador);
		}
		barraca.obtenerUltimaUnidadConstruida();
		assertFalse(barraca.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void crear2MarinesLleva6Turnos() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = crearBarracaConstruida();
		barraca.crearMarine();
		int turno = 0;
		int marinesCreados = 0;
		while (marinesCreados < 2){
			while(!barraca.getSeCreoUnaUnidadNueva()){
				turno++;
				barraca.avanzarTurno(jugador);
			}
			marinesCreados++;
			barraca.obtenerUltimaUnidadConstruida();
			barraca.crearMarine();
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
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
		}
		int vidaInicial = barraca.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		
		goliath.atacar(barraca);
		
		assertTrue(barraca.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		Goliath goliath = new Goliath(2,2);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
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
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
		}
		barraca.crearMarine();
		for (int j = 0; j < 3; j++) {
			barraca.avanzarTurno(jugador);
		}
		Unidad marine = barraca.obtenerUltimaUnidadConstruida();
		assertEquals(marine.getVidaActual(), 40);
	}
	
	@Test
	public void alConstruirUnaBarracaNoSeCreoUnaUnidadNueva(){
		Barraca barraca = crearBarracaConstruida();
		barraca.getSeCreoUnaUnidadNueva();
	}

	
	@Test(expected = NoSeCreoUnaNuevaUnidad.class)
	public void siLaBarracaCreaUnaUnidadYNoPasaronLosTurnosNecesariosParaQueSeConstruyaNoSeLaPuedeObtener() throws NoSeCreoUnaNuevaUnidad{
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
		}
		barraca.crearMarine();
		@SuppressWarnings("unused")
		Unidad marine = barraca.obtenerUltimaUnidadConstruida();
	}
	

	@Test
	public void cuandoLaBarracaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Barraca barraca = new Barraca(1, 1);
		
		try {
			barraca.crearMarine();
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alCrearseLaBarracaYPasar12TurnosYaNoEstaEnConstruccion() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
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
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno(jugador);
		}
		
		assertEquals(1000, barraca.getVidaActual());
	}
	
	@Test
	public void alCrearseLaBarracaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(1, 1);
		barraca.avanzarTurno(jugador);
		assertTrue(0 < barraca.getVidaActual());
	}
	
	@Test
	public void cuandoLaBarracaTrataDeCrearUnGoliathLanzaUnaExcepcion() throws ElEdificioEstaEnConstruccion {
		Barraca barraca = new Barraca(1, 1);
		
		try {
			barraca.crearGoliath();
			fail();
		} catch (ElEdificioNoPuedeCrearLaUnidad e) {}
	}
}
