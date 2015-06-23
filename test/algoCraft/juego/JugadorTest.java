package algoCraft.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.construcciones.Edificio;
import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.ElJugadorNoEstaActivoException;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.juego.excepciones.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class JugadorTest {

	private void avanzarTurnos(int turnos, Jugador jugador) {
		for(int i = 0; i < turnos; i++)
			jugador.avanzarTurno();
	}
	
	@Test
	public void cuandoSeCreaElJugadorTiene200UnidadesDeMineral() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		assertEquals(200, jugador.getMineral());
	}
	
	@Test
	public void cuandoSeCreaElJugadorTiene0UnidadesDeGasVespeno() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		assertEquals(0, jugador.getGasVespeno());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteTieneUnNombre() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		assertEquals("Jugador", jugador.getNombre());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTieneEdificios() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		ArrayList<Edificio> edificios = jugador.getEdificios();
		
		assertEquals(true, edificios.isEmpty());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTieneUnidades() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		ArrayList<Unidad> unidades = jugador.getUnidades();
		
		assertEquals(true, unidades.isEmpty());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTienePoblacion() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		int poblacion = jugador.getPoblacion();
		
		assertEquals(0, poblacion);
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoTienePoblacionMaxima() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		int poblacion = jugador.getPoblacionMaxima();
		
		assertEquals(0, poblacion);
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteTieneUnaBase() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		Base base = jugador.getBase();
		
		assertEquals(2500, base.getVidaActual());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoPerdioLaPartida() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		assertFalse(jugador.perdioLaPartida());
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElJugadorTiene50UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Mineral mineral = new Mineral(1, 1);
		int cantidadInicial = jugador.getMineral();
		
		jugador.crearCentroDeMineral(mineral);
		int cantidadFinal = jugador.getMineral();
		
		assertTrue(cantidadInicial == cantidadFinal + 50);
	}
	
	@Test
	public void cuandoSeTieneUnCentroDeMineralConstruidoYPasaUnTurnoElJugadorTiene10UnidadesMasDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		Mineral mineral = new Mineral(1, 1);
		jugador.crearCentroDeMineral(mineral);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministrosElJugadorTiene100UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		int cantidadInicial = jugador.getMineral();
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadFinal = jugador.getMineral();
		
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruidoElJugadorTiene5UnidadesMasDePoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(6, jugador);
		int cantidadFinal = jugador.getPoblacionMaxima();
		
		assertTrue(cantidadInicial + 5 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruyendoseElJugadorNoTieneMasUnidadesDePoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(3, jugador);
		int cantidadFinal = jugador.getPoblacionMaxima();
		
		assertTrue(cantidadInicial == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministroslElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		int cantidadEdificiosInicial = (jugador.getCasas()).size();
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadEdificiosFinal = (jugador.getCasas()).size();
		
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene100UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		GasVespeno gas = new GasVespeno(1, 1);
		
		int cantidadInicial = jugador.getMineral();
		jugador.crearRefineria(gas);
		int cantidadFinal = jugador.getMineral();
		
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnaRefineriaConstruidaYPasaUnTurnoElJugadorTiene10UnidadesMasDeGasVespeno() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		GasVespeno gas = new GasVespeno(1, 1);
		
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		jugador.crearRefineria(gas);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	

	@Test
	public void cuandoSeCreaUnaFabricaElJugadorTiene200UnidadesMenosDeMineralY100DeGas() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearDepositoDeSuministros(0, 0);
		avanzarTurnos(6, jugador);
		jugador.crearFabrica(2, 2);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaElJugadorPasaATenerUnEdificioMas () {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
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
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		int cantidadInicialMineral = jugador.getMineral();
		jugador.crearBarraca(1, 1);
		int cantidadFinalMineral = jugador.getMineral();
		
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 150);
	}
	
	@Test(expected = NoSeTienenLosRecursosSuficientes.class)
	public void cuandoSeIntentaCrearUnaBarracaConMineralInsuficienteLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		jugador.crearBarraca(1, 1);
		jugador.crearBarraca(2, 2);
	}
	
	@Test(expected = NoSeTienenLosRecursosSuficientes.class)
	public void cuandoSeIntentaCrearUnaBarracaConGasVespenoInsuficienteLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.crearFabrica(2, 2);
	}
	
	@Test
	public void cuandoSeCreaUnMarineAumentaEn1LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.sumarPoblacionMaxima(1);

		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		avanzarTurnos(3, jugador);
		
		assertTrue(1 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoSeCrean2MarinesAumentaEn2LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.sumarPoblacionMaxima(2);

		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		avanzarTurnos(6, jugador);
		
		assertTrue(2 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoSeCrean2MarinesAumentaEn2BarracasDistintasAumentaEn2LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarPoblacionMaxima(2);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		
		jugador.crearBarraca(1, 1);
		jugador.crearBarraca(2, 2);
		avanzarTurnos(12, jugador);

		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(2, 2, true))));
		avanzarTurnos(6, jugador);
		
		assertTrue(2 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoSeCreaUnGoliathAumentaEn1LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12, jugador);
		jugador.sumarPoblacionMaxima(2);

		jugador.crearGoliath(((Fabrica) mapa.getUnidad(new Posicion(2, 2, true))));
		avanzarTurnos(6, jugador);
		
		assertTrue(1 == jugador.getUnidades().size());
	}

	@Test
	public void cuandoSeCrean2GoliathsAumentaEn1LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12, jugador);
		jugador.sumarPoblacionMaxima(4);

		jugador.crearGoliath(((Fabrica) mapa.getUnidad(new Posicion(2, 2, true))));
		jugador.crearGoliath(((Fabrica) mapa.getUnidad(new Posicion(2, 2, true))));
		avanzarTurnos(12, jugador);
		
		assertTrue(2 == jugador.getUnidades().size());
	}

	@Test
	public void cuandoSeCreaUnMarineYUnGoliathAumentaEn2LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarPoblacionMaxima(3);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12, jugador);
		
		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		jugador.crearGoliath(((Fabrica) mapa.getUnidad(new Posicion(2, 2, true))));
		avanzarTurnos(6, jugador);
		
		assertTrue(2 == jugador.getUnidades().size());
	}
	
	@Test(expected = NoSePuedeConstruirLaUnidadPorSobrepoblacion.class)
	public void cuandoSeIntentaCrearUnaUnidadSinTenerLaCapacidadNecesariaLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);

		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
	}
	
	@Test(expected = NoSePuedeConstruirLaUnidadPorSobrepoblacion.class)
	public void cuandoSeIntentaCrearUnaUnidadSinTenerLaCapacidadNecesariaYTeniendoUnidadesLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.sumarPoblacionMaxima(1);
		
		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		avanzarTurnos(3, jugador);
		jugador.crearMarine(((Barraca) mapa.getUnidad(new Posicion(1, 1, true))));
		
	}

	@Test
	public void seCreanDosBarracasYSeConstruyeUnMarineEnLaSegundaElJugadorTiene1UnidadMas() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarPoblacionMaxima(3);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);

		jugador.crearBarraca(3, 3);
		avanzarTurnos(12, jugador);
		
		Barraca miBarraca = (Barraca) mapa.getUnidad(new Posicion(1, 1, true));
		jugador.crearMarine(miBarraca);
		avanzarTurnos(4, jugador);
		
		assertTrue(1 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoLaBaseDeUnJugadorEsDestruidaEstePierdeLaPartida() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Base base = jugador.getBase();
		Goliath goliath = new Goliath(2,2);
		
		while (!base.estaDestruido()) {
			goliath.atacar(base);
			goliath.avanzarTurno();
		}
		
		assertTrue(jugador.perdioLaPartida());
	}
	
	@Test(expected = ElJugadorNoEstaActivoException.class)
	public void cuandoElJugadorNoEstaActivoYSeIntentaCrearUnEdificioLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.crearBarraca(1, 1);
	}
	
	
}
