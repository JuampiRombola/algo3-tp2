package recursos;

import static org.junit.Assert.*;
import juego.Jugador;
import recursos.Mineral;

import org.junit.Test;

import construcciones.CentroDeMineral;

public class MineralTest {

	@Test
	public void cuandoSeCreaUnMineralTiene1500Unidades() {
		Mineral mineral = new Mineral();
		assertEquals(1500, mineral.getUnidadesRestantes());
	}

	@Test
	public void cuandoSeRecolectaUnMineralTiene10UnidadesMenos() {
		Jugador jugador = new Jugador();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		Mineral mineral = new Mineral();
		mineral.setRecolector(centro);
		mineral.recolectar();
		assertEquals(1490, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoSeRecolectan2VecesTiene20UnidadesMenos() {
		Jugador jugador = new Jugador();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		Mineral mineral = new Mineral();
		mineral.setRecolector(centro);
		mineral.recolectar();
		mineral.recolectar();
		assertEquals(1480, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralSeQuedaSeQuedaSinUnidadesEstaDestruido() {
		Jugador jugador = new Jugador();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		Mineral mineral = new Mineral();
		mineral.setRecolector(centro);
		while (!mineral.estaDestruido()) {
			mineral.recolectar();
		}
		assertEquals(0, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralEstaDestruidoNoSePuedeSeguirSacandoUnidades() {
		Jugador jugador = new Jugador();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		Mineral mineral = new Mineral();
		mineral.setRecolector(centro);
		while (!mineral.estaDestruido()) {
			mineral.recolectar();
		}
		mineral.recolectar();
		assertEquals(0, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoSeRecolectaUnMineralElCentroQueSeEncuentraSobreElTiene10UnidadesMas() {
		Jugador jugador = new Jugador();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		Mineral mineral = new Mineral();
		mineral.setRecolector(centro);
		int mineralesIniciales = centro.getRecursosRecolectados();
		mineral.recolectar();
		assertEquals(mineralesIniciales, centro.getRecursosRecolectados() - 10);
	}
	
	@Test
	public void cuandoSeCreaUnMineralYNoTieneNingunCentroAsociadoNoSePuedeRecolectar() {
		Mineral mineral = new Mineral();
		mineral.recolectar();
		assertEquals(1500, mineral.getUnidadesRestantes());
	}
}
