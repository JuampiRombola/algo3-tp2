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
import algoCraft.unidades.Unidad;

public class MapaTest {
	// Usar posiciones desde el (50, 50) hasta (450, 450) terrestres para testear
	// o cualquiera de las aéreas porque en otras posiciones puede haber recursos o bases
	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		try{
			mapa.getUnidad(new Posicion(50, 50, true));
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaPrimario = Mapa.getMapa();
		Mapa mapaSecundario = Mapa.getMapa();
		
		Assert.assertEquals(mapaPrimario, mapaSecundario);
	}
	
	@Test
	public void cuandoSeInicializaElMapaTiene16GasVespenoEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		
		Assert.assertTrue(16 == mapaGenerado.getCantidadGasVespeno());
	}
	
	@Test
	public void cuandoSeInicializaElMapaTiene24MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		
		Assert.assertTrue(24 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaTiene4BasesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		
		Assert.assertTrue(4 == mapaGenerado.getBases().size());
	}
	
	@Test
	public void noDeberiaHaberGasVespenoEnEl250y250() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		
		Assert.assertFalse(mapaGenerado.hayGasVespenoEn(new Posicion(250, 250, true)));
	}
	
	@Test
	public void noDeberiaHaberMineralesEnEl250y250() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		
		Assert.assertFalse(mapaGenerado.hayMineralEn(new Posicion(250, 250, true)));
	}
	
	@Test
	public void laPosicionVaciaMasCercanaAl11x11EsLa10x10() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicion posicionOcupada = new Posicion(11, 11, false);
		Posicion posicionLibre = mapa.getPosicionVaciaCercana(posicionOcupada);
		
		Assert.assertEquals((new Posicion(10, 10, false)), posicionLibre);
	}
	
	@Test
	public void laPosicionVaciaMasCercanaAl0x0EsLa0x2SiTodasLasAdyacentesAl0x0EstanOcupadas() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		try {
			mapa.agregarUnidad(new Unidad(1, null, new Posicion(0, 0, false), 1, 1));
			mapa.agregarUnidad(new Unidad(1, null, new Posicion(0, 1, false), 1, 1));
			mapa.agregarUnidad(new Unidad(1, null, new Posicion(1, 0, false), 1, 1));
			mapa.agregarUnidad(new Unidad(1, null, new Posicion(1, 1, false), 1, 1));
		} catch (PosicionInvalidaException e) {
		} catch (PosicionOcupadaException e) {}

		Posicion posicionLibre = mapa.getPosicionVaciaCercana(new Posicion (0, 0, false));
		Assert.assertEquals((new Posicion(0, 2, false)), posicionLibre);
	}
	
}
