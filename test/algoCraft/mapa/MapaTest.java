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
import algoCraft.recursos.GasVespeno;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class MapaTest {
	
	@Test
	public void cuandoSeOcupaUnaPosicionSeObtieneLoQueSeLeIntrodujo() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(null, 1, 1);			
		Assert.assertEquals(unidad, mapa.getUnidad(unidad.getPosicion()));
	}
	
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConXFueraDeRango() {
		Mapa.reiniciarInstanciaParaTest();
		
		new Marine(null, 10000, 1);
	}

	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConYFueraDeRango() {
		Mapa.reiniciarInstanciaParaTest();
		new Marine(null, 1, 10000);
	}
	
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaXNegativaSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		new Marine(null, -1, 1);
	}
	
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaOcuparUnaPosicionConCoordenadaYNegativaSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		new Marine(null, 1, -1);
	}
	
	@Test(expected = PosicionOcupadaException.class)
	public void agregarUnaUnidadEnUnaPosicionOcupadaNoAgregaNada() {
		Mapa.reiniciarInstanciaParaTest();
		new Marine(null, 1, 1);
		new Marine(null, 1, 1);	
	}
	
	@Test(expected = PosicionVaciaException.class)
	public void cuandoSeIntentaObtenerLaUnidadDeUnaPosicionLibreSeLanzaUnError() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		
		mapa.getUnidad(new Posicion(1, 1, true));
	}
	
	@Test(expected = PosicionVaciaException.class)
	public void cuandoSeRemueveUnaUnidadSuPosicionQuedaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(null, 1, 1);
		mapa.removerUnidad(unidad);
		mapa.getUnidad(unidad.getPosicion());
	}
	
	@Test
	public void cuandoSeMueveUnaUnidadParaObtenerlaHayQueBuscarlaConSuNuevaPosicion() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(null, 1, 1);
		
		mapa.moverUnidad(unidad, 2, 2);
		
		Assert.assertEquals(unidad, mapa.getUnidad(new Posicion(2, 2, true)));
	}
	
	@Test(expected = PosicionVaciaException.class)
	public void cuandoSeMueveUnaUnidadCorrectamenteDejaSuPosicionViejaVacia() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidad = new Marine(null, 1, 1);
		
		mapa.moverUnidad(unidad, 2, 2);
		mapa.getUnidad(new Posicion(1, 1, true));
	}
	
	@Test
	public void moverUnaUnidadEnUnaPosicionOcupadaNoDesplazaALaUnidad() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicionable unidadConMovimiento = new Marine(null, 1, 1);
		Posicionable unidad = new Marine(null, 2, 2);

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
	public void laPosicionVaciaMasCercanaAl11x11EsLa10x10() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicion posicionOcupada = new Posicion(11, 11, false);
		Posicion posicionLibre = mapa.getPosicionVaciaCercana(posicionOcupada);
		
		Assert.assertEquals((new Posicion(10, 10, false)), posicionLibre);
	}
	
	@Test
	public void laPosicionVaciaMasCercanaAl1x1EsLa1x2() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		Posicion posicionOcupada = new Posicion(1, 1, false);
		Posicion posicionLibre = mapa.getPosicionVaciaCercana(posicionOcupada);
		Assert.assertEquals((new Posicion(1, 2, false)), posicionLibre);
	}
	
	@Test
	public void laPosicionVaciaMasCercanaAl1x1EsLa1x3SiTodasSusAdyacentesEstanOcupadas() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();	
		new Unidad(null, 1, null, new Posicion(1, 2, true), 1);
		new Unidad(null, 1, null, new Posicion(2, 1, true), 1);
		new Unidad(null, 1, null, new Posicion(2, 2, true), 1);
		new Unidad(null, 1, null, new Posicion(1, 1, true), 1);
		Posicion posicionLibre = mapa.getPosicionVaciaCercana(new Posicion(1, 1, true));
		Assert.assertEquals((new Posicion(1, 3, true)), posicionLibre);
	}
	
	@Test
	public void siSeLimpiaElMapaEsteQuedaVacio() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		mapa.cargarBases(2);
		
		mapa.limpiarMapa();
		for (int i=0; i <= mapa.getAlto(); i++) {
			for (int j=0; j <= mapa.getAncho(); j++){
				assertFalse(mapa.posicionEstaOcupada(new Posicion (i, j, true)));
				assertFalse(mapa.posicionEstaOcupada(new Posicion (i, j, false)));
			}
		}
	}
	
	@Test
	public void usandoElMetodoReemplazarUnidadSeReemplazaCorrectamenteUnaUnidadPorOtra() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		
		Unidad unaUnidad = (new Unidad(null, 1, null, new Posicion(2, 2, true), 1));
		GasVespeno otraUnidad = new GasVespeno(2, 2);
		mapa.reemplazarUnidad(otraUnidad);
		
		assertTrue(otraUnidad == mapa.getUnidad(unaUnidad.getPosicion()));
	}
	
	@Test
	public void siSeUsaElMetodoReemplazarUnidadEnYLaPosicionEstabaVaciaSeOcupaSinNingunProblema() {
		Mapa.reiniciarInstanciaParaTest();
		Mapa mapa = Mapa.getMapa();
		
		GasVespeno unicaUnidad = new GasVespeno(1, 1);
		mapa.reemplazarUnidad(unicaUnidad);
		
		assertTrue(unicaUnidad == mapa.getUnidad(unicaUnidad.getPosicion()));
	}
	
	@Test(expected = PosicionInvalidaException.class)
	public void cuandoSeIntentaMoverUnaUnidadAUnaPosicionInvalidaLanzaExcepcion() {
		Mapa.reiniciarInstanciaParaTest();
		Marine marine = new Marine(null, 1, 1);
		
		marine.moverseA(-1, 0);
	}
}
