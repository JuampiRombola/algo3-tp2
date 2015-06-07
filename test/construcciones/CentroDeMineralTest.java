package construcciones;

import static org.junit.Assert.*;
import construcciones.CentroDeMineral;

import org.junit.Test;

import recursos.Mineral;

public class CentroDeMineralTest {

	@Test
	public void cuandoSeCreaUnCentroNoTieneMineralesRecolectados() {
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		assertEquals(0, centro.getRecursosRecolectados());
		}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadoTiene10Unidades() {
		Mineral mineral = new Mineral(1, 1);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		centro.recolectar(10);
		assertEquals(10, centro.getRecursosRecolectados());
	}
}
