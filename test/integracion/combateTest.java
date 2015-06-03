package integracion;

import interfaces.unidadesYEstructuras.Seleccionable;
import mapa.CasilleroInexistenteException;
import mapa.Mapa;
import mapa.Posicion;

import org.junit.Assert;
import org.junit.Test;

import unidades.Goliath;
import unidades.Marine;

public class combateTest {

	@Test
	public void cuandoElGoliathEstaFueraDelRangoDeAtaqueDelMarineNoLoPuedeAtacar() {
		Mapa mapa = new Mapa(100, 100);
		Seleccionable marine = new Marine();
		Seleccionable goliath = new Goliath();
		int vidaInicialGoliath = goliath.getVidaActual();
		Posicion posicionMarine = new Posicion(1,1);
		Posicion posicionGoliath = new Posicion(22, 22);
		double distancia = posicionMarine.calcularDistancia(posicionGoliath);
		
		try {
			mapa.agregarUnidadTerrestre(marine, posicionMarine);
			mapa.agregarUnidadTerrestre(goliath, posicionGoliath);
			marine.atacar(goliath, distancia);
			Assert.assertTrue(vidaInicialGoliath == goliath.getVidaActual());
			
		} catch (CasilleroInexistenteException e) {}
	}
	
	@Test
	public void cuandoElGoliathEstaDentroDelRangoDeAtaqueDelMarineLeSaca6DeVida() {
		Mapa mapa = new Mapa(10, 10);
		Seleccionable marine = new Marine();
		Seleccionable goliath = new Goliath();
		int vidaInicialGoliath = goliath.getVidaActual();
		Posicion posicionMarine = new Posicion(1,1);
		Posicion posicionGoliath = new Posicion(3, 3);
		double distancia = posicionMarine.calcularDistancia(posicionGoliath);
		
		try {
			mapa.agregarUnidadTerrestre(marine, posicionMarine);
			mapa.agregarUnidadTerrestre(goliath, posicionGoliath);
			marine.atacar(goliath, distancia);
			Assert.assertTrue(vidaInicialGoliath == (goliath.getVidaActual() + 6));
			
		} catch (CasilleroInexistenteException e) {}
	}
}
