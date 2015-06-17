package algoCraft.integracion;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.construcciones.EdificioConstructor;
import algoCraft.construcciones.ElEdificioEstaEnConstruccion;
import algoCraft.construcciones.ElEdificioNoPuedeCrearLaUnidad;
import algoCraft.juego.Jugador;
import algoCraft.juego.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.juego.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.recursos.Mineral;

public class simuladorPartidaTest {

	@Test
	public void simuladorPartidaTest() {
		Jugador jugador1 = new Jugador("Ariel");
		Jugador jugador2 = new Jugador("Juampi");
		Mapa mapa = Mapa.getMapa();
		
		//Se busca una posicion donde haya un mineral
		Posicion posicionMineral = new Posicion(0, 0, true);
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 30; j++) {
				posicionMineral = new Posicion(i, j, true);
				if (mapa.hayMineralEn(posicionMineral)) break;
			}
		}
		
		// Se obtiene el mineral de la posicion encontrada
		// y el jugador1 le crea un centro de mineral encima
		try {
			Mineral mineral = (Mineral) mapa.getUnidad(posicionMineral);
			jugador1.crearCentroDeMineral(mineral);
		} catch (PosicionVaciaException e) {
		} catch (NoSeTienenLosRecursosSuficientes e) {
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}

		//El jugador1 deberia poder crear una barraca
		try {
			jugador1.crearBarraca(130, 130);
		} catch (NoSeTienenLosRecursosSuficientes | PosicionInvalidaException | PosicionOcupadaException e) {}
		
		//El jugador2 construye su 1er barraca
		try {
			jugador2.crearBarraca(125, 125);
		} catch (NoSeTienenLosRecursosSuficientes | PosicionInvalidaException | PosicionOcupadaException e) {}
			
		//El jugador2 intenta crear una 2da barraca pero no puede
		try {
			jugador2.crearBarraca(126, 126);
			fail();
		} catch (NoSeTienenLosRecursosSuficientes | PosicionInvalidaException | PosicionOcupadaException e) {}
	
		//El jugador2 no deberia poder empezar la creacion de un marine
		try {
			EdificioConstructor barraca = (EdificioConstructor) mapa.getUnidad((new Posicion(130, 130, true)));
			jugador2.crearMarine(barraca);
			fail();
		} catch (NoSeTienenLosRecursosSuficientes | PosicionVaciaException | NoSePuedeConstruirLaUnidadPorSobrepoblacion | ElEdificioEstaEnConstruccion | ElEdificioNoPuedeCrearLaUnidad e) {}
				
		//Pasan 12 turnos
		for (int i=0; i < 12; i++) {
			jugador1.avanzarTurno();
			jugador2.avanzarTurno();
		}
		
		//El jugador2 deberia poder empezar la creacion de un marine
		try {
			EdificioConstructor barraca = (EdificioConstructor) mapa.getUnidad((new Posicion(130, 130, true)));
			jugador2.crearMarine(barraca);
		} catch (NoSeTienenLosRecursosSuficientes | PosicionVaciaException | NoSePuedeConstruirLaUnidadPorSobrepoblacion | ElEdificioEstaEnConstruccion | ElEdificioNoPuedeCrearLaUnidad e) {}
		
	}

}
