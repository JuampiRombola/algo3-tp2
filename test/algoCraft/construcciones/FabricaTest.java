package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.Fabrica;
import algoCraft.juego.ContadorDeTurnos;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class FabricaTest {

	@Test
	public void cuandoSeCreaUnaFabricaEstaEstaEnTierra() {
		Fabrica fabrica = new Fabrica(1, 1);
		assertEquals(true, fabrica.esTerrestre());
	}

	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathSuVidaDisminuye() {
		ContadorDeTurnos.iniciarContador();
		Fabrica fabrica = new Fabrica(1, 1);
		int vidaInicial = fabrica.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(fabrica);
		assertTrue(fabrica.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siLaFabricaEsAtacadaPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		ContadorDeTurnos.iniciarContador();
		Fabrica fabrica = new Fabrica(1, 1);
		Goliath goliath = new Goliath(2,2);
		while (!fabrica.estaDestruido()) {
			goliath.atacar(fabrica);
			ContadorDeTurnos.getInstancia().avanzarTurno();
		}
		goliath.atacar(fabrica);
		assertEquals(0, fabrica.getVidaActual());
	}
	
	@Test
	public void cuandoLaFabricaCreaUnGoliathEsteTieneTodaSuVida() {
		Fabrica fabrica = new Fabrica(1, 1);
		Unidad goliath = null;
		try {
			goliath = fabrica.crearUnidad();
		} catch (EdificioNoHabilitadoException e) {
			
		}
		assertEquals(goliath.getVidaActual(), 125);
	}
	
	@Test
	public void elMetodoGetNivelDevuelveQueLaFabricaEsUnEdificioDeNivel2(){
		Fabrica fabrica = new Fabrica(1, 1);
		assertTrue(fabrica.getNivel() == 2);
	}
	
	@Test
	public void alCrearseLaFabricaEstaHabilitada(){
		Fabrica barraca = new Fabrica(1, 1);
		assertTrue(barraca.estaHabilitado());
	}
	
	@Test
	public void alDeshabiltarseLaFabricaEstaDeshabilitada(){
		Fabrica fabrica = new Fabrica(1, 1);
		fabrica.deshabilitarProduccion();
		assertFalse(fabrica.estaHabilitado());
	}
	
	@Test
	public void alDeshabiltarseYHabilitarseLaFabricaEstaHabilitada(){
		Fabrica fabrica = new Fabrica(1, 1);
		fabrica.deshabilitarProduccion();
		fabrica.habilitarProduccion();
		assertTrue(fabrica.estaHabilitado());
	}
}
