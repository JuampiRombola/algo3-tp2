package mapa;

import interfaces.unidadesYEstructuras.Seleccionable;
import mapa.Casillero;
import unidades.UnidadTerran;

import org.junit.Assert;
import org.junit.Test;

public class testCasillero {

	@Test
	public void casilleroTerrestreVacioCuandoSeCrea() {
		Casillero casillero = new Casillero();
		Assert.assertTrue(casillero.estaVacioTierra());
	}

	@Test
	public void casilleroTerrestreNoEstaVacioCuandoSeOcupa() {
		Casillero casillero = new Casillero();
		Seleccionable unidad = new UnidadTerran(1, null);
		casillero.ocuparTierra(unidad);
		Assert.assertFalse(casillero.estaVacioTierra());
	}
	
	@Test
	public void casilleroTerrestreDevuelveLoQueSeLeDioParaQueLoOcupe() {
		Casillero casillero = new Casillero();
		Seleccionable unidad = new UnidadTerran(1, null);
		casillero.ocuparTierra(unidad);
		Assert.assertEquals(unidad, casillero.obtenerTierra());
	}
}
