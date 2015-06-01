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
}
