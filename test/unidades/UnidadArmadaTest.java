package unidades;

import static org.junit.Assert.assertTrue;
import interfacesParaUnidadesYEstructuras.Atacable;

import org.junit.Test;

public class UnidadArmadaTest {

	private int rangoArmaDePrueba = 10;
	private int danioArmaDePrueba = 100;
	private Arma armaDePrueba = new Arma(danioArmaDePrueba, rangoArmaDePrueba);
	private int vidaMaximaUnidadDePrueba = 10;
	private UnidadArmada unidadAtacante = new UnidadArmada(vidaMaximaUnidadDePrueba,
												     		armaDePrueba);
	
	@Test
	public void siDisparoUnArmaEnRangoConIgualDanioQueLaVidaDelAtacableEsteEsDestruido(){
		int vidaAtacable = danioArmaDePrueba;
		Atacable atacable = new Unidad(vidaAtacable);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaEnRangoConMenorDanioQueLaVidaDelAtacableEsteNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba + 1;
		Atacable atacable = new Unidad(vidaAtacable);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba);
		assertTrue(!atacable.estaDestruido());
	}
	
	@Test
	public void siDisparoUnArmaConMayorDanioQueLaVidaDelAtacablePeroFueraDeRangoElAtacableNoEsDestruido(){
		int vidaAtacable = danioArmaDePrueba - 1;
		Atacable atacable = new Unidad(vidaAtacable);
		unidadAtacante.atacar(atacable, rangoArmaDePrueba + 1);
		assertTrue(!atacable.estaDestruido());
	}
}
