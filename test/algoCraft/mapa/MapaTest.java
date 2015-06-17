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
	// Usar posiciones desde el (50, 50) hasta (450, 450) inclusive para testear
	// porque en otras posiciones puede haber recursos o bases
	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(50, 50);
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
		Posicionable unidad = new Marine(51, 51);
		Posicionable unidadIntrusa = new Marine(51, 51);
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
		Posicionable unidad = new Marine(52, 52);
		Posicionable unidadIntrusa = new Marine(52, 52);
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
			mapa.getUnidad(new Posicion(53, 53, true));
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(54, 54);
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
		Posicionable unidad = new Marine(55, 55);
		try{
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidad, 56, 56);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(56, 56, true)));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadCorrectamenteDejaSuPosicionViejaVacia() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(57, 57);
		try{
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidad, 58, 58);
			mapa.getUnidad(new Posicion(57, 57, true));
			
			fail();
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void moverUnaUnidadEnUnaPosicionOcupadaNoDesplazaALaUnidad() {
		Mapa mapa = Mapa.getMapa();
		Posicionable unidadConMovimiento = new Marine(59, 59);
		Posicionable unidad = new Marine(60, 60);
		try{
			mapa.agregarUnidad(unidadConMovimiento);
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidadConMovimiento, 60, 60);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(60, 60, true)));
			Assert.assertEquals(unidadConMovimiento, mapa.getUnidad(new Posicion(59, 59, true)));
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
