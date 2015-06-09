package construcciones;

import static org.junit.Assert.*;
import juego.ContadorDeTurnos;

import org.junit.Test;

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
		ContadorDeTurnos.iniciarContador();
		Barraca barraca = new Barraca(1, 1);
		int vidaInicial = barraca.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(barraca);
		assertTrue(barraca.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaBarracaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		ContadorDeTurnos.iniciarContador();
		Barraca barraca = new Barraca(1, 1);
		Goliath goliath = new Goliath(2,2);
		while (!barraca.estaDestruido()) {
			goliath.atacar(barraca);
			ContadorDeTurnos.getInstancia().avanzarTurno();
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
