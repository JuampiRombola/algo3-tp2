package algoCraft.construcciones;

import org.junit.Assert;
import org.junit.Test;

public class BaseTest {

	@Test
	public void cuandoSeCreaUnaBaseEstaEnTierra() {
		Base base = new Base(1, 1);
		
		Assert.assertEquals(true, base.esTerrestre());
	}
	
	@Test
	public void cuandoSeCreaUnaBaseTiene2500DeVida() {
		Base base = new Base(1, 1);
		
		Assert.assertTrue(2500 == base.getVidaActual());
	}
}
