package algoCraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.unidades.Vida;

public class VidaTest {

	
	@Test
	public void siLaVidaAlCrearseEsDe10GetVidaMaximaDevuelve10(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		assertTrue(vida.getPuntosDeVidaMaximos() == 10);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe20GetVidaMaximaDevuelve10(){
		int puntosDeVidaMaximos = 20;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		assertTrue(vida.getPuntosDeVidaMaximos() == 20);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10GetVidaDevuelve10(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		assertTrue(vida.getPuntosDeVida() == 10);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10GetVidaDevuelve20(){
		int puntosDeVidaMaximos = 20;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		assertTrue(vida.getPuntosDeVida() == 20);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10YRecibe5DeDanioEnUnGolpeGetVidaDevuelve5(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		vida.recibirDanio(5);
		
		assertTrue(vida.getPuntosDeVida() == 5);
	}
	@Test
	public void siLaVidaAlCrearseEsDe7YRecibe10DeDanioEnUnGolpeGetVidaDevuelve3(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		vida.recibirDanio(7);
		
		assertTrue(vida.getPuntosDeVida() == 3);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10YRecibe12DeDanioEnUnGolpeGetVidaDevuelve0(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		vida.recibirDanio(12);
		
		assertTrue(vida.getPuntosDeVida() == 0);
	}
	
	@Test
	public void siLaVidaAlCrearseEsSeteadaEn0Tiene0PuntosDeVidaActual(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		vida.setVidaActualEnCero();
		
		assertTrue(vida.getPuntosDeVida() == 0);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10YRecibe5DeDanioYLuegoSeMaximizaLaVidaEstaEsIgualALaVidaMaxima(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		
		vida.recibirDanio(5);
		vida.maximizarVida();
		
		assertTrue(vida.getPuntosDeVida() == vida.getPuntosDeVidaMaximos());
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10YRecibe5DeDanioYLuegoSeLeSuma4LaVidaTotalEsDe9(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		vida.recibirDanio(5);
		vida.sumarVida(4);
		assertTrue(vida.getPuntosDeVida() == 9);
	}
}
