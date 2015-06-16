package algoCraft.mapa;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.unidades.Marine;

public class MapaTest {

	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(0, 0);
		try{
			mapa.agregarUnidad(unidad);
			
		Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConXFueraDeRango() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(10000, 1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConYFueraDeRango() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 10000);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaXNegativaSeLanzaUnError() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(-1, 1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaYNegativaSeLanzaUnError() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, -1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
	}
	
	@Test
	public void agregarUnaUnidadEnUnaPosicionOcupadaNoAgregaNada() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(2, 2);
		Posicionable unidadIntrusa = new Marine(2, 2);
		try {
			mapa.agregarUnidad(unidad);
			mapa.agregarUnidad(unidadIntrusa);
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
		
		try {
			Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
			Assert.assertFalse(unidadIntrusa == mapa.getUnidad(unidad.getPosicion()));
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void agregarUnaUnidadEnUnaPosicionOcupadaLanzaExcepcion() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		Posicionable unidadIntrusa = new Marine(1, 1);
		try{
			mapa.agregarUnidad(unidad);
			mapa.agregarUnidad(unidadIntrusa);
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaObtenerLaUnidadDeUnaPosicionLibreSeLanzaUnError() {
		Mapa mapa = Mapa.getMapa();
		try{
			mapa.getUnidad(new Posicion(500, 500, true));
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(3, 3);
		try{
			mapa.agregarUnidad(unidad);
			mapa.removerUnidad(unidad);
			mapa.getUnidad(unidad.getPosicion());
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadParaObtenerlaHayQueBuscarlaConSuNuevaPosicion() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(4, 4);
		try{
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidad, 5, 5);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(5, 5, true)));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadCorrectamenteDejaSuPosicionViejaVacia() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(6, 6);
		try{
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidad, 7, 7);
			mapa.getUnidad(new Posicion(6, 6, true));
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void moverUnaUnidadEnUnaPosicionOcupadaNoDesplazaALaUnidad() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidadConMovimiento = new Marine(8, 8);
		Posicionable unidad = new Marine(9, 9);
		try{
			mapa.agregarUnidad(unidadConMovimiento);
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidadConMovimiento, 9, 9);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(9, 9, true)));
			Assert.assertEquals(unidadConMovimiento, mapa.getUnidad(new Posicion(8, 8, true)));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void seObtieneSoloUnaInstanciaDelMapa() {
		Mapa mapaPrimario = Mapa.getMapa();
		Mapa mapaSecundario = Mapa.getMapa();
		
		Assert.assertEquals(mapaPrimario, mapaSecundario);
	}
}
