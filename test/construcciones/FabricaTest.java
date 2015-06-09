package construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import construcciones.Fabrica;
import unidades.Goliath;
import unidades.Unidad;

public class FabricaTest {

	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Fabrica fabrica = new Fabrica(1, 1);
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Fabrica fabrica = new Fabrica(1, 1);
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(fabrica);
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Fabrica fabrica = new Fabrica(1, 1);
		Goliath goliath = new Goliath(2,2);
		while (!fabrica.estaDestruido()) {
			goliath.atacar(fabrica);
			goliath.avanzarTurno();
		}
		goliath.atacar(fabrica);
		assertEquals(0, fabrica.getVidaActual());
	}
	
	@Test
	public void cuandoLaFabricaCreaUnGoliathEsteTieneTodaSuVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		Unidad goliath = fabrica.crearUnidad();
		assertEquals(goliath.getVidaActual(), 125);
	}
}