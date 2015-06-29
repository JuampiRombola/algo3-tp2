package algoCraft.integracion;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Marine;

public class CombateTest {
	@Test
	public void combateAMuerteEntreUnMarineYUnGoliath() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Jugador jugador1 = new Jugador("jugador1", new Base(3, 3));
		jugador1.activar();
		Jugador jugador2 = new Jugador("jugador2", new Base(4, 4));
		jugador2.activar();
		Marine marine = new Marine(jugador1, 1, 1);
		Goliath goliath = new Goliath(jugador2, 1, 7);
		
		//La vida del marine y del goliath estan completas
		assertTrue(marine.getVidaMaxima() == 40);
		assertTrue(goliath.getVidaMaxima() == 125);
		
		Assert.assertEquals(marine, mapa.getUnidad(new Posicion(1, 1, true)));
		Assert.assertEquals(goliath, mapa.getUnidad(new Posicion(1, 7, true)));
		
		//Los dos intentan atacarse pero como estan fuera de rango no se sacan vida
		marine.atacar(goliath);
		goliath.atacar(marine);
		assertTrue(marine.getVidaMaxima() == 40);
		assertTrue(goliath.getVidaMaxima() == 125);
		
		marine.avanzarTurno();
		goliath.avanzarTurno();
		
		//El goliath se mueve dentro del rango del marine
		goliath.moverseA(1, 5);
		
		//La posicion (1, 7) terrestre queda vacia
		try {
			mapa.getUnidad(new Posicion(1, 7, true));
			fail();
		} catch (PosicionVaciaException e) {}
		
		//Las posiciones (1, 1) y (1, 5) terrestres estan ocupadas
		Assert.assertEquals(marine, mapa.getUnidad(new Posicion(1, 1, true)));
		Assert.assertEquals(goliath, mapa.getUnidad(new Posicion(1, 5, true)));
		
		// El marine ataca al goliath
		marine.atacar(goliath);
		marine.avanzarTurno();
		
		// El goliath perdio 6 de vida y el marino esta intacto
		Assert.assertTrue(40 == marine.getVidaActual());
		Assert.assertTrue(119 == goliath.getVidaActual());
		
		// Comienza el combate a muerte entre el goliath y el marine, gana el goliath
		// a pesar de tener 6 menos de vida por el primer ataque del marine
		while (!(goliath.estaDestruido() || marine.estaDestruido()) ) {
			marine.atacar(goliath);
			goliath.atacar(marine);
			marine.avanzarTurno();
			goliath.avanzarTurno();
		}
		Assert.assertTrue(marine.estaDestruido());
		Assert.assertFalse(goliath.estaDestruido());
		
		//La posicion (1, 1) terrestre queda vacia
		try {
			mapa.getUnidad(new Posicion(1, 1, true));
			fail();
		} catch (PosicionVaciaException e) {}
				
		//La posicion (1, 5) terrestre se mantiene ocupada por el goliath
		Assert.assertEquals(goliath, mapa.getUnidad(new Posicion(1, 5, true)));
	}
}