package unidades;

import static org.junit.Assert.*;
import interfacesParaUnidadesYEstructuras.Atacable;

import org.junit.Test;

public class UnidadTest{
	private int rangoArmaDePrueba = 10;
	private int danioArmaDePrueba = 100;
	private Arma armaDePrueba = new Arma(danioArmaDePrueba, rangoArmaDePrueba);
	private int vidaMaximaUnidadDePrueba = 10;
	private Unidad unidadAtacante = new Unidad(vidaMaximaUnidadDePrueba,
												     		armaDePrueba);
	@Test
	public void alCrearseLaUnidadNoEstaDestruida(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		assertTrue(!unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaEstaDestruido(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaEstaDestruido(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima + 1);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirEnDosAtaquesUnDanioIgualASuVidaMAximaEstaDestruido(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaLaVidaActualEs0(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaLaVidaActualEs0(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(vidaMaxima + 20);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructor(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructorLuegoDeRecibirDanio(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima, armaDePrueba);
		unidad.recibePuntosDeDanio(100);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDelAtacableEsteEsDestruido(){
		int vidaAtacable = danioArmaDePrueba;
		Atacable atacable = new Unidad(vidaAtacable, armaDePrueba);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaEnRangoConMenorDanioQueLaVidaDelAtacableEsteNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba + 1;
		Atacable atacable = new Unidad(vidaAtacable, armaDePrueba);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaConMayorDanioQueLaVidaDelAtacablePeroFueraDeRangoElAtacableNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba - 1;
		Atacable atacable = new Unidad(vidaAtacable, armaDePrueba);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba + 1);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void elTamanioDeUnaUnidadPorDefaultEs1(){
		int vidaAtacable = 100;
		Unidad unidad = new Unidad(vidaAtacable, armaDePrueba);
		assertTrue(unidad.getTamanio() == 1);
	}
}
