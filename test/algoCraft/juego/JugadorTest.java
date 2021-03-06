package algoCraft.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.construcciones.DepositoDeSuministros;
import algoCraft.construcciones.Edificio;
import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.ConstruccionFueraDeRangoException;
import algoCraft.juego.excepciones.ElJugadorNoEstaActivoException;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.juego.excepciones.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.juego.excepciones.ElEdificioPerteneceAOtroJugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class JugadorTest {

	private void avanzarTurnos(int turnos) {
		Collection<Posicionable> posicionables = Mapa.getMapa().getElementos();
		for(int i = 0; i < turnos; i++)
			for(Posicionable posicionable : posicionables)
				posicionable.avanzarTurno();
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
		
		assertEquals(1000, base.getVidaActual());
	}
	
	@Test
	public void cuandoSeCreaUnJugadorEsteNoPerdioLaPartida() {
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		
		assertFalse(jugador.perdioLaPartida());
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElJugadorTiene50UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
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
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		Mineral mineral = new Mineral(1, 1);
		
		jugador.crearCentroDeMineral(mineral);
		avanzarTurnos(4);
		int cantidadInicial = jugador.getMineral();
		avanzarTurnos(1);
		int cantidadFinal = jugador.getMineral();
		
		assertTrue(cantidadInicial + 10 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnCentroDeMineralConstruidoYPasanDosTurnosElJugadorTiene20UnidadesMasDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		Mineral mineral = new Mineral(1, 1);
		
		jugador.crearCentroDeMineral(mineral);
		avanzarTurnos(4);
		int cantidadInicial = jugador.getMineral();
		avanzarTurnos(2);
		int cantidadFinal = jugador.getMineral();
		
		assertTrue(cantidadInicial + 20 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
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
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		int cantidadInicial = jugador.getMineral();
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadFinal = jugador.getMineral();
		
		assertTrue(cantidadInicial == cantidadFinal + 100);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruidoElJugadorTiene5UnidadesMasDePoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(6);
		int cantidadFinal = jugador.getPoblacionMaxima();
		
		assertTrue(cantidadInicial + 5 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnDepositoDeSuministrosConstruyendoseElJugadorNoTieneMasUnidadesDePoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadInicial = jugador.getPoblacionMaxima();
		avanzarTurnos(3);
		int cantidadFinal = jugador.getPoblacionMaxima();
		
		assertTrue(cantidadInicial == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnDepositoDeSuministroslElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		jugador.crearDepositoDeSuministros(1, 1);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene100UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
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
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		GasVespeno gas = new GasVespeno(1, 1);
		
		jugador.crearRefineria(gas);
		avanzarTurnos(6);
		int cantidadInicial = jugador.getGasVespeno();
		avanzarTurnos(1);
		int cantidadFinal = jugador.getGasVespeno();
		
		assertTrue(cantidadInicial + 10 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeTieneUnaRefineriaConstruidaYPasanDosTurnosElJugadorTiene20UnidadesMasDeGasVespeno() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		GasVespeno gas = new GasVespeno(1, 1);
		
		jugador.crearRefineria(gas);
		avanzarTurnos(6);
		int cantidadInicial = jugador.getGasVespeno();
		avanzarTurnos(2);
		int cantidadFinal = jugador.getGasVespeno();
		
		assertTrue(cantidadInicial + 20 == cantidadFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaRefinerialElJugadorTiene1EdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
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
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		int cantidadInicialMineral = jugador.getMineral();
		int cantidadInicialGas = jugador.getGasVespeno();
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
		int cantidadFinalMineral = jugador.getMineral();
		int cantidadFinalGas = jugador.getGasVespeno();
		
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 200);
		assertTrue(cantidadInicialGas == cantidadFinalGas + 100);
	}
	
	@Test(expected = NoSePuedeConstruirElEdificio.class)
	public void cuandoSeTrataDeCrearUnaFabricaTeniendoUnaBarracaEnConstruccionSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		jugador.crearDepositoDeSuministros(4, 4);
		avanzarTurnos(10);
		jugador.crearFabrica(2, 2);
	}
	
	@Test(expected = NoSePuedeConstruirElEdificio.class)
	public void cuandoSeTrataDeCrearUnaFabricaSinTenerUnaBarracaSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearDepositoDeSuministros(4, 4);
		avanzarTurnos(6);
		jugador.crearFabrica(2, 2);
	}
	
	@Test
	public void cuandoSeCreaUnaFabricaElJugadorPasaATenerUnEdificioMas() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		jugador.crearDepositoDeSuministros(4, 4);
		int cantidadEdificiosInicial = (jugador.getEdificios()).size();
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
		int cantidadEdificiosFinal = (jugador.getEdificios()).size();
		
		assertTrue(cantidadEdificiosInicial + 1 == cantidadEdificiosFinal);
	}
	
	@Test
	public void cuandoSeCreaUnaBarracaElJugadorTiene150UnidadesMenosDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		int cantidadInicialMineral = jugador.getMineral();
		jugador.crearBarraca(1, 1);
		int cantidadFinalMineral = jugador.getMineral();
		
		assertTrue(cantidadInicialMineral == cantidadFinalMineral + 150);
	}
	
	@Test(expected = NoSeTienenLosRecursosSuficientes.class)
	public void cuandoSeIntentaCrearUnaBarracaConMineralInsuficienteLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.crearBarraca(1, 1);
		jugador.crearBarraca(2, 2);
	}
	
	@Test(expected = NoSeTienenLosRecursosSuficientes.class)
	public void cuandoSeIntentaCrearUnaBarracaConGasVespenoInsuficienteLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
	}
	
	@Test
	public void cuandoSeCreaUnMarineAumentaEn1LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.sumarPoblacionMaxima(1);

		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		avanzarTurnos(3);
		
		assertTrue(1 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoSeCrean2MarinesAumentaEn2LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.sumarPoblacionMaxima(2);

		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		avanzarTurnos(6);
		
		assertTrue(2 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoSeCrean2MarinesAumentaEn2BarracasDistintasAumentaEn2LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarPoblacionMaxima(2);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		
		jugador.crearBarraca(1, 1);
		jugador.crearBarraca(2, 2);
		avanzarTurnos(12);

		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(2, 2, true))));
		avanzarTurnos(6);
		
		assertTrue(2 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoSeCreaUnGoliathAumentaEn1LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12);
		jugador.sumarPoblacionMaxima(2);

		jugador.crearGoliath(((Fabrica) mapa.getPosicionable(new Posicion(2, 2, true))));
		avanzarTurnos(6);
		
		assertTrue(1 == jugador.getUnidades().size());
	}

	@Test
	public void cuandoSeCrean2GoliathsAumentaEn1LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12);
		jugador.sumarPoblacionMaxima(4);

		jugador.crearGoliath(((Fabrica) mapa.getPosicionable(new Posicion(2, 2, true))));
		jugador.crearGoliath(((Fabrica) mapa.getPosicionable(new Posicion(2, 2, true))));
		avanzarTurnos(12);
		
		assertTrue(2 == jugador.getUnidades().size());
	}

	@Test
	public void cuandoSeCreaUnMarineYUnGoliathAumentaEn2LaCantidadDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarPoblacionMaxima(3);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12);
		
		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		jugador.crearGoliath(((Fabrica) mapa.getPosicionable(new Posicion(2, 2, true))));
		avanzarTurnos(6);
		
		assertTrue(2 == jugador.getUnidades().size());
	}
	
	@Test(expected = NoSePuedeConstruirLaUnidadPorSobrepoblacion.class)
	public void cuandoSeIntentaCrearUnaUnidadSinTenerLaCapacidadNecesariaLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);

		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
	}
	
	@Test(expected = NoSePuedeConstruirLaUnidadPorSobrepoblacion.class)
	public void cuandoSeIntentaCrearUnaUnidadSinTenerLaCapacidadNecesariaYTeniendoUnidadesLanzaError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.sumarPoblacionMaxima(1);
		
		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		avanzarTurnos(3);
		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
		
	}

	@Test
	public void seCreanDosBarracasYSeConstruyeUnMarineEnLaSegundaElJugadorTiene1UnidadMas() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.sumarPoblacionMaxima(3);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);

		jugador.crearBarraca(3, 3);
		avanzarTurnos(12);
		
		Barraca miBarraca = (Barraca) mapa.getPosicionable(new Posicion(1, 1, true));
		jugador.crearMarine(miBarraca);
		avanzarTurnos(4);
		
		assertTrue(1 == jugador.getUnidades().size());
	}
	
	@Test
	public void cuandoLaBaseDeUnJugadorEsDestruidaEstePierdeLaPartida() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		Base base = jugador.getBase();
		Goliath goliath = new Goliath(jugador, 2, 2);
		
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
	
	@Test
	public void cuandoAUnJugadorLeDestruyenUnEdificioEsteEsEliminadoDeSuListaDeEdificios() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		Base base2 = new Base(4, 4);
		Jugador jugador2 = new Jugador("Jugador", base2);
		base2.setJugador(jugador2);
		jugador.activar();
		jugador2.activar();
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		Goliath goliath = new Goliath(jugador2, 2, 2);
		Barraca barraca = (Barraca)Mapa.getMapa().getPosicionable(new Posicion(1, 1, true));
		int cantidadEdificiosInicial = jugador.getEdificios().size();
		
		while (!barraca.estaDestruido()) {
			goliath.atacar(barraca);
			goliath.avanzarTurno();
		}
		avanzarTurnos(1);
		int cantidadEdificiosFinal = jugador.getEdificios().size();
		
		assertTrue(cantidadEdificiosInicial == cantidadEdificiosFinal + 1);
	}
	
	@Test
	public void cuandoAUnJugadorLeDestruyenUnDepositoDeSuministrosEsteEsEliminadoDeSuListaDeCasasYSereduceSuPoblaxonMaxima() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		Base base2 = new Base(4, 4);
		Jugador jugador2 = new Jugador("Jugador", base2);
		base2.setJugador(jugador2);
		jugador.activar();
		jugador2.activar();
		jugador.crearDepositoDeSuministros(1, 1);
		avanzarTurnos(3);
		Goliath goliath = new Goliath(jugador2, 2, 2);
		DepositoDeSuministros deposito = (DepositoDeSuministros )Mapa.getMapa().getPosicionable(new Posicion(1, 1, true));
		int poblacionInicial = jugador.getPoblacionMaxima();
		int cantidadEdificiosInicial = jugador.getEdificios().size();
		
		while (!deposito.estaDestruido()) {
			goliath.atacar(deposito);
			goliath.avanzarTurno();
		}
		int poblacionFinal = jugador.getPoblacionMaxima();
		int cantidadEdificiosFinal = jugador.getEdificios().size();
		
		assertTrue(poblacionInicial == poblacionFinal + 5);
		assertTrue(cantidadEdificiosInicial == cantidadEdificiosFinal + 1);
	}
	
	@Test
	public void cuandoAUnJugadorTieneDosBarracasYSeLeDestruyenUnaEstaEsEliminadoDeSuListaDeEdificios() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		Base base2 = new Base(4, 4);
		Jugador jugador2 = new Jugador("Jugador", base2);
		base2.setJugador(jugador2);
		jugador.activar();
		jugador2.activar();
		jugador.sumarUnidadesDeMineral(1000);
		jugador.crearBarraca(1, 1);
		jugador.crearBarraca(3, 3);
		avanzarTurnos(12);
		Goliath goliath = new Goliath(jugador2, 2, 2);
		Barraca barraca = (Barraca)Mapa.getMapa().getPosicionable(new Posicion(3, 3, true));
		int cantidadEdificiosInicial = jugador.getEdificios().size();
		
		while (!barraca.estaDestruido()) {
			goliath.atacar(barraca);
			goliath.avanzarTurno();
		}
		int cantidadEdificiosFinal = jugador.getEdificios().size();
		
		assertTrue(jugador.getEdificios().get(0) == (Barraca)Mapa.getMapa().getPosicionable(new Posicion(1, 1, true)));
		assertTrue(cantidadEdificiosInicial == cantidadEdificiosFinal + 1);
	}
	
	/*@Test Test no valido, no se puede saber la posicion de la unidad de antemano
	public void cuandoAUnJugadorSeLeDestruyenUnaUnidadEstaEsEliminadaDeSuListaDeUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.activar();
		jugador.sumarUnidadesDeMineral(1000);
		jugador.sumarPoblacionMaxima(5);
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12, jugador);
		jugador.crearMarine((Barraca)Mapa.getMapa().getUnidad(new Posicion(1, 1, true)));
		Goliath goliath = new Goliath(null, 1,0);
		avanzarTurnos(6, jugador);
		int cantidadInicial = jugador.getUnidades().size();
		Marine marine = (Marine)Mapa.getMapa().getUnidad(new Posicion(1, 1, true));

		while (!marine.estaDestruido()) {
			goliath.atacar(marine);
			goliath.avanzarTurno();
		}
		jugador.actualizarEstado();
		int cantidadFinal = jugador.getUnidades().size();
		assertTrue(cantidadInicial == cantidadFinal + 1);
	}*/
	
	@Test
	public void cuandoAUnJugadorNoSeLeDestruyeNingunElementoYActualizaSuEstadoSigueTeniendoLaMismaCantidadDeEdificiosYUnidades() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.activar();
		jugador.crearDepositoDeSuministros(1, 1);
		jugador.crearBarraca(2, 2);
		avanzarTurnos(12);
		jugador.crearMarine((Barraca)Mapa.getMapa().getPosicionable(new Posicion(2, 2, true)));
		avanzarTurnos(6);
		int cantidadInicialEdificios = jugador.getEdificios().size();
		int cantidadInicialCasas = jugador.getEdificios().size();
		int cantidadInicialUnidades = jugador.getUnidades().size();

		int cantidadFinalEdificios = jugador.getEdificios().size();
		int cantidadFinalCasas = jugador.getEdificios().size();
		int cantidadFinalUnidades = jugador.getUnidades().size();
		
		assertEquals(cantidadFinalEdificios, cantidadInicialEdificios);
		assertEquals(cantidadFinalCasas, cantidadInicialCasas);
		assertEquals(cantidadFinalUnidades, cantidadInicialUnidades);
	}
	
	@Test(expected = ElJugadorNoEstaActivoException.class)
	public void siUnJugadorNoEstaActivoNoPuedeCrearUnaUnidad() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.desactivar();

		jugador.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
	}
	
	@Test(expected = ElEdificioPerteneceAOtroJugador.class)
	public void elJugadorNoPuedeCrearUnMarineEnUnaBarracaQueNoLePertenece() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador1 = new Jugador("Jugador", base);
		base.setJugador(jugador1);
		jugador1.activar();
		jugador1.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador1.desactivar();
		Base base2 = new Base(4, 4);
		Jugador jugador2 = new Jugador("Jugador", base2);
		base2.setJugador(jugador2); 
		jugador2.activar();
		jugador2.sumarPoblacionMaxima(5);

		jugador2.crearMarine(((Barraca) mapa.getPosicionable(new Posicion(1, 1, true))));
	}
	
	@Test(expected = ElEdificioPerteneceAOtroJugador.class)
	public void elJugadorNoPuedeCrearUnGoliathEnUnaFabricaQueNoLePertenece() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base1 = new Base(3, 3);
		Jugador jugador1 = new Jugador("Jugador", base1);
		base1.setJugador(jugador1);
		Base base2 = new Base(4, 4);
		Jugador jugador2 = new Jugador("Jugador", base2);
		base2.setJugador(jugador2);
		jugador1.sumarUnidadesDeMineral(1000);
		jugador1.sumarUnidadesDeGasVespeno(1000);
		jugador1.activar();
		jugador1.crearBarraca(5, 5);
		avanzarTurnos(12);
		jugador1.crearFabrica(1, 1);
		avanzarTurnos(12);
		jugador1.desactivar();
		jugador2.sumarPoblacionMaxima(5);
		jugador2.activar();

		jugador2.crearGoliath(((Fabrica) mapa.getPosicionable(new Posicion(1, 1, true))));
	}
	
	@Test
	public void siLeDestruyenUnaBarracaConUnidadesEnConstruccionPasaATenerMenosPoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.sumarPoblacionMaxima(10);
		jugador.activar();
		jugador.crearBarraca(1, 1);
		Barraca barraca = (Barraca) mapa.getPosicionable(new Posicion(1, 1, true));
		avanzarTurnos(12);
		
		for (int i = 0; i < 3; i++)
			jugador.crearMarine(barraca);
		int poblacion = jugador.getPoblacion();
		barraca.recibePuntosDeDanio(1000);
		
		assertTrue(poblacion - (Marine.getCantidadDePoblacionQueOcupa())*3 == jugador.getPoblacion());
	}
	
	@Test
	public void siLeDestruyenUnaFabricaConUnidadesEnConstruccionPasaATenerMenosPoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarPoblacionMaxima(10);
		jugador.activar();
		jugador.crearBarraca(1, 1);
		avanzarTurnos(12);
		jugador.crearFabrica(2, 2);
		avanzarTurnos(12);
		
		Fabrica fabrica = (Fabrica) mapa.getPosicionable(new Posicion(2, 2, true));
		for (int i = 0; i < 3; i++)
			jugador.crearGoliath(fabrica);
		int poblacion = jugador.getPoblacion();
		fabrica.recibePuntosDeDanio(1259);
		
		assertTrue(poblacion - (Goliath.getCantidadDePoblacionQueOcupa())*3 == jugador.getPoblacion());
	}
	
	@Test(expected = ConstruccionFueraDeRangoException.class)
	public void cuandoSeCreaUnaBarracaFueraDelRangoDeConstruccionSeLanzaUnaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(3, 3);
		Jugador jugador = new Jugador("Jugador", base);
		base.setJugador(jugador);
		jugador.activar();
		
		jugador.crearBarraca(10, 10);
	}
}
