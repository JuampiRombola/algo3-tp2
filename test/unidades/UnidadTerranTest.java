package unidades;

import static org.junit.Assert.*;
import interfaces.unidadesYEstructuras.Atacable;

import org.junit.Test;

public class UnidadTerranTest{
	private int rangoArmaDePrueba = 10;
	private int danioArmaDePrueba = 100;
	private Arma armaDePrueba = new Arma(danioArmaDePrueba, rangoArmaDePrueba);
	private int vidaMaxima = 10;
	private UnidadTerran unidadAtacante = new UnidadTerran(vidaMaxima,
												     		armaDePrueba);
	@Test
	public void alCrearseLaUnidadNoEstaDestruida(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		assertTrue(!unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaEstaDestruido(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaEstaDestruido(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima + 1);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirEnDosAtaquesUnDanioIgualASuVidaMAximaEstaDestruido(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaLaVidaActualEs0(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaLaVidaActualEs0(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima + 20);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructor(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructorLuegoDeRecibirDanio(){
		Unidad unidad = new UnidadTerran(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(100);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDelAtacableEsteEsDestruido(){
		int vidaAtacable = danioArmaDePrueba;
		Atacable atacable = new UnidadTerran(vidaAtacable, armaDePrueba);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaEnRangoConMenorDanioQueLaVidaDelAtacableEsteNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba + 1;
		Atacable atacable = new UnidadTerran(vidaAtacable, armaDePrueba);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaConMayorDanioQueLaVidaDelAtacablePeroFueraDeRangoElAtacableNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba - 1;
		Atacable atacable = new UnidadTerran(vidaAtacable, armaDePrueba);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba + 1);
		assertTrue(!atacable.estaDestruido());
	}
	
}