package mapa;

import interfaces.unidadesYEstructuras.Seleccionable;

import java.util.ArrayList;

public class Mapa {
	//Para el test de integracion, hay que probar el combate acercando al Marine
	//Se necesita un metodo que permita mover la unidad de un lado al otro
	private ArrayList<Casillero> areaEspacial;
	private ArrayList<Casillero> areaTerrestre;
	
	public Mapa(int alto, int ancho){
		this.areaEspacial = new ArrayList<Casillero>();
		this.areaTerrestre = new ArrayList<Casillero>();
		for (int i=0; i < alto; i++) {
			for (int j=0; j < ancho; j++) {
				areaEspacial.add(new Casillero(i, j));
				areaTerrestre.add(new Casillero(i, j));
			}
		}
		//this.generarMapa();
	}
	
	public Casillero getCasilleroTerrestre(Posicion posicion) throws CasilleroInexistenteException {
		for (Casillero casillero : this.areaTerrestre) {
			if (posicion.equals(casillero.getPosicion())) {
				return casillero;
			}
		}
		throw new CasilleroInexistenteException();
	}
	
	public void agregarUnidadTerrestre(Seleccionable unidad, Posicion posicion) throws CasilleroInexistenteException {
		Casillero casillero = this.getCasilleroTerrestre(posicion);
		casillero.ocupar(unidad);
	}
	

}