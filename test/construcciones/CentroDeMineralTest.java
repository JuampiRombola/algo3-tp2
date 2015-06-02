package construcciones;

import static org.junit.Assert.*;
import juego.Jugador;

import org.junit.Test;

public class CentroDeMineralTest {

	@Test
	public void cuandoSeCreaUnCentroNoTieneMineralesRecolectados() {
		CentroDeMineral centro = new CentroDeMineral(null);
		assertEquals(0, centro.getRecursosRecolectados());
		}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadoTiene10Unidades() {
		Jugador jugador = new Jugador();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		centro.recolectar(10);
		assertEquals(10, centro.getRecursosRecolectados());
	}
	
	@Test
	public void siSeRecolecta10UnidadesDespuesDeHaberSidoCreadoElJugadorTiene10UnidadesMas() {
		Jugador jugador = new Jugador();
		int mineralesIniciales = jugador.getMinerales();
		CentroDeMineral centro = new CentroDeMineral(jugador);
		centro.recolectar(10);
		assertEquals(mineralesIniciales, centro.jugador.getMinerales() - 10);
	}

}
