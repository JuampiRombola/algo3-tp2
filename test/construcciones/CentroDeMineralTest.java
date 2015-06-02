package construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class CentroDeMineralTest {

	@Test
	public void cuandoSeCreaUnCentroNoTieneMineralesRecolectados() {
		CentroDeMineral centro = new CentroDeMineral();
		assertEquals(0, centro.getRecursosRecolectados());
		}

}
