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
		equipo3.crearBarraca(10, 10);
		
		juego.siguienteJugador();
		
		//Ahora el equipo3 no deberia poder hacer nada
		try{
			equipo3.crearBarraca(10, 10);
			
			fail();
		} catch (ElJugadorNoEstaActivoException e){}
		
		//Nico deberia poder crear una barraca
		nico.crearBarraca(12, 12);
		
		//Se avanzan 23 turnos hasta terminar de construir la barraca del equipo3
		avanzarTurnos(juego, 23);
		
		//equipo3 esta activo, crea un marine
		equipo3.crearMarine((Barraca) Mapa.getMapa().getUnidad(new Posicion(10, 10, true)));
		
		juego.siguienteJugador();
		
		//nico esta activo, crea un marine
		nico.crearMarine((Barraca) Mapa.getMapa().getUnidad(new Posicion(12, 12, true)));
		
		//Se avanzan 6 turnos hasta terminar de construir el marino de nico
		avanzarTurnos(juego, 6);
		
		//Como es el turno de nico ahora su marine se puede mover
		Marine marineNico = (Marine) Mapa.getMapa().getUnidad(new Posicion(11, 11, true));
		Base baseEquipo3 = equipo3.getBase();
		marineNico.moverseA(11, 9);
		avanzarTurnos(juego, 2);
		marineNico.moverseA(11, 8);
		avanzarTurnos(juego, 2);
		marineNico.moverseA(9, 8);
		avanzarTurnos(juego, 2);
		marineNico.moverseA(9, 7);
		avanzarTurnos(juego, 2);
		marineNico.moverseA(7, 7);
		avanzarTurnos(juego, 2);
		marineNico.moverseA(7, 5);
		avanzarTurnos(juego, 2);
		marineNico.moverseA(5, 5);
		
		marineNico.atacar(baseEquipo3);
		
		while (!baseEquipo3.estaDestruido()) {
			avanzarTurnos(juego, 2);
			marineNico.atacar(baseEquipo3);
		}
		juego.siguienteJugador();
		
		assertTrue(juego.hayGanador());
		assertTrue(equipo3.perdioLaPartida());
	}

}
