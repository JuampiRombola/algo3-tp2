package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnidadTest{
	
	@Test
	public void alCrearseLaUnidadNoEstaDestruida(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		assertTrue(!unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaEstaDestruido(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaEstaDestruido(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		unidad.recibePuntosDeDanio(vidaMaxima + 1);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirEnDosAtaquesUnDanioIgualASuVidaMAximaEstaDestruido(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaLaVidaActualEs0(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaLaVidaActualEs0(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		unidad.recibePuntosDeDanio(vidaMaxima + 20);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructor(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructorLuegoDeRecibirDanio(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		unidad.recibePuntosDeDanio(100);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
}
