package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class VidaTest {

	
	@Test
	public void siLaVidaAlCrearseEsDe10GetVidaMaximaDevuelve10(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		assertTrue(vida.getVidaMaxima() == 10);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe20GetVidaMaximaDevuelve10(){
		int puntosDeVidaMaximos = 20;
		Vida vida = new Vida(puntosDeVidaMaximos);
		assertTrue(vida.getVidaMaxima() == 20);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10GetVidaDevuelve10(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		assertTrue(vida.getVida() == 10);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10GetVidaDevuelve20(){
		int puntosDeVidaMaximos = 20;
		Vida vida = new Vida(puntosDeVidaMaximos);
		assertTrue(vida.getVida() == 20);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10YRecibe5DeDanioEnUnGolpeGetVidaDevuelve5(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		vida.recibirDanio(5);
		assertTrue(vida.getVida() == 5);
	}
	@Test
	public void siLaVidaAlCrearseEsDe7YRecibe10DeDanioEnUnGolpeGetVidaDevuelve3(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		vida.recibirDanio(7);
		assertTrue(vida.getVida() == 3);
	}
	
	@Test
	public void siLaVidaAlCrearseEsDe10YRecibe12DeDanioEnUnGolpeGetVidaDevuelve0(){
		int puntosDeVidaMaximos = 10;
		Vida vida = new Vida(puntosDeVidaMaximos);
		vida.recibirDanio(12);
		assertTrue(vida.getVida() == 0);
	}
}
