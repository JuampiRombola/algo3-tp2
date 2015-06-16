package algoCraft.integracion;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Marine;

public class combateTest {

	@Test
	public void combateAMuerteEntreUnMarineYUnGoliath() {
		Mapa mapa = new Mapa(100, 100);
		Marine marine = new Marine(1, 1);
		Goliath goliath = new Goliath(22, 22);
		
		//La vida del marine y del goliath estan completas
		assertTrue(marine.getVidaMaxima() == 40);
		assertTrue(goliath.getVidaMaxima() == 125);
		
		try {
			mapa.agregarUnidad(marine);
			mapa.agregarUnidad(goliath);
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
		
		try {
			Assert.assertEquals(marine, mapa.getUnidad(new Posicion(1, 1, true)));
			Assert.assertEquals(goliath, mapa.getUnidad(new Posicion(22, 22, true)));
		} catch (PosicionVaciaException e) {}
		
		//Los dos intentan atacarse pero como estan fuera de rango no se sacan vida
		marine.atacar(goliath);
		goliath.atacar(marine);
		assertTrue(marine.getVidaMaxima() == 40);
		assertTrue(goliath.getVidaMaxima() == 125);
		
		marine.avanzarTurno();
		goliath.avanzarTurno();
		
		//Se mueve al goliath dentro del rango del marine
		try {
			mapa.moverUnidad(goliath, 3, 3);
		} catch (PosicionInvalidaException e) {}
		
		//La posicion (22, 22) terrestre queda vacia
		try {
			mapa.getUnidad(new Posicion(22, 22, true));
			fail();
		} catch (PosicionVaciaException e) {}
		
		//Las posiciones (1, 1) y (3, 3) terrestres estan ocupadas
		try {
			Assert.assertEquals(marine, mapa.getUnidad(new Posicion(1, 1, true)));
			Assert.assertEquals(goliath, mapa.getUnidad(new Posicion(3, 3, true)));
		} catch (PosicionVaciaException e) {}
		
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
		
		// Se remueve al marine del mapa
		mapa.removerUnidad(marine);
		
		//La posicion (1, 1) terrestre queda vacia
		try {
			mapa.getUnidad(new Posicion(1, 1, true));
			fail();
		} catch (PosicionVaciaException e) {}
				
		//La posicion (3, 3) terrestre se mantiene ocupada por el goliath
		try {
			Assert.assertEquals(goliath, mapa.getUnidad(new Posicion(3, 3, true)));
		} catch (PosicionVaciaException e) {}
	}
}
