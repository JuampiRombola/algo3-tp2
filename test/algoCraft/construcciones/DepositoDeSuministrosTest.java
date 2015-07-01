package algoCraft.construcciones;

import static org.junit.Assert.*;

import org.junit.Test;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;

public class DepositoDeSuministrosTest {

	@Test
	public void elDepositoCuesta200UnidadesDeMineral() {
		
		assertEquals(100, DepositoDeSuministros.getCostoMineral());
	}
	
	@Test
	public void elDepositoCuesta100UnidadesDeGasVespeno() {
		
		assertEquals(0, DepositoDeSuministros.getCostoGas());
	}
	
	@Test
	public void cuandoSeConstruyeUnDepositoEnUnaPosicionOcupadaNoSeLeCobraAlJugador() {
		Mapa.reiniciarInstanciaParaTest();
		Jugador jugador = new Jugador("Jugador", new Base(3, 3));
		jugador.sumarUnidadesDeGasVespeno(1000);
		jugador.sumarUnidadesDeMineral(1000);
		jugador.activar();
		DepositoDeSuministros deposito = new DepositoDeSuministros(jugador, 1, 1);
		Mapa.getMapa().agregarPosicionable(deposito);
		int mineralesAntesDelDeposito = jugador.getMineral();
		
		new DepositoDeSuministros(jugador, 1, 1);
		
		assertTrue(jugador.getMineral() == mineralesAntesDelDeposito);
	}

}
