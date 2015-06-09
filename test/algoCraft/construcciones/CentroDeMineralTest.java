package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.construcciones.CentroDeMineral;
import algoCraft.juego.ContadorDeTurnos;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;

public class CentroDeMineralTest {

	@Test
	public void cuandoSeCreaUnCentroNoTieneMineralesRecolectados() {
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		assertEquals(0, centro.getRecursosRecolectados());
		}
	
	@Test
	public void cuandoSeCreaUnCentroDeMineralEsteEstaEnTierra() {
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		assertEquals(true, centro.esTerrestre());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadoTiene10Unidades() {
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		centro.recolectar();
		assertEquals(10, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta2VecessDespuesDeHaberSidoCreadoTiene20Unidades() {
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		centro.recolectar();
		centro.recolectar();
		assertEquals(20, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siElCentroEsAtacadoPorUnGoliathSuVidaDisminuye() {
		ContadorDeTurnos.iniciarContador();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		int vidaInicial = centro.getVidaActual();
		Goliath goliath = new Goliath(2,2);
		goliath.atacar(centro);
		assertTrue(centro.getVidaActual() < vidaInicial);
	}
	
	@Test
	public void siElCentroEsAtacadoPorUnGoliathHastaSerDestruidoNoRecibeMasDanio() {
		ContadorDeTurnos.iniciarContador();
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		Goliath goliath = new Goliath(2,2);
		while (!centro.estaDestruido()) {
			goliath.atacar(centro);
			ContadorDeTurnos.getInstancia().avanzarTurno();
		}
		goliath.atacar(centro);
		assertEquals(0, centro.getVidaActual());
	}
}