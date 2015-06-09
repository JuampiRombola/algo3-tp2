package algoCraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.Atacable;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Arma;
import algoCraft.unidades.Unidad;

public class ArmaTest {
	private int vidaMaxima = 10;
	
	public Atacable crearAtacable(){
		//El arma y la posicion en una atacable no tiene relevancia, es solo para crear la unidad.
		Arma arma = new Arma(10,10);
		Posicion posicion = new Posicion(1,1, true);
		Atacable atacable = new Unidad(vidaMaxima, arma, posicion);
		return atacable;
	}
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDeLaUnidadEstaEsDestruida(){
		int vidaMaxima = 10;
		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = crearAtacable();
		arma.atacar(atacable, rango);
		assertTrue(atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMenorDanioQueLaVidaDelAtacableEstaNoEsDestruida(){
		int vidaMaxima = 10;
		int danio =  vidaMaxima - 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = crearAtacable();
		arma.atacar(atacable, rango);
		assertTrue(!atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMayorDanioQueLavidaMmaximaDeLaUnidadPeroNoEstoyEnRangoNoEsDestruida(){
		int vidaMaxima = 10;

		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = crearAtacable();
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