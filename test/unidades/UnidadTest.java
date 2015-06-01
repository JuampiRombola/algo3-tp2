package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class UnidadTest {

	@Test
	public void testLaUnidadAlCrearseNoEstaDestruida() {
		int vidaMaxima = 10;
		Unidad unidad = new Unidad(vidaMaxima);
		assertTrue(!unidad.estaDestruida());
	}
}
