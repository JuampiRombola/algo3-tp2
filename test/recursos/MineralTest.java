package recursos;

import static org.junit.Assert.*;
import recursos.Mineral;

import org.junit.Test;


public class MineralTest {

	@Test
	public void cuandoSeCreaUnMineralTiene1500Unidades() {
		Mineral mineral = new Mineral(1, 1);
		assertEquals(1500, mineral.getUnidadesRestantes());
	}

	@Test
	public void cuandoSeExtra10UnidadesDeUnMineralTiene10UnidadesMenos() {
		Mineral mineral = new Mineral(1, 1);
		mineral.extraer(10);
		assertEquals(1490, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoSeExtra20UnidadesDeUnMineralTiene20UnidadesMenos() {
		Mineral mineral = new Mineral(1, 1);
		mineral.extraer(10);
		mineral.extraer(10);
		assertEquals(1480, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralSeQuedaSeQuedaSinUnidadesEstaDestruido() {
		Mineral mineral = new Mineral(1, 1);
		while (!mineral.estaDestruido()) {
			mineral.extraer(10);
		}
		assertEquals(0, mineral.getUnidadesRestantes());
	}
	
	@Test
	public void cuandoElMineralEstaDestruidoNoSePuedeSeguirSacandoUnidades() {
		Mineral mineral = new Mineral(1, 1);
		while (!mineral.estaDestruido()) {
			mineral.extraer(10);
		}
		mineral.extraer(10);
		assertEquals(0, mineral.getUnidadesRestantes());
	}
}
