package algoCraft.mapa;

import java.util.ArrayList;
import java.util.Collection;

import algoCraft.mapa.Posicion;

import org.junit.Assert;
import org.junit.Test;

import algoCraft.mapa.Superficie;

public class SuperficieTest {
	
	private boolean esTerrestre = true;
	
	private Collection<Posicion> inicializarPosiciones(boolean esTerrestre) {
		Collection<Posicion> posiciones = new ArrayList<Posicion>();
		posiciones.add(new Posicion(0, 0, esTerrestre));
		posiciones.add(new Posicion(0, 1, esTerrestre));
		posiciones.add(new Posicion(1, 0, esTerrestre));
		posiciones.add(new Posicion(1, 1, esTerrestre));
		return posiciones;
	}
	
	@Test
	public void superficieTieneLasPosicionesDeBase() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		Assert.assertTrue(superficie.contienePosicion(new Posicion(0, 0, esTerrestre)));
		Assert.assertTrue(superficie.contienePosicion(new Posicion(0, 1, esTerrestre)));
		Assert.assertTrue(superficie.contienePosicion(new Posicion(1, 0, esTerrestre)));
		Assert.assertTrue(superficie.contienePosicion(new Posicion(1, 1, esTerrestre)));
	}

	@Test
	public void superficieNoTieneUnaPosicionNoAsignada() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		Assert.assertFalse(superficie.contienePosicion(new Posicion(0, 0, !esTerrestre)));
	}
	
	@Test
	public void setearLasPosicionesEn0x4TransformaLasPosicionesYLasViejasYaNoSonValidas() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		superficie.setPosicionCentral(new Posicion(0, 4, esTerrestre));
		
		Assert.assertFalse(superficie.contienePosicion(new Posicion(0, 0, esTerrestre)));
		Assert.assertFalse(superficie.contienePosicion(new Posicion(0, 1, esTerrestre)));
		Assert.assertFalse(superficie.contienePosicion(new Posicion(1, 0, esTerrestre)));
		Assert.assertFalse(superficie.contienePosicion(new Posicion(1, 1, esTerrestre)));
	}
	
	@Test
	public void setearLasPosicionesEn0x4TransformaLasPosicionesA0x4y0x5y1x4y1x5() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		superficie.setPosicionCentral(new Posicion(0, 4, esTerrestre));
		
		Assert.assertTrue(superficie.contienePosicion(new Posicion(0, 4, esTerrestre)));
		Assert.assertTrue(superficie.contienePosicion(new Posicion(0, 5, esTerrestre)));
		Assert.assertTrue(superficie.contienePosicion(new Posicion(1, 4, esTerrestre)));
		Assert.assertTrue(superficie.contienePosicion(new Posicion(1, 5, esTerrestre)));
	}
	
	@Test
	public void siDosSuperficiesTienenLasMismasPosicionesColisionan() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		Assert.assertTrue(superficie.colicionaConSuperficie(superficie));
	}
	
	@Test
	public void siDosSuperficiesTienenAlMenosUnaPosicionIgualColicionan() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		Superficie superficie2 = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		superficie2.setPosicionCentral(new Posicion(1, 1, esTerrestre));
		
		Assert.assertTrue(superficie.colicionaConSuperficie(superficie2));
	}
	
	@Test
	public void siDosSuperficiesTienenDistintasPosicionesColicionan() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		Superficie superficie2 = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		superficie2.setPosicionCentral(new Posicion(0, 4, esTerrestre));
		
		Assert.assertFalse(superficie.colicionaConSuperficie(superficie2));
	}
	
	@Test
	public void siDosSuperficiesTienenLasMismasCoordenadasPeroUnaEsTerrestreYLaOtraNoEntoncesNoColisionan() {
		Superficie superficieTerrestre = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		Superficie superficieAerea = new Superficie(inicializarPosiciones(!esTerrestre), new Posicion(0, 0, !esTerrestre));
		
		superficieAerea.setPosicionCentral(new Posicion(0, 0, !esTerrestre));
		
		Assert.assertFalse(superficieTerrestre.colicionaConSuperficie(superficieAerea));
	}
	
	@Test
	public void laDistanciaDeUnaSuperficieUnitaria0x0AOtra0x5EsDe5() {
		Collection<Posicion> posiciones0x0 = new ArrayList<Posicion>();
		posiciones0x0.add(new Posicion(0, 0, esTerrestre));
		Collection<Posicion> posiciones0x5 = new ArrayList<Posicion>();
		posiciones0x5.add(new Posicion(0, 5, esTerrestre));
		Superficie superficie0x0 = new Superficie(posiciones0x0, new Posicion(0, 0, esTerrestre));
		Superficie superficie0x5 = new Superficie(posiciones0x5, new Posicion(0, 5, esTerrestre));
		
		Assert.assertTrue(5 == superficie0x0.distanciaALaSuperficie(superficie0x5));
	}
	
	@Test
	public void laDistanciaDeDosSuperficiesCuadradasSeTomaComoLaMenorDistanciaEntreEllas() {
		Superficie superficie = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		Superficie superficie2 = new Superficie(inicializarPosiciones(esTerrestre), new Posicion(0, 0, esTerrestre));
		
		superficie.setPosicionCentral(new Posicion(0, 4, esTerrestre));
		
		Assert.assertTrue(3 == superficie.distanciaALaSuperficie(superficie2));
	}
}
