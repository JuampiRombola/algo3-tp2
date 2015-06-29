package algoCraft.construcciones;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.mapa.Mapa;

public class BaseTest {

	@Test
	public void cuandoSeCreaUnaBaseEstaEnTierra() {
		Base base = new Base(1, 1);
		
		Assert.assertEquals(true, base.esTerrestre());
	}
	
	@Test
	public void cuandoSeCreaUnaBaseTiene2500DeVida() {
		Base base = new Base(1, 1);
		
		Assert.assertTrue(2500 == base.getVidaActual());
	}
	
	@Test
	public void cuandoSeCreaUnaBaseTiene200Mineral() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertTrue(200 == base.getMineral());
	}
	
	@Test
	public void cuandoSeCreaUnaBaseTiene0DeGasVespeno() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertTrue(0 == base.getGasVespeno());
	}
	
	@Test
	public void cuandoSeCreaUnaBaseTiene0DePoblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertTrue(0 == base.getPoblacion());
	}
	
	@Test
	public void cuandoSeCreaUnaBaseTiene0DePoblacionMaxima() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertTrue(0 == base.getPoblacionMaxima());
	}
	
	@Test
	public void cuandoSeAgregan10MineralesLaBaseTiene210Minerales() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		base.sumarUnidadesDeMineral(10);
		
		Assert.assertTrue(210 == base.getMineral());
	}
	
	@Test
	public void cuandoSeAgregan10UnidadesDeGasVespenoLaBaseTiene10GasVespeno() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		base.sumarUnidadesDeGasVespeno(10);
		
		Assert.assertTrue(10 == base.getGasVespeno());
	}
	
	@Test
	public void cuandoSeAgrega10DePoblacionLaBaseTiene10Poblacion() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		base.sumarPoblacion(10);
		
		Assert.assertTrue(10 == base.getPoblacion());
	}
	
	@Test
	public void cuandoSeAgrega10DePoblacionLaBaseTiene10PoblacionMaxima() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		base.sumarPoblacionMaxima(10);
		
		Assert.assertTrue(10 == base.getPoblacionMaxima());
	}
	
	@Test
	public void cuandoLaPoblacionMaximaEs10LaPoblacionEs0YSeIntentaOcupar5HayCapacidadSuficiente() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		base.sumarPoblacionMaxima(10);
		
		Assert.assertTrue(base.hayCapacidadSuficiente(5));
	}
	
	@Test
	public void cuandoLaPoblacionMaximaEs10LaPoblacionEs0YSeIntentaOcupar15NoHayCapacidadSuficiente() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		base.sumarPoblacionMaxima(10);
		
		Assert.assertFalse(base.hayCapacidadSuficiente(15));
	}
	
	@Test
	public void siCreaUnaBaseYSeTrataDeGastar250MineralesNoHayRecursosSuficientes() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertFalse(base.hayRecursosSuficientes(250, 0));
	}
	
	@Test
	public void siCreaUnaBaseYSeTrataDeGastar200MineralesHayRecursosSuficientes() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertTrue(base.hayRecursosSuficientes(200, 0));
	}
	
	@Test
	public void siCreaUnaBaseYSeTrataDeGastar200MineralesY1DeGasVespenoNoHayRecursosSuficientes() {
		Mapa.reiniciarInstanciaParaTest();
		Base base = new Base(1, 1);
		
		Assert.assertFalse(base.hayRecursosSuficientes(200, 1));
	}
}
