package algoCraft.integracion;

import static org.junit.Assert.*;

import java.util.Iterator;

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
		Mapa mapa = Mapa.getMapa();
		Iterator<Base> it = (mapa.cargarBases(2)).iterator();
		Base base1 = it.next();
		Base base2 = it.next();
		Jugador jugador1 = new Jugador("Ariel", base1);
		jugador1.activar();
		Jugador jugador2 = new Jugador("Juampi", base2);
		jugador2.activar();
		jugador1.sumarPoblacionMaxima(2);
		jugador2.sumarPoblacionMaxima(2);
		
		//Se busca un mineral del mapa y se le crea un centro encima
		Mineral mineral = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapa.hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) mapa.getUnidad(new Posicion(i, j, true));
			}
		}
		jugador1.crearCentroDeMineral(mineral);
		
		//El jugador1 deberia poder crear una barraca
		jugador1.crearBarraca(10, 10);
		
		//El jugador2 comienza a construir su 1er barraca
		jugador2.crearBarraca(12, 12);
			
		//El jugador2 intenta crear una 2da barraca pero no puede
		int cantidadMinerales1 = jugador2.getMineral();
		try {
			jugador2.crearBarraca(13, 13);
			fail();
		} catch (NoSeTienenLosRecursosSuficientes e) {}
		int cantidadMinerales2 = jugador2.getMineral();
		assertEquals(cantidadMinerales1, cantidadMinerales2);

		//El jugador2 no deberia poder empezar la creacion de un marine
		Barraca barraca = (Barraca) mapa.getUnidad((new Posicion(12, 12, true)));
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
