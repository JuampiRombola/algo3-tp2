package mapa;

import interfaces.unidadesYEstructuras.Seleccionable;

import java.util.Collection;

public class Mapa {

	private Collection<Casillero> areaEspacial;
	private Collection<Casillero> areaTerrestre;
	
	public Mapa(int alto, int ancho){
		for (int i=0; i < alto; i++) {
			for (int j=0; j < ancho; j++) {
				Casillero casilleroEspacial = new Casillero(i, j);
				Casillero casilleroTerrestre = new Casillero(i, j);
				areaEspacial.add(casilleroEspacial);
				areaTerrestre.add(casilleroTerrestre);
			}
		}
		//this.generarMapa();
	}
	
	public Casillero getCasilleroTerrestre(Posicion posicion) throws CasilleroInexistenteException {
		Casillero casilleroBuscado = null;
		for (Casillero casilleroSeleccionado : this.areaTerrestre) {
			if (posicion.equals(casilleroSeleccionado.getPosicion())) {
				casilleroBuscado = casilleroSeleccionado;
			}
		}
		if (casilleroBuscado == null)
			throw new CasilleroInexistenteException();
		return casilleroBuscado;
	}
	
	public void agregarUnidadTerrestre(Seleccionable unidad, int x, int y) throws CasilleroInexistenteException {
		Posicion posicion = new Posicion(x, y);
		Casillero casillero = this.getCasilleroTerrestre(posicion);
		casillero.ocupar(unidad);
	}
}