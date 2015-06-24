package algoCraft.mapa;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.construcciones.Base;
import algoCraft.construcciones.CentroDeMineral;
import algoCraft.construcciones.EdificioRecolector;
import algoCraft.construcciones.Refineria;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class MapaTest {
	
	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);			
		Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
	}
	
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConXFueraDeRango() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(10000, 1);
		
		try{
			mapa.agregarUnidad(unidad);
			
			fail();
		} catch (PosicionInvalidaException e) {}
	}

	@SuppressWarnings("unused")
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConYFueraDeRango() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 10000);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaXNegativaSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(-1, 1);
	}
	
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaYNegativaSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, -1);
		mapa.agregarUnidad(unidad);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = PosicionOcupadaException.class)
	public void agregarUnaUnidadEnUnaPosicionOcupadaNoAgregaNada() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		Posicionable unidadIntrusa = new Marine(1, 1);	
	}
	
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
	
	@Test(expected = PosicionVaciaException.class)
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		mapa.removerUnidad(unidad);
		mapa.getUnidad(unidad.getPosicion());
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadParaObtenerlaHayQueBuscarlaConSuNuevaPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(1, 1);
		try{
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
		
		Assert.assertTrue(6 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon2BasesTiene16MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(2);
		
		Assert.assertTrue(12 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon3BasesTiene24MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(3);
		
		Assert.assertTrue(18 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void cuandoSeInicializaElMapaCon4BasesTiene32MineralesEnTodoElMapa() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		
		Assert.assertTrue(24 == mapaGenerado.getCantidadMinerales());
	}
	
	@Test
	public void siSeCargaUnaSolaBaseEstaSeEncuentraEnEl3x3() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(1);
		
		Assert.assertTrue(mapa.posicionEstaOcupada(new Posicion(3, 3, true)));
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
	public void noDeberiaHaberGasVespenoEnEl10y10() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		
		Assert.assertFalse(mapaGenerado.hayGasVespenoEn(new Posicion(10, 10, true)));
	}
	
	@Test
	public void noDeberiaHaberMineralesEnEl10y10() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(4);
		
		Assert.assertFalse(mapaGenerado.hayMineralEn(new Posicion(10, 10, true)));
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
	
	@SuppressWarnings("unused")
	@Test
	public void laPosicionVaciaMasCercanaAl0x0EsLa0x2SiTodasSusAdyacentesEstanOcupadas() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();	
		Posicion posicionOcupada = new Posicion(0, 0, false);
		Unidad unidad1 = new Unidad(1, null, new Posicion(0, 0, false), 1);
		Unidad unidad2 = (new Unidad(1, null, new Posicion(0, 1, false), 1));
		Unidad unidad3 = (new Unidad(1, null, new Posicion(1, 0, false), 1));
		Unidad unidad4 = (new Unidad(1, null, new Posicion(1, 1, false), 1));
		Posicion posicionLibre = mapa.getPosicionVaciaCercana(posicionOcupada);
		Assert.assertEquals((new Posicion(0, 2, false)), posicionLibre);
	}
	
	@Test
	public void alOcuparUnMineralEnEsaPosicionDelMapaQuedaUnCentroDeMineral() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		Mineral mineral = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapaGenerado.hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) mapaGenerado.getUnidad(new Posicion(i, j, true));
			}
		}
		
		EdificioRecolector centro = new CentroDeMineral(mineral);
		mapaGenerado.ocuparRecurso(centro);
		
		Assert.assertTrue(centro == mapaGenerado.getUnidad(mineral.getPosicion()));
	}
	
	@Test
	public void alOcuparUnMineralCuandoSeLePreguntaAlMapaPorEsaPosicionYaNoTieneMineralAhi() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		Mineral mineral = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapaGenerado.hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) mapaGenerado.getUnidad(new Posicion(i, j, true));
			}
		}
		
		EdificioRecolector centro = new CentroDeMineral(mineral);
		mapaGenerado.ocuparRecurso(centro);
		
		Assert.assertFalse(mapaGenerado.hayMineralEn(mineral.getPosicion()));
	}
	
	@Test
	public void alOcuparUnGasVespenoEnEsaPosicionDelMapaQuedaUnaRefineria() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		GasVespeno gasVespeno = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapaGenerado.hayGasVespenoEn(new Posicion(i, j, true))) continue;
				gasVespeno = (GasVespeno) mapaGenerado.getUnidad(new Posicion(i, j, true));
			}
		}
		
		EdificioRecolector refineria = new Refineria(gasVespeno);
		mapaGenerado.ocuparRecurso(refineria);
		
		Assert.assertTrue(refineria == mapaGenerado.getUnidad(gasVespeno.getPosicion()));
	}
	
	@Test
	public void alOcuparUnGasVespenoCuandoSeLePreguntaAlMapaPorEsaPosicionYaNoTieneGasVespenoAhi() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		GasVespeno gasVespeno = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapaGenerado.hayGasVespenoEn(new Posicion(i, j, true))) continue;
				gasVespeno = (GasVespeno) mapaGenerado.getUnidad(new Posicion(i, j, true));
			}
		}
		
		EdificioRecolector refineria = new Refineria(gasVespeno);
		mapaGenerado.ocuparRecurso(refineria);
		
		Assert.assertFalse(mapaGenerado.hayGasVespenoEn(gasVespeno.getPosicion()));
	}
	
	@Test
	public void siSeRemueveUnMineralYaNoHayMineralEnEsaPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		Mineral mineral = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapaGenerado.hayMineralEn(new Posicion(i, j, true))) continue;
				mineral = (Mineral) mapaGenerado.getUnidad(new Posicion(i, j, true));
			}
		}
		mapaGenerado.removerRecurso(mineral);
		
		assertFalse(mapaGenerado.hayMineralEn(mineral.getPosicion()));
	}
	
	@Test
	public void siSeRemueveUnGasVespenoYaNoHayGasVespenoEnEsaPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapaGenerado = Mapa.getMapa();
		mapaGenerado.cargarBases(1);
		GasVespeno gasVespeno = null;
		
		for (int i=0; i < 25; i++) {
			for (int j=0; j < 25; j++){
				if (!mapaGenerado.hayGasVespenoEn(new Posicion(i, j, true))) continue;
				gasVespeno = (GasVespeno) mapaGenerado.getUnidad(new Posicion(i, j, true));
			}
		}
		mapaGenerado.removerRecurso(gasVespeno);
		
		assertFalse(mapaGenerado.hayGasVespenoEn(gasVespeno.getPosicion()));
	}
}
