package algoCraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.Atacable;
import algoCraft.construcciones.Base;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Arma;
import algoCraft.unidades.Unidad;
import algoCraft.unidades.excepciones.AtaqueFueraDeRangoException;

public class ArmaTest {
	private int vidaMaxima = 10;
	
	public Atacable crearAtacable(){
		//El arma y la posicion en una atacable no tiene relevancia, es solo para crear la unidad.
		Mapa.reiniciarInstanciaParaTest();
		Arma arma = new Arma(10,10);
		Posicion posicion = new Posicion(1,1, true);
		Atacable atacable = new Unidad(new Jugador("jugador1", new Base(3, 3)), vidaMaxima, arma, posicion, 1);
		return atacable;
	}
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDeLaUnidadEstaEsDestruida(){
		Mapa.reiniciarInstanciaParaTest();
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
		Mapa.reiniciarInstanciaParaTest();
		int vidaMaxima = 10;
		int danio =  vidaMaxima - 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = crearAtacable();
		arma.atacar(atacable, rango);
		assertTrue(!atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMayorDanioQueLaVidaMmaximaDeLaUnidadPeroNoEstoyEnRangoNoEsDestruida(){
		Mapa.reiniciarInstanciaParaTest();
		int vidaMaxima = 10;

		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		Atacable atacable = crearAtacable();
		try {
			arma.atacar(atacable, rango + 1);
			
			fail();
		} catch (AtaqueFueraDeRangoException e) {}
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void unArmaConDanio1DevuelveConGetDanio1(){
		Mapa.reiniciarInstanciaParaTest();
		int danio = 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getDanio() == 1);
	}
	
	@Test
	public void unArmaConDanio2DevuelveConGetDanio2(){
		Mapa.reiniciarInstanciaParaTest();
		int danio = 2;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getDanio() == 2);
	}
	
	@Test
	public void unArmaConRango2DevuelveConGetRango2(){
		Mapa.reiniciarInstanciaParaTest();
		int danio = 10;
		int rango = 2;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getRango() == 2);
	}
	
	@Test
	public void unArmaConRango1DevuelveConGetRango1(){
		Mapa.reiniciarInstanciaParaTest();
		int danio = 10;
		int rango = 1;
		Arma arma = new Arma(danio, rango);
		assertTrue(arma.getRango() == 1);
	}
}
