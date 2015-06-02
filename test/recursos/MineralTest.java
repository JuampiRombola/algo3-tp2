package recursos;

import static org.junit.Assert.*;

import org.junit.Test;

public class MineralTest {

	@Test
	public void cuandoSeCreaUnMineralTiene1500Unidades() {
		Mineral mineral = new Mineral();
		assertEquals(1500, mineral.getUnidadesRestantes());
	}

	@Test
	public void cuandoSeRecolectaUnMineralTiene10UnidadesMenos() {
		Mineral mineral = new Mineral();
		mineral.recolectar();
		assertEquals(1490, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoSeRecolectan2VecesTiene20UnidadesMenos() {
		Mineral mineral = new Mineral();
		mineral.recolectar();
		mineral.recolectar();
		assertEquals(1480, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralSeQuedaSeQuedaSinUnidadesEstaDestruido() {
		Mineral mineral = new Mineral();
		while (!mineral.estaDestruido()) {
			mineral.recolectar();
		}
		assertEquals(0, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralEstaDestruidoNoSePuedeSeguirSacandoUnidades() {
		Mineral mineral = new Mineral();
		while (!mineral.estaDestruido()) {
			mineral.recolectar();
		}
		mineral.recolectar();
		assertEquals(0, mineral.getUnidadesRestantes());
	}
}
