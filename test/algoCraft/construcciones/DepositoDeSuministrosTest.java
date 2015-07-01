package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepositoDeSuministrosTest {

	@Test
	public void elDepositoCuesta200UnidadesDeMineral() {
		
		assertEquals(100, DepositoDeSuministros.getCostoMineral());
	}
	
	@Test
	public void elDepositoCuesta100UnidadesDeGasVespeno() {
		
		assertEquals(0, DepositoDeSuministros.getCostoGas());
	}

}
