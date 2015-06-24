package algoCraft.integracion;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.Mineral;

public class SimuladorPartidaTest {

	private void avanzarTurnos(int turnos, Jugador jugador) {
		for(int i = 0; i < turnos; i++)
			jugador.avanzarTurno();
	}
	
	@Test
	public void simuladorPartidaTest() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador1 = new Jugador("Ariel", new Base(10, 10));
		jugador1.activar();
		Jugador jugador2 = new Jugador("Juampi", new Base(1, 1));
		jugador2.activar();
		jugador1.sumarPoblacionMaxima(2);
		jugador2.sumarPoblacionMaxima(2);
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(2);
		
		//Se busca una posicion donde haya un mineral
		Posicion posicionMineral = new Posicion(0, 0, true);
		for (int i=0; i < 1000; i++) {
			for (int j=0; j < 1000; j++) {
				posicionMineral = new Posicion(i, j, true);
				if (mapa.hayMineralEn(posicionMineral))
					break;
			}
			if (mapa.hayMineralEn(posicionMineral))
				break;
		}
		
		// Se obtiene el mineral de la posicion encontrada
		// y el jugador1 le crea un centro de mineral encima
		Mineral mineral = (Mineral) mapa.getUnidad(posicionMineral);
		jugador1.crearCentroDeMineral(mineral);
		
		//El jugador1 deberia poder crear una barraca
		jugador1.crearBarraca(2, 2);
		
		//El jugador2 comienza a construir su 1er barraca
		jugador2.crearBarraca(3, 3);
			
		//El jugador2 intenta crear una 2da barraca pero no puede
		int cantidadMinerales1 = jugador2.getMineral();
		try {
			jugador2.crearBarraca(7, 7);
			fail();
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadMinerales2 = jugador2.getMineral();
		assertEquals(cantidadMinerales1, cantidadMinerales2);

		//El jugador2 no deberia poder empezar la creacion de un marine
		Barraca barraca = (Barraca) mapa.getUnidad((new Posicion(3, 3, true)));
		int cantidadMineralesAntesDeMarine = jugador2.getMineral();
		try {
			jugador2.crearMarine(barraca);
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
		int cantidadMineralesDespuesDeMarine = jugador2.getMineral();
		assertEquals(cantidadMineralesAntesDeMarine, cantidadMineralesDespuesDeMarine);

		int cantidadMineralesJugador1 = jugador1.getMineral();
		//Pasan 12 turnos
		avanzarTurnos(12, jugador1);
		avanzarTurnos(12, jugador2);
		
		//El jugador1 tendria que haber recogido minerales
		int cantidadMineralesJugador1DespuesDe12Turnos = jugador1.getMineral();
		assertTrue(cantidadMineralesJugador1DespuesDe12Turnos > cantidadMineralesJugador1);
		
		//El jugador1 deberia tener 2 edificios y el jugador 2 uno solo
		assertEquals(2, jugador1.getEdificios().size());
		assertEquals(1, jugador2.getEdificios().size());
		
		//El jugador2 deberia poder empezar la creacion de un marine
		int cantidadMinerales3 = jugador2.getMineral();
		jugador2.crearMarine(barraca);
		int cantidadMinerales4 = jugador2.getMineral();
		assertTrue(cantidadMinerales3 == cantidadMinerales4 + 50);
	}

}
