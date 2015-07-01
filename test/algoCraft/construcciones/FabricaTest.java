package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;

public class FabricaTest {
	
	private Fabrica crearFabricaConstruida(){
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.activar();
		Barraca barraca = new Barraca(jugador, 0, 0);
		for(int i = 0; i < 12; i++)
			barraca.avanzarTurno();
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		for(int i = 0; i < 12; i++)
			fabrica.avanzarTurno();
		return fabrica;
	}
	
	private void construirEdificio(Edificio edificio, Jugador jugador) {
		Mapa.reiniciarInstanciaParaTest();
		for(int i = 0; i < 12; i++)
			edificio.avanzarTurno();
	}
	
	@Test
	public void laVidaMaximaDeLaFabricaEs1250() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		
		assertTrue(1250 == fabrica.getVidaMaxima());
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		
		Posicion posicion = new Posicion(1,1, fabrica.esTerrestre());
		
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl22DevuelveUnaPosicionEnEL11ConGetPosicion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 2, 2, barraca);
		
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void unaFabricaEnEl11DevuelveUnaPosicionEnEL22ConSetPosicion22() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		fabrica.setPosicion(2, 2);
		
		Posicion posicion = new Posicion(2,2, fabrica.esTerrestre());
		
		assertEquals(fabrica.getPosicion(), posicion);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Jugador jugador2 = new Jugador("Jugador", new Base(4, 4));
		jugador2.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		construirEdificio(fabrica, jugador);
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(jugador2, 2, 2);
		
		goliath.atacar(fabrica);
		
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		Jugador jugador2 = new Jugador("Jugador", new Base(4, 4));
		jugador2.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		construirEdificio(fabrica, jugador);
		Goliath goliath = new Goliath(jugador2, 2, 2);
		
		while (!fabrica.estaDestruido()) {
			goliath.atacar(fabrica);
			goliath.avanzarTurno();
		}
		goliath.atacar(fabrica);
		
		assertEquals(0, fabrica.getVidaActual());
	}
	
	@Test
	public void laFabricaEstaEnConstruccionCuandoRecienEsCreada() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		assertTrue(fabrica.estaEnConstruccion());
	}
	
	@Test
	public void laFabricaDejaDeEstarEnConstruccionCuandoPasan12Turnos() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
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
		
		assertFalse(fabrica.seTerminoDeCrearLaUnidad());
	}
	
	@Test
	public void laFabricaTarda6TurnosLuegoDeSuConstrucionEnCrearUnMarine() throws ElEdificioEstaEnConstruccion{
		Mapa.reiniciarInstanciaParaTest();
		Fabrica fabrica = crearFabricaConstruida();
		fabrica.crearGoliath();
		int turno = 0;
		
		while(!fabrica.seTerminoDeCrearLaUnidad()){
			turno++;
			fabrica.avanzarTurno();
		}
		
		assertTrue(turno == 6);
	}

	@Test
	public void cuandoSeCreaUnaFabricaEstaNoCreoNingunaUnidad() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		
		assertEquals(false, fabrica.seTerminoDeCrearLaUnidad());
	}
	
	@Test(expected = ElEdificioEstaEnConstruccion.class)
	public void cuandoLaFabricaNoEstaHabilidadNoPuedeCrearUnaUnidadYLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		
		fabrica.crearGoliath();
	}
	
	@Test
	public void alCrearseLaFabricaYPasar12TurnosEstaTieneTodaLaVida() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		
		construirEdificio(fabrica, jugador);
		
		assertEquals(1250, fabrica.getVidaActual());
	}
	
	@Test
	public void alCrearseLaFabricaYPasarUnTurnoTieneMasDe0PuntosDeVida() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		fabrica.avanzarTurno();
		
		assertTrue(0 < fabrica.getVidaActual());
	}
	
	@Test(expected = NoSePuedeConstruirElEdificio.class)
	public void siLaBarracaNoEstaConstruidaLaFabricaLanzaUnErrorAlCrearse() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);

		new Fabrica(jugador, 1, 1, barraca);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaSePuedeSeguirCreandoUnMarineYAhoraTambienUnGoliath() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		Barraca barraca = new Barraca(jugador, 0, 0);
		construirEdificio(barraca, jugador);
		
		Fabrica fabrica = new Fabrica(jugador, 1, 1, barraca);
		construirEdificio(fabrica, jugador);
		
		barraca.crearMarine();
		fabrica.crearGoliath();
		
		for (int i=0; i < 6; i++) {
			barraca.avanzarTurno();
			fabrica.avanzarTurno();
		}
		
		assertTrue(barraca.seTerminoDeCrearLaUnidad());
		assertTrue(fabrica.seTerminoDeCrearLaUnidad());
	}
	
	@Test
	public void laFabricaCuesta200UnidadesDeMineral() {
		
		assertEquals(200, Fabrica.getCostoMineral());
	}
	
	@Test
	public void laFabricaCuesta100UnidadesDeGasVespeno() {
		
		assertEquals(100, Fabrica.getCostoGas());
	}
	
	@Test
	public void cuandoSeConstruyeUnaFabricaEnUnaPosicionOcupadaNoSeLeCobraAlJugador() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.activar();
		Barraca barraca = new Barraca(jugador, 1, 1);
		for(int i = 0; i < 12; i++)
			barraca.avanzarTurno();
		Mapa.getMapa().agregarUnidad(barraca);
		int mineralesAntesDeLaFabrica = jugador.getMineral();
		
		new Fabrica(jugador, 1, 1, barraca);
		
		assertTrue(jugador.getMineral() == mineralesAntesDeLaFabrica);
	}
}
