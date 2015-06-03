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
	
	public Casillero getCasilleroTerrestre(Posicion posicion) {
		for (Casillero casillero : this.areaTerrestre) {
			if (posicion.equals(casillero.getPosicion())) {
				return casillero;
			}
		}
		return null;
	}
	
	public boolean agregarUnidadTerrestre(Seleccionable unidad, int x, int y) {
		Posicion posicion = new Posicion(x, y);
		Casillero casillero = this.getCasilleroTerrestre(posicion);
		if (casillero != null) {
			casillero.ocupar(unidad);
			return true;
		}
		return false;
	}
}