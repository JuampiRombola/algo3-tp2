package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Barraca;
import algoCraft.juego.ContadorDeTurnos;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

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
	
	@Test
	public void elMetodoGetNivelDevuelveQueLaBarracaEsUnEdificioDeNivel1(){
		Barraca barraca = new Barraca(1, 1);
		assertTrue(barraca.getNivel() == 1);
	}
}
