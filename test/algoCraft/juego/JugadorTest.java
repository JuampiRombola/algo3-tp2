package algoCraft.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoCraft.construcciones.Edificio;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.mapa.Mapa;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Unidad;

public class JugadorTest {

	private void avanzarTurnos(int turnos, Jugador jugador) {
		for(int i = 0; i < turnos; i++) {
			jugador.avanzarTurno();
		}
	}
	
	@Test
	public void cuandoSeCreaElJugadorTiene200UnidadesDeMineral() {
		Jugador jugador = new Jugador("Jugador");
		
		assertEquals(200, jugador.getMineral());
	}
	
	@Test
	public void cuandoSeCreaElJugadorTiene0UnidadesDeGasVespeno() {
		Jugador jugador = new Jugador("Jugador");
		
		assertEquals(0, jugador.getGasVespeno());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteTieneUnNombre() {
		Jugador jugador = new Jugador("Jugador");
		
		assertEquals("Jugador", jugador.getNombre());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTieneEdificios() {
		Jugador jugador = new Jugador("Jugador");
		
		ArrayList<Edificio> edificios = jugador.getEdificios();
		
		assertEquals(true, edificios.isEmpty());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTieneUnidades() {
		Jugador jugador = new Jugador("Jugador");
		
		ArrayList<Unidad> unidades = jugador.getUnidades();
		
		assertEquals(true, unidades.isEmpty());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTienePoblacion() {
		Jugador jugador = new Jugador("Jugador");
		
		int poblacion = jugador.getPoblacion();
		
		assertEquals(0, poblacion);
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTienePoblacionMaxima() {
		Jugador jugador = new Jugador("Jugador");
		
		int poblacion = jugador.getPoblacionMaxima();
		
		assertEquals(0, poblacion);
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElJugadorTiene50UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		Mineral mineral = new Mineral(1, 1);
		int cantidadInicial = jugador.getMineral();
		jugador.crearCentroDeMineral(mineral);
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial == cantidadFinal + 50);
	}
	
	@Test
	public void cuandoSeTieneUnCentroDeMineralConstruidoYPasaUnTurnoElJugadorTiene10UnidadesMasDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		Mineral mineral = new Mineral(1, 1);
		jugador.crearCentroDeMineral(mineral);
		avanzarTurnos(4, jugador);
		int cantidadInicial = jugador.getMineral();
		jugador.avanzarTurno();
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial + 10 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnCentroDeMineralConstruidoYPasanDosTurnosElJugadorTiene20UnidadesMasDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		Mineral mineral = new Mineral(1, 1);
		jugador.crearCentroDeMineral(mineral);
		avanzarTurnos(4, jugador);
		int cantidadInicial = jugador.getMineral();
		avanzarTurnos(2, jugador);
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial + 20 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		Mineral mineral = new Mineral(1, 1);
		jugador.crearCentroDeMineral(mineral);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministrosElJugadorTiene100UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		int cantidadInicial = jugador.getMineral();
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruidoElJugadorTiene5UnidadesMasDePoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(6, jugador);
		int cantidadFinal = jugador.getPoblacionMaxima();
		assertTrue(cantidadInicial + 5 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruyendoseElJugadorNoTieneMasUnidadesDePoblacion() {
		Jugador jugador = new Jugador("Jugador");
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(3, jugador);
		int cantidadFinal = jugador.getPoblacionMaxima();
		assertTrue(cantidadInicial == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministroslElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene100UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		int cantidadInicial = jugador.getMineral();
		jugador.crearRefineria(gas);
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnaRefineriaConstruidaYPasaUnTurnoElJugadorTiene10UnidadesMasDeGasVespeno() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		jugador.crearRefineria(gas);
		avanzarTurnos(6, jugador);
		int cantidadInicial = jugador.getGasVespeno();
		jugador.avanzarTurno();
		int cantidadFinal = jugador.getGasVespeno();
		assertTrue(cantidadInicial + 10 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnaRefineriaConstruidaYPasanDosTurnosElJugadorTiene20UnidadesMasDeGasVespeno() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		jugador.crearRefineria(gas);
		avanzarTurnos(6, jugador);
		int cantidadInicial = jugador.getGasVespeno();
		avanzarTurnos(2, jugador);
		int cantidadFinal = jugador.getGasVespeno();
		assertTrue(cantidadInicial + 20 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		jugador.crearRefineria(gas);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	

	@Test
	public void cuandoSeCreaUnaFabricaElJugadorTiene200UnidadesMenosDeMineralY100DeGas() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		int cantidadInicialMineral = jugador.getMineral();
		int cantidadInicialGas = jugador.getGasVespeno();
		avanzarTurnos(12, jugador);
		jugador.crearFabrica(2, 2);
		int cantidadFinalMineral = jugador.getMineral();
		int cantidadFinalGas = jugador.getGasVespeno();
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 200);
		assertTrue(cantidadInicialGas == cantidadFinalGas + 100);
	}
	
	@Test(expected = NoSePuedeConstruirElEdificio.class)
	public void cuandoSeTrataDeCrearUnaFabricaTeniendoUnaBarracaEnConstruccionSeLanzaUnaExcepcion () {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		jugador.crearDepositoDeSuministros(0, 0);
		avanzarTurnos(10, jugador);
		jugador.crearFabrica(2, 2);
	}
	
	@Test(expected = NoSePuedeConstruirElEdificio.class)
	public void cuandoSeTrataDeCrearUnaFabricaSinTenerUnaBarracaSeLanzaUnaExcepcion () {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearDepositoDeSuministros(0, 0);
		avanzarTurnos(6, jugador);
		jugador.crearFabrica(2, 2);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaElJugadorPasaATenerUnEdificioMas () {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		jugador.crearDepositoDeSuministros(0, 0);
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		avanzarTurnos(12, jugador);
		jugador.crearFabrica(2, 2);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaElJugadorTiene150UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		int cantidadInicialMineral = jugador.getMineral();
		jugador.crearBarraca(1, 1);
		int cantidadFinalMineral = jugador.getMineral();
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 150);
	}
}
