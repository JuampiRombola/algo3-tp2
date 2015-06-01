package unidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnidadTest {

	@Test
	public void testLaUnidadAlCrearseNoEstaDestruida() {
		int vidaMaxima = 10;
		Unidad unidad = new Unidad(vidaMaxima);
		assert(unidad.estaDestruida());
	}

}
