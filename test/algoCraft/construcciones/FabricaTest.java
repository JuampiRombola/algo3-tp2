package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class FabricaTest {
	
	private Fabrica crearFabricaConstruida(){
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		for(int i = 0; i < 12; i++)
			fabrica.avanzarTurno(jugador);
		return fabrica;
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Fabrica fabrica = new Fabrica(1, 1);
		Posicion posicion = new Posicion(1,1, fabrica.esTerrestre());
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Fabrica fabrica = new Fabrica(2, 2);
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL22ConSetPosicion22() {
		Fabrica fabrica = new Fabrica(1, 1);
		fabrica.setPosicion(2, 2);
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Fabrica fabrica = new Fabrica(1, 1);
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno(jugador);
		}
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		
		goliath.atacar(fabrica);
		
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno(jugador);
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
	public void laFabricaEstaEnConstruccionCuandoRecienEsCreada() {
		Fabrica fabrica = new Fabrica(1, 1);
		assertTrue(fabrica.estaEnConstruccion());
	}
	
	@Test
	public void laFabricaDejaDeEstarEnConstruccionCuandoPasan12Turnos() {
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		int turno = 0;
		while(fabrica.estaEnConstruccion()){
			fabrica.avanzarTurno(jugador);
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
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearGoliath();
		int turno = 0;
		while(!fabrica.getSeCreoUnaUnidadNueva()){
			turno++;
			fabrica.avanzarTurno(jugador);
		}
		assertTrue(turno == 6);
	}

	@Test
	public void siSacoElGoliathDespuesDeHaberSidoCreadoLaBarracaMuestraQueNoHayUnaUnidadNueva() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearGoliath();
		while(!fabrica.getSeCreoUnaUnidadNueva()){
			fabrica.avanzarTurno(jugador);
		}
		fabrica.obtenerUltimaUnidadConstruida();
		assertFalse(fabrica.getSeCreoUnaUnidadNueva());
	}
	
	@Test
	public void crear2GoliathsLleva12Turnos() throws ElEdificioEstaEnConstruccion, NoSeCreoUnaNuevaUnidad{
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearGoliath();
		int turno = 0;
		int marinesCreados = 0;
		while (marinesCreados < 2){
			while(!fabrica.getSeCreoUnaUnidadNueva()){
				turno++;
				fabrica.avanzarTurno(jugador);
			}
			marinesCreados++;
			fabrica.obtenerUltimaUnidadConstruida();
			fabrica.crearGoliath();
		}
		assertTrue(turno == 12);
	}
	
	@Test
	public void cuandoLaFabricaCreaUnMarineEsteTieneTodaSuVida() {
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno(jugador);
		}
		fabrica.crearGoliath();
		for (int j = 0; j < 6; j++) {
			fabrica.avanzarTurno(jugador);
		}
		Unidad goliath = fabrica.obtenerUltimaUnidadConstruida();
		assertEquals(goliath.getVidaActual(), 125);
	}
	
	@Test(expected = NoSeCreoUnaNuevaUnidad.class)
	public void siLaFabricaCreaUnaUnidadYNoPasaronLosTurnosNecesariosParaQueSeConstruyaNoSeLaPuedeObtener() throws NoSeCreoUnaNuevaUnidad{
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno(jugador);
		}
		fabrica.crearGoliath();
		@SuppressWarnings("unused")
		Unidad goliath = fabrica.obtenerUltimaUnidadConstruida();
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
			fabrica.crearGoliath();
			
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alCrearseLaFabricaYPasar12TurnosEstaTieneTodaLaVida() {
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		
		for (int i = 0; i < 12; i++) {
			fabrica.avanzarTurno(jugador);
		}
		
		assertEquals(1250, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = new Fabrica(1, 1);
		fabrica.avanzarTurno(jugador);
		assertTrue(0 < fabrica.getVidaActual());
	}
	
	@Test
	public void cuandoLaBarracaTrataDeCrearUnGoliathLanzaUnaExcepcion() throws ElEdificioEstaEnConstruccion {
		Fabrica fabrica = new Fabrica(1, 1);
		
		try {
			fabrica.crearMarine();
			fail();
		} catch (ElEdificioNoPuedeCrearLaUnidad e) {}
	}
}
