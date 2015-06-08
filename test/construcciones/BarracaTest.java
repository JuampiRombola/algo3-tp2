package construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import construcciones.Barraca;
import unidades.Goliath;
import unidades.Unidad;

public class BarracaTest {

	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Barraca barraca = new Barraca(1, 1);
		assertEquals(true, barraca.esTerrestre());
	}

	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		Barraca barraca = new Barraca(1, 1);
		int vidaInicial = barraca.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(barraca);
		assertTrue(barraca.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		Barraca barraca = new Barraca(1, 1);
		Goliath goliath = new Goliath(2,2);
		while (!barraca.estaDestruido()) {
			goliath.atacar(barraca);
			goliath.avanzarTurno();
		}
		goliath.atacar(barraca);
		assertEquals(0, barraca.getVidaActual());
	}
	
	@Test
	public void cuandoLaBarracaCreaUnMarineEsteTieneTodaSuVida() {
		Barraca barraca = new Barraca(1, 1);
		Unidad marine = barraca.crearUnidad();
		assertEquals(marine.getVidaActual(), 40);
	}
}
