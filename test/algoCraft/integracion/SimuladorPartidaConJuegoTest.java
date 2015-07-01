package algoCraft.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.ElJugadorNoEstaActivoException;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;

public class SimuladorPartidaConJuegoTest {

	private void avanzarTurnos(Juego juego, int turnos) {
		for (int i=0; i < turnos; i++)
			juego.siguienteJugador();
	}
	
	@Test
	public void entreDosJugadoresSiUnoLeDestruyeLaBaseAlOtroEntoncesGana() {
		Mapa.reiniciarInstanciaParaTest();
		Juego juego = new Juego();
		
		juego.agregarJugadorLlamado("Equipo3");
		juego.agregarJugadorLlamado("Nico");
		
		juego.iniciarPartida();
		
		ArrayList<Jugador> jugadores = juego.getJugadores();
		Jugador equipo3 = jugadores.get(0);
		Jugador nico = jugadores.get(1);
		
		equipo3.sumarPoblacionMaxima(1);
		nico.sumarPoblacionMaxima(1);
		
		//Empieza a jugar el Equipo3, por lo tanto Nico no deberia poder hacer nada
		try{
			nico.crearBarraca(10, 10);
			
			fail();
		} catch (ElJugadorNoEstaActivoException e){}
				
		//El equipo3 deberia poder crear una barraca
		equipo3.crearBarraca(5, 5);
		
		juego.siguienteJugador();
		
		//Ahora el equipo3 no deberia poder hacer nada
		try{
			equipo3.crearBarraca(10, 10);
			
			fail();
		} catch (ElJugadorNoEstaActivoException e){}
		
		//Nico deberia poder crear una barraca
		nico.crearBarraca(34, 20);
		
		//Se avanzan 23 turnos hasta terminar de construir la barraca del equipo3
		avanzarTurnos(juego, 23);
		
		//equipo3 esta activo, crea un marine
		equipo3.crearMarine((Barraca) Mapa.getMapa().getPosicionable(new Posicion(5, 5, true)));
		
		juego.siguienteJugador();
		
		//nico esta activo, crea un marine
		nico.crearMarine((Barraca) Mapa.getMapa().getPosicionable(new Posicion(34, 20, true)));
		
		//Se avanzan 6 turnos hasta terminar de construir el marino de nico
		avanzarTurnos(juego, 6);
		
		//Como es el turno de nico ahora su marine se puede mover
		Marine marineNico = (Marine) Mapa.getMapa().getPosicionable(new Posicion(33, 19, true));
		Base baseEquipo3 = equipo3.getBase();
		moverMarineHastaPosicion(marineNico, juego, 5, 5);
		
		marineNico.atacar(baseEquipo3);
		
		while (!baseEquipo3.estaDestruido()) {
			avanzarTurnos(juego, 2);
			marineNico.atacar(baseEquipo3);
		}
		juego.siguienteJugador();
		
		assertTrue(juego.hayGanador());
		assertTrue(equipo3.perdioLaPartida());
	}

	private void moverMarineHastaPosicion(Marine marineNico, Juego juego, int x, int y) {
		while (marineNico.getPosicion().getY() > 5) {
			Posicion posicion = marineNico.getPosicion();
			marineNico.moverseA(posicion.getX() - 4, posicion.getY());
			avanzarTurnos(juego, 2);
			posicion = marineNico.getPosicion();
			marineNico.moverseA(posicion.getX(), posicion.getY() - 2);
			avanzarTurnos(juego, 2);
		}
		
	}

}
