package algoCraft.mapa;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.construcciones.Base;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class MapaTest {
	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		
		mapa.agregarUnidad(unidad);
			
		Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConXFueraDeRango() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(10000, 1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConYFueraDeRango() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 10000);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaXNegativaSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(-1, 1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaYNegativaSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, -1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}
	
	@Test
	public void agregarUnaUnidadEnUnaPosicionOcupadaNoAgregaNada() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		Posicionable unidadIntrusa = new Marine(1, 1);
		try {
			mapa.agregarUnidad(unidad);
			mapa.agregarUnidad(unidadIntrusa);
			
			fail();
		} catch (PosicionOcupadaException e) {}
		
		Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
		Assert.assertFalse(unidadIntrusa == mapa.getUnidad(unidad.getPosicion()));
	}
	
	@Test
	public void agregarUnaUnidadEnUnaPosicionOcupadaLanzaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		Posicionable unidadIntrusa = new Marine(1, 1);
		try{
			mapa.agregarUnidad(unidad);
			mapa.agregarUnidad(unidadIntrusa);
			
			fail();
		} catch (PosicionOcupadaException e) {}
	}
	
	@Test
	public void cuandoSeIntentaObtenerLaUnidadDeUnaPosicionLibreSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		try{
			mapa.getUnidad(new Posicion(1, 1, true));
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		try{
			mapa.agregarUnidad(unidad);
			mapa.removerUnidad(unidad);
			mapa.getUnidad(unidad.getPosicion());
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadParaObtenerlaHayQueBuscarlaConSuNuevaPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		try{
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidad, 2, 2);
			
			Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(2, 2, true)));
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadCorrectamenteDejaSuPosicionViejaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		try{
			mapa.agregarUnidad(unidad);
			mapa.moverUnidad(unidad, 2, 2);
			mapa.getUnidad(new Posicion(1, 1, true));
			
			fail();
		} catch (PosicionVaciaException e) {}
	}
	
	@Test
	public void moverUnaUnidadEnUnaPosicionOcupadaNoDesplazaALaUnidad() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidadConMovimiento = new Marine(1, 1);
		Posicionable unidad = new Marine(2, 2);

		mapa.agregarUnidad(unidadConMovimiento);
		mapa.agregarUnidad(unidad);
		mapa.moverUnidad(unidadConMovimiento, 2, 2);
		
		Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(2, 2, true)));
		Assert.assertEquals(unidadConMovimiento, mapa.getUnidad(new Posicion(1, 1, true)));

	}
	
	@Test
	public void seObtieneSoloUnaInstanciaDelMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaPrimario = Mapa.getMapa();
		Mapa mapaSecundario = Mapa.getMapa();
		
		Assert.assertEquals(mapaPrimario, mapaSecundario);
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon1BaseTiene2GasVespenoEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		Assert.assertTrue(2 == mapaGenerado.getCantidadGasVespeno());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon2BasesTiene4GasVespenoEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(2);
		Assert.assertTrue(4 == mapaGenerado.getCantidadGasVespeno());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon3BasesTiene6GasVespenoEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(3);
		Assert.assertTrue(6 == mapaGenerado.getCantidadGasVespeno());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon4BasesTiene8GasVespenoEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		Assert.assertTrue(8 == mapaGenerado.getCantidadGasVespeno());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon1BaseTiene8MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		
		Assert.assertTrue(8 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon2BasesTiene16MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(2);
		
		Assert.assertTrue(16 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon3BasesTiene24MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(3);
		
		Assert.assertTrue(24 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon4BasesTiene32MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		
		Assert.assertTrue(32 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void siSeCargaUnaSolaBaseEstaSeEncuentraEnEl15x15() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		Assert.assertTrue(mapa.posicionEstaOcupada(new Posicion(15, 15, true)));
	}
	
	@Test
	public void siSeCarga1BaseSeDevuelveUnaColeccionCon1Base() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Collection<Base> bases = mapa.cargarBases(1);
		
		Assert.assertTrue(1 == bases.size());
	}
	
	@Test
	public void siSeCargan2BasesSeDevuelveUnaColeccionCon2Bases() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Collection<Base> bases = mapa.cargarBases(2);
		
		Assert.assertTrue(2 == bases.size());
	}
	
	@Test
	public void siSeCargan3BasesSeDevuelveUnaColeccionCon3Bases() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Collection<Base> bases = mapa.cargarBases(3);
		
		Assert.assertTrue(3 == bases.size());
	}
	
	@Test
	public void siSeCargan4BasesSeDevuelveUnaColeccionCon4Bases() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Collection<Base> bases = mapa.cargarBases(4);
		
		Assert.assertTrue(4 == bases.size());
	}
	
	@Test
	public void siSeCargan5OMasBasesSeDevuelveUnaColeccionConSolo4Bases() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Collection<Base> bases = mapa.cargarBases(5);
		
		Assert.assertTrue(4 == bases.size());
	}
	
	@Test
	public void noDeberiaHaberGasVespenoEnEl250y250() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		
		Assert.assertFalse(mapaGenerado.hayGasVespenoEn(new Posicion(250, 250, true)));
	}
	
	@Test
	public void noDeberiaHaberMineralesEnEl250y250() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		
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
	public void laPosicionVaciaMasCercanaAl0x0EsLa0x1() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicion posicionOcupada = new Posicion(0, 0, false);
		Posicion posicionLibre = mapa.getPosicionVaciaCercana(posicionOcupada);
		
		Assert.assertEquals((new Posicion(0, 1, false)), posicionLibre);
	}
	
	@Test
	public void laPosicionVaciaMasCercanaAl0x0EsLa0x2SiTodasSusAdyacentesEstanOcupadas() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();

		mapa.agregarUnidad(new Unidad(1, null, new Posicion(0, 0, false), 1, 1));
		mapa.agregarUnidad(new Unidad(1, null, new Posicion(0, 1, false), 1, 1));
		mapa.agregarUnidad(new Unidad(1, null, new Posicion(1, 0, false), 1, 1));
		mapa.agregarUnidad(new Unidad(1, null, new Posicion(1, 1, false), 1, 1));

		Posicion posicionLibre = mapa.getPosicionVaciaCercana(new Posicion (0, 0, false));
		Assert.assertEquals((new Posicion(0, 2, false)), posicionLibre);
	}
	
}
