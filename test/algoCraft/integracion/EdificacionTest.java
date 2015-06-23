package algoCraft.integracion;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;

public class EdificacionTest {

	private Mineral obtenerUnMineralDelMapa() {
		Mineral mineral = null;
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 30; j++){
				if (!Mapa.getMapa().hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) Mapa.getMapa().getUnidad(new Posicion(i, j, true));
			}
		}
		return mineral;
	}
	
	private GasVespeno obtenerUnGasVespenoDelMapa() {
		GasVespeno gasVespeno = null;
		for (int i=0; i < Mapa.getMapa().getAlto(); i++) {
			for (int j=0; j < Mapa.getMapa().getAncho(); j++){
				if (!Mapa.getMapa().hayGasVespenoEn(new Posicion(i, j, true))) continue;
				gasVespeno = (GasVespeno) Mapa.getMapa().getUnidad(new Posicion(i, j, true));
			}
		}
		return gasVespeno;
	}
	
	@Test
	public void construccionDeEdificiosYUnidadesDeUnMismoJugadorRespetandoDependencias() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		Jugador jugador = new Jugador("JugadorDePrueba", new Base(3, 3));
		
		// Se busca un mineral y se le construye un centro encima
		Mineral mineral = this.obtenerUnMineralDelMapa();
		jugador.crearCentroDeMineral(mineral);
		assertTrue(150 == jugador.getMineral());
		
		// Se busca un mineral y se le construye un centro encima
		GasVespeno gasVespeno = this.obtenerUnGasVespenoDelMapa();
		jugador.crearRefineria(gasVespeno);
		assertTrue(50 == jugador.getMineral());
			
		//Pasan 13 turnos
		for (int i=0; i < 13; i++)
			jugador.avanzarTurno();
		
		//El jugador deberia tener 150 de mineral y 80 de gasVespeno
		assertTrue(150 == jugador.getMineral());
		assertTrue(80 == jugador.getGasVespeno());
		
		//El jugador deberia poder crear una barraca
		jugador.crearBarraca(5, 5);
		
		assertTrue(0 == jugador.getMineral());
		assertTrue(80 == jugador.getGasVespeno());
		
		//Pasan 12 turnos
		for (int i=0; i < 15; i++)
			jugador.avanzarTurno();
		
		assertTrue(150 == jugador.getMineral());
		assertTrue(230 == jugador.getGasVespeno());
		
		//El jugador no deberia poder crear un Marine por sobrepoblacion
		try {
			jugador.crearMarine((Barraca) mapa.getUnidad(new Posicion(5, 5, true)));
			
			fail();
		} catch (NoSePuedeConstruirLaUnidadPorSobrepoblacion e) {}
		
		jugador.crearDepositoDeSuministros(6, 6);
		
		//Pasan 6 turnos
		for (int i=0; i < 6; i++)
			jugador.avanzarTurno();
		
		//Se aumenta la poblacion a 5
		assertTrue(5 == jugador.getPoblacionMaxima());
		
		assertTrue(110 == jugador.getMineral());
		assertTrue(290 == jugador.getGasVespeno());
		
		//El jugador debería poder crear un Marine
		jugador.crearMarine((Barraca) mapa.getUnidad(new Posicion(5, 5, true)));
		
		assertTrue(60 == jugador.getMineral());
		assertTrue(290 == jugador.getGasVespeno());
		
		//Pasan 14 turnos
		for (int i=0; i < 14; i++)
			jugador.avanzarTurno();
		
		assertTrue(200 == jugador.getMineral());
		assertTrue(430 == jugador.getGasVespeno());
		
		//El jugador deberia poder crear una fabrica
		jugador.crearFabrica(7, 7);
		
		assertTrue(0 == jugador.getMineral());
		assertTrue(330 == jugador.getGasVespeno());
		
		//Pasan 12 turnos
		for (int i=0; i < 12; i++)
			jugador.avanzarTurno();
		
		assertTrue(120 == jugador.getMineral());
		assertTrue(450 == jugador.getGasVespeno());
		
		//El jugador deberia poder crear un Goliath
		jugador.crearGoliath((Fabrica) mapa.getUnidad(new Posicion(7, 7, true)));
		
		assertTrue(20 == jugador.getMineral());
		assertTrue(400 == jugador.getGasVespeno());
	}

}