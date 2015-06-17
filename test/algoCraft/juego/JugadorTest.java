package algoCraft.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoCraft.construcciones.Edificio;
import algoCraft.juego.Jugador;
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
		Jugador jugador = new Jugador("Jugador");
		Mineral mineral = new Mineral(1, 1);
		int cantidadInicial = jugador.getMineral();
		try {
			jugador.crearCentroDeMineral(mineral);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial == cantidadFinal + 50);
	}
	
	@Test
	public void cuandoSeTieneUnCentroDeMineralConstruidoYPasaUnTurnoElJugadorTiene10UnidadesMasDeMineral() {
		Jugador jugador = new Jugador("Jugador");
		Mineral mineral = new Mineral(1, 1);
		try {
			jugador.crearCentroDeMineral(mineral);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		avanzarTurnos(4, jugador);
		int cantidadInicial = jugador.getMineral();
		jugador.avanzarTurno();
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial + 10 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnCentroDeMineralConstruidoYPasanDosTurnosElJugadorTiene20UnidadesMasDeMineral() {
		Jugador jugador = new Jugador("Jugador");
		Mineral mineral = new Mineral(1, 1);
		try {
			jugador.crearCentroDeMineral(mineral);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		avanzarTurnos(4, jugador);
		int cantidadInicial = jugador.getMineral();
		avanzarTurnos(2, jugador);
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial + 20 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElJugadorTiene1EdificioMas() {
		Jugador jugador = new Jugador("Jugador");
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		Mineral mineral = new Mineral(1, 1);
		try {
			jugador.crearCentroDeMineral(mineral);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministrosElJugadorTiene100UnidadesMenosDeMineral() {
		Jugador jugador = new Jugador("Jugador");
		int cantidadInicial = jugador.getMineral();
		try {
			jugador.crearDepositoDeSuministros(1, 1);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruidoElJugadorTiene5UnidadesMasDePoblacion() {
		Jugador jugador = new Jugador("Jugador");
		try {
			jugador.crearDepositoDeSuministros(1, 1);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(6, jugador);
		int cantidadFinal = jugador.getPoblacionMaxima();
		assertTrue(cantidadInicial + 5 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruyendoseElJugadorNoTieneMasUnidadesDePoblacion() {
		Jugador jugador = new Jugador("Jugador");
		try {
			jugador.crearDepositoDeSuministros(1, 1);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(3, jugador);
		int cantidadFinal = jugador.getPoblacionMaxima();
		assertTrue(cantidadInicial == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministroslElJugadorTiene1EdificioMas() {
		Jugador jugador = new Jugador("Jugador");
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		try {
			jugador.crearDepositoDeSuministros(1, 1);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene100UnidadesMenosDeMineral() {
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		int cantidadInicial = jugador.getMineral();
		try {
			jugador.crearRefineria(gas);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadFinal = jugador.getMineral();
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnaRefineriaConstruidaYPasaUnTurnoElJugadorTiene10UnidadesMasDeGasVespeno() {
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		try {
			jugador.crearRefineria(gas);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		avanzarTurnos(6, jugador);
		int cantidadInicial = jugador.getGasVespeno();
		jugador.avanzarTurno();
		int cantidadFinal = jugador.getGasVespeno();
		assertTrue(cantidadInicial + 10 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnaRefineriaConstruidaYPasanDosTurnosElJugadorTiene20UnidadesMasDeGasVespeno() {
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		try {
			jugador.crearRefineria(gas);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		avanzarTurnos(6, jugador);
		int cantidadInicial = jugador.getGasVespeno();
		avanzarTurnos(2, jugador);
		int cantidadFinal = jugador.getGasVespeno();
		assertTrue(cantidadInicial + 20 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene1EdificioMas() {
		Jugador jugador = new Jugador("Jugador");
		GasVespeno gas = new GasVespeno(1, 1);
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		try {
			jugador.crearRefineria(gas);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	

	@Test
	public void cuandoSeCreaUnaFabricaElJugadorTiene200UnidadesMenosDeMineralY100DeGas() {
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		try {
			jugador.crearBarraca(1, 1);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadInicialMineral = jugador.getMineral();
		int cantidadInicialGas = jugador.getGasVespeno();
		avanzarTurnos(12, jugador);
		try {
			try {
				jugador.crearFabrica(2, 2);
			} catch (NoSeTienenLosRecursosSuficientes e) {}
		} catch (NoSePuedeConstruirElEdificio e) {}
		int cantidadFinalMineral = jugador.getMineral();
		int cantidadFinalGas = jugador.getGasVespeno();
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 200);
		assertTrue(cantidadInicialGas == cantidadFinalGas + 100);
	}
	
	@Test
	public void cuandoSeTrataDeCrearUnaFabricaTeniendoUnaBarracaEnConstruccionSeLanzaUnaExcepcion () {
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		try {
			jugador.crearBarraca(1, 1);
			jugador.crearDepositoDeSuministros(0, 0);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		avanzarTurnos(10, jugador);
		try {
			try {
				jugador.crearFabrica(2, 2);
			} catch (NoSeTienenLosRecursosSuficientes e) {}
			fail();
		} catch (NoSePuedeConstruirElEdificio e) {}
	}
	
	@Test
	public void cuandoSeTrataDeCrearUnaFabricaSinTenerUnaBarracaSeLanzaUnaExcepcion () {
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		try {
			jugador.crearDepositoDeSuministros(0, 0);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		avanzarTurnos(6, jugador);
		try {
			try {
				jugador.crearFabrica(2, 2);
			} catch (NoSeTienenLosRecursosSuficientes e) {}
			fail();
		} catch (NoSePuedeConstruirElEdificio e) {}
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaElJugadorPasaATenerUnEdificioMas () {
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		try {
			jugador.crearBarraca(1, 1);
			jugador.crearDepositoDeSuministros(0, 0);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		avanzarTurnos(12, jugador);
		try {
			try {
				jugador.crearFabrica(2, 2);
			} catch (NoSeTienenLosRecursosSuficientes e) {}
		} catch (NoSePuedeConstruirElEdificio e) {}
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaElJugadorTiene150UnidadesMenosDeMineral() {
		Jugador jugador = new Jugador("Jugador");
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		int cantidadInicialMineral = jugador.getMineral();
		try {
			jugador.crearBarraca(1, 1);
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadFinalMineral = jugador.getMineral();
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 150);
	}
}
