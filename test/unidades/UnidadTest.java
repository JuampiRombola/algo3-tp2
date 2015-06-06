package unidades;

import static org.junit.Assert.*;
import mapa.Posicion;

import org.junit.Test;

public class UnidadTest{
	private int rangoArmaDePrueba = 10;
	private int danioArmaDePrueba = 100;
	private Arma armaDePrueba = new Arma(danioArmaDePrueba, rangoArmaDePrueba);
	private int vidaMaxima = 10;
	private Posicion posicion = new Posicion(1,1, true);
	private Posicion posicionEnRango = new Posicion (2,1, true);
	//La posicion de la unidad que vaya a atacar es 1 1. 100, 100 esta fuera de rango.
	private Posicion posicionFueraDeRango = new Posicion(100,100, true);
	private Unidad unidadAtacante = new Unidad(vidaMaxima, armaDePrueba, posicion);
	
	public Unidad nuevaUnidad(){
		return new Unidad(vidaMaxima, armaDePrueba, posicion);
	}
	
	public Unidad nuevaUnidadEnRangoDeAtaque(int vida){
		return  new Unidad(vida, armaDePrueba, posicionEnRango);
	}
	
	public Unidad nuevaUnidadFueraDeRangoDeAtaque(int vida){
		return new Unidad(vidaMaxima, armaDePrueba, posicionFueraDeRango);
	}
	@Test
	public void alCrearseLaUnidadNoEstaDestruida(){
		Unidad unidad = nuevaUnidad();
		assertTrue(!unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaEstaDestruido(){
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaEstaDestruido(){
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima + 1);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirEnDosAtaquesUnDanioIgualASuVidaMAximaEstaDestruido(){
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		unidad.recibePuntosDeDanio(vidaMaxima/2);
		assertTrue(unidad.estaDestruido());
	}
	
	@Test 
	public void alRecibirUnDanioIgualALaVidaMaximaLaVidaActualEs0(){
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void alRecibirUnDanioMayorALaVidaMaximaLaVidaActualEs0(){
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(vidaMaxima + 20);
		assertTrue(unidad.getVidaActual() == 0);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructor(){
		Unidad unidad = nuevaUnidad();
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	
	@Test 
	public void laVidaMaximaCoincideConLaDelConstructorLuegoDeRecibirDanio(){
		Unidad unidad = nuevaUnidad();
		unidad.recibePuntosDeDanio(100);
		assertTrue(unidad.getVidaMaxima() == vidaMaxima);
	}
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDelAtacableEsteEsDestruido(){
		int vidaAtacable = danioArmaDePrueba;
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaEnRangoConMenorDanioQueLaVidaDelAtacableEsteNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba + 1;
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaConMayorDanioQueLaVidaDelAtacablePeroFueraDeRangoElAtacableNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba - 1;
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba + 1);
		assertTrue(!atacable.estaDestruido());
	}
	
	
	@Test
	public void siTengoUnArmaConDanio1DevuelveConGetDanio1(){
		int danio = 1;
		int rango = 10;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(vida, arma, posicion);
		assertTrue(unidad.getDanio() == 1);
	}
	
	public Unidad crearUnidadDestruida(){
		Unidad unidad = nuevaUnidad();
		armaDePrueba.atacar(unidad, rangoArmaDePrueba);
		return unidad;
	}
	
	@Test
	public void siDisparoUnArmaYEstoyDestruidoNoPuedoDestruirAUnAtacable(){
		int vidaAtacable = danioArmaDePrueba;
		Unidad unidadDestruida = crearUnidadDestruida();
		Unidad atacable = nuevaUnidadEnRangoDeAtaque(vidaAtacable);
		unidadDestruida.atacar(atacable, rangoArmaDePrueba);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void siTengoUnArmaConDanio2DevuelveConGetDanio2(){
		int danio = 2;
		int rango = 10;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(vida, arma, posicion);
		assertTrue(unidad.getDanio() == 2);
	}
	
	@Test
	public void  siTengoUnArmaConRango2DevuelveConGetRango2(){
		int danio = 10;
		int rango = 2;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(vida, arma, posicion);
		assertTrue(unidad.getRango() == 2);
	}
	
	@Test
	public void  siTengoUnArmaConRango1DevuelveConGetRango1(){
		int danio = 10;
		int rango = 1;
		int vida = 10;
		Arma arma = new Arma(danio, rango);
		Unidad unidad = new Unidad(vida, arma, posicion);
		assertTrue(unidad.getRango() == 1);
	}
	
}
