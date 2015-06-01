package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArmaTests {
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDeLaUnidadEstaEsDestruida(){
		int vidaMaxima = 10;
		Unidad unidad = new Unidad(vidaMaxima);
		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		arma.atacar(unidad, rango);
		assertTrue(unidad.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMenorDanioQueLaVidaDeLaUnidadEstaNoEsDestruida(){
		int vidaMaxima = 10;
		Unidad unidad = new Unidad( vidaMaxima);
		int danio =  vidaMaxima - 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		arma.atacar(unidad, rango);
		assertTrue(!unidad.estaDestruido());
	}
	@Test
	public void siDisparoUnArmaConMayorDanioQueLavidaMmaximaDeLaUnidadPeroNoEstoyEnRangoNoEsDestruida(){
		int vidaMaxima = 10;
		Unidad unidad = new Unidad(vidaMaxima);
		int danio =  vidaMaxima + 1;
		int rango = 10;
		Arma arma = new Arma(danio, rango);
		arma.atacar(unidad, rango + 1);
		assertTrue(!unidad.estaDestruido());
	}
}
