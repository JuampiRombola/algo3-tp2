package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;

public class BarracaTest {

	private Barraca crearBarracaConstruida(){
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 1, 1);
		for (int i = 0; i < 12; i++)
			barraca.avanzarTurno();
		return barraca;
	}
	
	@Test
	public void laVidaMaximaDeLaBarracaEs1000() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		
		assertTrue(1000 == barraca.getVidaMaxima());
	}
	
	@Test
	public void laBarracaEstaEnConstruccionCuandoRecienEsCreada(){
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		assertTrue(barraca.estaEnConstruccion());
	}
	
	@Test
	public void laBarracaDejaDeEstarEnConstruccionCuandoPasan12Turnos(){
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		int turno = 0;
		while(barraca.estaEnConstruccion()){
			barraca.avanzarTurno();
			turno++;
		}
		assertTrue(turno == 12);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaEstaNoCreoNingunaUnidad() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		assertFalse(barraca.seTerminoDeCrearLaUnidad());
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaYSeTerminaDeConstruirNoSeCreoNingunaUnidad(){
		Barraca barraca = crearBarracaConstruida();
		assertFalse(barraca.seTerminoDeCrearLaUnidad());
	}
	
	@Test
	public void laBarracaTarda3TurnosLuegoDeSuConstrucionEnCrearUnMarine() throws ElEdificioEstaEnConstruccion{
		Mapa.reiniciarInstanciaParaTest();
		Barraca barraca = crearBarracaConstruida();
		barraca.crearMarine();
		int turno = 0;
		while(!barraca.seTerminoDeCrearLaUnidad()){
			turno++;
			barraca.avanzarTurno();
		}
		assertTrue(turno == 3);
	}
	
	@Test
	public void laBarracaTarda6TurnosLuegoDeSuConstrucionEnCrear2Marines() throws ElEdificioEstaEnConstruccion{
		Mapa.reiniciarInstanciaParaTest();
		Barraca barraca = crearBarracaConstruida();
		barraca.crearMarine();
		int turno = 0;
		int marinesCreados = 0;
		while(marinesCreados < 2){
			while(!barraca.seTerminoDeCrearLaUnidad()){
				turno++;
				barraca.avanzarTurno();
			}
			marinesCreados++;
			barraca.crearMarine();
		}
		assertTrue(turno == 6);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaEstaEstaEnTierra() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		assertEquals(true, barraca.esTerrestre());
	}
	@Test
	public void unaBarracaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		Posicion posicion = new Posicion(1,1, barraca.esTerrestre());
		assertEquals(barraca.getPosicion(), posicion);
	}
	
	@Test
	public void unaBarracaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 2, 2);
		Posicion posicion = new Posicion(2,2, barraca.esTerrestre());
		assertEquals(barraca.getPosicion(), posicion);
	}
	
	@Test
	public void unaBarracaEnEl11movidaAl22DevuelveUnaPosicionEnEL22ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		barraca.setPosicion(2, 2);
		Posicion posicion = new Posicion(2,2, barraca.esTerrestre());
		assertEquals(barraca.getPosicion(), posicion);
	}

	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Jugador jugador2 = new Jugador("Jugador", new Base(4, 4));
		jugador2.activar();
		Barraca barraca = new Barraca(jugador, 1, 1);
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		int vidaInicial = barraca.getVidaActual();
		Goliath goliath = new Goliath(jugador2, 2, 2);
		
		goliath.atacar(barraca);
		
		assertTrue(barraca.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Jugador jugador2 = new Jugador("Jugador", new Base(4, 4));
		jugador2.activar();
		Barraca barraca = new Barraca(jugador, 1, 1);
		Goliath goliath = new Goliath(jugador2, 2, 2);
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
	public void alConstruirUnaBarracaNoSeCreoUnaUnidadNueva(){
		Barraca barraca = crearBarracaConstruida();
		barraca.seTerminoDeCrearLaUnidad();
	}

	@Test
	public void cuandoLaBarracaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		
		try {
			barraca.crearMarine();
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
	}
	
	@Test
	public void alCrearseLaBarracaYPasar12TurnosYaNoEstaEnConstruccion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		assertFalse(barraca.estaEnConstruccion());
	}
	
	@Test
	public void alCrearseLaBarracaEstaEnConstruccion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);

		assertTrue(barraca.estaEnConstruccion());
	}
	
	@Test
	public void alCrearseLaBarracaYPasar12TurnosEstaTieneTodaLaVida() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		
		for (int i = 0; i < 12; i++) {
			barraca.avanzarTurno();
		}
		
		assertEquals(1000, barraca.getVidaActual());
	}
	
	@Test
	public void alCrearseLaBarracaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Barraca barraca = new Barraca(jugador, 1, 1);
		barraca.avanzarTurno();
		assertTrue(0 < barraca.getVidaActual());
	}
	
	@Test
	public void SeCreanDosBarracasYSeConstruyeUnMarineEnLaPrimeraCorrectamente(){
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeMineral(1000);
		jugador.activar();
		
		Barraca barraca1 = new Barraca(jugador, 1, 1);
		for (int i = 0; i < 12; i++)
			barraca1.avanzarTurno();
		mapa.agregarUnidad(barraca1);
		
		Barraca barraca2 = new Barraca(jugador, 2, 2);
		for (int i = 0; i < 12; i++)
			barraca2.avanzarTurno();
		mapa.agregarUnidad(barraca2);
		
		barraca1 = (Barraca) mapa.getUnidad(new Posicion(1, 1, true));
		barraca1.crearMarine();
		
		for (int i = 0; i < 4; i++)
			barraca1.avanzarTurno();
		
		assertTrue(barraca1.seTerminoDeCrearLaUnidad());
		assertTrue(1 == jugador.getUnidades().size());
	}
	
	@Test
	public void laBarracaCuesta150UnidadesDeMineral() {
		
		assertEquals(150, Barraca.getCostoMineral());
	}
	
	@Test
	public void laBarracaCuesta0UnidadesDeGasVespeno() {
		
		assertEquals(0, Barraca.getCostoGas());
	}
	
	@Test
	public void laBarracaTarda12TurnosEnConstruirse() {
		Barraca barraca = crearBarracaConstruida();
		assertEquals(12, barraca.getTurnosEnConstruirse());
	}
	
	@Test
	public void siSeAvanzaUnTurnoLuegoDeQueSeComenzoAConstruirLaBarracaSuContadorDeTurnosEsIgualA1() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Barraca barraca = new Barraca(jugador, 1, 1);
		
		barraca.avanzarTurno();
		
		assertEquals(1, barraca.getTurnosQuePasaroDeConstruccion());
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaNoTieneUnidadesEnConstruccion() {
		Barraca barraca = crearBarracaConstruida();
		assertEquals(0, barraca.getUnidadesEnCola());
	}
	
	@Test
	public void cuandoSeComienzaACrearUnMarineFaltanTresTurnosParaQueSeTermineDeConstruir() {
		Barraca barraca = crearBarracaConstruida();
		barraca.crearMarine();
		assertEquals(3, barraca.getTurnosParaProducirUnidad());
	}
}
