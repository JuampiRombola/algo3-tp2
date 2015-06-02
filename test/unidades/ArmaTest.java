package unidades;

import static org.junit.Assert.*;
import interfaces.unidadesYEstructuras.Atacable;

import org.junit.Test;

public class ArmaTest {
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDeLaUnidadEstaEsDestruida(){
		int vidaMaxima = 10;

		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = new UnidadTerran(vidaMaxima, arma);
		arma.atacar(atacable, rango);
		assertTrue(atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMenorDanioQueLaVidaDelAtacableEstaNoEsDestruida(){
		int vidaMaxima = 10;
		int danio =  vidaMaxima - 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = new UnidadTerran(vidaMaxima, arma);
		arma.atacar(atacable, rango);
		assertTrue(!atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMayorDanioQueLavidaMmaximaDeLaUnidadPeroNoEstoyEnRangoNoEsDestruida(){
		int vidaMaxima = 10;

		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = new UnidadTerran(vidaMaxima, arma);
		arma.atacar(atacable, rango + 1);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void unArmaConDanio1DevuelveConGetDanio1(){
		int danio = 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getDanio() == 1);
	}
	
	@Test
	public void unArmaConDanio2DevuelveConGetDanio2(){
		int danio = 2;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getDanio() == 2);
	}
	
	@Test
	public void unArmaConRango2DevuelveConGetRango2(){
		int danio = 10;
		int rango = 2;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getRango() == 2);
	}
	
	@Test
	public void unArmaConRango1DevuelveConGetRango1(){
		int danio = 10;
		int rango = 1;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getRango() == 1);
	}
}
