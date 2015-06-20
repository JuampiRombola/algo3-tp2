package algoCraft.integracion;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class SimuladorPartidaTest {

	@Test
	public void simuladorPartidaTest() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador1 = new Jugador("Ariel");
		Jugador jugador2 = new Jugador("Juampi");
		jugador1.sumarPoblacionMaxima(2);
		jugador2.sumarPoblacionMaxima(2);
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(2);
		/*
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
		 */
		//El jugador1 deberia poder crear una barraca
		jugador1.crearBarraca(130, 130);
		
		//El jugador2 construye su 1er barraca
		jugador2.crearBarraca(125, 125);
			
		//El jugador2 intenta crear una 2da barraca pero no puede
		try {
			jugador2.crearBarraca(126, 126);
			
			fail();
		} catch (NoSeTienenLosRecursosSuficientes e) {}
	
		//El jugador2 no deberia poder empezar la creacion de un marine
		try {
			Barraca barraca = (Barraca) mapa.getUnidad((new Posicion(130, 130, true)));
			jugador2.crearMarine(barraca);
			
			fail();
		} catch (ElEdificioEstaEnConstruccion e) {}
				
		//Pasan 12 turnos
		for (int i=0; i < 12; i++) {
			jugador1.avanzarTurno();
			jugador2.avanzarTurno();
		}
		
		//El jugador2 deberia poder empezar la creacion de un marine
		Barraca barraca = (Barraca) mapa.getUnidad((new Posicion(130, 130, true)));
		jugador2.crearMarine(barraca);
	}

}
