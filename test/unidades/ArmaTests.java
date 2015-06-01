package unidades;

import static org.junit.Assert.*;
import interfacesParaUnidadesYEstructuras.Atacable;

import org.junit.Test;

public class ArmaTests {
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDeLaUnidadEstaEsDestruida(){
		int vidaMaxima = 10;
		Atacable atacable = new Unidad(vidaMaxima);
		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		arma.atacar(atacable, rango);
		assertTrue(atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMenorDanioQueLaVidaDelAtacableEstaNoEsDestruida(){
		int vidaMaxima = 10;
		Atacable atacable = new Unidad( vidaMaxima);
		int danio =  vidaMaxima - 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		arma.atacar(atacable, rango);
		assertTrue(!atacable.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMayorDanioQueLavidaMmaximaDeLaUnidadPeroNoEstoyEnRangoNoEsDestruida(){
		int vidaMaxima = 10;
		Atacable atacable = new Unidad(vidaMaxima);
		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		arma.atacar(atacable, rango + 1);
		assertTrue(!atacable.estaDestruido());
	}
}
