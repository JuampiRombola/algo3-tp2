package algoCraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.mapa.Mapa;
import algoCraft.unidades.Marine;

public class MarineTest {
	
	public Marine nuevoMarine(){
		Mapa.reiniciarInstanciaParaTest();
		int posicionEnX  = 1;
		int posicionEnY = 1;
		return new Marine(posicionEnX,posicionEnY);
	}
	
	@Test
	public void unMarineTiene40PuntosDeVidaMaximaInicialmente(){
		Mapa.reiniciarInstanciaParaTest();
		Marine marine = nuevoMarine();
		assertTrue(marine.getVidaMaxima() == 40);
	}
	
	@Test
	public void unMarineTiene40PuntosDeVidaActualInicialmente(){
		Mapa.reiniciarInstanciaParaTest();
		Marine marine = nuevoMarine();
		assertTrue(marine.getVidaActual() == 40);
	}
	
	@Test
	public void unMarineTiene6DeDanioInicialmente(){
		Mapa.reiniciarInstanciaParaTest();
		Marine marine = nuevoMarine();
		assertTrue(marine.getDanio() == 6);
	}
	
	@Test
	public void unMarineTiene4DeRangoInicialmente(){
		Mapa.reiniciarInstanciaParaTest();
		Marine marine = nuevoMarine();
		assertTrue(marine.getRango() == 4);
	}
}
