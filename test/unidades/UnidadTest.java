package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnidadTest {
	
	@Test
	public void alCrearseLaUnidadNoEstaDestruida(){
		int vidaMaxima = 200;
		Unidad unidad = new Unidad(vidaMaxima);
		assertTrue(!unidad.estaDestruido());
	}
}
