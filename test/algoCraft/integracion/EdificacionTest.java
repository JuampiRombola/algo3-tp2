package algoCraft.integracion;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.construcciones.Fabrica;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;

public class EdificacionTest {
	/* Mapa muy grande
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
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 30; j++){
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
		jugador.sumarPoblacionMaxima(50);
		
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
		jugador.crearBarraca(50, 50);
		
		assertTrue(0 == jugador.getMineral());
		assertTrue(80 == jugador.getGasVespeno());
		
		//Pasan 12 turnos
		for (int i=0; i < 12; i++)
			jugador.avanzarTurno();
		
		assertTrue(120 == jugador.getMineral());
		assertTrue(200 == jugador.getGasVespeno());
		
		//El jugador deberia poder crear un Marine
		jugador.crearMarine((Barraca) mapa.getUnidad(new Posicion(50, 50, true)));
		
		assertTrue(70 == jugador.getMineral());
		assertTrue(200 == jugador.getGasVespeno());
		
		//Pasan 13 turnos
		for (int i=0; i < 13; i++)
			jugador.avanzarTurno();
		
		assertTrue(200 == jugador.getMineral());
		assertTrue(330 == jugador.getGasVespeno());
		
		//El jugador deberia poder crear una fabrica
		jugador.crearFabrica(60, 60);
		
		assertTrue(0 == jugador.getMineral());
		assertTrue(230 == jugador.getGasVespeno());
		
		//Pasan 12 turnos
		for (int i=0; i < 12; i++)
			jugador.avanzarTurno();
		
		assertTrue(120 == jugador.getMineral());
		assertTrue(350 == jugador.getGasVespeno());
		
		//El jugador deberia poder crear un Goliath
		jugador.crearGoliath((Fabrica) mapa.getUnidad(new Posicion(60, 60, true)));
		
		assertTrue(20 == jugador.getMineral());
		assertTrue(300 == jugador.getGasVespeno());
	}*/

}