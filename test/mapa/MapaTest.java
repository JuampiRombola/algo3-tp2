package mapa;

import static org.junit.Assert.*;
import mapa.excepciones.PosicionInvalidaException;
import mapa.excepciones.PosicionVaciaException;

import org.junit.Assert;
import org.junit.Test;

import unidades.Marine;

public class MapaTest {

	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		try{
			mapa.agregarUnidad(unidad, 1, 1);
			
			Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConXFueraDeRango() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		
		try{
			mapa.agregarUnidad(unidad, 11, 1);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConYFueraDeRango() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		
		try{
			mapa.agregarUnidad(unidad, 1, 11);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaXNegativaSeLanzaUnError() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		
		try{
			mapa.agregarUnidad(unidad, -1, 1);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaYNegativaSeLanzaUnError() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		
		try{
			mapa.agregarUnidad(unidad, 1, -1);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void agregarUnaUnidadEnUnaPosicionOcupadaNoAgregaNada() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		Posicionable unidadIntrusa = new Marine(1,1);
		try{
			mapa.agregarUnidad(unidad, 1, 1);
			mapa.agregarUnidad(unidadIntrusa, 1, 1);
			
			Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
			Assert.assertFalse(unidadIntrusa == mapa.getUnidad(unidad.getPosicion()));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaObtenerLaUnidadDeUnaPosicionLibreSeLanzaUnError() {
		Mapa mapa = new Mapa(10, 10);
		try{
			mapa.getUnidad(new Posicion(1, 1, true));
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		try{
			mapa.agregarUnidad(unidad, 1, 1);
			mapa.removerUnidad(unidad);
			mapa.getUnidad(unidad.getPosicion());
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadParaObtenerlaHayQueBuscarlaConSuNuevaPosicion() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		try{
			mapa.agregarUnidad(unidad, 1, 1);
			mapa.moverUnidad(unidad, 2, 2);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(2, 2, true)));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadCorrectamenteDejaSuPosicionViejaVacia() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidad = new Marine(1,1);
		try{
			mapa.agregarUnidad(unidad, 1, 1);
			mapa.moverUnidad(unidad, 2, 2);
			mapa.getUnidad(new Posicion(1, 1, true));
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void moverUnaUnidadEnUnaPosicionOcupadaNoDesplazaALaUnidad() {
		Mapa mapa = new Mapa(10, 10);
		Posicionable unidadConMovimiento = new Marine(1,1);
		Posicionable unidad = new Marine(1,1);
		try{
			mapa.agregarUnidad(unidadConMovimiento, 1, 1);
			mapa.agregarUnidad(unidad, 1, 2);
			mapa.moverUnidad(unidadConMovimiento, 1, 2);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(1, 2, true)));
			Assert.assertEquals(unidadConMovimiento, mapa.getUnidad(new Posicion(1, 1, true)));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionVaciaException e) {}
	}
}
