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
	
	
	@Test
	public void unArmaConDanio1DevuelveConGetDanio1(){
		int danio = 1;
		int rango = 10;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		UnidadTerran unidadTerran = new UnidadTerran(vida, arma);
		assertTrue(unidadTerran.getDanio() == 1);
	}
	
	@Test
	public void siTengoUnArmaConDanio2DevuelveConGetDanio2(){
		int danio = 2;
		int rango = 10;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		UnidadTerran unidadTerran = new UnidadTerran(vida, arma);
		assertTrue(unidadTerran.getDanio() == 2);
	}
	
	@Test
	public void  siTengoUnArmaConRango2DevuelveConGetRango2(){
		int danio = 10;
		int rango = 2;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		UnidadTerran unidadTerran = new UnidadTerran(vida, arma);
		assertTrue(unidadTerran.getRango() == 2);
	}
	
	@Test
	public void  siTengoUnArmaConRango1DevuelveConGetRango1(){
		int danio = 10;
		int rango = 1;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		UnidadTerran unidadTerran = new UnidadTerran(vida, arma);
		assertTrue(unidadTerran.getRango() == 1);
	}
	
}
