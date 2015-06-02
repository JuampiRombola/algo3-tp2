package mapa;

import interfaces.unidadesYEstructuras.Seleccionable;
import mapa.Casillero;
import unidades.Marine;

import org.junit.Assert;
import org.junit.Test;

public class testCasillero {

	@Test
	public void casilleroVacioCuandoSeCrea() {
		Casillero casillero = new Casillero();
		
		Assert.assertTrue(casillero.estaVacio());
	}

	@Test
	public void casilleroNoEstaVacioCuandoSeOcupa() {
		Casillero casillero = new Casillero();
		Seleccionable unidad = new Marine();
		
		casillero.ocupar(unidad);
		
		Assert.assertFalse(casillero.estaVacio());
	}
	
	@Test
	public void casilleroDevuelveLoQueSeLeDioParaQueLoOcupe() {
		Casillero casillero = new Casillero();
		Seleccionable unidad = new Marine();
		
		casillero.ocupar(unidad);
		
		Assert.assertEquals(unidad, casillero.obtener());
	}
	
	@Test
	public void casilleroEstaVacioCuandoSeLibera() {
		Casillero casillero = new Casillero();
		Seleccionable unidad = new Marine();
		
		casillero.ocupar(unidad);
		casillero.liberar();
		
		Assert.assertTrue(casillero.estaVacio());
	}
	
	@Test
	public void casilleroDevuelveSeleccionableCuandoSeLibera() {
		Casillero casillero = new Casillero();
		Seleccionable unidad = new Marine();
		
		casillero.ocupar(unidad);
		
		Assert.assertEquals(unidad, casillero.liberar());
	}
	
	@Test
	public void casilleroObtieneLasCoordenadasAsignadas() {
		Casillero casillero = new Casillero(1, 2);
		
		Posicion posicion = casillero.getPosicion();
		
		Assert.assertEquals(1, posicion.getX());
		Assert.assertEquals(2, posicion.getY());
	}
}
