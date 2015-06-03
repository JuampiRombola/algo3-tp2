package mapa;

import static org.junit.Assert.*;
import interfaces.unidadesYEstructuras.Seleccionable;

import org.junit.Assert;
import org.junit.Test;

import unidades.Marine;

public class MapaTest {

	@Test
	public void cuandoSeOcupaUnCasilleroSeObtieneLoQueSeLeIntrodujo() {
		Mapa mapa = new Mapa(10, 10);
		Seleccionable unidad = new Marine();
		Posicion posicion = new Posicion(1, 2);
		
		try{
			mapa.agregarUnidadTerrestre(unidad, posicion);	
			Casillero casillero = mapa.getCasilleroTerrestre(posicion);
			Assert.assertEquals(unidad, casillero.obtener());
		} catch (CasilleroInexistenteException e) {}
	}
	
	@Test
	public void cuandoSeIntentaOcuparUnCasilleroInexistenteSeLanzaUnError() {
		Mapa mapa = new Mapa(10, 10);
		Posicion posicion = new Posicion(11, 11);
		
		try{
			mapa.getCasilleroTerrestre(posicion);
			fail();
		} catch (CasilleroInexistenteException e) {}
	}

}
