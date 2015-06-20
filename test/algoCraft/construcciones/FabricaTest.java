package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;

public class FabricaTest {
	
	private Fabrica crearFabricaConstruida(){
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		for(int i = 0; i < 12; i++)
			barraca.avanzarTurno(jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		for(int i = 0; i < 12; i++)
			fabrica.avanzarTurno(jugador);
		return fabrica;
	}
	
	private void construirEdificio(Edificio edificio, Jugador jugador) {
		for(int i = 0; i < 12; i++)
			edificio.avanzarTurno(jugador);
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		
		Posicion posicion = new Posicion(1,1, fabrica.esTerrestre());
		
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(2, 2, barraca);
		
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL22ConSetPosicion22() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		fabrica.setPosicion(2, 2);
		
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		construirEdificio(fabrica, jugador);
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		
		goliath.atacar(fabrica);
		
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		construirEdificio(fabrica, jugador);
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
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		assertTrue(fabrica.estaEnConstruccion());
	}
	
	@Test
	public void laFabricaDejaDeEstarEnConstruccionCuandoPasan12Turnos() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
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
		
		assertFalse(fabrica.seTerminoDeCrearLaUnidad());
	}
	
	@Test
	public void laFabricaTarda6TurnosLuegoDeSuConstrucionEnCrearUnMarine() throws ElEdificioEstaEnConstruccion{
		Jugador jugador = new Jugador("Jugador");
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearGoliath();
		int turno = 0;
		
		while(!fabrica.seTerminoDeCrearLaUnidad()){
			turno++;
			fabrica.avanzarTurno(jugador);
		}
		
		assertTrue(turno == 6);
	}

	@Test
	public void cuandoSeCreaUnaFabricaEstaNoCreoNingunaUnidad() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		
		assertEquals(false, fabrica.seTerminoDeCrearLaUnidad());
	}
	
	@Test(expected = ElEdificioEstaEnConstruccion.class)
	public void cuandoLaFabricaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		
		fabrica.crearGoliath();
	}
	
	@Test
	public void alCrearseLaFabricaYPasar12TurnosEstaTieneTodaLaVida() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		
		construirEdificio(fabrica, jugador);
		
		assertEquals(1250, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		fabrica.avanzarTurno(jugador);
		
		assertTrue(0 < fabrica.getVidaActual());
	}
	
	@Test(expected = NoSePuedeConstruirElEdificio.class)
	public void siLaBarracaNoEstaConstruidaLaFabricaLanzaUnErrorAlCrearse() {
		Barraca barraca = new Barraca(0, 0);

		new Fabrica(1, 1, barraca);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaSePuedeSeguirCreandoUnMarineYAhoraTambienUnGoliath() {
		Jugador jugador = new Jugador("Jugador");
		Barraca barraca = new Barraca(0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(1, 1, barraca);
		construirEdificio(fabrica, jugador);
		
		barraca.crearMarine();
		fabrica.crearGoliath();
		
		for (int i=0; i < 6; i++) {
			barraca.avanzarTurno(jugador);
			fabrica.avanzarTurno(jugador);
		}
		
		assertTrue(barraca.seTerminoDeCrearLaUnidad());
		assertTrue(fabrica.seTerminoDeCrearLaUnidad());
	}
}
